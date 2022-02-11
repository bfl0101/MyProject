package com.zhidao.kstb.shqffkjs.dao;


import com.zhidao.kstb.shqffkjs.model.req.ShqfRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface Kstb_sfz_shqffkxxDao {
    int deleteByPrimaryKey(String ID);

    int insert(ShqfRecord shqfRecord);

    int insertSelective(ShqfRecord shqfRecord);

    ShqfRecord selectByPrimaryKey(String ID);

    int updateByPrimaryKeySelective(ShqfRecord shqfRecord);

    int updateByPrimaryKey(ShqfRecord shqfRecord);

    @Select("select count(1) from KSTB_SFZ_SQSHQFXX_FS where ywxtbh = #{ywxtbh}")
    int selectYWXTBH(String ywxtbh);

    @Update("update KSTB_SFZ_SQSHQFXX_FS set JMSFZSHJGDM = #{jmsfzshjgdm},SHQFFKXLH = #{ywxtbh},SHJG_JYQK = #{shjg_jyqk} where YWXTBH = #{ywxtbh} and code = 10")
    void updateYWXTBH(String jmsfzshjgdm, String ywxtbh, String shjg_jyqk);
}