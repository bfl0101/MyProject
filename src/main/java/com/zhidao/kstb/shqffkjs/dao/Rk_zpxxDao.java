package com.zhidao.kstb.shqffkjs.dao;

import com.zhidao.kstb.shqffkjs.model.Rk_zpxx;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Rk_zpxxDao {
    int deleteByPrimaryKey(Long XLH);

    int insert(Rk_zpxx record);

    int insertSelective(Rk_zpxx record);

    Rk_zpxx selectByPrimaryKey(Long XLH);

    int updateByPrimaryKeySelective(Rk_zpxx record);

    int updateByPrimaryKeyWithBLOBs(Rk_zpxx record);

    int updateByPrimaryKey(Rk_zpxx record);
}