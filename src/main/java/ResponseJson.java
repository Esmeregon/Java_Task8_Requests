import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class ResponseJson {

    private String success;
    private String url = "https://reqbin.com/echo/post/json ";
    private String json;
    private Response response;


    public String getSuccess() {
        return success;
    }


    public ResponseJson(String json) {
        this.json = json;
    }


    Response send() {
        RequestSpecification request = RestAssured.given();
        request.body(json);
        request.contentType("application/json");
        response = request.post(url);
        return response;
    }
}