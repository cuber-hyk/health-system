package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 食谱分类：(RecipeClassification)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "RecipeClassification")
public class RecipeClassification implements Serializable {

    // RecipeClassification编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_classification_id")
    private Integer recipe_classification_id;

    // 食谱分类
    @Basic
    private String recipe_classification;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
