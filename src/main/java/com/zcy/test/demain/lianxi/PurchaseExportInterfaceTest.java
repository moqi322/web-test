//package com.zcy.test.demain.lianxi;
//
//public class PurchaseExportInterfaceTest {
//    private String cookie = "Cookie: _zcy_log_client_uuid=3669e4f0-82c1-11ea-8037-7fe8a68b96c2; UM_distinctid=1719631a319331-018dcef29443d9-10376653-13c680-1719631a31a2e5; aid=430102; aid=430102; _dg_playback.bbc15f7dfd2de351.63bf=1; _dg_abtestInfo.bbc15f7dfd2de351.63bf=1; _dg_check.bbc15f7dfd2de351.63bf=-1; districtCode=439900; districtName=%E6%B9%96%E5%8D%97%E7%9C%81%E6%9C%AC%E7%BA%A7; _dg_id.bbc15f7dfd2de351.63bf=7747337fa944a892%7C%7C%7C1589794798%7C%7C%7C23%7C%7C%7C1591695109%7C%7C%7C1591695051%7C%7C%7C%7C%7C%7Cf70d9b8ff4a23a8a%7C%7C%7C%7C%7C%7C%7C%7C%7C0%7C%7C%7Cundefined; platform_code=zcy; user_type=01; session_application_code=zcy.industry.trade; SESSION=ODI3N2JkY2EtZGJmZS00NDVlLTkyODEtNjljYzRhNDA0ZmUz; uid=10007004009; tenant_code=339900; districtCode=339900; districtName=%E6%B5%99%E6%B1%9F%E7%9C%81%E6%9C%AC%E7%BA%A7";
//    private final int THREADS = 20;
//
//    /**
//     * 测试导出接口是否能够正常工作，同postman类似
//     *
//     * @Remark :
//     * 1、开发环境读取订单RPC因版本问题无法正确读取订单，因此这里使用test环境，但是由于测试环境开启服务器不只一台，
//     * 因此发送mq可能会发送到其它服务器，造成执行任务失败
//     * 2、最大发送次数可以通过在vm options中进行配置-Dhttp.maxConnections=你想要执行的最大次数。
//     */
//    @Test
//    public void testExport() throws InterruptedException {
//
//        CloseableHttpClient client = HttpClientBuilder.create().useSystemProperties().build();
//
//        HttpPost post = new HttpPost("http://localhost:9007/api/direct-purchase/purchase/export/create");
//        post.setHeader("Content-Type", "application/json");
//        post.setHeader("Cookie", cookie);
//
//        ExportPurchaseParam exportPurchaseParam = new ExportPurchaseParam();
//        WebPurchaseSearchParam webPurchaseSearchParam = new WebPurchaseSearchParam();
//
//        webPurchaseSearchParam.setTab(null);
//        webPurchaseSearchParam.setIndustryTag(2);
//        webPurchaseSearchParam.setQueryMenu(0);
//        exportPurchaseParam.setWebPurchaseSearchParam(webPurchaseSearchParam);
//
//        StringEntity stringEntity = new StringEntity(JSON.toJSONString(exportPurchaseParam), "UTF-8");
//        post.setEntity(stringEntity);
//
//        CyclicBarrier barrier = new CyclicBarrier(THREADS);
//        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
//
//        CountDownLatch latch = new CountDownLatch(THREADS);
//
//        for (int i = 1; i <= THREADS; i++) {
//            executorService.execute(() -> {
//                try {
//                    barrier.await();
//                    CloseableHttpResponse closeableHttpResponse = client.execute(post);
//                    System.out.println(closeableHttpResponse.getEntity());
//                    latch.countDown();
//                } catch (Exception e) {
//                    System.out.println(Throwables.getStackTrace(e));
//                }
//            });
//        }
//
//        latch.await();
//
//    }
//
//    /**
//     * 测试对于get接口的sentinel是否成功，一秒只反应一次请求
//     */
//    @Test
//    public void testPurchaseSentinel() throws InterruptedException {
//        CloseableHttpClient client = HttpClients.createDefault();
//
//        HttpPost post = new HttpPost("http://localhost:9007/api/direct-purchase/purchase/export/get");
//        post.setHeader("Content-Type", "application/json");
//        post.setHeader("Cookie", cookie);
//
//        BasePageSearchParam pageSearchParam = new BasePageSearchParam();
//        pageSearchParam.setPageNo(1);
//        pageSearchParam.setPageSize(10);
//
//        StringEntity stringEntity = new StringEntity(JSON.toJSONString(pageSearchParam), "UTF-8");
//        post.setEntity(stringEntity);
//
//        CyclicBarrier barrier = new CyclicBarrier(THREADS);
//        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
//
//        CountDownLatch latch = new CountDownLatch(THREADS);
//        for (int i = 1; i <= THREADS; i++) {
//            executorService.execute(() -> {
//                try {
//                    barrier.await();
//                    CloseableHttpResponse closeableHttpResponse = client.execute(post);
//                    Response response = JSON.parseObject(EntityUtils.toString(closeableHttpResponse.getEntity()), Response.class);
//                    //if (!response.isSuccess()) {
//                    System.out.println(JSON.toJSONString(response));
//                    //}
//                    latch.countDown();
//                } catch (Exception e) {
//                    System.out.println(Throwables.getStackTrace(e));
//                }
//            });
//        }
//
//        latch.await();
//    }
//
//}
//
