package com.zte.clonedata.web.dto;

import lombok.Builder;
import lombok.Data;

/**
 * ProjectName: clonedata-com.zte.clonedata.web.dto
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 10:56 2020/6/8
 * @Description:
 */
@Data
@Builder
public class TaskDTO {

    private String id;
    private String name;
    private String cron;

}
