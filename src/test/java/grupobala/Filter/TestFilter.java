package grupobala.Filter;

import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Extract.Filter.FilterBuilder;
import grupobala.Entities.Extract.Filter.IFilter.IFilter;
import grupobala.Entities.Extract.Filter.IFilter.IFilterBuilder;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.Entities.Transaction.Transaction;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestFilter {

    @Test
    public void testDateFilterShouldPass() {
        Date transactionDate = makeDate(5, Calendar.JANUARY, 2022);
        Date initDate = makeDate(1, Calendar.JANUARY, 2022);
        Date endDate = makeDate(10, Calendar.JANUARY, 2022);
        CategoryEnum category = CategoryEnum.OTHERS;

        ITransaction transaction = new Transaction(
            0,
            0,
            "Teste",
            CategoryEnum.OTHERS,
            transactionDate
        );

        IFilterBuilder builder = new FilterBuilder();
        IFilter filter = builder
            .fromDate(initDate)
            .toDate(endDate)
            .withCategory(category)
            .build();

        Assertions.assertTrue(filter.matchesFilter(transaction));
    }

    @Test
    public void testDateFilterShouldFailWrongCategory() {
        Date transactionDate = makeDate(12, Calendar.JANUARY, 2022);
        Date initDate = makeDate(1, Calendar.JANUARY, 2022);
        Date endDate = makeDate(15, Calendar.JANUARY, 2022);
        CategoryEnum category = CategoryEnum.FOOD;

        ITransaction transaction = new Transaction(
            0,
            0,
            "Teste",
            CategoryEnum.OTHERS,
            transactionDate
        );

        IFilterBuilder builder = new FilterBuilder();
        IFilter filter = builder
            .fromDate(initDate)
            .toDate(endDate)
            .withCategory(category)
            .build();

        Assertions.assertFalse(filter.matchesFilter(transaction));
    }

    @Test
    public void testDateFilterShouldFailWrongDate() {
        Date transactionDate = makeDate(12, Calendar.JANUARY, 2022);
        Date initDate = makeDate(1, Calendar.JANUARY, 2022);
        Date endDate = makeDate(10, Calendar.JANUARY, 2022);
        CategoryEnum category = CategoryEnum.OTHERS;

        ITransaction transaction = new Transaction(
            0,
            0,
            "Teste",
            CategoryEnum.OTHERS,
            transactionDate
        );

        IFilterBuilder builder = new FilterBuilder();
        IFilter filter = builder
            .fromDate(initDate)
            .toDate(endDate)
            .withCategory(category)
            .build();

        Assertions.assertFalse(filter.matchesFilter(transaction));
    }

    private Date makeDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        return calendar.getTime();
    }
}
