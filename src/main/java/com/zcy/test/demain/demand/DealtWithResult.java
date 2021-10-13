package com.zcy.test.demain.demand;

import lombok.Data;

import java.io.Serializable;
@Data
public class DealtWithResult implements Serializable {
    /**
     * 异构系统标识  应该是政采云标示
     */
    private  String syscode;

    /**
     *
     */
    private  DateTpe dateTpe;

    /**
     *   1：成功
     * 0：失败
     */
    private  Integer operResult;

    /**
     * 错误信息
     */
    private String message;
}
