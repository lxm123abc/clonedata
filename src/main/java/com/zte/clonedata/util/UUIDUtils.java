package com.zte.clonedata.util;

import java.util.UUID;

/**
 * ProjectName: clonedata-com.zte.clonedata.util
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 11:22 2020/6/9
 * @Description:
 */
public class UUIDUtils {
    private UUIDUtils(){}

    public static String get(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
