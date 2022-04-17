package cn.yurihentai.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.yurihentai.bean.权限;
import cn.yurihentai.bean.用户;
import cn.yurihentai.bean.用户Example;
import cn.yurihentai.bean.角色;
import cn.yurihentai.mapper.权限Mapper;
import cn.yurihentai.mapper.用户Mapper;
import cn.yurihentai.mapper.角色Mapper;

import java.util.List;

@Service
public class 用户服务 {

    private final 用户Mapper 用户Mapper;
    private final 角色Mapper 角色Mapper;
    private final cn.yurihentai.mapper.权限Mapper 权限Mapper;

    @Autowired
    public 用户服务(用户Mapper 用户Mapper,
                角色Mapper 角色Mapper,
                权限Mapper 权限Mapper) {
        this.用户Mapper = 用户Mapper;
        this.角色Mapper = 角色Mapper;
        this.权限Mapper = 权限Mapper;
    }

    public List<用户> 获取所有用户() {
        用户Example 用户Example = new 用户Example();
        用户Example.createCriteria().and编号IsNotNull();
        return 用户Mapper.selectByExample(用户Example);
    }

    //或limit语句手写sql
    public PageInfo<用户> 分页获取所有用户(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        用户Example 用户Example = new 用户Example();
        用户Example.createCriteria().and编号IsNotNull();
        List<用户> 用户集合 = 用户Mapper.selectByExample(用户Example);
        return new PageInfo<>(用户集合);
    }

    public 用户 通过账号查找用户(String 账号) {
        用户Example 用户Example = new 用户Example();
        用户Example.createCriteria().and账号EqualTo(账号).and是否删除EqualTo(false);
        List<用户> 用户List = 用户Mapper.selectByExample(用户Example);
        if(CollectionUtils.isNotEmpty(用户List)) {
            return 用户List.get(0);
        }
        return null;
    }

    public List<角色> 通过用户编号查找用户角色(Long 用户编号) {
        return 角色Mapper.通过用户编号查找用户角色(用户编号);
    }

    public List<权限> 通过角色编号查找权限(Long 角色编号) {
        return 权限Mapper.通过角色编号查找权限(角色编号);
    }

}
