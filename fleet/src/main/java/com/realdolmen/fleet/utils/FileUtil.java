package com.realdolmen.fleet.utils;

import com.realdolmen.fleet.Application;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
public class FileUtil {

    public void saveCarImage(MultipartFile image, String filename) throws IOException {

            String directory = "/static/images/cars/";
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(new File(Application.ROOT + "/" + filename)));
            FileCopyUtils.copy(image.getInputStream(), stream);
            stream.close();

    }

    public String getFilenamefor(String filename) {
        return filename.replace(".jpg", "").replace(".png", "").replace(".gif", "") +  "_" + System.nanoTime() + ".jpg";
    }
}
