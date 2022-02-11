package com.zhidao.kstb.dao;

import com.zhidao.kstb.model.tabc.KSTB_CS_BYLHXTXX;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface KSTB_CS_BYLHXTXXDao {
    int insert(KSTB_CS_BYLHXTXX record);

    int insertSelective(KSTB_CS_BYLHXTXX record);
}