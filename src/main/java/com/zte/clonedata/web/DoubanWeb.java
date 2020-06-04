package com.zte.clonedata.web;

import com.alibaba.fastjson.JSONObject;
import com.zte.clonedata.contanst.Contanst;
import com.zte.clonedata.model.Douban;
import com.zte.clonedata.util.HttpUtils;
import com.zte.clonedata.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * ProjectName: clonedata-com.zte.clonedata.web
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 12:15 2020/6/1
 * @Description:
 */
@RestController
@RequestMapping("/douban")
@Slf4j
public class DoubanWeb {



    @GetMapping("/getDoubanScoreList")
    public List<Douban> getData() throws Exception {
        int i = 0;
        List<Douban> list = new LinkedList<>();
        while (true){
            String url ="https://movie.douban.com/j/search_subjects?type=movie&tag=热门&page_limit="
                    .concat(String.valueOf(i+1000))
                    .concat("&page_start=")
                    .concat(String.valueOf(i));
            String result = HttpUtils.getJson(url,Contanst.DOUBAN_HOST1);
            String data = JSONObject.parseObject(result, Map.class).get(Contanst.JSON_KEY_DOUBAN).toString();
            List<Douban> doubans = JSONUtils.parseArray(data, Douban.class);
            list.addAll(doubans);
            if (doubans.size()<1000) break;
            else i = i+1000;
        }
        return list;
    }

}
