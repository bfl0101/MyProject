package com.zhidao.kstb.dao;

import com.zhidao.kstb.model.CZRK_CSSQSPXX;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CZRK_CSSQSPXXDao {
    int deleteByPrimaryKey(Long xlh);

    int insert(CZRK_CSSQSPXX record);

    int insertSelective(CZRK_CSSQSPXX record);

    CZRK_CSSQSPXX selectByPrimaryKey(Long xlh);

    int updateByPrimaryKeySelective(CZRK_CSSQSPXX record);

    int updateByPrimaryKey(CZRK_CSSQSPXX record);

    @Select({"select ${seqname} from dual"})
    long selectSequence(String seqname);//序列号
}