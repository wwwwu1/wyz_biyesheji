package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 签到信息：(SignInInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "SignInInformation")
public class SignInInformation implements Serializable {

    // SignInInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sign_in_information_id")
    private Integer sign_in_information_id;

    // 签到用户
    @Basic
    private Integer sign_in_user;
    // 用户姓名
    @Basic
    private String user_name;
    // 签到日期
    @Basic
    private Timestamp sign_in_date;
    // 签到备注
    @Basic
    private String sign_in_remarks;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
