package com.zhidao.kstb.shqffkjs.dao;

import com.zhidao.kstb.shqffkjs.model.Sfz_glxx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Sfz_glxxDao {
    int deleteByPrimaryKey(Long XLH);

    int insert(Sfz_glxx record);

    int insertSelective(Sfz_glxx record);

    Sfz_glxx selectByPrimaryKey(Long XLH);

    int updateByPrimaryKeySelective(Sfz_glxx record);

    int updateByPrimaryKey(Sfz_glxx record);
//公用
    @Select({"select ${seqname} from dual"})
    String selectSequence(String seqname);//序列号

    @Select("SELECT XLH FROM CZRK_JBXX WHERE GMSFHM =#{GMSFHM} and zt=0")
    String cxckxlh(@Param("GMSFHM")String a); //查询人口序列号

    @Select("SELECT pcsdm FROM CZRK_JBXX WHERE GMSFHM =#{GMSFHM} and zt=0")
    String cxckpcs(@Param("GMSFHM")String a); //查询人口派出所

    @Select("SELECT ryid FROM CZRK_JBXX WHERE GMSFHM =#{GMSFHM} and zt=0")
    String cxckrkid(@Param("GMSFHM")String a);

    @Select("SELECT clnr_blob FROM KSTB_CLXX WHERE YWXTBH =#{YWXTBH} and CLLBDM=#{CLLBDM} and rownum=1")
    byte[] cxsqxp(@Param("YWXTBH")String a, @Param("CLLBDM")String b);

    @Select("SELECT dqm FROM CZRK_JBXX WHERE GMSFHM =#{GMSFHM} and zt=0")
    String cxckdqm(@Param("GMSFHM")String a);

    @Select("SELECT substr(xzqh,1,4)||'00' FROM zh_user WHERE unit =#{SLJGDM} and rownum=1")
    String cxsldqm(@Param("SLJGDM")String a);

    @Select("SELECT nvl(pcs,fxjdm||'000') FROM zh_user WHERE unit =#{SLJGDM} and rownum=1")
    String cxslpcs(@Param("SLJGDM")String a);

    @Select("SELECT xzqh FROM zh_user WHERE unit =#{SLJGDM} and rownum=1")
    String cxslssxq(@Param("SLJGDM")String a);

    @Select("SELECT fxjdm FROM zh_user WHERE unit =#{SLJGDM} and rownum=1")
    String cxslfxj(@Param("SLJGDM")String a);

    @Select("SELECT value FROM rk_ywpzb WHERE name =#{NAME} and rownum=1")
    String getInsert(@Param("NAME")String a);

    @Select("SELECT dwjb FROM zh_user WHERE unit =#{sldw_gajgjgdm} and rownum=1")
    String cxsldwlx(String sldw_gajgjgdm);
}