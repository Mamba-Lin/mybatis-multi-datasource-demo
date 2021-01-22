package com.ethan.mybatismultidatasourcedemo.test1.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @ClassName MoneyEntity
 * @Description TODO
 * @Author Yutao.Lin
 * @Date 1/22/21 1:15 PM
 * @Version 1.0
 **/
@Data
public class MoneyEntity {
    private Integer id;

    private String name;

    private Long money;

    private Integer isDeleted;

    private Timestamp createAt;

    private Timestamp updateAt;
}