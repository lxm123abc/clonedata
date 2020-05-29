package com.zte.clonedata.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.zte.clonedata.Contanst;
import com.zte.clonedata.dao.DoubanRepository;
import com.zte.clonedata.model.Douban;
import com.zte.clonedata.util.HttpUtils;
import com.zte.clonedata.util.JSONUtils;
import com.zte.clonedata.util.PicDownUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * ProjectName: clonedata-com.zte.clonedata.job
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 13:46 2020/5/28
 * @Description:
 */
@Slf4j
@Component
@Async
public class JobDouban{


    @Autowired
    private DoubanRepository doubanRepository;
    private static String resultMD5 = "";

    /**
     */
    @Scheduled(cron = "0 9 * * * ?")
    public void execute(){
        PicDownUtils picDownUtils = new PicDownUtils();
        int i = 0;
        int j = 0;
        while (true){
            String url ="https://movie.douban.com/j/search_subjects?type=movie&tag=热门&page_limit="
                    .concat(String.valueOf(i+1000))
                    .concat("&page_start=")
                    .concat(String.valueOf(i));
            String result = HttpUtils.getJson(url);
            String data = JSONObject.parseObject(result, Map.class).get(Contanst.JSON_KEY_DOUBAN).toString();
            if (StringUtils.isEmpty(data) || data.length() <10){
                break;
            }
            String MD5 = DigestUtils.md5DigestAsHex(data.getBytes());
            if (resultMD5.equals(MD5)){
                break;
            }else {
                resultMD5 = MD5;
            }
            i = i+1000;
            List<Douban> doubans = JSONUtils.parseArray(data, Douban.class);
            for (Douban douban : doubans) {
                douban.setSort(j++);
                picDownUtils.urls.add(douban.getCover());
                picDownUtils.paths.add(Contanst.TYPE_DOUBAN.concat(String.valueOf(i)));
            }
            doubanRepository.saveAll(doubans);
            log.info("豆瓣第" + i + "也加载完毕");
        }
        Thread t1 = new Thread(picDownUtils);
        t1.start();
    }

}
