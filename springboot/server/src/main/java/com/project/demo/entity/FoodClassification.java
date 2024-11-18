package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 食物分类：(FoodClassification)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "FoodClassification")
public class FoodClassification implements Serializable {

    // FoodClassification编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_classification_id")
    private Integer food_classification_id;

    // 食物分类
    @Basic
    private String food_classification;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
