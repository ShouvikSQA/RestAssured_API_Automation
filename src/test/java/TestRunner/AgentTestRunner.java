package TestRunner;

import Controller.TransactionController;
import Config.Setup;
import Config.TransactionModel;
import Utils.Utils;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;

public class AgentTestRunner extends Setup {

    @Test(priority = 1, description = "Agent can deposit money successfully to customer1")
    public void depositMoneyToCustomer() throws IOException, ParseException, org.json.simple.parser.ParseException {
        String from_account = Utils.getPhoneNumber(1); // Agent Phone Number
        String to_account = Utils.getPhoneNumber(3); // Customer 1 Phone Number
        int amount = 1500;

        TransactionController transController  = new TransactionController(props);
        TransactionModel model = new TransactionModel(from_account,to_account, amount);
        JsonPath jsonObj = transController.deposit(model);

        String messageActual = jsonObj.get("message");
        String messageExpected = "Deposit successful";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }
}
