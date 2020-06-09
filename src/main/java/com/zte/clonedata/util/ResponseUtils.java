package com.zte.clonedata.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProjectName: esay-data-connection-com.lxm.esaydataconnection.res
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 18:11 2020/5/14
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUtils {

    private String code = "0";
    private String msg = "";
    private Integer count = 0;
    private Object data;

    public static ResponseUtils success(String msg){
        return new ResponseUtils("0",msg,0,null);
    }
    public static ResponseUtils success(Integer count, Object data){
        return new ResponseUtils("0","",count,data);
    }
    public static ResponseUtils successData(Object data){
        return new ResponseUtils("0","",0,data);
    }

    public static ResponseUtils success(String msg, Integer count, Object data){
        return new ResponseUtils("0",msg,count,data);
    }
    public static ResponseUtils fail(String msg){
        return new ResponseUtils("1",msg,0,null);
    }
}
