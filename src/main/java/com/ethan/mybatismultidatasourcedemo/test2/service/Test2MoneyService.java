package com.ethan.mybatismultidatasourcedemo.test2.service;

import com.ethan.mybatismultidatasourcedemo.test2.entity.MoneyEntity;
import com.ethan.mybatismultidatasourcedemo.test2.mapper.Test2MoneyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Test2MoneyService
 * @Description TODO
 * @Author Yutao.Lin
 * @Date 1/22/21 1:19 PM
 * @Version 1.0
 **/
@Service("test2Service")
public class Test2MoneyService {
    @Resource
    private Test2MoneyMapper moneyMapper;

    public void queryList(){
        List<MoneyEntity> list = moneyMapper.findByIds(Arrays.asList(1, 1000));
        System.out.println("数据源为test1返回数据2:"+list);
    }
}