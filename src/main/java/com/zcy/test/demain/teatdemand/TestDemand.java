package com.zcy.test.demain.teatdemand;

import com.zcy.test.demain.parm.CommodityEO;
import com.zcy.test.demain.util.NumUtil;
import org.springframework.util.StringUtils;

public class TestDemand {
    public static void main(String[] args) {

        StringBuilder errReason = new StringBuilder();
        String  errorReason = "";
        int errorType = 0;

        CommodityEO commodityEO= new CommodityEO();
        commodityEO.setUnitePrice(String.valueOf(177.55));

        if (commodityEO.getUnitePrice().length() - (commodityEO.getUnitePrice().indexOf(".") + 1)>2){
            System.out.println("=-----");
        }
        Double unitPrice = NumUtil.string2Num(commodityEO.getUnitePrice(), Double.class);
        if (StringUtils.isEmpty(commodityEO.getUnitePrice()) || unitPrice == null) {
            errorType = 2;
            errorReason = "excel数据必填项不能为空";
            errReason.append("预算单价不能为空;");
            System.out.println(errorReason);
            System.out.println(errReason);
        }
        commodityEO.setUnitePrice1((long)(unitPrice * 100));
        commodityEO.setTotalPrice((long)(4*unitPrice * 100));
        System.out.println(commodityEO);

        Double d = 1.23;
//float d = 3.12f;



        String str="6.33";

       System.out.println(str.length() - (str.indexOf(".") + 1));


        //int b = unitPrice.tostring().Substring(unitPrice.tostring().IndexOf(".")).Length -1;



    }
}
