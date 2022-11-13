package grupobala.View.Pages.Dashboard.ExtractPage;

import grupobala.Controller.Extract.ExtractController;
import grupobala.Entities.Extract.IExtract.IExtract;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.View.Components.ExtractList.ExtractLambda;
import grupobala.View.Components.NavigationBar.NavigationBar;
import grupobala.View.Pages.Page.Page;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ExtractPage implements Page {

    private StackPane mainPane = new StackPane();

    private NavigationBar navigationBar = new NavigationBar();
    private Locale localeBR;
    private DateFormat dateFormat;
    private VBox mainContainer;
    private VBox container;
    private ExtractLambda callback;

    @Override
    public StackPane getMainPane() {
        localeBR = new Locale("pt", "BR");
        dateFormat = new SimpleDateFormat("dd 'de' MMM yyyy", localeBR);

        mainContainer = new VBox();
        container = new VBox();
        VBox title = getTitlePage();

        mainContainer.getStyleClass().add("main-container");
        mainPane.getStyleClass().add("extract");
        container.getStyleClass().add("container");

        mainPane
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Pages/ExtractPage/ExtractPage.css"
            );

        mainPane.getChildren().add(mainContainer);
        mainContainer
            .getChildren()
            .addAll(navigationBar.getComponent(), container);
        container.getChildren().add(title);

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
        title.getStyleClass().add("extract-transaction-title");
        tbox.getStyleClass().add("extract-transaction");
        right.getStyleClass().add("extract-right");
        left.getStyleClass().add("extract-left");

        tboxCont.setOnMouseClicked(e -> {
            this.callback.onClick(t);
        });

        return tboxCont;
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
