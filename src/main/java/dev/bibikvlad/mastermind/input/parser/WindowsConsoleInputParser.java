package dev.bibikvlad.mastermind.input.parser;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.nio.charset.StandardCharsets;

public class WindowsConsoleInputParser implements Parser {
    private static final int STD_INPUT_HANDLE = -10;
    private static final int BUFFER_SIZE = 1024;

    private final MethodHandle readConsoleW;
    private final MemorySegment inputHandle;

    public WindowsConsoleInputParser() {
        try {
            Arena arena = Arena.ofShared();
            Linker linker = Linker.nativeLinker();
            SymbolLookup kernel32 =
                    SymbolLookup.libraryLookup("Kernel32", arena);

            readConsoleW = linker.downcallHandle(
                    kernel32.find("ReadConsoleW").orElseThrow(),
                    FunctionDescriptor.of(
                            ValueLayout.JAVA_INT,
                            ValueLayout.ADDRESS,
                            ValueLayout.ADDRESS,
                            ValueLayout.JAVA_INT,
                            ValueLayout.ADDRESS,
                            ValueLayout.ADDRESS
                    )
            );

            MethodHandle getStdHandle = linker.downcallHandle(
                    kernel32.find("GetStdHandle").orElseThrow(),
                    FunctionDescriptor.of(
                            ValueLayout.ADDRESS,
                            ValueLayout.JAVA_INT
                    )
            );

            inputHandle = (MemorySegment)
                    getStdHandle.invokeExact(STD_INPUT_HANDLE);

            if (inputHandle.equals(MemorySegment.NULL)) {
                throw new IllegalStateException("GetStdHandle(STD_INPUT_HANDLE) failed");
            }

        } catch (Throwable throwable) {
            throw new IllegalStateException("Failed to initialize Windows console input", throwable);
        }
    }

    @Override
    public String parse() {
        try (Arena readArena = Arena.ofConfined()) {
            MemorySegment buffer = readArena.allocate(BUFFER_SIZE * ValueLayout.JAVA_CHAR.byteSize());

            MemorySegment charsRead = readArena.allocate(ValueLayout.JAVA_INT);

            int result = (int) readConsoleW.invokeExact(
                    inputHandle,
                    buffer,
                    BUFFER_SIZE,
                    charsRead,
                    MemorySegment.NULL
            );

            if (result == 0) {
                throw new IllegalStateException("ReadConsoleW failed");
            }

            int count = charsRead.get(ValueLayout.JAVA_INT, 0);

            byte[] bytes = buffer.reinterpret(count * ValueLayout.JAVA_CHAR.byteSize())
                    .toArray(ValueLayout.JAVA_BYTE);

            return new String(bytes, StandardCharsets.UTF_16LE)
                    .replace("\r\n", "")
                    .replace("\n", "")
                    .replace("\r", "");

        } catch (Throwable throwable) {
            throw new IllegalStateException("Could not read Windows console input", throwable);
        }
    }
}