package com.zcy.test.demain.lianxi;

public class IndexOf {
    public static void main(String[] args) {
        String aa= "00.2";
        if (aa.indexOf(".")>0) {
            if (aa.length() - (aa.indexOf(".") + 1)>2){
                System.out.println("检验判断");
            }else {
                System.out.println("有小数位");
            }
        }
//        String substring = aa.substring(0,aa.indexOf("."));
//        String str2 = aa.substring(aa.substring(0,aa.indexOf(".")).length() + 1 ,aa.length());
//        if (str2.length()<=2) {
//            System.out.println("11");
//        }
//
//       // commodityEO.getUnitePrice().substring(commodityEO.getUnitePrice().substring(0,commodityEO.getUnitePrice().indexOf(".")).length() + 1 , commodityEO.getUnitePrice().length()).length() > 2
//
//            System.out.println(aa.indexOf(".")+1);
//        System.out.println(aa.length());
//        System.out.println(substring);
//        System.out.println(str2);
//        if (str2.length() > 2 ){
//            System.out.println("超出了");
//        }else {
//            System.out.println("可以提交");
//        }

//        if (commodityEO.getUnitePrice().length() - (commodityEO.getUnitePrice().indexOf(".") + 1)>2){

    }
}
