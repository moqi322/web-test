package com.zcy.test.demao;

import com.zcy.test.demain.zhuanjia.ExpertInformationDTO;
import com.zcy.test.demain.zhuanjia.OaLunZhengDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Dameo {
    public static void main(String[] args) {

        //stringchange();
        //lamdba();
       // extracted();
        ExpertInformationDTO expertInformationDTO=new ExpertInformationDTO();
        ExpertInformationDTO expertInformationDTO1=new ExpertInformationDTO();
        ExpertInformationDTO expertInformationDTO0=new ExpertInformationDTO();
        expertInformationDTO.setShoujihaoma(123);
        expertInformationDTO.setGongzuodanw("aa");
        expertInformationDTO0.setShoujihaoma(000);
        expertInformationDTO1.setZhuanjiaxingm("asa");

        List<ExpertInformationDTO> expertInformationDTOS = new ArrayList<>();
        expertInformationDTOS.add(expertInformationDTO);
        expertInformationDTOS.add(expertInformationDTO1);
       expertInformationDTOS.add(expertInformationDTO0);
        System.out.println(expertInformationDTOS);


        OaLunZhengDTO oaLunZhengDTO =new OaLunZhengDTO();
        oaLunZhengDTO.setZhuanjigxinxi(expertInformationDTOS);
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        String oaExtended;
//        System.out.println(String.format("%s/%s发起的%s%s/%s", sdf.format("ccc"),"aaa","bbb","cc"));
        //System.out.println(String.format("%s/%s发起的%s%s","dd","aaa","bbb","["+"cc"+"]"));





        if(oaLunZhengDTO.getZhuanjigxinxi().stream().count()<2){
            System.out.println("0890dsdu");

        }else {
            System.out.println("32342");

        }
        System.out.println("------");

    }


}
