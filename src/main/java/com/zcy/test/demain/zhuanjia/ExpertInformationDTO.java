package com.zcy.test.demain.zhuanjia;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExpertInformationDTO implements Serializable {
    /**
     * 专家信息
     */

    private  long   shoujihaoma;

    private  String gongzuodanw;

    private  String zhuanjiaxingm;

    private String zhichen;

}
