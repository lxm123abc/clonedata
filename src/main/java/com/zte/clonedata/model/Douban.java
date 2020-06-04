package com.zte.clonedata.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Douban implements Serializable {
    private String id;
    private String cover;
    private String coverX;
    private String coverY;
    private String filepath;
    private Integer isNew;
    private String pDate;
    private Integer playable;
    private String rate;
    private Integer sort;
    private String title;
    private String url;
    private static final long serialVersionUID = 1L;

}