package com.zcy.test.demain.teatdemand.first;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

public class ShouZiMuChuan {

        /**
         * 取得给定汉字串的首字母串,即声母串
         * Title: ChineseCharToEn
         * @date 2004-02-19 注：只支持GB2312字符集中的汉字
         */
        public  static final class ChineseCharToEn {
            private final static int[] li_SecPosValue = {1601, 1637, 1833, 2078, 2274,
                    2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858,
                    4027, 4086, 4390, 4558, 4684, 4925, 5249, 5590};
            private final static String[] lc_FirstLetter = {"A", "B", "C", "D", "E",
                    "F", "G", "H", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
                    "T", "W", "X", "Y", "Z"};

            /**
             * 取得给定汉字串的首字母串,即声母串
             *
             * @param str 给定汉字串
             * @return 声母串
             */
            public String getAllFirstLetter(String str) {
                if (str == null || str.trim().length() == 0) {
                    return "";
                }
                String _str = "";
                for (int i = 0; i < str.length(); i++) {
                    _str = _str + this.getFirstLetter(str.substring(i, i + 1));
                }
                return _str;
            }

            /**
             * 取得给定汉字的首字母,即声母
             *
             * @param chinese 给定的汉字
             * @return 给定汉字的声母
             */
            public String getFirstLetter(String chinese) {
                if (chinese == null || chinese.trim().length() == 0) {
                    return "";
                }
                chinese = this.conversionStr(chinese, "GB2312", "ISO8859-1");
                if (chinese.length() > 1) // 判断是不是汉字
                {
                    int li_SectorCode = (int) chinese.charAt(0); // 汉字区码
                    int li_PositionCode = (int) chinese.charAt(1); // 汉字位码
                    li_SectorCode = li_SectorCode - 160;
                    li_PositionCode = li_PositionCode - 160;
                    int li_SecPosCode = li_SectorCode * 100 + li_PositionCode; // 汉字区位码
                    if (li_SecPosCode > 1600 && li_SecPosCode < 5590) {
                        for (int i = 0; i < 23; i++) {
                            if (li_SecPosCode >= li_SecPosValue[i]
                                    && li_SecPosCode < li_SecPosValue[i + 1]) {
                                chinese = lc_FirstLetter[i];
                                break;
                            }
                        }
                    } else // 非汉字字符,如图形符号或ASCII码
                    {
                        chinese = this.conversionStr(chinese, "ISO8859-1", "GB2312");
                        chinese = chinese.substring(0, 1);
                    }
                }
                return chinese;
            }

            /**
             * 字符串编码转换
             *
             * @param str           要转换编码的字符串
             * @param charsetName   原来的编码
             * @param toCharsetName 转换后的编码
             * @return 经过编码转换后的字符串
             */
            private String conversionStr(String str, String charsetName, String toCharsetName) {
                try {
                    str = new String(str.getBytes(charsetName), toCharsetName);
                } catch (UnsupportedEncodingException ex) {
                    System.out.println("字符串编码转换异常：" + ex.getMessage());
                }
                return str;
            }


            //
//
            //方法 一
//System.currentTimeMillis();
////方法 二
//Calendar.getInstance().getTimeInMillis();
////方法 三
//new Date().getTime();
            public static void main(String[] args) {
               // ShouZiMuChuan.ChineseCharToEn cte = .ChineseCharToEn();
                //FirstLetterUtil.ChineseCharToEn cte = new FirstLetterUtil.ChineseCharToEn();
                //new ShouZiMuChuan().C
                Calendar cal = Calendar.getInstance();

                ChineseCharToEn cte = new ChineseCharToEn();
               // Calendar cal = Calendar.getInstance();
//                String str = "1000"; //123456789
//                if (str.length() < 9) {
//                    while (str.length()<9){
//                        StringBuffer sb = new StringBuffer();
//                        sb.append("0").append(str);// 左补0
//                        str = sb.toString();
//                    }
//                }
                String aa="细腻s";
                if (aa.length()<4){
                    System.out.println(cte.getAllFirstLetter(aa)+"-"+cal.get(Calendar.YEAR)+"-"+String.format("%09d", 1234567890));
                }else {
                    System.out.println("获取拼音首字母："+ cte.getAllFirstLetter(aa).substring(0,4)+"-"+cal.get(Calendar.YEAR)+"-"+String.format("%09d", 1234567890));
                }

               // System.out.println(String.format("%09d", 256));
               // String docNo=cte.getAllFirstLetter("单一来源申请".substring(0, 4) + "-" + cal.get(Calendar.YEAR)) + "-" + String.format("%09d", identityFactory.next(IDENTITY)+"");
                //System.out.println(cte.getAllFirstLetter("合同"));
               // System.out.println("获取拼音首字母："+ cte.getAllFirstLetter("yy合同会签").substring(0,4)+"-"+cal.get(Calendar.YEAR)+"-"+String.format("%09d", 1234567890));
               // System.out.println("获取拼音首字母："+ cte.getAllFirstLetter("yy").substring(0,4)+"-"+cal.get(Calendar.YEAR)+"-"+String.format("%09d", 1234567890));
                //System.out.println("获取拼音首字母：" + cte.getAllFirstLetter("合同会签都是打电话叫此地v").substring(0,4)+ "-" + cal.get(Calendar.YEAR) + "-" + str);
               //System.out.println(docNo);

            }
        }
    }