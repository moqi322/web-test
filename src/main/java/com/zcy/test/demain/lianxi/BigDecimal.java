package com.zcy.test.demain.lianxi;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.zcy.test.util.JSONUtil;
import io.terminus.common.utils.JsonMapper;
import org.apache.poi.hssf.record.DVALRecord;
import org.json.JSONObject;
import org.springframework.util.CollectionUtils;
import sun.security.ssl.ProtocolVersion;

import java.io.*;
import java.util.*;

import static io.terminus.common.utils.JsonMapper.JSON_NON_EMPTY_MAPPER;

public class BigDecimal {
    private static Object File;
    public static final JavaType MAP_OF_STRING =
            JSON_NON_EMPTY_MAPPER.createCollectionType(Map.class, String.class, Object.class);

    public static void main(String[] args) throws  IOException {

//                // 缓冲字节流，提高了效率
                BufferedOutputStream bis = new BufferedOutputStream(new FileOutputStream("/Users/zcy/Desktop/aa", true));

                // 要写入的字符串
                String string = "松下问童子，言师采药去。只在此山中，云深不知处。____haifa";
                // 写入文件
                bis.write(string.getBytes());
                // 关闭流
                bis.close();


                BufferedInputStream fis = new BufferedInputStream(new FileInputStream("/Users/zcy/Desktop/aa"));

                // 一次性取多少个字节
                byte[] bytes = new byte[1024];
                // 用来接收读取的字节数组
                StringBuilder sb = new StringBuilder();
                // 读取到的字节数组长度，为-1时表示没有数据
                int length = 0;
                // 循环取数据
                while ((length = fis.read(bytes)) != -1) {
                    // 将读取的内容转换成字符串
                    sb.append(new String(bytes, 0, length));
                }
                // 关闭流
                fis.close();

             System.out.println(sb);

            // 创建字符缓冲流
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/zcy/Desktop/aa",true));







    }
}
