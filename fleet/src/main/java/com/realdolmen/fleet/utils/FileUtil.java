package com.realdolmen.fleet.utils;

import com.realdolmen.fleet.Application;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUtil {

    FileUtil() {

    }

    public String toString() {
        return "fileUtil";
    }

    public static void saveCarImage(MultipartFile image, String filename) throws FileNotFoundException {
        try {
            String directory = "/static/images/cars/";
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(new File(Application.ROOT + "/" + filename)));
            FileCopyUtils.copy(image.getInputStream(), stream);
            stream.close();
        } catch (Exception e) {
            throw new FileNotFoundException("File not found");
        }
    }

    public static String getFilenamefor(String filename) {
        return filename.replace(".jpg", "").replace(".png", "").replace(".gif", "") +  "_" + System.nanoTime() + ".jpg";
    }
}
