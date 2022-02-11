package com.zhidao.kstb.shqffkjs.dao;

import com.zhidao.kstb.shqffkjs.model.Zwqd_zwsjxx;
import com.zhidao.kstb.shqffkjs.model.Zwqd_zwsjxxWithBLOBs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Zwqd_zwsjxxDao {
    int deleteByPrimaryKey(Short XLH);

    int insert(Zwqd_zwsjxxWithBLOBs record);

    int insertSelective(Zwqd_zwsjxxWithBLOBs record);

    Zwqd_zwsjxxWithBLOBs selectByPrimaryKey(Short XLH);

    int updateByPrimaryKeySelective(Zwqd_zwsjxxWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(Zwqd_zwsjxxWithBLOBs record);

    int updateByPrimaryKey(Zwqd_zwsjxx record);
}