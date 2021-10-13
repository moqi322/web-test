//package com.zcy.test.demain.teatdemand;
//
//import org.assertj.core.util.Lists;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class ZiChanRuKuTest {
//        public static void main(String[] args) {
//
//            AssetStockInItemDTO item = new AssetStockInItemDTO();
//            AssetStockInItemDTO item1 = new AssetStockInItemDTO();
//            item.setName("1");
//            item.setPrice(233);
//            item1.setPrice(333);
//            item1.setName("ceshi");
//            List<AssetStockInItemDTO> TOS = Arrays.asList(item, item1);
//            AssetStockInOrderSearchResultDTO assetDTO = new AssetStockInOrderSearchResultDTO();
//            assetDTO.setItems(TOS);
//            List<AssetStockInOrderSearchResultDTO> list= Arrays.asList(assetDTO);
//
//            //以商品纬度重新生成list
//            List<AssetStockInOrderSearchResultDTO> newList = new ArrayList<>();
//            for(AssetStockInOrderSearchResultDTO searchResultDTO : list){
//                int itemCount = searchResultDTO.getItems().size();
//                for(int i=0; i<itemCount; i++){
//                    AssetStockInOrderSearchResultDTO resultDTO = new AssetStockInOrderSearchResultDTO();
//                    BeanUtil.copyPropertiesIgnoreNull(searchResultDTO,resultDTO);
//                    resultDTO.setItems(Lists.newArrayList(searchResultDTO.getItems().get(i)));
//                    newList.add(resultDTO);
////                System.out.println(resultDTO);
//                }
//            }
//            newList.forEach(System.out::println);
//        }
//    }
//
//}
