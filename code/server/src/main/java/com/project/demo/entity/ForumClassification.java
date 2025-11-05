package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 论坛分类：(ForumClassification)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ForumClassification")
public class ForumClassification implements Serializable {

    // ForumClassification编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forum_classification_id")
    private Integer forum_classification_id;

    // 论坛类型
    @Basic
    private String forum_type;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
