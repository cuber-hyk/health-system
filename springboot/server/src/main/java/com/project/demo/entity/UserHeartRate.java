package com.project.demo.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
@Setter
@Getter
@Entity
public class UserHeartRate implements Serializable {
    private static final long serialVersionUID = -552913649360581182L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_heart_rate_id")
    private Integer user_heart_rate_id;

    @Basic
    @Column(name = "user_id", nullable = false)
    private Integer user_id;

    @Basic
    @Column(name = "heart_rate", nullable = false)
    private Integer heart_rate;

    @Basic
    @Column(name = "create_time", nullable = false)
    private Timestamp createTime;

    @Basic
    @Column(name = "additional_info")
    private String additional_info;


}
