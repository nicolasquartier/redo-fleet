package com.realdolmen.fleet.utils;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FileUtilTest {

    @Test
    public void fileUtilCanBeConstructed() {
        FileUtil fileUtil = new FileUtil();
        assertNotNull(fileUtil);
    }


    public void saveFileToIncorrectPathHandlesTheException() throws Exception {
        byte[] fakeFile = new byte[1];
        MultipartFile mockMultipartFile = new MockMultipartFile("fake1231",fakeFile);
        FileUtil.saveCarImage(mockMultipartFile, "fake212");
    }

    @Test
    public void getFileNameDoesTheJob() {
        String filenamefor = FileUtil.getFilenamefor("expected.png");
        assertTrue(filenamefor.startsWith("expected"));
        assertTrue(filenamefor.endsWith(".jpg"));

    }

    @Test
    public void toStringMethod() {
        FileUtil util = new FileUtil();
        assertEquals("fileUtil", util.toString());
    }

}