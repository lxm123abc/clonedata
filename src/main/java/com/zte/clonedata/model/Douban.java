package com.zte.clonedata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ProjectName: clonedata-com.zte.clonedata.model
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 16:00 2020/5/28
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Douban{

    private String did;
    private String rate;
    private String cover_x;
    private String title;
    private String url;
    private boolean playable;
    private String cover;
    private String id;
    private String cover_y;
    private boolean is_new;
    private int sort;
    private String pDate;
    private String filepath;


}
