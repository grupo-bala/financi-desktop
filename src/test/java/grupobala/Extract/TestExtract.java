package grupobala.Extract;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Extract.Extract;
import grupobala.Entities.Transaction.Transaction;
import grupobala.Entities.Transaction.ITransaction.ITransaction;

public class TestExtract {
 
    @Test
    @Order(1)
    public void TestExtractEntry() throws Exception {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.FEBRUARY, 28);
        Date transactionDate = calendar.getTime();

        ITransaction transExampIn = new Transaction(1, 100, "GiftCard", CategoryEnum.ENTERTAINMENT, transactionDate);
        ITransaction transExampOut = new Transaction(2, -50, "BetsOnline", CategoryEnum.PAYMENTS, transactionDate);
        ITransaction transExampInTwo = new Transaction(3, 65, "Gift", CategoryEnum.OTHERS, transactionDate);
        ITransaction transExampOutTwo = new Transaction(4, -25, "Snacks", CategoryEnum.FOOD, transactionDate);
        ITransaction transExampInThree = new Transaction(5, 150, "Savings", CategoryEnum.OTHERS, transactionDate);
        
        ArrayList<ITransaction> transactionsList = new ArrayList<>();
        transactionsList.add(transExampIn);
        transactionsList.add(transExampOut);
        transactionsList.add(transExampInTwo);
        transactionsList.add(transExampOutTwo);
        transactionsList.add(transExampInThree);
        
        Extract newExtract = new Extract(transactionsList);

        assertEquals(315, newExtract.getEntry());
    }

    @Test
    @Order(2)
    public void TestExtractOutput() throws Exception {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.FEBRUARY, 28);
        Date transactionDate = calendar.getTime();

        ITransaction transExampIn = new Transaction(1, 100, "GiftCard", CategoryEnum.ENTERTAINMENT, transactionDate);
        ITransaction transExampOut = new Transaction(2, -50, "BetsOnline", CategoryEnum.PAYMENTS, transactionDate);
        ITransaction transExampInTwo = new Transaction(3, 65, "Gift", CategoryEnum.OTHERS, transactionDate);
        ITransaction transExampOutTwo = new Transaction(4, -25, "Snacks", CategoryEnum.FOOD, transactionDate);
        ITransaction transExampInThree = new Transaction(5, 150, "Savings", CategoryEnum.OTHERS, transactionDate);
        
        ArrayList<ITransaction> transactionsList = new ArrayList<>();
        transactionsList.add(transExampIn);
        transactionsList.add(transExampOut);
        transactionsList.add(transExampInTwo);
        transactionsList.add(transExampOutTwo);
        transactionsList.add(transExampInThree);
        
        Extract newExtract = new Extract(transactionsList);

        assertEquals(-75, newExtract.getOutput());
    }
}
