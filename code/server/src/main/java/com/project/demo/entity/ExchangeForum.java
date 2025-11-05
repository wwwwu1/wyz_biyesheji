package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 动漫交流：(ExchangeForum)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ExchangeForum")
public class ExchangeForum implements Serializable {

    // ExchangeForum编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exchange_forum_id")
    private Integer exchange_forum_id;

    // 论坛标题
    @Basic
    private String forum_title;
    // 论坛类型
    @Basic
    private String forum_type;
    // 论坛标记
    @Basic
    private String forum_tag;
    // 发布用户
    @Basic
    private Integer publishing_users;
    // 封面图片
    @Basic
    private String cover_photo;
    // 发布内容
    @Basic
    private String publishing_content;

    // 点击数
    @Basic
    private Integer hits;

    // 点赞数
    @Basic
    private Integer praise_len;

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
