package dev.bibikvlad.platform.windows;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class WindowsConsoleConfigurator {

    private static final int UTF8_CODE_PAGE = 65001;

    private static final int STD_OUTPUT_HANDLE = -11;

    private static final int ENABLE_VIRTUAL_TERMINAL_PROCESSING = 0x0004;

    private static final int LF_FACESIZE = 32;

    /*
     * CONSOLE_FONT_INFOEX:
     *
     * DWORD cbSize;             //  0
     * DWORD nFont;              //  4
     * COORD dwFontSize;         //  8
     * UINT FontFamily;          // 12
     * UINT FontWeight;          // 16
     * WCHAR FaceName[32];       // 20
     *
     * Total: 84 bytes
     */
    private static final int FONT_INFO_SIZE = 84;

    private static final int FONT_FACE_NAME_OFFSET = 20;

    private static final String[] PREFERRED_FONTS = {
            "Cascadia Mono",
            "Cascadia Code",
            "Consolas"
    };

    private WindowsConsoleConfigurator() {
        throw new AssertionError("The class WindowsConsoleConfigurator cannot be instantiated.");
    }

    public static boolean configureIfAvailable() {
        if (!isWindows()) {
            return false;
        }

        /*
         * Windows Terminal owns its font configuration.
         *
         * Do not attempt to change it from the application.
         */
        if (isWindowsTerminal()) {
            return false;
        }

        try (Arena arena = Arena.ofConfined()) {
            Linker linker = Linker.nativeLinker();

            SymbolLookup kernel32 = SymbolLookup.libraryLookup("Kernel32", arena);

            MethodHandle getStdHandle = linker.downcallHandle(
                    kernel32.find("GetStdHandle").orElseThrow(),
                    FunctionDescriptor.of(
                            ValueLayout.ADDRESS,
                            ValueLayout.JAVA_INT
                    )
            );

            MethodHandle getConsoleMode = linker.downcallHandle(
                    kernel32.find("GetConsoleMode").orElseThrow(),
                    FunctionDescriptor.of(
                            ValueLayout.JAVA_INT,
                            ValueLayout.ADDRESS,
                            ValueLayout.ADDRESS
                    )
            );

            MethodHandle setConsoleMode = linker.downcallHandle(
                    kernel32.find("SetConsoleMode").orElseThrow(),
                    FunctionDescriptor.of(
                            ValueLayout.JAVA_INT,
                            ValueLayout.ADDRESS,
                            ValueLayout.JAVA_INT
                    )
            );

            MemorySegment outputHandle =
                    (MemorySegment) getStdHandle.invokeExact(
                            STD_OUTPUT_HANDLE
                    );

            if (outputHandle.equals(MemorySegment.NULL)) {
                throw new IllegalStateException("GetStdHandle(STD_OUTPUT_HANDLE) failed");
            }

            try (Arena modeArena = Arena.ofConfined()) {
                MemorySegment mode = modeArena.allocate(ValueLayout.JAVA_INT);

                int result = (int) getConsoleMode.invokeExact(outputHandle, mode);

                if (result == 0) {
                    return false;
                }

                int originalMode = mode.get(ValueLayout.JAVA_INT, 0);

                configureCodePages(linker, kernel32);
                configureVirtualTerminalProcessing(
                        setConsoleMode,
                        outputHandle,
                        originalMode
                );
                configureFont(linker, kernel32, outputHandle);
            }

        } catch (Throwable throwable) {
            throw new IllegalStateException("Failed to configure Windows console", throwable);
        }

        return true;
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().startsWith("windows");
    }

    private static boolean isWindowsTerminal() {
        return System.getenv("WT_SESSION") != null;
    }

    private static void configureCodePages(Linker linker, SymbolLookup kernel32) throws Throwable {
        MethodHandle setConsoleCP = linker.downcallHandle(
                kernel32.find("SetConsoleCP").orElseThrow(),
                FunctionDescriptor.of(
                        ValueLayout.JAVA_INT,
                        ValueLayout.JAVA_INT
                )
        );

        MethodHandle setConsoleOutputCP = linker.downcallHandle(
                kernel32.find("SetConsoleOutputCP").orElseThrow(),
                FunctionDescriptor.of(
                        ValueLayout.JAVA_INT,
                        ValueLayout.JAVA_INT
                )
        );

        setCodePage(setConsoleCP, "SetConsoleCP");
        setCodePage(setConsoleOutputCP, "SetConsoleOutputCP");
    }

    private static void configureVirtualTerminalProcessing(MethodHandle setConsoleMode, MemorySegment outputHandle,
                                                           int originalMode) throws Throwable {
        int newMode = originalMode | ENABLE_VIRTUAL_TERMINAL_PROCESSING;

        int result = (int) setConsoleMode.invokeExact(outputHandle, newMode);

        if (result == 0) {
            throw new IllegalStateException("SetConsoleMode failed");
        }
    }

    private static void configureFont(Linker linker, SymbolLookup kernel32,
                                      MemorySegment outputHandle) throws Throwable {
        MethodHandle getCurrentConsoleFontEx = linker.downcallHandle(
                kernel32.find("GetCurrentConsoleFontEx")
                        .orElseThrow(),
                FunctionDescriptor.of(
                        ValueLayout.JAVA_INT,
                        ValueLayout.ADDRESS,
                        ValueLayout.JAVA_INT,
                        ValueLayout.ADDRESS
                )
        );

        MethodHandle setCurrentConsoleFontEx = linker.downcallHandle(
                kernel32.find("SetCurrentConsoleFontEx")
                        .orElseThrow(),
                FunctionDescriptor.of(
                        ValueLayout.JAVA_INT,
                        ValueLayout.ADDRESS,
                        ValueLayout.JAVA_INT,
                        ValueLayout.ADDRESS
                )
        );

        try (Arena arena = Arena.ofConfined()) {
            MemorySegment fontInfo = arena.allocate(FONT_INFO_SIZE);

            /*
             * cbSize must contain sizeof(CONSOLE_FONT_INFOEX).
             */
            fontInfo.set(ValueLayout.JAVA_INT, 0, FONT_INFO_SIZE);

            int result = (int) getCurrentConsoleFontEx.invokeExact(outputHandle, 0, fontInfo);

            if (result == 0) {
                return;
            }

            for (String fontName : PREFERRED_FONTS) {
                if (trySetFont(setCurrentConsoleFontEx, outputHandle, fontInfo, fontName)) {
                    return;
                }
            }
        }
    }

    private static boolean trySetFont(MethodHandle setCurrentConsoleFontEx, MemorySegment outputHandle,
                                      MemorySegment fontInfo, String fontName) throws Throwable {
        writeFaceName(fontInfo, fontName);

        int result = (int) setCurrentConsoleFontEx.invokeExact(outputHandle, 0, fontInfo);

        return result != 0;
    }

    private static void writeFaceName(MemorySegment fontInfo, String fontName) {
        MemorySegment faceName = fontInfo.asSlice(FONT_FACE_NAME_OFFSET, LF_FACESIZE * 2L);

        for (int i = 0; i < LF_FACESIZE; i++) {
            faceName.set(ValueLayout.JAVA_CHAR, i * 2L, i < fontName.length() ? fontName.charAt(i) : '\0');
        }
    }

    private static void setCodePage(MethodHandle methodHandle, String methodName) throws Throwable {
        int result = (int) methodHandle.invokeExact(UTF8_CODE_PAGE);

        if (result == 0) {
            throw new IllegalStateException(methodName + "(65001) failed");
        }
    }
}