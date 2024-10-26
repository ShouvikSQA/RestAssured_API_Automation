package TestRunner;

import Controller.UserController;
import Config.Setup;
import Utils.Utils;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.naming.ConfigurationException;
import java.io.IOException;

public class LoginTestRunner extends Setup {
    @Test(description = "Admin can login successfully with valid Creds")
    public void doLogin() throws IOException, javax.naming.ConfigurationException, ConfigurationException, org.apache.commons.configuration.ConfigurationException {
        UserController userController = new UserController(props);
        JsonPath jsonObj = userController.doLogin("admin@roadtocareer.net","1234");
        String messageActual = jsonObj.get("message");
        String messageExpected = "Login successful";
        Assert.assertTrue(messageActual.contains(messageExpected));

        String token = jsonObj.get("token");
        System.out.println(token);
        Utils.setEnvVar("token", token);
    }
}
