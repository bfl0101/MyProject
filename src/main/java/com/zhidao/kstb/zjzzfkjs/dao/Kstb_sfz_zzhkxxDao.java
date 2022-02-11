package com.zhidao.kstb.zjzzfkjs.dao;

import com.zhidao.kstb.zjzzfkjs.model.req.ZjzzRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface Kstb_sfz_zzhkxxDao {
    int insert(ZjzzRecord record);

    int insertSelective(ZjzzRecord record);
}