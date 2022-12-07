package api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.Assert;

//Singleton pattern
public class RestApi {

    private static RestApi restApi;
    private static String baseUri;
    private static RequestSpecification request;


    private RestApi(){}

    public static RestApi getObject() {
        if(restApi == null) {
            restApi = new RestApi();
            request = RestAssured.given();
        }
        return restApi;
    }

    public void init(String baseUri) {
        this.baseUri = baseUri;
        request.baseUri(this.baseUri);
    }

    public Response get(String path) {
        return request.get(path);
    }

    public Response getAndValidate(String path, int expectedStatusCode) {
        Response response = RestAssured.given().get(path);
        validate(response, expectedStatusCode);
        return response;
    }

    public void validate(Response response, int expectedStatusCode) {
        Assert.assertNotEquals(response, null);
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
    }

}
