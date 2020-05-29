package com.zte.clonedata;

import com.zte.clonedata.util.DateUtils;

/**
 * ProjectName: clonedata-com.zte.clonedata
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 15:41 2020/5/28
 * @Description:
 */
public class Contanst {

    /**
     * Common
     */
    public static final String BASEURL = "/home/zte/clonedata/";
    public static final String METHOD_TYPE = "GET";
    public static final String CHARSET = "utf-8";

    /**
     * 豆瓣
     */
    public static final String TYPE_DOUBAN = DateUtils.getNowYYYYMMDD().concat("/douban/douban");
    public static final String JSON_KEY_DOUBAN = "subjects";

}
