package com.zcy.test.util;

import cn.gov.zcy.workflow.valid.ValidateUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class StringUtils {


        private static final DecimalFormat DF = new DecimalFormat("#.00");
        private static final DecimalFormat DF_0 = new DecimalFormat("");
        public static final String EMPTY_STRING = "";
        //换行符
        public static final String lineSeparator = System.getProperty("line.separator");
        //邮箱的正则表达式
        public static final String REGEX_EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        /**
         * 手机号的正则表达式(用于匹配)
         */
        public static final String REGEX_MOBILE_MATCH = "^1[0123456789]\\d{9}$";
        /**
         * 手机号的正则表达式(用于抓取)
         */
        public static final String REGEX_MOBILE_GRAB = "(?<!\\d)(?:(?:1[358]\\d{9})|(?:861[34578]\\d{9}))(?!\\d)";
        /**
         * URL的正则表达式(用于匹配)
         */
        public static final String REGEX_URL_MATCH = "\\w+://.+";
        /**
         * URL的正则表达式(用于抓取)
         */
        public static final String REGEX_URL_GRAB = "([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)";
        /**
         * a标签的正则表达式(用于抓取)
         */
        public static final String REGEX_TAG_A_GRAB = "<a[^>]+?href=[\"\']?([^\"\']+)[\"\']?[^>]*>([^<]+)</a>";
        /**
         * a标签href中内容的正则表达式(用于抓取)
         */
        public static final String REGEX_TAG_A_HREF_GRAB = "href=[\"\'][\\w?/\\.]*\"";
        /**
         * a标签中的文本内容的正则表达式(用于抓取)
         */
        public static final String REGEX_TAG_A_TEXT_GRAB = "<a.*?>(.*)</a>";
        /**
         * 粗略校验身份证号的正则
         */
        public static final String REGEX_SIMPLE_CHECK_IDCARD = "^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)$";

        public static String join(Collection<?> list, String separator){
            if(!ValidateUtils.isEmpty(list)){
                StringBuilder sb = new StringBuilder();
                int i = 0;
                for (Object o : list) {
                    if(i++ > 0){
                        sb.append(separator);
                    }
                    sb.append(o.toString());
                }
                return sb.toString();
            }else{
                return "";
            }
        }

        public static String join(Object[] list, String separator){
            if(!ValidateUtils.isEmpty(list)){
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < list.length; i++) {
                    if(i > 0){
                        sb.append(separator);
                    }
                    sb.append(list[i]);
                }
                return sb.toString();
            }else{
                return "";
            }
        }

        public static void join(StringBuilder sb, List<?> list, String separator){
            if(!ValidateUtils.isEmpty(list)){
                for (int i = 0; i < list.size(); i++) {
                    if(i > 0){
                        sb.append(separator);
                    }
                    sb.append(list.get(i));
                }
            }
        }

        public static void join(StringBuilder sb, Object[] list, String separator){
            if(!ValidateUtils.isEmpty(list)){
                for (int i = 0; i < list.length; i++) {
                    if(i > 0){
                        sb.append(separator);
                    }
                    sb.append(list[i]);
                }
            }
        }

        /**
         * 获取手机号字符串
         * @author guangyu 598506170@qq.com
         * @date 2017年9月23日 下午5:01:27
         * @param content
         * @return
         */
        public static List<String> grabMobile(String content){
            return grabInfoListByRegex(cn.gov.zcy.workflow.valid.StringUtils.REGEX_MOBILE_GRAB, content);
        }

        public static String upperCaseStr(String fldName) {
            String first = fldName.substring(0, 1).toUpperCase();
            String rest = fldName.substring(1, fldName.length());
            String newStr = first + rest;
            return newStr;
        }

        //汉语中数字大写
        private static final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆","伍", "陆", "柒", "捌", "玖" };
        //汉语中货币单位大写，这样的设计类似于占位符
        private static final String[] CN_UPPER_MONETRAY_UNIT = { "分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾","佰", "仟" };
        //特殊字符：整
        private static final String CN_FULL = "整";
        //特殊字符：负
        private static final String CN_NEGATIVE = "负";
        //金额的精度，默认值为2
        private static final int MONEY_PRECISION = 2;
        //特殊字符：零元整
        private static final String CN_ZEOR_FULL = "零元" + CN_FULL;

        /**
         * 把输入的金额转换为汉语中人民币的大写
         * @return
         */
        public static String toChineseUpperCase(BigDecimal numberOfMoney){
            StringBuilder sb = new StringBuilder();
            // -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
            // positive.
            int signum = numberOfMoney.signum();
            // 零元整的情况
            if (signum == 0) {
                return CN_ZEOR_FULL;
            }
            //这里会进行金额的四舍五入
            long number = numberOfMoney.movePointRight(MONEY_PRECISION)
                    .setScale(0, 4).abs().longValue();
            // 得到小数点后两位值
            long scale = number % 100;
            int numUnit = 0;
            int numIndex = 0;
            boolean getZero = false;
            // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
            if (!(scale > 0)) {
                numIndex = 2;
                number = number / 100;
                getZero = true;
            }
            if ((scale > 0) && (!(scale % 10 > 0))) {
                numIndex = 1;
                number = number / 10;
                getZero = true;
            }
            int zeroSize = 0;
            while (true) {
                if (number <= 0) {
                    break;
                }
                // 每次获取到最后一个数
                numUnit = (int) (number % 10);
                if (numUnit > 0) {
                    if ((numIndex == 9) && (zeroSize >= 3)) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                    }
                    if ((numIndex == 13) && (zeroSize >= 3)) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                    }
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                    getZero = false;
                    zeroSize = 0;
                } else {
                    ++zeroSize;
                    if (!(getZero)) {
                        sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                    }
                    if (numIndex == 2) {
                        if (number > 0) {
                            sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                        }
                    } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                    getZero = true;
                }
                // 让number每次都去掉最后一个数
                number = number / 10;
                ++numIndex;
            }
            // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
            if (signum == -1) {
                sb.insert(0, CN_NEGATIVE);
            }
            // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
            if (!(scale > 0)) {
                sb.append(CN_FULL);
            }
            return sb.toString();
        }

        /**
         * 将double后面的0去掉
         * @return
         */
        public static String removeDoubleLast0(double num){
            String num_ = DF_0.format(num);
            if(num_.contains(".") && num_.length() > num_.indexOf(".")+3){
                num_ = DF.format(num);
            }
            if(num_.contains(",")){
                num_ = num_.replace(",","");
            }
            if(num_.startsWith(".")){
                num_ = 0+num_;
            }
            if("0.0".equals(num_) || "0.00".equals(num_)){
                num_ = "0";
            }
            return num_;
        }

        /**
         * 四舍五入保留两位小数
         * @author guangyu 598506170@qq.com
         * @date 2017年10月6日 上午9:11:33
         * @param num
         * @return
         */
        public static String toRetainDecimal(double num){
            return String.format("%.2f",num);
        }

        /**
         * 保留两位小数
         * @param num
         * @return
         */
        public static String toRetainTwoDecimal(double num){
            String num_ = DF_0.format(num);
            if(num_.contains(".") && num_.length() > num_.indexOf(".")+3){
                num_ = DF.format(num);
            }else{
                if(!num_.contains(".")){
                    num_ = num_+".00";
                }else{
                    num_ = DF.format(num);
                }
            }
            if(num_.contains(",")){
                num_ = num_.replace(",","");
            }
            if(num_.startsWith(".")){
                num_ = 0+num_;
            }
            if("0.0".equals(num_) || "0.00".equals(num_)){
                num_ = "0.00";
            }
            return num_;
        }

        /**
         * 保留两位小数
         * @param num
         * @return
         */
        public static String toRetainTwoDecimal(String numString){
            double num = 0;
            if (!ValidateUtils.isEmpty(numString)) {
                try {
                    num = Double.parseDouble(numString);
                } catch (NumberFormatException e) {
                    num = 0;
                }
            }
            String num_ = DF_0.format(num);
            if(num_.contains(".") && num_.length() > num_.indexOf(".")+3){
                num_ = DF.format(num);
            }else{
                if(!num_.contains(".")){
                    num_ = num_+".00";
                }else{
                    num_ = DF.format(num);
                }
            }
            if(num_.contains(",")){
                num_ = num_.replace(",","");
            }
            if(num_.startsWith(".")){
                num_ = 0+num_;
            }
            if("0.0".equals(num_) || "0.00".equals(num_)){
                num_ = "0.00";
            }
            return num_;
        }

        /**
         * 从文本中抓取url
         * @author guangyu 598506170@qq.com
         * @date 2017年9月23日 下午3:54:27
         * @param content
         * @return
         */
        public static List<String> grabUrlList(String content){
            return grabInfoListByRegex(cn.gov.zcy.workflow.valid.StringUtils.REGEX_URL_GRAB, content);
        }

        /**
         * 正则匹配抓取数据列表
         * @author guangyu 598506170@qq.com
         * @date 2017年9月24日 下午12:10:46
         * @param regex
         * @param content
         * @return
         */
        public static List<String> grabInfoListByRegex(String regex,String content){
            List<String> resultList = new ArrayList<String>();
            if(ValidateUtils.isEmpty(content)){
                return resultList;
            }
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(content);
            while(matcher.find()){
                resultList.add(matcher.group());
            }
            return resultList;
        }

        public static List<String> grabTagAList(String content){
            return grabInfoListByRegex(cn.gov.zcy.workflow.valid.StringUtils.REGEX_TAG_A_GRAB, content);
        }

        public static List<String> grabTagAHrefList(String tagA){
            List<String> resultList = new ArrayList<String>();
            if(ValidateUtils.isEmpty(tagA)){
                return resultList;
            }
            Pattern pattern = Pattern.compile(cn.gov.zcy.workflow.valid.StringUtils.REGEX_TAG_A_HREF_GRAB);
            Matcher matcher = pattern.matcher(tagA);
            while(matcher.find()){
                String result = matcher.group();
                resultList.add(result.substring(6, result.length()-1));
            }
            return resultList;
        }

        public static List<String> grabTagATextList(String tagA){
            List<String> resultList = new ArrayList<String>();
            if(ValidateUtils.isEmpty(tagA)){
                return resultList;
            }
            Pattern pattern = Pattern.compile(cn.gov.zcy.workflow.valid.StringUtils.REGEX_TAG_A_TEXT_GRAB);
            Matcher matcher = pattern.matcher(tagA);
            while(matcher.find()){
                resultList.add(matcher.group(1));
            }
            return resultList;
        }

        public static String formatString(String str,Object[] arguments){
            if(arguments!=null && arguments.length>0){
                return String.format(str, arguments);
            }else{
                return str;
            }
        }

        public static int getKeyNum(String text,String key) {
            int num = 0;
            int index = -1;
            while((index = text.indexOf(key,index)) != -1) {
                num ++;
                index ++;
            }
            return num;
        }

        public static boolean isEmpty(String str){
            return str == null || str.length() == 0;
        }

    }


