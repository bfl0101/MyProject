package com.zhidao.kstb.hjlzm.dao;


import com.zhidao.kstb.hjlzm.model.come.HjzmRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HjzmDao {
    @Insert("INSERT INTO KSTB_SQKJHJZM\n" +
            "(YWXTBH, YWLSH, XM, GMSFHM, HJDZ_SSXQDM, HJDZ_QHNXXDZ, LXDH, JMSFZLZFSDM, SJR_XM, SJR_LXDH, SJR_YZBM, SJR_TXDZ, HJZMLXDM, ZMSXNR_JYQK,BZ," +
            " SLDW_GAJGJGDM, SLDW_GAJGMC, SLDW_LXDH, SLR_GMSFHM, SLR_XM, SLR_LXDH, SLSJ, XTYW_QYFWDM, FSDW_SJGSDWDM, FSDW_SJGSDWMC, JSDW_SJGSDWDM, JSDW_SJGSDWMC)\n" +
            "VALUES(#{h.ywxtbh}, #{h.ywlsh}, #{h.xm},#{h.gmsfhm},#{h.hjdz_ssxqdm}, #{h.hjdz_qhnxxdz}, #{h.lxdh},#{h.jmsfzlzfsdm}, #{h.sjr_xm}, #{h.sjr_lxdh}," +
            " #{h.sjr_yzbm}, #{h.sjr_txdz},#{h.hjzmlxdm},#{h.zmsxnr_jyqk}, #{h.bz}, #{h.sldw_gajgjgdm}, #{h.sldw_gajgmc},#{h.sldw_lxdh}, #{h.slr_gmsfhm}," +
            " #{h.slr_xm}, #{h.slr_lxdh},to_date(#{h.slsj},'yyyy-mm-dd hh24:mi:ss'),#{h.xtyw_qyfwdm},#{h.fsdw_sjgsdwdm},#{h.fsdw_sjgsdwmc}, #{h.jsdw_sjgsdwdm}," +
            "#{h.jsdw_sjgsdwmc})\n")
    public int insertSqkj(@Param("h") HjzmRecord HjzmRecord);
    @Insert("INSERT INTO KSTB_CLXX (ywxtbh,sxh,cllbdm,clmc,clgs,clnr,ID) VALUES (#{h.ywxtbh},#{h.sxh},#{h.cllbdm},#{h.clmc},#{h.clgs},#{h.clnr},#{id})")
    int insertClxx(@Param("h")HjzmRecord hjzmRecord,@Param("id")String uuid);
    @Insert("INSERT INTO KSTB_CJHJZM " +
            "(YWXTBH,YWLSH,XM,GMSFHM,HJDZ_SSXQDM,HJDZ_QHNXXDZ,HJZMLXDM,BZ,BLDW_GAJGJGDM,BLDW_GAJGMC,BLDW_LXDH,BLR_GMSFHM,BLR_XM,BLR_LSDH,BLSJ,XTYW_QYFWDM," +
            "FSDW_SJGSDWDM,FSDW_SJGSDWMC,JSDW_SJGSDWDM,JSDW_SJGSDWMC) VALUES(#{h.ywxtbh}, #{h.ywlsh}, #{h.xm}, #{h.gmsfhm}, #{h.hjdz_ssxqdm}, #{h.hjdz_qhnxxdz}," +
            " #{h.hjzmlxdm}, #{h.bz}, #{h.bldw_gajgjgdm}, #{h.bldw_gajgmc}, #{h.bldw_lxdh}, #{h.blr_gmsfhm}, #{h.blr_xm}, #{h.blr_lsdh},to_date(#{h.blsj},'yyyy-mm-dd hh24:mi:ss')," +
            " #{h.xtyw_qyfwdm},#{h.fsdw_sjgsdwdm}, #{h.fsdw_sjgsdwmc}, #{h.jsdw_sjgsdwdm}, #{h.jsdw_sjgsdwmc})")
    int insertKjxx(@Param("h")HjzmRecord hjzmRecord);

    @Insert("INSERT INTO KSTB_BYKJHJZM " +
            "(YWXTBH,YWLSH,XM,GMSFHM,HJZMLXDM,BYCJHJZMYYDM,BYCJHJZMYY_JYQK,XTYW_QYFWDM,BLDW_GAJGJGDM,BLDW_GAJGMC,BLDW_LXDH,BLR_GMSFHM,BLR_XM,BLR_LSDH,BLSJ," +
            "FSDW_SJGSDWDM,FSDW_SJGSDWMC,JSDW_SJGSDWDM,JSDW_SJGSDWMC) VALUES(#{h.ywxtbh},#{h.ywlsh},#{h.xm},#{h.gmsfhm},#{h.hjzmlxdm},#{h.bycjhjzmyydm}," +
            "#{h.bycjhjzmyy_jyqk},#{h.xtyw_qyfwdm},#{h.bldw_gajgjgdm},#{h.bldw_gajgmc},#{h.bldw_lxdh},#{h.blr_gmsfhm},#{h.blr_xm},#{h.blr_lsdh},to_date(#{h.blsj},'yyyy-mm-dd hh24:mi:ss')," +
            "#{h.fsdw_sjgsdwdm},#{h.fsdw_sjgsdwmc},#{h.jsdw_sjgsdwdm},#{h.jsdw_sjgsdwmc})")
    int insertBykj(@Param("h")HjzmRecord hjzmRecord);





}
