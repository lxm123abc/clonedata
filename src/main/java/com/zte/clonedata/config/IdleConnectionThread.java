package com.zte.clonedata.config;

/**
 * @Author: Liangxiaomin
 * @Date Created in 11:17 2019/6/12
 * @Description:
 */

import com.google.common.collect.Maps;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.pool.PoolStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class IdleConnectionThread extends Thread {
    private static final Logger log = LoggerFactory.getLogger(IdleConnectionThread.class);
    private final PoolingHttpClientConnectionManager connManager;
    private volatile boolean shutdown;
    private Long waitTime;

    public IdleConnectionThread(PoolingHttpClientConnectionManager connManager, Long waitTime) {
        this.waitTime = waitTime;
        this.setDaemon(true);
        this.connManager = connManager;
        this.start();
    }

    public void run() {
        while(true) {
            try {
                if (!this.shutdown) {
                    synchronized(this) {
                        this.wait(this.waitTime);
                        //this.connManager.closeExpiredConnections();
                        PoolStats poolStats = this.connManager.getTotalStats();
                        Map<String, String> map = Maps.newHashMap();
                        map.put("最大线程数", String.valueOf(poolStats.getMax()));
                        map.put("空闲的线程数", String.valueOf(poolStats.getAvailable()));
                        map.put("租用的线程数", String.valueOf(poolStats.getLeased()));
                        map.put("即将启动的线程数", String.valueOf(poolStats.getPending()));
                        log.info("连接池信息:" + map);
                        this.connManager.closeIdleConnections(30L, TimeUnit.SECONDS);
                        continue;
                    }
                }
            } catch (InterruptedException var6) {
                var6.printStackTrace();
            }

            return;
        }
    }

    public void shutDown() {
        synchronized(this) {
            this.shutdown = true;
            this.notifyAll();
        }
    }
}