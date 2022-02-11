package com.zhidao.kstb.log.dao;

import com.zhidao.kstb.log.model.Log_jkfwWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface Log_jkfwDao {
    int insert(Log_jkfwWithBLOBs record);

    int insertSelective(Log_jkfwWithBLOBs record);

    @Select("select to_char(sysdate,'yyyyMMddHH24misssss') a from dual")
    String selectDate();
}