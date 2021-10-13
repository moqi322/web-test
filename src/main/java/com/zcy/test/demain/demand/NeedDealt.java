package com.zcy.test.demain.demand;


import lombok.Data;

import java.io.Serializable;

@Data
public class NeedDealt implements Serializable {
    /**
     * 异构系统标识
     */
    private String syscode	;

    /**
     * // 流程实例id
      */
    private String flowi ;

    /**
     * 标题
     */
    private String requestname	;

    /**
     * 流程类型名称
     */
    private String  workflowname ;

    /**
     * 步骤名称（节点名称）
     */
    private String  nodename ;

    /**
     *  PC地址     应该是跳转地址
     */
    private String  pcurl ;

    /**
     *  APP地址
     */
    private String  appurl ;

    /**
     *   流程处理状态  0：待办  2：已办  4：办结 8：抄送 目前只知两种状态 处理 标记已处理
     */
    private String  isremark ;

    /**
     *     流程查看状态 0：未读 1：已读;  感觉可以根据上面做判断 待办 已办
     */
    private String  viewtype ;

    /**
     *创建人（原值）
     */
    private String  creator ;

    /**
     * 创建日期时间
     */
    private String  createdatetime ;

    /**
     * 接收人（原值）	 确认下
     */
    private String  receiver ;

    /**
     * 接收日期时间
     */
    private String  receivedatetime ;

    /**
     * 时间戳字段，客户端使用线程调用接口的时候
     * 根据此字段判断是否需要更新数据，防止后发的请求数据被之前的覆盖
     */
    private String  receivets ;



}
