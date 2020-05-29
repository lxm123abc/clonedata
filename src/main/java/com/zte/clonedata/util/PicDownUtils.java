package com.zte.clonedata.util;

import com.zte.clonedata.Contanst;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * ProjectName: clonedata-com.zte.clonedata.util
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 15:17 2020/5/28
 * @Description:
 */
public class PicDownUtils implements Runnable{

    public volatile static List<String > urls = new LinkedList<>();
    public volatile static List<String>  paths = new LinkedList<>();

    @Override
    public void run() {
        for (int i = 0; i < urls.size(); i++) {

            String url = urls.get(i);
            String type = paths.get(i);

            String name = url.substring(url.lastIndexOf("/")+1);
            try{
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
                httpURLConnection.setRequestMethod(Contanst.METHOD_TYPE);
                InputStream inputStream = httpURLConnection.getInputStream();
                if (inputStream != null){
                    File file = new File(Contanst.BASEURL.concat(type).concat(File.separator).concat(name));
                    File fileParent = file.getParentFile();
                    //判断是否存在
                    if (!fileParent.exists()) {
                        fileParent.mkdirs();
                    }
                    file.createNewFile();
                    FileUtils.writeToFile(file,inputStream);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        urls.clear();
        paths.clear();
    }
}
