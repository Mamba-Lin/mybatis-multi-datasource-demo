package com.ethan.mybatismultidatasourcedemo.test1.mapper;

import com.ethan.mybatismultidatasourcedemo.test1.entity.MoneyEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by linyutao on 1/22/21 1:17 PM
 */
@Mapper
public interface Test1MoneyMapper {
    List<MoneyEntity> findByIds(List<Integer> ids);
}
