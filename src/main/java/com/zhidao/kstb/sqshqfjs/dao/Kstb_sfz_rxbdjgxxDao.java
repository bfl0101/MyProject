package com.zhidao.kstb.sqshqfjs.dao;

import com.zhidao.kstb.sqshqfjs.model.req.SqshRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface Kstb_sfz_rxbdjgxxDao {
    int insert(SqshRecord sqshRecord);

    int insertSelective(SqshRecord sqshRecord);
}