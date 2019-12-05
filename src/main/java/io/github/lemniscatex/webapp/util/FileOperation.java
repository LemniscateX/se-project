package io.github.lemniscatex.webapp.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.apache.tomcat.util.http.fileupload.FileUtils.deleteDirectory;

public class FileOperation {

    public static void storeFile(String type, Integer id, MultipartFile[] files) {
        String pathString = type + "/" + id.toString() + "/";
        File folder = new File(pathString);
        if (!folder.exists())
            folder.mkdir();
        for (MultipartFile file : files) {
            Path path = Paths.get(pathString + file.getOriginalFilename());
            try (OutputStream os = Files.newOutputStream(path)) {
                os.write(file.getBytes());
            } catch (IOException e) {

            }
        }
    }

    public static void deleteFile(String type, Integer id) {
        String pathString = type + "/" + id.toString();
        File file = new File(pathString);
        if (file.isDirectory()) {
            String[] childFiles = file.list();
            if (childFiles == null) {
                file.delete();
            } else {
                for (String childFilePath : childFiles) {
                    File currentFile = new File(file.getPath(), childFilePath);
                    currentFile.delete();
                }
                file.delete();
            }

        } else {
            file.delete();
        }
    }
}
