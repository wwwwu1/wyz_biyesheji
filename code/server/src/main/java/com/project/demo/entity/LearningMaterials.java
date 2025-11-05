package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 动漫视频：(LearningMaterials)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "LearningMaterials")
public class LearningMaterials implements Serializable {

    // LearningMaterials编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "learning_materials_id")
    private Integer learning_materials_id;

    // 动漫名称
    @Basic
    private String data_name;
    // 动漫封面
    @Basic
    private String data_cover;
    // 动漫类型
    @Basic
    private String data_type;
    // 动漫介绍
    @Basic
    private String data_introduction;
    // 动漫视频
    @Basic
    private String information_video;
    // 参考积分
    @Basic
    private Integer reference_integral;
    // 动漫内容
    @Basic
    private String data_content;
    // 备注详情
    @Basic
    private String note_details;

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
