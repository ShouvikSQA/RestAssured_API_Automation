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

public class Customer2_TestRunner extends Setup {



    @Test(priority = 1, description = "Customer2 make successful Payment to merchant")
    public void paymentToMerchant() throws IOException, ParseException {
        String from_account = Utils.getPhoneNumber(2); // Customer2 phone number
        String to_account ="01506639468"; // Merchant Phone Number
        int amount = 100;

        TransactionController transController = new TransactionController(props);

        TransactionModel model = new TransactionModel(from_account,to_account,amount);

        JsonPath jsonObj =  transController.payment(model);

        String messageActual = jsonObj.get("message");
        String messageExpected = "Payment successful";
        Assert.assertTrue(messageActual.contains(messageExpected));

    }

    @Test(priority = 2,description = "Customer2 can successfully check balance")
    public void checkBalance() throws IOException, ParseException {
        TransactionController transController = new TransactionController(props);
        String phnNum = Utils.getPhoneNumber(2); // Customer 2 phone number
        JsonPath jsonObj =  transController.checkBalance(phnNum);

        String messageActual = jsonObj.get("message");
        String messageExpected = "User balance";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }
}
