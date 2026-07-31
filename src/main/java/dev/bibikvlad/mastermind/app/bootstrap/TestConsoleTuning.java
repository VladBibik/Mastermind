package dev.bibikvlad.mastermind.app.bootstrap;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class TestConsoleTuning {
    private static final int UTF8_CODE_PAGE = 65001;

    public static void tune() {
        if (!System.getProperty("os.name").toLowerCase().startsWith("windows")) {
            return;
        }

        try (Arena arena = Arena.ofConfined()) {
            Linker linker = Linker.nativeLinker();
            SymbolLookup kernel32 = SymbolLookup.libraryLookup("Kernel32", arena);

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

            int outputResult = (int) setConsoleOutputCP.invokeExact(UTF8_CODE_PAGE);
            int inputResult = (int) setConsoleCP.invokeExact(UTF8_CODE_PAGE);

            if (outputResult == 0 || inputResult == 0) {
                throw new IllegalStateException("Failed to set Windows console UTF-8 code page");
            }
        } catch (Throwable throwable) {
            throw new IllegalStateException("Failed to configure Windows console", throwable);
        }
    }
}
