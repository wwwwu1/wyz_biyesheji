package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 举报信息：(ReportingInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ReportingInformation")
public class ReportingInformation implements Serializable {

    // ReportingInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reporting_information_id")
    private Integer reporting_information_id;

    // 论坛标题
    @Basic
    private String forum_title;
    // 论坛类型
    @Basic
    private String forum_type;
    // 发布用户
    @Basic
    private Integer publishing_users;
    // 举报用户
    @Basic
    private Integer report_users;
    // 举报时间
    @Basic
    private Timestamp reporting_time;
    // 举报内容
    @Basic
    private String report_content;



    // 审核状态
    @Basic
    private String examine_state;








    // 审核回复
    @Basic
    private String examine_reply;



    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
