package Controller;

import Config.TransactionModel;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class TransactionController  {
    Properties props;
    public TransactionController(Properties props) throws IOException {
        this.props=props;
        RestAssured.baseURI= props.getProperty("baseUrl");
    }

    public JsonPath deposit(TransactionModel model) {

        Response res = given().contentType("application/json").body(model)
                .header("Authorization", "bearer " + props.getProperty("token"))
                .header("X-AUTH-SECRET-KEY", props.getProperty("secretKey"))
                .when().post("transaction/deposit");
        System.out.println(res.asString());
        return res.jsonPath();
    }

    public JsonPath withdraw(TransactionModel model) {

        Response res = given().contentType("application/json").body(model)
                .header("Authorization",  "bearer " + props.getProperty("token"))
                .header("X-AUTH-SECRET-KEY", props.getProperty("secretKey"))
                .when().post("transaction/withdraw");
        System.out.println(res.asString());
        return res.jsonPath();
    }

    public JsonPath sendMoney(TransactionModel model) {

        Response res = given().contentType("application/json").body(model)
                .header("Authorization", "bearer " + props.getProperty("token"))
                .header("X-AUTH-SECRET-KEY", props.getProperty("secretKey"))
                .when().post("transaction/sendMoney");
        System.out.println(res.asString());
        return res.jsonPath();
    }

    public JsonPath payment(TransactionModel model) {

        Response res = given().contentType("application/json").body(model)
                .header("Authorization", "bearer " + props.getProperty("token"))
                .header("X-AUTH-SECRET-KEY", props.getProperty("secretKey"))
                .when().post("transaction/payment");
        System.out.println(res.asString());
        return res.jsonPath();
    }

    public JsonPath checkBalance(String phoneNumber) {

        Response res = given().contentType("application/json")
                .header("Authorization", "bearer " + props.getProperty("token"))
                .header("X-AUTH-SECRET-KEY", props.getProperty("secretKey"))
                .when().get("transaction/balance/" + phoneNumber);
        System.out.println(res.asString());
        return res.jsonPath();
    }

}
