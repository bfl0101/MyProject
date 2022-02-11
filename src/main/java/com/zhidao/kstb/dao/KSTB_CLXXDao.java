package com.zhidao.kstb.dao;

import com.zhidao.kstb.model.tabc.KSTB_CLXXC;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface KSTB_CLXXDao {
    int insert(KSTB_CLXXC record);

    int insertSelective(KSTB_CLXXC record);
}