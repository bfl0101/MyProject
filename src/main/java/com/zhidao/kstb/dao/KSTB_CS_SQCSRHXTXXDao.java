package com.zhidao.kstb.dao;

import com.zhidao.kstb.model.tabc.KSTB_CS_SQCSRHXTXX;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface KSTB_CS_SQCSRHXTXXDao {
    int insert(KSTB_CS_SQCSRHXTXX record);

    int insertSelective(KSTB_CS_SQCSRHXTXX record);
}