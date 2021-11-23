package com.gestionObras.util;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.Part;
import lombok.Data;

@Data
public class Imagen {

    private String pathFiles;
    private File uploads;
    private String[] extens = {".png", ".jpg", ".jpeg"};

    public Imagen() {
        pathFiles =  "C:\\fotos\\";
        uploads = new File(pathFiles);
    }

    public String saveFile(Part part, File pathUploads) {
        String pathAbsolute = "";

        try {
            Path path = Paths.get(part.getSubmittedFileName());
            String fileName = path.getFileName().toString();
            InputStream input = part.getInputStream();

            if (input != null) {
                File file = new File(pathUploads, fileName);
                pathAbsolute = file.getAbsolutePath();
                Files.copy(input, file.toPath());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pathAbsolute;
    }

    public boolean isExtension(String fileName, String[] extensions) {
        for (String et : extensions) {
            if (fileName.toLowerCase().endsWith(et)) {
                return true;
            }
        }

        return false;
    }

}
