package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 参考信息：(ReferenceInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ReferenceInformation")
public class ReferenceInformation implements Serializable {

    // ReferenceInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reference_information_id")
    private Integer reference_information_id;

    // 动漫名称
    @Basic
    private String data_name;
    // 动漫类型
    @Basic
    private String data_type;
    // 参考积分
    @Basic
    private Integer reference_integral;
    // 参考用户
    @Basic
    private Integer reference_user;
    // 用户姓名
    @Basic
    private String user_name;
    // 参考时间
    @Basic
    private Timestamp reference_time;
    // 详情备注
    @Basic
    private String detailed_remarks;
    // 动漫文件
    @Basic
    private String information_documents;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
