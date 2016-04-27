package com.realdolmen.fleet.utils;

import com.realdolmen.fleet.Application;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class FileUtil {

    public static void saveCarImage(MultipartFile image, String filename) {
        try {
            String directory = "/static/images/cars/";
//            String filepath = Paths.get(directory, filename).toString();

            // Save the file locally
//            BufferedOutputStream stream =
//                    new BufferedOutputStream(new FileOutputStream(new File(filepath)));
//            stream.write(image.getBytes());
//            stream.close();

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(new File(Application.ROOT + "/" + filename)));
            FileCopyUtils.copy(image.getInputStream(), stream);
            stream.close();

//            File file = new File("/static/images/cars/" + filename);
//            image.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFilenamefor(String filename) {
        return filename.replace(".jpg", "").replace(".png", "").replace(".gif", "") +  "_" + System.nanoTime() + ".jpg";
    }
}
