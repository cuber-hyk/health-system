package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 食物信息：(FoodInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "FoodInformation")
public class FoodInformation implements Serializable {

    // FoodInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_information_id")
    private Integer food_information_id;

    // 食物名称
    @Basic
    private String food_name;
    // 食物分类
    @Basic
    private String food_classification;
    // 封面
    @Basic
    private String cover;
    // 食用禁忌
    @Basic
    private String taboo;
    // 能量
    @Basic
    private String energy;
    // 适用人群
    @Basic
    private String intended_for;
    // 食物介绍
    @Basic
    private String food_introduction;

    // 点击数
    @Basic
    private Integer hits;

    // 点赞数
    @Basic
    private Integer praise_len;












    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
