package com.zcy.test.demain.demand;

import lombok.Data;

@Data
public class AccessAppKeyResult {
    /**
     * 给接入方颁发的key，用作身份唯一表示
     */
    private String appKey;

    /**
     * cehsi可以删除
     */
    private String privatekey;
}
