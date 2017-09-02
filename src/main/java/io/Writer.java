package io;

import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Optional;

public class Writer {

    @Getter
    private Optional<RandomAccessFile> randomAccessFile;

    public Writer() {
        randomAccessFile = Optional.empty();
    }

    public void obtainOutputStream(String location, String url) {
        String fullPathOfFile = location + File.separator + extractFileName(url);
        try {
            randomAccessFile = Optional.of(new RandomAccessFile(fullPathOfFile, "rw"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String extractFileName(String url) {
        return url.substring(url.lastIndexOf("/") + 1, url.length());
    }

    public void seek(int downloaded) {
        try {
            if (randomAccessFile.isPresent()) {
                randomAccessFile.get().seek(downloaded);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(int bytesRead, byte[] buffer) {
        randomAccessFile.ifPresent(file -> {
            try {
                file.write(buffer, 0, bytesRead);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void close() {
        randomAccessFile.ifPresent(file -> {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
