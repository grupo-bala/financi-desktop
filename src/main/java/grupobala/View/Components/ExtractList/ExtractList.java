package grupobala.View.Components.ExtractList;

import grupobala.Controller.Extract.ExtractController;
import grupobala.Entities.Extract.IExtract.IExtract;
import grupobala.Entities.Iterator.IteratorEnum.IteratorEnum;
import grupobala.Entities.Iterator.IteratorInterface.IteratorInterface;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.View.Components.Component.Component;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ExtractList implements Component {

    private VBox mainPane = new VBox();
    private Locale localeBR;
    private DateFormat dateFormat;
    private VBox mainContainer;
    private ExtractLambda callback;

    @Override
    public VBox getComponent() {
        localeBR = new Locale("pt", "BR");
        dateFormat = new SimpleDateFormat("dd 'de' MMM yyyy", localeBR);

        mainContainer = new VBox();
        VBox extractTitle = getTitlePage();

        mainPane.getStyleClass().add("extract");
        mainContainer.getStyleClass().add("container-extract");

        mainPane
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/ExtractList/ExtractList.css"
            );

        mainPane.getChildren().addAll(extractTitle, mainContainer);

        try {
            loadExtract();
        } catch (Exception e) {}

        return mainPane;
    }

    public void setOnMouseClicked(ExtractLambda callback) {
        this.callback = callback;
    }

    public void reloadExtract() {
        try {
            mainContainer.getChildren().clear();
            loadExtract();
        } catch (Exception e) {}
    }

    private void loadExtract() throws SQLException, ParseException {
        ExtractController extrato2 = new ExtractController();

        IExtract extract = extrato2.getExtract();
        VBox transactions = getTransactionsPreview(extract);
        mainContainer.getChildren().add(transactions);
    }

    private VBox getTransactionsPreview(IExtract extract) {
        VBox outputs = new VBox();
        int limit = 0;

        IteratorInterface<ITransaction> extractIterator = extract.iterator(
            IteratorEnum.REVERSE
        );

        while (extractIterator.hasNext()) {
            ITransaction transaction = extractIterator.next();

            if (limit == 4) {
                break;
            }
            limit++;
            VBox tview = compilingTransactionPreview(transaction);

            outputs.getChildren().add(tview);
        }

        return outputs;
    }

    private VBox compilingTransactionPreview(ITransaction t) {
        boolean isNegative = t.getValue() < 0;

        Text title = new Text(t.getTitle());
        Text date = new Text(dateFormat.format(t.getDate()));
        Text value = new Text(String.format("R$ %.2f", Math.abs(t.getValue())));

        VBox left = new VBox(title, date);
        HBox right = new HBox(value);
        HBox tbox = new HBox(getIcon(t), left, right);
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

        tboxCont.setOnMouseClicked(e -> {
            this.callback.onClick(t);
        });

        return tboxCont;
    }

    private VBox getTitlePage() {
        VBox container = new VBox();
        Text title = new Text("Movimentações recentes");
        title.getStyleClass().add("extract-title");
        container.getStyleClass().add("container-title");

        container.getChildren().add(title);
        container.setAlignment(Pos.CENTER);

        return container;
    }

    private HBox getIcon(ITransaction t) {
        Image imageLocation;

        switch (t.getCategory()) {
            case FOOD:
                imageLocation =
                    new Image(
                        "file:src/main/resources/grupobala/images/Food Bar.png"
                    );
                break;
            case CLOTHING:
                imageLocation =
                    new Image(
                        "file:src/main/resources/grupobala/images/fashion.png"
                    );
                break;
            case HEALTH:
                imageLocation =
                    new Image(
                        "file:src/main/resources/grupobala/images/health.png"
                    );
                break;
            case ENTERTAINMENT:
                imageLocation =
                    new Image(
                        "file:src/main/resources/grupobala/images/entertainment.png"
                    );
                break;
            case PAYMENTS:
                imageLocation =
                    new Image(
                        "file:src/main/resources/grupobala/images/dollar-symbol.png"
                    );
                break;
            default:
                imageLocation =
                    new Image(
                        "file:src/main/resources/grupobala/images/others.png"
                    );
        }
        ImageView image = new ImageView(imageLocation);
        HBox container = new HBox(image);
        container.getStyleClass().add("icon-container");

        return container;
    }
}
