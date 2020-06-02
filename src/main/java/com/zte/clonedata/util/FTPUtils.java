package com.zte.clonedata.util;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * ProjectName: clonedata-com.zte.clonedata.util
 *
 *

#FTP目标服务器配置
ftp:
  isopen: false
  host: 192.168.139.202
  port: 22
  username: root
  password: 123
  savepath: /home/img/

 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 9:49 2020/6/2
 * @Description:
 */
@Slf4j
@Component
public class FTPUtils {
    private ChannelSftp sftp = null;
    private Session sshSession = null;

    @Value("${ftp.host:}")
    private String FTP_ADDRESS;
    @Value("${ftp.port:0}")
    private int FTP_PORT;
    @Value("${ftp.username:}")
    private String FTP_USERNAME;
    @Value("${ftp.password:}")
    private String FTP_PASSWORD;
    @Value("${ftp.savepath:}")
    private String FTP_BASEPATH;
    @Value("${ftp.isopen:false}")
    private boolean isopen;

    /**
     * 连接sftp服务器
     * @return ChannelSftp sftp连接实例
     */
    public ChannelSftp connect() {
        if (!isopen || StringUtils.isEmpty(FTP_ADDRESS)){//FTP上传
            return null;
        }
        log.info("-->sftp连接开始>>>>>> " + FTP_ADDRESS + ":" + FTP_PORT + " >>>username=" + FTP_USERNAME);
        JSch jsch = new JSch();
        try {
            sshSession = jsch.getSession(FTP_USERNAME, FTP_ADDRESS, FTP_PORT);
            sshSession.setPassword(FTP_PASSWORD);
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(properties);
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            log.info(" ftp Connected to " + FTP_ADDRESS + ":" + FTP_PORT);
        } catch (JSchException e) {
            throw new RuntimeException("sftp连接失败", e);
        }
        return sftp;
    }

    /**
     * 上传单个文件
     */
    public void uploadFile(List<File> fileList){
        if (!isopen || StringUtils.isEmpty(FTP_ADDRESS)){//FTP上传
            return;
        }
        log.info(">>>>>>>>>uploadFile--ftp上传文件开始>>>>>>>>>>>>>");
        long start = System.currentTimeMillis();
        connect();
        try {
            sftp.cd(FTP_BASEPATH);
        } catch (SftpException e) {
            try {
                sftp.mkdir(FTP_BASEPATH);
                sftp.cd(FTP_BASEPATH);
            } catch (SftpException e1) {
                log.error("ftp创建文件路径失败，路径为" + FTP_BASEPATH);
                throw new RuntimeException("ftp创建文件路径失败" + FTP_BASEPATH);
            }
        }
        for (int i = 0; i < fileList.size(); i++) {
            File file = fileList.get(i);
            InputStream inputStream =null;
            try {
                inputStream = new FileInputStream(file);
                String filename = file.getName();
                sftp.put(inputStream, filename);
                Thread.sleep(200);
            } catch (SftpException e) {
                log.error("sftp异常-->", e);
            } catch (FileNotFoundException e) {
                log.error("FileInputStream-->", e);
            } catch (InterruptedException e) {
                log.error("sleep-->", e);
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        log.info("Close stream error." + e.getMessage());
                    }
                }
            }
        }
        //关闭资源
        disconnect();
        log.info(">>>>>>>>>uploadFile--ftp上传文件结束,用时: {}>>>>>>>>>>>>>",System.currentTimeMillis()-start);
    }

    /**
     * 关闭连接
     */
    public void disconnect() {
        if (this.sftp != null) {
            if (this.sftp.isConnected()) {
                this.sftp.disconnect();
                this.sftp = null;
                log.info("sftp 连接已关闭！");
            }
        }
        if (this.sshSession != null) {
            if (this.sshSession.isConnected()) {
                this.sshSession.disconnect();
                this.sshSession = null;
                log.info("sshSession 连接已关闭！");
            }
        }
    }


}
