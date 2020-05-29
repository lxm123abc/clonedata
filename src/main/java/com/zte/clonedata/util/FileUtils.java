package com.zte.clonedata.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * ProjectName: clonedata-com.zte.clonedata.util
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 17:18 2020/5/28
 * @Description:
 */
public class FileUtils {
    private FileUtils(){}

    public static void writeToFile(File file, InputStream inputStream) {
        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[8192];

            int bytesRead;
            while((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException var14) {
                    var14.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException var13) {
                    var13.printStackTrace();
                }
            }

        }

    }
}
