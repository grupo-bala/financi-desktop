package grupobala.View.Pages.Dashboard.ExtractPage;

import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Extract.Extract;
import grupobala.Entities.Extract.IExtract.IExtract;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.Entities.Transaction.Transaction;
import grupobala.View.Pages.Page.Page;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ExtractPage implements Page {

    private StackPane mainPane = new StackPane();

    private Locale localeBR;
    private DateFormat dateFormat;
    private VBox mainContainer;

    @Override
    public StackPane getMainPane() {
        localeBR = new Locale("pt", "BR");
        dateFormat = new SimpleDateFormat("dd 'de' MMM yyyy", localeBR);

        mainContainer = new VBox();
        VBox title = getTitlePage();

        mainPane.getStyleClass().add("extract");
        mainContainer.getStyleClass().add("container");

        mainPane
            .getStylesheets()
            .add(
                "file:src/main/java/grupobala/View/Pages/Dashboard/ExtractPage/ExtractPage.css"
            );

        mainPane.getChildren().add(mainContainer);
        mainContainer.getChildren().add(title);

        try {
            loadExtract();
        } catch (Exception e) {}

        return mainPane;
    }

    private void loadExtract() throws SQLException, ParseException {
        Calendar calendarBegin = Calendar.getInstance();
        calendarBegin.set(Calendar.YEAR, 2020);
        calendarBegin.set(Calendar.MONTH, Calendar.DECEMBER);
        calendarBegin.set(Calendar.DAY_OF_MONTH, 31);

        Date dataBegin = calendarBegin.getTime();

        ITransaction transEx = new Transaction(
            12,
            1000,
            "teste",
            CategoryEnum.CLOTHING,
            dataBegin
        );
        ITransaction transExT = new Transaction(
            12,
            -1000,
            "teste",
            CategoryEnum.CLOTHING,
            dataBegin
        );
        ITransaction transExTT = new Transaction(
            12,
            1000,
            "teste",
            CategoryEnum.CLOTHING,
            dataBegin
        );

        ArrayList<ITransaction> lista = new ArrayList<>();
        lista.add(transEx);
        lista.add(transExT);
        lista.add(transExTT);

        Extract extract = new Extract(lista);
        VBox transactions = getTransactionsPreview(extract);
        mainContainer.getChildren().add(transactions);
    }

    private VBox getTitlePage() {
        VBox container = new VBox();
        Text title = new Text("Histórico de Transações");
        title.getStyleClass().add("extract-title");

        container.getChildren().add(title);
        container.setAlignment(Pos.TOP_CENTER);

        return container;
    }

    private VBox getTransactionsPreview(IExtract extract) {
        VBox outputs = new VBox();
        for (ITransaction t : extract) {
            VBox tview = compilingTransactionPreview(t);

            outputs.getChildren().add(tview);
        }

        return outputs;
    }

    private VBox compilingTransactionPreview(ITransaction t) {
        boolean isNegative = t.getValue() < 0;

        Text title = new Text(t.getTitle());
        Text date = new Text(dateFormat.format(t.getDate()));
        Text value = new Text(
            (isNegative ? "-" : "") +
            String.format("R$ %.2f", Math.abs(t.getValue()))
        );

        VBox left = new VBox(title, date);
        VBox right = new VBox(value);
        HBox tbox = new HBox(left, right);
        VBox tboxCont = new VBox(tbox);

        value
            .getStyleClass()
            .add(isNegative ? "extract-value-red" : "extract-value-green");
        tboxCont.getStyleClass().add("extract-transaction-container");
        date.getStyleClass().add("extract-transaction-date");
        tbox.getStyleClass().add("extract-transaction");
        right.getStyleClass().add("extract-right");
        left.getStyleClass().add("extract-left");

        return tboxCont;
    }
}
