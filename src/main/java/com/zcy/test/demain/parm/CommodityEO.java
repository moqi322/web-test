package com.zcy.test.demain.parm;

import lombok.Data;

import java.io.Serializable;


/**
 * @author fanxiaozhen
 */
@Data
public class CommodityEO implements Serializable {

    private static final long serialVersionUID = -2505848637901626061L;
    private String unitePrice;

    /**
     * 预算单价
     */
    private Long unitePrice1;

    /**
     * 预算总价
     */
    private Long totalPrice;

}
