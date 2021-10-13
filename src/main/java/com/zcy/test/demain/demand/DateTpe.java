package com.zcy.test.demain.demand;

import lombok.Data;

import java.io.Serializable;
@Data
public class DateTpe implements Serializable {
    /**
     * 统一待办中心
     */
    private  String sUse;

    /**
     * 异构系统
     */
    private  String OtherSys;

    /**
     * 流程类型
     */
    private  String WfType;

    /**
     * 流程数据
     */
    private  String WfData;

    /**
     * 参数设置
     */
    private  String SetParam;

}
