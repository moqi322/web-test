package com.zcy.test.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

/**
 * 常规post请求
 *    可以设置Header来伪装浏览器请求
 */
public class DoPOST {
    public static void main(String args[]) {
        try {
//            JSONObject obj = new JSONObject();
//            obj.put("app_name", "全民大讨论");
//            obj.put("app_ip", "10.21.243.234");
//            obj.put("app_port", 8080);
//            obj.put("app_type", "001");
//            obj.put("app_area", "asd");
//            System.out.println(obj);

            String  ss = "{\"requestname\":\"测试流程001\",\"creator\":\"111\",\"receiver\":\"111\",\"workflowname\":\"测试流程名称001\",\"pcurl\":\"http://www.baidu.com\",\"viewtype\":\"1\",\"appurl\":\"http://www.baidu.com\",\"syscode\":\"zcy\",\"nodename\":\"测试节点001\",\"receivedatetime\":\"2021-09-22 15:19:59\",\"isremark\":\"0\",\"createdatetime\":\"2021-09-22 15:20:12\",\"flowid\":\"001\"}";



            CloseableHttpClient httpclient = HttpClients.createDefault();


            HttpPost httpPost = new HttpPost("http://122.224.251.228:8081/rest/ofs/ReceiveRequestInfoByJson");
            httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");

            // 解决中文乱码问题
            StringEntity stringEntity = new StringEntity(ss, "UTF-8");
            stringEntity.setContentEncoding("UTF-8");

            httpPost.setEntity(stringEntity);

            // CloseableHttpResponse response =
            // httpclient.execute(httpPost);

            System.out.println("Executing request " + httpPost.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(final HttpResponse response)
                        throws ClientProtocolException, IOException {//
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {

                        HttpEntity entity = response.getEntity();

                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException(
                                "Unexpected response status: " + status);
                    }
                }
            };
            String responseBody = httpclient.execute(httpPost, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}