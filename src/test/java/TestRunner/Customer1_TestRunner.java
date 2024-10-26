package TestRunner;

import Controller.TransactionController;
import Config.Setup;
import Config.TransactionModel;
import Utils.Utils;
import io.restassured.path.json.JsonPath;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class Customer1_TestRunner extends Setup {


    @Test(priority = 1, description = "Customer1 can withdraw successfully from agent")
    public void withdrawMoneyToAgent() throws IOException, ParseException {
        String from_account = Utils.getPhoneNumber(3); // Customer1 Phone number
        String to_account = Utils.getPhoneNumber(1); // Agent Phone Number
        int amount = 500;

        TransactionController transController = new TransactionController(props);
        TransactionModel model = new TransactionModel(from_account,to_account,amount);
        JsonPath jsonObj = transController.withdraw(model);

        String messageActual = jsonObj.get("message");
        String messageExpected = "Withdraw successful";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }

    @Test(priority = 2, description = "Customer1 can send money successfully to Customer2")
    public void sendMoneyTOAnotherCustomer() throws IOException, ParseException {
        String from_account = Utils.getPhoneNumber(3); // Customer1 Phone Number
        String to_account = Utils.getPhoneNumber(2); // Customer2 Phone Number
        int amount = 500;

        TransactionController transController = new TransactionController(props);
        TransactionModel model = new TransactionModel(from_account,to_account,amount);

        JsonPath jsonObj = transController.sendMoney(model);

        String messageActual = jsonObj.get("message");
        String messageExpected = "Send money successful";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }

}
