package com.zte.clonedata.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TaskLog implements Serializable {
    private String id;

    private Integer type;

    private String begintime;

    private String endtime;

    private Long time;

    private Integer status;

    private String executeResult;

    private static final long serialVersionUID = 1L;

}