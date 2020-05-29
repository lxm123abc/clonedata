package com.zte.clonedata.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ProjectName: clonedata-com.zte.clonedata.util
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 9:36 2020/5/29
 * @Description:
 */
public class DateUtils {

    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");

    public static String getNowYYYYMMDD(){
        return sdf1.format(new Date());
    }

}
