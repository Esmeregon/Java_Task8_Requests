import com.google.gson.Gson;
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

    private ArrayList<HashMap> orderList = new ArrayList<HashMap>();
    private HashMap<String,Object> listInfo = new HashMap();
    private HashMap<String,Object> orderInfo = new HashMap();
    private ArrayList<String> productList = new ArrayList<String>();
    private HashMap<String,Object> listInfo2 = new HashMap();
    private HashMap<String,Object> orderInfo2 = new HashMap();
    private ArrayList<String> productList2 = new ArrayList<String>();


    @Test
    public void test1(){

        productList.add("IPTV");
        productList.add("INTERNET");
        orderInfo.put("orderType", "sell");
        orderInfo.put("productList", productList);
        listInfo.put("orderId","152468");
        listInfo.put("orderInfo", orderInfo);

        productList2.add("IPTV");
        productList2.add("INTERNET");
        orderInfo2.put("orderType", "change");
        orderInfo2.put("productList", productList2);
        listInfo2.put("orderId","152469");
        listInfo2.put("orderInfo", orderInfo2);

        orderList.add(listInfo);
        orderList.add(listInfo2);

        Order order = new Order(customerLogin, orderList);
        Gson gson = new Gson();
        String json = gson.toJson(order);
        System.out.println(json);

        ResponseJson responseJson = new ResponseJson(json);
        responseJson = gson.fromJson(responseJson.send().asString(), ResponseJson.class);
        System.out.println(responseJson.getSuccess() +  "\n");
    }

    @Test
    public void test2(){
        String externalId = "178689818";
        String affiliateId = "SIB.NSK";
        String systemId = "SIB.NSK.STR";

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

        ResponseXml responseXml = new ResponseXml(requestBody);
        System.out.println(responseXml.send() + "\n");

        Document document = null;
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse(new ByteArrayInputStream(responseXml.send().getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        NodeList nodeList = document.getElementsByTagName("sec:SecretWord");
        String secretWord = nodeList.item(0).getTextContent();
        System.out.println("SecretWord: " + secretWord);
    }

}