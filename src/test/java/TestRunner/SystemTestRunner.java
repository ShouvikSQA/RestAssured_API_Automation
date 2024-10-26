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

public class SystemTestRunner extends Setup {
    @Test(priority = 1,description = "System can successfully deposit money to agent")
    public void addMoneyToAgent() throws IOException, ParseException {
        String from_account = "System";
        String to_account = Utils.getPhoneNumber(1); // agent phone number
        int amount = 2000;

        TransactionController transactionController = new TransactionController(props);
        TransactionModel model = new TransactionModel(from_account,to_account,amount);

        JsonPath jsonObj = transactionController.deposit(model);
        String messageActual = jsonObj.get("message");
        String messageExpected = "Deposit successful";
        Assert.assertTrue(messageActual.contains(messageExpected));


    }
}
