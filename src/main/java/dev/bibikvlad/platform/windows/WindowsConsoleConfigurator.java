package dev.bibikvlad.platform.windows;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class WindowsConsoleConfigurator {
    private static final int UTF8_CODE_PAGE = 65001;

    private static final int STD_INPUT_HANDLE = -10;
    private static final int STD_OUTPUT_HANDLE = -11;

    private static final int ENABLE_VIRTUAL_TERMINAL_PROCESSING = 0x0004;

    private WindowsConsoleConfigurator() {
        throw new AssertionError("The class WindowsConsoleConfigurator cannot be instantiated.");
    }

    public static void enableUtf8() {
        if (!System.getProperty("os.name").toLowerCase().startsWith("windows")) {
            System.out.println("[WindowsConsoleConfigurator] Not Windows. Skipping configuration.");
            return;
        }

        try (Arena arena = Arena.ofConfined()) {
            Linker linker = Linker.nativeLinker();
            SymbolLookup kernel32 = SymbolLookup.libraryLookup("Kernel32", arena);

            MethodHandle getConsoleCP = linker.downcallHandle(
                    kernel32.find("GetConsoleCP").orElseThrow(),
                    FunctionDescriptor.of(ValueLayout.JAVA_INT)
            );

            MethodHandle getConsoleOutputCP = linker.downcallHandle(
                    kernel32.find("GetConsoleOutputCP").orElseThrow(),
                    FunctionDescriptor.of(ValueLayout.JAVA_INT)
            );

            MethodHandle setConsoleOutputCP = linker.downcallHandle(
                    kernel32.find("SetConsoleOutputCP").orElseThrow(),
                    FunctionDescriptor.of(
                            ValueLayout.JAVA_INT,
                            ValueLayout.JAVA_INT
                    )
            );

            MethodHandle setConsoleCP = linker.downcallHandle(
                    kernel32.find("SetConsoleCP").orElseThrow(),
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

            System.out.println();
            System.out.println("=== Windows Console Diagnostics ===");

            // ---------------------------------------------------------
            // Code pages BEFORE configuration
            // ---------------------------------------------------------

            int inputCodePage = (int) getConsoleCP.invokeExact();
            int outputCodePage = (int) getConsoleOutputCP.invokeExact();

            System.out.println("Input code page BEFORE : " + inputCodePage);
            System.out.println("Output code page BEFORE: " + outputCodePage);

            // ---------------------------------------------------------
            // UTF-8 configuration
            // ---------------------------------------------------------

            int outputResult = (int) setConsoleOutputCP.invokeExact(UTF8_CODE_PAGE);
            int inputResult = (int) setConsoleCP.invokeExact(UTF8_CODE_PAGE);

            System.out.println("SetConsoleOutputCP(65001): " + (outputResult != 0 ? "SUCCESS" : "FAILED"));
            System.out.println("SetConsoleCP(65001): " + (inputResult != 0 ? "SUCCESS" : "FAILED"));

            // ---------------------------------------------------------
            // Code pages AFTER configuration
            // ---------------------------------------------------------

            inputCodePage = (int) getConsoleCP.invokeExact();
            outputCodePage = (int) getConsoleOutputCP.invokeExact();

            System.out.println("Input code page AFTER  : " + inputCodePage);
            System.out.println("Output code page AFTER : " + outputCodePage);

            // ---------------------------------------------------------
            // Enable Virtual Terminal Processing
            // ---------------------------------------------------------

            MemorySegment outputHandle = (MemorySegment) getStdHandle.invokeExact(STD_OUTPUT_HANDLE);

            System.out.println("STD_OUTPUT_HANDLE: " + outputHandle);

            if (outputHandle.equals(MemorySegment.NULL)) {
                System.out.println("GetStdHandle(STD_OUTPUT_HANDLE): FAILED");
                System.out.println("=================================");
                return;
            }

            try (Arena modeArena = Arena.ofConfined()) {
                MemorySegment mode = modeArena.allocate(ValueLayout.JAVA_INT);

                int getModeResult = (int) getConsoleMode.invokeExact(
                        outputHandle,
                        mode
                );

                if (getModeResult == 0) {
                    System.out.println("GetConsoleMode: FAILED");
                    System.out.println("=================================");
                    return;
                }

                int originalMode = mode.get(ValueLayout.JAVA_INT, 0);

                System.out.printf(
                        "Console output mode BEFORE: 0x%08X%n",
                        originalMode
                );

                int newMode = originalMode | ENABLE_VIRTUAL_TERMINAL_PROCESSING;

                System.out.printf(
                        "Console output mode AFTER : 0x%08X%n",
                        newMode
                );

                int setModeResult = (int) setConsoleMode.invokeExact(
                        outputHandle,
                        newMode
                );

                System.out.println(
                        "ENABLE_VIRTUAL_TERMINAL_PROCESSING: "
                                + (setModeResult != 0 ? "SUCCESS" : "FAILED")
                );

                // Read it back to verify it actually stuck.
                int verifyResult = (int) getConsoleMode.invokeExact(
                        outputHandle,
                        mode
                );

                if (verifyResult != 0) {
                    int verifiedMode = mode.get(ValueLayout.JAVA_INT, 0);

                    System.out.printf(
                            "Console output mode VERIFIED: 0x%08X%n",
                            verifiedMode
                    );

                    boolean vtEnabled =
                            (verifiedMode & ENABLE_VIRTUAL_TERMINAL_PROCESSING) != 0;

                    System.out.println("VT processing actually enabled: " + vtEnabled);
                } else {
                    System.out.println("Could not verify console mode.");
                }
            }

            MemorySegment inputHandle =
                    (MemorySegment) getStdHandle.invokeExact(STD_INPUT_HANDLE);

            System.out.println("STD_INPUT_HANDLE: " + inputHandle);

            if (inputHandle.equals(MemorySegment.NULL)) {
                System.out.println("GetStdHandle(STD_INPUT_HANDLE): FAILED");
            } else {
                try (Arena inputModeArena = Arena.ofConfined()) {
                    MemorySegment inputMode =
                            inputModeArena.allocate(ValueLayout.JAVA_INT);

                    int inputModeResult = (int) getConsoleMode.invokeExact(
                            inputHandle,
                            inputMode
                    );

                    if (inputModeResult == 0) {
                        System.out.println("GetConsoleMode(INPUT): FAILED");
                    } else {
                        int modeValue = inputMode.get(ValueLayout.JAVA_INT, 0);

                        System.out.printf(
                                "Console input mode: 0x%08X%n",
                                modeValue
                        );
                    }
                }
            }

            System.out.println("=================================");
            System.out.println();

        } catch (Throwable throwable) {
            System.out.println();
            System.out.println("=== Windows Console Configuration ERROR ===");
            throwable.printStackTrace(System.out);
            System.out.println("===========================================");
            System.out.println();
        }
    }
}