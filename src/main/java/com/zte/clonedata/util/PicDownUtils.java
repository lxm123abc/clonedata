package com.zte.clonedata.util;

import com.zte.clonedata.contanst.Contanst;
import lombok.extern.slf4j.Slf4j;

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

    public volatile List<String > urls = new LinkedList<>();
    public volatile List<String>  paths = new LinkedList<>();

    private int err = 0;
    @Override
    public void run() {
        synchronized (this){
            String nowYYYYMMDD = DateUtils.getNowYYYYMMDD();
            long start = System.currentTimeMillis();
            int size = urls.size();
            log.info("{} 开始执行图片下载,保存 任务 ===============",nowYYYYMMDD);
            log.info("图片下载个数为: {}",size);
            List<File> fileList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                String url = urls.get(i);
                String path = paths.get(i);
                File file = null;
                try {
                    file = downSaveReturnFile(url, path);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                if (file != null) fileList.add(file);
            }
            DecimalFormat df=new DecimalFormat("0.00");
            log.info("成功: {}, 失败: {}, 成功率: {}",size,err,err==0?"100%":String.valueOf(df.format((double) (size-err) / (double) size *100)).concat("%"));
            log.info("执行图片下载,保存任务结束,用时: {} ===============",System.currentTimeMillis()-start);
            //FTP
            FTPUtils ftpUtils = SpringContextUtil.getBean(FTPUtils.class);
            ftpUtils.uploadFile(fileList);
        }
    }

    private int c = 0;
    private File downSaveReturnFile(String url, String path) throws InterruptedException {
        File file = new File(Contanst.BASEURL.concat(path));
        if (file.exists()){
            return file;
        }
        try{
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setRequestMethod(Contanst.METHOD_TYPE_GET);
            InputStream inputStream = httpURLConnection.getInputStream();
            if (inputStream != null){
                File fileParent = file.getParentFile();
                //判断是否存在
                if (!fileParent.exists()) {
                    fileParent.mkdirs();
                }
                file.createNewFile();
                //保存本地
                FileUtils.writeToFile(file,inputStream);
                return file;
            }
            c = 0;
            Thread.sleep(200);
        }catch (IOException e){
            log.error(e.getMessage());
            if (c++ < 10){
                log.error("三秒后再次尝试连接  >>>{}<<<",c);
                Thread.sleep(3000);
                return downSaveReturnFile(url,path);
            }else{
                err++;
                c = 0;
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            c = 0;
        }
        return null;
    }


}
