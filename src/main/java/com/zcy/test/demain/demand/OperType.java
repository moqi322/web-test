package com.zcy.test.demain.demand;

import lombok.Data;

import java.io.Serializable;
@Data
public class OperType implements Serializable {
    /**
     * 自动创建
     */
    private String AutoNew;

    /**
     * 新建
     */
    private String New;

    /**
     * 自动更新
     */
    private String AutoEdit;

    /**
     * 编辑
     */
    private String Edit;

    /**
     * 删除
     */
    private String Del;

    /**
     * 检测
     */
    private String Check;

    /**
     * 设置
     */
    private String Set;

}
