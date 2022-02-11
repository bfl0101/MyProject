package com.zhidao.kstb.hjlzm.service;


import com.zhidao.kstb.hjlzm.dao.HjzmDao;
import com.zhidao.kstb.hjlzm.model.come.HjzmPackeage;
import com.zhidao.kstb.hjlzm.model.come.HjzmRecord;
import com.zhidao.kstb.hjlzm.model.go.no.ErrData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

@Component
@Transactional
public class CaoZuo {
    @Autowired
    HjzmDao hjzmDao;

    public String shenQing(HjzmPackeage hjzmPackeage, ArrayList<ErrData> list) throws SQLException {

        int x=0;
        for(HjzmRecord hjzmRecord:hjzmPackeage.getData()){
            if("011007".equals(hjzmRecord.getSid())){
                ErrData err=new ErrData();
                err.setNo(hjzmRecord.getNo());
                err.setSid(hjzmRecord.getSid());
                list.add(err);
                x+=hjzmDao.insertSqkj(hjzmRecord);
                err.setCode("10");
                err.setMsg("接口成功调用");
            }else if("010100".equals(hjzmRecord.getSid())){
                ErrData err=new ErrData();
                err.setNo(hjzmRecord.getNo());
                err.setSid(hjzmRecord.getSid());
                list.add(err);
                x+=hjzmDao.insertClxx(hjzmRecord, UUID.randomUUID().toString());
                err.setCode("10");
                err.setMsg("接口成功调用");
            }
        }


        return x+"";
    }
    public String fanKui(HjzmPackeage hjzmPackeage, ArrayList<ErrData> list)throws SQLException{

        System.out.println(hjzmPackeage);
        int x=0;
        for(HjzmRecord hjzmRecord:hjzmPackeage.getData()){
            if("011009".equals(hjzmRecord.getSid())){
                ErrData err=new ErrData();
                err.setNo(hjzmRecord.getNo());
                err.setSid(hjzmRecord.getSid());
                list.add(err);
                x+=hjzmDao.insertKjxx(hjzmRecord);
                err.setCode("10");
                err.setMsg("接口成功调用");
            }else if("011008".equals(hjzmRecord.getSid())){
                ErrData err=new ErrData();
                err.setNo(hjzmRecord.getNo());
                err.setSid(hjzmRecord.getSid());
                list.add(err);
                x+=hjzmDao.insertBykj(hjzmRecord);
                err.setCode("10");
                err.setMsg("接口成功调用");
            }else if("010100".equals(hjzmRecord.getSid())){
                ErrData err=new ErrData();
                err.setNo(hjzmRecord.getNo());
                err.setSid(hjzmRecord.getSid());
                list.add(err);
                x+=hjzmDao.insertClxx(hjzmRecord, UUID.randomUUID().toString());
                err.setCode("10");
                err.setMsg("接口成功调用");
            }
        }


        return x+"";
    }


}
