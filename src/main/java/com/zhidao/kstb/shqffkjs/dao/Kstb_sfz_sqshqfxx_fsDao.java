package com.zhidao.kstb.shqffkjs.dao;

import com.zhidao.kstb.shqffkjs.model.Kstb_sfz_sqshqfxx_fsWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Kstb_sfz_sqshqfxx_fsDao {
    int insert(Kstb_sfz_sqshqfxx_fsWithBLOBs record);

    int insertSelective(Kstb_sfz_sqshqfxx_fsWithBLOBs record);

    @Select("SELECT * FROM KSTB_SFZ_SQSHQFXX_FS  WHERE YWXTBH =#{YWXTBH} and rownum=1")
    Kstb_sfz_sqshqfxx_fsWithBLOBs selsqshqf(@Param("YWXTBH")String a);

    @Select("select to_char(sysdate,'yyyymmddhh24miss') from dual")
    String datetostring();
}