package com.zhidao.kstb.controller;

import com.zhidao.kstb.dao.CZRK_CSSQSPXXDao;
import com.zhidao.kstb.model.CZRK_CSSQSPXX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CZRKController {

    @Autowired
    private CZRK_CSSQSPXXDao cssqspxxDao;

    @RequestMapping("/czrkin")//出生保存后台
    public String czrkinsert(@RequestBody CZRK_CSSQSPXX cssqspxx )  {
        String msg;
        try {
            cssqspxx.setXlh(cssqspxxDao.selectSequence("seq_czrk_cssqspxx_xlh.nextval"));
            cssqspxxDao.insertSelective(cssqspxx);
            msg = "10";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            msg = e.getMessage();
        }

        return msg;

    }
}
