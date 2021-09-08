package xyz.yurihentai.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.yurihentai.bean.用户;
import xyz.yurihentai.bean.用户Example;
import xyz.yurihentai.mapper.用户Mapper;

import java.util.List;

@Service
public class 用户服务 {

    private final 用户Mapper 用户Mapper;

    @Autowired
    public 用户服务(用户Mapper 用户Mapper) {
        this.用户Mapper = 用户Mapper;
    }

    public List<用户> getAll() {
        用户Example 用户Example = new 用户Example();
        用户Example.createCriteria().and主键IsNotNull();
        return 用户Mapper.selectByExample(用户Example);
    }

    //或limit语句手写sql
    public PageInfo<用户> pageAll(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        用户Example 用户Example = new 用户Example();
        用户Example.createCriteria().and主键IsNotNull();
        List<用户> 用户集合 = 用户Mapper.selectByExample(用户Example);
        return new PageInfo<>(用户集合);
    }

}
