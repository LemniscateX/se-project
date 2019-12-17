package io.github.lemniscatex.webapp.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FileOperation {

    public static String storeFile(String type, Integer id, MultipartFile file) {
        File folder = Paths.get("/public", type, id.toString()).toFile();
        if (!folder.exists()) {
            boolean mkdir = folder.mkdirs();
            if (!mkdir) {
                return null;
            }
        }
        String filename = Optional.ofNullable(file.getOriginalFilename()).orElse("image.jpg");
        Path path = folder.toPath().resolve(filename);
        try (OutputStream os = Files.newOutputStream(path)) {
            os.write(file.getBytes());
            return path.subpath(1, path.getNameCount()).toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
