package com.zcy.test.demain.zhuanjia;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OaLunZhengDTO implements Serializable {

    /**
     * 专家论证
     *     论证意见
     *      论证信息
     *      附件上传
     */

    private String lunzhengyijian;

    private List<ExpertInformationDTO> zhuanjigxinxi;

    private List<AttachmentDTO> fujianshangchuan;



}
