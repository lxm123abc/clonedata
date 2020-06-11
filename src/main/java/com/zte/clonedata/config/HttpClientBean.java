package com.zte.clonedata.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

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
    public HttpClientConnectionManager httpClientConnectionManager() throws KeyManagementException, NoSuchAlgorithmException {
        //采用绕过验证的方式处理https请求
        SSLContext sslcontext = createIgnoreVerifySSL();
        //设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        poolingHttpClientConnectionManager.setMaxTotal(100);
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
        b.setConnectTimeout(8000);
        b.setConnectionRequestTimeout(800);
        b.setSocketTimeout(8000);
        return b;
    }
    @Bean
    public RequestConfig requestConfig(RequestConfig.Builder requestConfigBuilder){
        RequestConfig build = requestConfigBuilder.build();
        return build;
    }




    /**
     * 绕过验证
     */
    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("TLSv1.2");
        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }
            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        sc.init(null, new TrustManager[] { trustManager }, null);
        sc.setDefault(sc);
        return sc;
    }

}
