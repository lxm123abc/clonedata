package com.zte.clonedata.util;

import com.zte.clonedata.contanst.Contanst;
import com.zte.clonedata.model.error.BusinessException;
import com.zte.clonedata.model.error.EmBusinessError;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * ProjectName: clonedata-com.zte.clonedata.util
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 18:03 2020/5/28
 * @Description:
 */
@Slf4j
public class HttpUtils {
    private HttpUtils(){}

    public static String getJson(String url,String host) throws BusinessException {
        log.info("即将访问: {}, GET",url);
        CloseableHttpClient client = initHttpClient();//Spring: 连接池
        //HttpClient client = HttpClients.createDefault();//main: 创建一个
        HttpGet httpGet = null;
        HttpResponse response = null;
        String resultJson = null;
        try {
            httpGet = new HttpGet(url);
            httpGet.setHeader("Host", host);
            httpGet.setHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
            response = client.execute(httpGet);
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {

                resultJson = EntityUtils
                        .toString(response.getEntity(), Contanst.CHARSET);
                return resultJson;
            }
        } catch (ClientProtocolException e) {
            throw new BusinessException(EmBusinessError.HTTP_ERROR);
        } catch (IOException e) {
            throw new BusinessException(EmBusinessError.IO_ERROR);
        }finally {
            // 释放资源
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (IOException e) {
                throw new BusinessException(EmBusinessError.IO_ERROR);
            }
        }
        throw new BusinessException(EmBusinessError.NUKNOW_ERROR);
    }

    public static CloseableHttpClient initHttpClient() throws BusinessException {
        CloseableHttpClient closeableHttpClient = SpringContextUtil.getBean("httpClient");
        if (closeableHttpClient == null) {
            log.error("连接池获取异常");
            throw new BusinessException(EmBusinessError.HTTP_POOL_ERROR);
        } else {
            return closeableHttpClient;
        }
    }
}
