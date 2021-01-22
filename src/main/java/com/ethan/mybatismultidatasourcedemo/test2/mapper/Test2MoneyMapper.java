package com.ethan.mybatismultidatasourcedemo.test2.mapper;

import com.ethan.mybatismultidatasourcedemo.test2.entity.MoneyEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by linyutao on 1/22/21 1:17 PM
 */
@Mapper
public interface Test2MoneyMapper {
    List<MoneyEntity> findByIds(List<Integer> ids);
}
