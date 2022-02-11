package com.zhidao.kstb.dao;

import com.zhidao.kstb.model.tabc.KSTB_CS_JMHKBGRXX;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface KSTB_CS_JMHKBGRXXDao {
    int insert(KSTB_CS_JMHKBGRXX record);

    int insertSelective(KSTB_CS_JMHKBGRXX record);
}