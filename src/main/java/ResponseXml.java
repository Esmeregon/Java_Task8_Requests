import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ResponseXml {
    private String requestBody;
    private String url = "http://10.28.43.17:28080/aifemulator/integration/SecretWordService?WSDL";
    private Response response;
    private String text;

    public ResponseXml(String requestBody) {
        this.requestBody = requestBody;
    }

    String send() {
        RequestSpecification request = RestAssured.given();
        request.body(requestBody);
        request.contentType("text/xml");
        Response response = request.post(url);
        text = response.getBody().asString();
        return text;
    }
}
