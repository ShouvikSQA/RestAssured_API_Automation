package TestRunner;

import Controller.UserController;
import Config.Setup;
import Config.UserModel;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import Utils.Utils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;

public class CreateTestRunner extends Setup {

    @Test(priority = 1 , description = "Admin can create first customer successfully")
    public void createCustomer1() throws IOException, ConfigurationException, javax.naming.ConfigurationException, org.json.simple.parser.ParseException {
        Faker faker = new Faker();

        UserController userController = new UserController(props)    ;
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String password = faker.number().digits(4);
        String phone_number = Utils.randomPhoneNumber();
        String nid = "12345678";
        String role = "Customer";

        UserModel model = new UserModel(name,  email,  password,  phone_number,  nid, role);

        JsonPath jsonObj = userController.createUser(model);

        String messageActual = jsonObj.get("message");
        String messageExpected = "User created";
        Assert.assertTrue(messageActual.contains(messageExpected));

        Utils.saveUsers(model);
    }


    @Test(priority = 2 , description = "Admin can create second customer successfully")
    public void createCustomer() throws IOException, ConfigurationException, ParseException, javax.naming.ConfigurationException, org.json.simple.parser.ParseException {
        Faker faker = new Faker();
        UserController userController = new UserController(props);
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String password = faker.number().digits(4);
        String phone_number = Utils.randomPhoneNumber();;
        String nid = "12345678";
        String role = "Customer";

        UserModel model = new UserModel(name,  email,  password,  phone_number,  nid, role);

        JsonPath jsonObj = userController.createUser(model);

        String messageActual = jsonObj.get("message");
        String messageExpected = "User created";
        Assert.assertTrue(messageActual.contains(messageExpected));

        Utils.saveUsers(model);
    }

    @Test(priority = 3 , description = "Admin can create agent successfully")
    public void createAgent() throws IOException, ConfigurationException, ParseException, javax.naming.ConfigurationException, org.json.simple.parser.ParseException {
        Faker faker = new Faker();
        UserController userController = new UserController(props);
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String password = faker.number().digits(4);
        String phone_number = Utils.randomPhoneNumber();;
        String nid = faker.number().digits(8);
        String role = "Agent";

        UserModel model = new UserModel(name,  email,  password,  phone_number,  nid, role);

        JsonPath jsonObj = userController.createUser(model);

        String messageActual = jsonObj.get("message");
        String messageExpected = "User created";
        Assert.assertTrue(messageActual.contains(messageExpected));

        Utils.saveUsers(model);

    }

}
