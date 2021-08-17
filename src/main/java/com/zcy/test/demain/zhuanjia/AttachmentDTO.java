package com.zcy.test.demain.zhuanjia;
import lombok.Data;

import java.io.Serializable;

@Data
public class AttachmentDTO implements Serializable {

    /**
     * 专家论证中  附件上传信息
     */

    private String name;

    private String fileId;

    private String status;


}
