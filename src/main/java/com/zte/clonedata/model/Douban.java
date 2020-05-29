package com.zte.clonedata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * ProjectName: clonedata-com.zte.clonedata.model
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 12:00 2020/5/28
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clone_douban")
@ToString
public class Douban{

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String did;
    @Column(name = "rate")
    private Float rate;
    @Column(name = "cover_x")
    private int cover_x;
    @Column(name = "title")
    private String title;
    @Column(name = "url")
    private String url;
    @Column(name = "playable")
    private boolean playable;
    @Column(name = "cover")
    private String cover;
    @Column(name = "id")
    private String id;
    @Column(name = "cover_y")
    private String cover_y;
    @Column(name = "is_new")
    private boolean is_new;
    @Column(name = "sort")
    private int sort;

}
