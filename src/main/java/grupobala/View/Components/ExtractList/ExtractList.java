package grupobala.View.Components.ExtractList;

import grupobala.Controller.Extract.ExtractController;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Extract.Extract;
import grupobala.Entities.Extract.IExtract.IExtract;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.Entities.Transaction.Transaction;
import grupobala.View.Components.Component.Component;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ExtractList implements Component {

    private VBox mainPane = new VBox();
    private Locale localeBR;
    private DateFormat dateFormat;
    private VBox mainContainer;

    @Override
    public VBox getComponent() {
        localeBR = new Locale("pt", "BR");
        dateFormat = new SimpleDateFormat("dd 'de' MMM yyyy", localeBR);

        mainContainer = new VBox();

        mainPane.getStyleClass().add("extract");
        mainContainer.getStyleClass().add("container-extract");

        mainPane
            .getStylesheets()
            .add(
                "file:src/main/java/grupobala/View/Pages/Dashboard/ExtractPage/ExtractPage.css"
            );

        mainPane.getChildren().add(mainContainer);

        try {
            loadExtract();
        } catch (Exception e) {}

        return mainPane;
    }

    private void loadExtract() throws SQLException, ParseException {
        ExtractController extrato2 = new ExtractController();

        IExtract extract = extrato2.getExtract();
        VBox transactions = getTransactionsPreview(extract);
        mainContainer.getChildren().add(transactions);
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
        HBox right = new HBox(value);
        HBox tbox = new HBox(left, right);
        VBox tboxCont = new VBox(tbox);

        HBox.setHgrow(right, Priority.ALWAYS);

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
