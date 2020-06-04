package com.zte.clonedata.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * ProjectName: quartzjob-com.zte.quartzjob.config
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 14:11 2020/6/3
 * @Description:
 *
 *
#http 连接池配置
# 最大连接数
httpClient.maxTotal=200
#默认并发数
httpClient.defaultMaxPerRoute=50
#连接的最长时间毫秒
httpClient.connectTimeout=5000
#连接池中获取到连接的最长时间 毫秒
httpClient.connectionRequestTimeout=500
#数据传输的最长时间 毫秒
httpClient.socketTimeout=10000
#清理无效连接等待时长 毫秒
httpClient.waitTime=30000
 */
@Configuration
public class HttpClientBean {

    @Bean(destroyMethod = "shutdown")
    public HttpClientConnectionManager httpClientConnectionManager(){
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(200);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(50);
        return poolingHttpClientConnectionManager;
    }
    @Bean
    public HttpClientBuilder httpClientBuilder(HttpClientConnectionManager httpClientConnectionManager){
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(httpClientConnectionManager);
        return httpClientBuilder;
    }
    @Bean
    @Scope("prototype")
    public HttpClient httpClient(HttpClientBuilder httpClientBuilder){
        CloseableHttpClient build = httpClientBuilder.build();
        return build;
    }
    @Bean(destroyMethod = "shutDown")
    public IdleConnectionThread idleConnectionThread(HttpClientConnectionManager httpClientConnectionManager){
        IdleConnectionThread idleConnectionThread = new IdleConnectionThread((PoolingHttpClientConnectionManager) httpClientConnectionManager,30000L);
        return idleConnectionThread;
    }


    @Bean
    public RequestConfig.Builder requestConfigBuilder(){
        RequestConfig.Builder b = RequestConfig.custom();
        b.setConnectTimeout(5000);
        b.setConnectionRequestTimeout(500);
        b.setSocketTimeout(10000);
        return b;
    }
    @Bean
    public RequestConfig requestConfig(RequestConfig.Builder requestConfigBuilder){
        RequestConfig build = requestConfigBuilder.build();
        return build;
    }

}
