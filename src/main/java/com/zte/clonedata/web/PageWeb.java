package com.zte.clonedata.web;

import com.zte.clonedata.job.douban.JobDouban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProjectName: clonedata-com.zte.clonedata.web
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 12:22 2020/6/1
 * @Description:
 */
@RestController
@RequestMapping("/page")
public class PageWeb {

    @Autowired
    private JobDouban jobDouban;

    @GetMapping("/execute")
    public void execute(String id,String token) throws Exception {
        if (token.equals("LXM_123!")){
            jobDouban.execute();
        }
    }

    @RequestMapping("/")
    public void ftp(){

    }

}
