package com.realdolmen.fleet.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FileUtilMockTest {

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private InputStream inputStream;

    private FileUtil fileUtil;

    @Before
    public void setUp() throws IOException {
        this.fileUtil = new FileUtil();

        when(multipartFile.getInputStream()).thenReturn(inputStream);
        when(inputStream.read(any())).thenReturn(-1);
    }

    @Test
    public void saveFileToIncorrectPathHandlesTheException() throws Exception {
        fileUtil.saveCarImage(multipartFile, "fake212");
    }

    @Test
    public void getFileNameDoesTheJob() {
        String filenamefor = fileUtil.getFilenamefor("expected.png");
        assertTrue(filenamefor.startsWith("expected"));
        assertTrue(filenamefor.endsWith(".jpg"));

    }


}