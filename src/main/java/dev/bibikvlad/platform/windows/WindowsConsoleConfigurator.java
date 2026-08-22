package dev.bibikvlad.platform.windows;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class WindowsConsoleConfigurator {

    private static final int UTF8_CODE_PAGE = 65001;

    private static final int STD_OUTPUT_HANDLE = -11;

    private static final int ENABLE_PROCESSED_OUTPUT = 0x0001;
    private static final int ENABLE_VIRTUAL_TERMINAL_PROCESSING = 0x0004;

    private WindowsConsoleConfigurator() {
        throw new AssertionError(
                "The class WindowsConsoleConfigurator cannot be instantiated."
        );
    }

    public static void configure() {
        if (!System.getProperty("os.name").toLowerCase().startsWith("windows")) {
            return;
        }

        try (Arena arena = Arena.ofConfined()) {
            Linker linker = Linker.nativeLinker();
            SymbolLookup kernel32 =
                    SymbolLookup.libraryLookup("Kernel32", arena);

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

            setCodePage(setConsoleCP, "SetConsoleCP");
            setCodePage(setConsoleOutputCP, "SetConsoleOutputCP");

            MemorySegment outputHandle = (MemorySegment) getStdHandle.invokeExact(STD_OUTPUT_HANDLE);

            if (outputHandle.equals(MemorySegment.NULL)) {
                throw new IllegalStateException("GetStdHandle(STD_OUTPUT_HANDLE) failed");
            }

            try (Arena modeArena = Arena.ofConfined()) {
                MemorySegment mode = modeArena.allocate(ValueLayout.JAVA_INT);

                int result = (int) getConsoleMode.invokeExact(outputHandle, mode);

                if (result == 0) {
                    throw new IllegalStateException("GetConsoleMode failed");
                }

                int originalMode = mode.get(ValueLayout.JAVA_INT, 0);

                int newMode = originalMode | ENABLE_PROCESSED_OUTPUT | ENABLE_VIRTUAL_TERMINAL_PROCESSING;

                result = (int) setConsoleMode.invokeExact(outputHandle, newMode);

                if (result == 0) {
                    throw new IllegalStateException("SetConsoleMode failed");
                }
            }

        } catch (Throwable throwable) {
            throw new IllegalStateException("Failed to configure Windows console", throwable);
        }
    }

    private static void setCodePage(MethodHandle methodHandle, String methodName) throws Throwable {
        int result = (int) methodHandle.invokeExact(UTF8_CODE_PAGE);

        if (result == 0) {
            throw new IllegalStateException(methodName + "(65001) failed");
        }
    }
}