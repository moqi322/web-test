package com.zcy.test.demain.lianxi;

import com.zcy.test.demain.parm.User;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class test009 {
    public static void main(String[] args) {
        String seq_datetime=null;
//        String.format(seq_datetime, new Object[] { DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")});

        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");

        String[] str = {"0","1","2","3","4","5","6","7","8","9","10","11"};
        int [] aa={1,1,4,3,5};
        int i = aa[0];
        System.out.println(i);
        String title = "【" + i + "审批】" + i + "待您审批,点击进入审批单.";
        System.out.println(title);
    }

}
