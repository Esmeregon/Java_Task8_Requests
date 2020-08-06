import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private String customerLogin;
    private ArrayList<HashMap> orderList;

    public Order(String customerLogin, ArrayList<HashMap> orderList) {
        this.customerLogin = customerLogin;
        this.orderList = orderList;
    }
}
/*
    public Order(String customerLogin, ArrayList<HashMap> orderList, HashMap<String, Object> orderInfo, ArrayList<String> productList) {
        this.customerLogin = customerLogin;
        this.orderList = orderList;
        this.orderInfo = orderInfo;
        this.productList = productList;
    }
}
*/
    /*
        productList.add("IPTV");
                productList.add("INTERNET");
//генерим orderInfo
                orderInfo.put("orderType", "sell");
                orderInfo.put("productList", productList);
//генерим orderList
                listInfo.put("orderId","152468");
                listInfo.put("orderInfo", orderInfo);
*/
/*
{
   "customerLogin":"customer",
   "orderList":[
      {
         "orderId":"152468",
         "orderInfo":{
            "orderType":"sell",
            "productList":[
               "IPTV",
               "INTERNET"
            ]
         }
      },
      {
         "orderId":"152469",
         "orderInfo":{
            "orderType":"change",
            "productList":[
               "IPTV",
               "INTERNET"
            ]
         }
      }
   ]
}
*/