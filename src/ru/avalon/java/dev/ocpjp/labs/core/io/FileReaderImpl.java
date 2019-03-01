package ru.avalon.java.dev.ocpjp.labs.core.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.IntSummaryStatistics;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

final class FileReaderImpl implements FileReader {

    private RandomAccessFile stream;

    private final int linesCount;

    private final int averageLineSize;

    FileReaderImpl(File file) throws IOException {
        linesCount = Files.readAllLines(file.toPath()).size();
        stream = new RandomAccessFile(file, "r");
//        IntSummaryStatistics statistics = Stream
        averageLineSize = (int)Stream
                .generate(this::readLine)
                //.takeWhile(Objects::nonNull)  // because java 8
                .filter(Objects::nonNull)
                .limit(linesCount)
                .mapToDouble(String::length)
                .average()
                .getAsDouble();
//                .mapToInt(String::length)
//                .summaryStatistics();
    }

    @Override
    public int getLinesCount() {
        return linesCount;
    }

    private String readLine() {
        try {
            return stream.readLine();
        } catch (IOException ignore) {
            return null;
        }
    }

    @Override
    public String readLine(int lineNumber) throws IOException {
        stream.seek(lineNumber * averageLineSize);
        stream.readLine();
        String line = stream.readLine();
        byte[] bytes = line.getBytes(StandardCharsets.ISO_8859_1);
        return new String(bytes);
    }

    @Override
    public void close() throws IOException {
        assert nonNull(stream);
        stream.close();
        stream = null;
    }
}
