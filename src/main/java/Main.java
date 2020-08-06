import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    private String customerLogin = "customer";

    //private String url = "https://reqbin.com/echo/post/json ";


    private ArrayList<HashMap> orderList = new ArrayList<HashMap>();
    private HashMap<String,Object> listInfo = new HashMap();
    private HashMap<String,Object> orderInfo = new HashMap();
    private ArrayList<String> productList = new ArrayList<String>();
    private HashMap<String,Object> listInfo2 = new HashMap();
    private HashMap<String,Object> orderInfo2 = new HashMap();
    private ArrayList<String> productList2 = new ArrayList<String>();


    @Test
    public void test1(){
        String url = "https://reqbin.com/echo/post/json ";
//Первая HashMap
//генерим(наполняем) productList
        productList.add("IPTV");
        productList.add("INTERNET");
//генерим orderInfo
        orderInfo.put("orderType", "sell");
        orderInfo.put("productList", productList);
//генерим orderList
        listInfo.put("orderId","152468");
        listInfo.put("orderInfo", orderInfo);

//        Order order = new Order(customerLogin, orderList);



//Вторая HashMap
//генерим(наполняем) productList
        productList2.add("IPTV");
        productList2.add("INTERNET");
//генерим orderInfo
        orderInfo2.put("orderType", "change");
        orderInfo2.put("productList", productList2);
//генерим orderList
        listInfo2.put("orderId","152469");
        listInfo2.put("orderInfo", orderInfo2);


//наполняем orderList
        orderList.add(listInfo);
        orderList.add(listInfo2);


//        Order order2 = new Order(customerLogin, orderList);
        Order order = new Order(customerLogin, orderList);
        Gson gson = new Gson();
        String json = gson.toJson(order);
        System.out.println(json);


        RequestSpecification request = RestAssured.given();
        request.body(json);
        request.contentType("application/json");
        Response response = request.post(url);
        //System.out.println(response.asString());
        ResponseJson responseJson = gson.fromJson(response.asString(), ResponseJson.class);
        System.out.println(responseJson.getSuccess());
    }



/*
{
   "customerLogin":"customer",
   "orderList":[
      { //круг три
         "orderId":"152468",

         //круг два
         "orderInfo":{
            "orderType":"sell",
           //круг один
            "productList":[
               "IPTV",
               "INTERNET"
            ]
           //круг один
         }
         //круг два
      },
      //круг три
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

    @Test
    public void test2(){
        String externalId = "178689818";
        String affiliateId = "SIB.NSK";
        String systemId = "SIB.NSK.STR";
        String url = "http://10.28.43.17:28080/aifemulator/integration/SecretWordService?WSDL";
        String requestBody= "<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "   <S:Body>\n" +
                "      <ns2:GetSecretWord xmlns:ns3=\"http://www.rt.ru/integration/messages/fault\" xmlns:ns2=\"http://www.rt.ru/integration/messages/crm/secretword\" xmlns=\"http://www.rt.ru/integration/messages/core\">\n" +
                "         <ns2:Destination>\n" +
                "         <AffiliateId>"+ affiliateId +"</AffiliateId>\n" +
                "         <SystemId>" + systemId + "</SystemId>\n" +
                "         </ns2:Destination>\n" +
                "         <ns2:ExternalId>" + externalId +"</ns2:ExternalId>\n" +
                "      </ns2:GetSecretWord>\n" +
                "   </S:Body>\n" +
                "</S:Envelope>";

        /*
        System.out.println(json);
        RequestSpecification request = RestAssured.given();
        request.body(json);
        request.contentType("application/json");
        Response response = request.post(url);
         */

        RequestSpecification request = RestAssured.given();
        request.body(requestBody);
        //System.out.println(requestBody + "\n");
        //request.header("Content-Type", "application/json");
        request.contentType("text/xml");
        Response response = request.post(url);
        String text = response.getBody().asString();
        System.out.println(text + "\n");

        Document document = null;
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse(new ByteArrayInputStream(text.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        NodeList nodeList = document.getElementsByTagName("sec:SecretWord");
        String secretWord = nodeList.item(0).getTextContent();
        System.out.println("SecretWord: " + secretWord);


        //System.out.println(document);
        //NodeList nodeList = document.getElementsByTagName("ns2:weight");

        //nodeList.item(0).getTextContent();
    }


}