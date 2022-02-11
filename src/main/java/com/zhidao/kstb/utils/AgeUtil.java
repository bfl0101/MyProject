package com.zhidao.kstb.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AgeUtil {
    //计算周岁
    public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }
  //日期计算
    public static String getDate(String s, int type) throws ParseException {
        String dateStr ="";
        //给定模式(这里给定的模式须与给定日期字符串格式匹配)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
        Date date = sdf.parse(s);

        Calendar cal = Calendar.getInstance();
        System.out.println("日期转换："+date);
        cal.setTime(date);//设置起时间

        if (type == 1) {
            cal.add(Calendar.YEAR, 5);          //增加5年
            dateStr = sdf.format(cal.getTime());
        } else if (type == 2) {
            cal.add(Calendar.YEAR, 10);          //增加10年
            dateStr = sdf.format(cal.getTime());
        } else if (type == 3) {
            cal.add(Calendar.MONTH, 20);         //增加一个季度
            dateStr = sdf.format(cal.getTime());
        } else {
            dateStr="长期";
        }
        return dateStr;
    }
}
