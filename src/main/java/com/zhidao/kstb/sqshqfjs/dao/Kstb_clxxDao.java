package com.zhidao.kstb.sqshqfjs.dao;

import com.zhidao.kstb.sqshqfjs.model.req.SqshRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Kstb_clxxDao {

    int insert(SqshRecord sqshRecord);

    int insertSelective(SqshRecord sqshRecord);

}