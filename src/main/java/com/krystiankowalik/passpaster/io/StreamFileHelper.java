package com.krystiankowalik.passpaster.io;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StreamFileHelper {

    private final Logger logger = Logger.getLogger(this.getClass());


    public List<String> readAllLines(Path filePath) {

        List<String> lines = null;

        if (!Files.exists(filePath)) {
            logger.error("No such file " + filePath.toAbsolutePath().toString());
        } else if (!Files.isRegularFile(filePath)) {
            logger.error("Not a file " + filePath.toAbsolutePath().toString());
        } else {

            try (Stream<String> linesStream = Files.lines(filePath, Charset.forName("UTF-8"))) {
                lines = linesStream.collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return lines;

    }

    public List<Path> getAllFilesInDirectory(Path directoryPath) {
        List<Path> files = null;

        if (!Files.exists(directoryPath)) {
            logger.error("No such directory " + directoryPath.toAbsolutePath().toString());
        } else if (!Files.isDirectory(directoryPath)) {
            logger.error("Not a directory " + directoryPath.toAbsolutePath().toString());
        } else {

            try {
                files = Files
                        .list(directoryPath)
                        .filter(f -> Files.isRegularFile(f))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return files;
    }
}