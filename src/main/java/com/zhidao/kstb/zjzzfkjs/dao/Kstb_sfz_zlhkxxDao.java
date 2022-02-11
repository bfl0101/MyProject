package com.zhidao.kstb.zjzzfkjs.dao;

import com.zhidao.kstb.zjzzfkjs.model.req.ZjzzRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface Kstb_sfz_zlhkxxDao {
    int insert(ZjzzRecord record);

    int insertSelective(ZjzzRecord record);

    @Select("select count(1) from KSTB_SFZ_SQSHQFXX_FS where ywxtbh = #{ywxtbh}")
    int selectYWXTBH(String ywxtbh);

    @Update("update KSTB_SFZ_SQSHQFXX_FS set zlhkxlh = #{ywxtbh} where YWXTBH = #{ywxtbh} and code = 10")
    void updateZLHK_YWXTBH(String ywxtbh);

    @Update("update KSTB_SFZ_SQSHQFXX_FS set zzhkxlh = #{ywxtbh} where YWXTBH = #{ywxtbh} and code = 10")
    void updateZZHK_YWXTBH(String ywxtbh);


}