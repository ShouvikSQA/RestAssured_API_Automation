package Controller;

import Config.Setup;
import Config.UserModel;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import javax.naming.ConfigurationException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class UserController  {
    Properties props;
    public UserController(Properties props) throws IOException {
        this.props=props;
        RestAssured.baseURI= props.getProperty("baseUrl");


    }

    public JsonPath doLogin(String email, String password) throws org.apache.commons.configuration.ConfigurationException {

        UserModel model = new UserModel();
        model.setEmail(email);
        model.setPassword(password);
        Response res = given().contentType("application/json").body(model).when().post("/user/login")
                .then().assertThat().statusCode(200).extract().response();

        return res.jsonPath();
    }

    public JsonPath createUser(UserModel model) throws ConfigurationException {

        Response res = given().contentType("application/json").body(model)
                .header("Authorization","bearer " + props.getProperty("token"))
                .header("X-AUTH-SECRET-KEY", props.getProperty("secretKey"))
                .when().post("user/create")
                .then().assertThat().statusCode(201).extract().response();

        return res.jsonPath();

    }

}
