package com.zte.clonedata.util;

import com.zte.clonedata.contanst.Contanst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * ProjectName: clonedata-com.zte.clonedata.util
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 17:17 2020/5/28
 * @Description:
 */
@Slf4j
public class PicDownUtils implements Runnable{

    public volatile static List<String > urls = new LinkedList<>();
    public volatile static List<String>  paths = new LinkedList<>();

    @Override
    public void run() {
        synchronized (this){
            String nowYYYYMMDD = DateUtils.getNowYYYYMMDD();
            long start = System.currentTimeMillis();
            int size = urls.size();
            log.info("{} 开始执行图片下载,保存 任务 ===============",nowYYYYMMDD);
            log.info("图片下载个数为: {}",size);
            int err = 0;
            List<File> fileList = new LinkedList<>();
            for (int i = 0; i < size; i++) {

                String url = urls.get(i);
                String path = paths.get(i);

                try{
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
                    httpURLConnection.setRequestMethod(Contanst.METHOD_TYPE_GET);
                    InputStream inputStream = httpURLConnection.getInputStream();
                    if (inputStream != null){
                        File file = new File(Contanst.BASEURL.concat(path));
                        File fileParent = file.getParentFile();
                        //判断是否存在
                        if (!fileParent.exists()) {
                            fileParent.mkdirs();
                        }
                        file.createNewFile();
                        //保存本地
                        FileUtils.writeToFile(file,inputStream);
                        fileList.add(file);
                    }
                }catch (IOException e){
                    log.error(e.getMessage());
                    err++;
                }
            }
            DecimalFormat df=new DecimalFormat("0.00");
            log.info("成功: {}, 失败: {}, 成功率: {}",size,err,err==0?"100%":String.valueOf(df.format((double) (size-err) / (double) size *100)).concat("%"));
            log.info("执行图片下载,保存任务结束,用时: {} ===============",System.currentTimeMillis()-start);
            //FTP
            FTPUtils ftpUtils = SpringContextUtil.getBean(FTPUtils.class);
            ftpUtils.uploadFile(fileList);
        }
    }

    public void clear(){
        urls.clear();
        paths.clear();
    }
}
