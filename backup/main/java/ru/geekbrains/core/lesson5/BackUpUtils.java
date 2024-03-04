package ru.geekbrains.core.lesson5;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackUpUtils {
    public static void main(String[] args) {
        String sourceDirectory = "./src";
        createBackup(sourceDirectory);
    }

    public static void createBackup(String sourceDir) {
        File backupDir = new File("./backup/");
        backupDir.mkdirs();
        copyFiles(new File(sourceDir), backupDir);
        System.out.println("Резервная копия успешно создана в " + backupDir.getAbsolutePath());
    }


    private static void copyFiles(File source, File destination) {
        if (source.isDirectory()) {
            if (!destination.exists()) {
                destination.mkdir();
            }

            String[] files = source.list();
            if (files != null) {
                for (String file : files) {
                    File srcFile = new File(source, file);
                    File destFile = new File(destination, file);
                    copyFiles(srcFile, destFile);
                }
            }
        } else {
            try {
                Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
