package grupobala.View.Pages.Dashboard.ExtractPage;

import grupobala.Controller.Extract.ExtractController;
import grupobala.Controller.Transaction.TransactionController;
import grupobala.Entities.Extract.IExtract.IExtract;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.Entities.User.User;
import grupobala.View.Components.Card.CardVBoxComponent;
import grupobala.View.Components.ExtractList.ExtractLambda;
import grupobala.View.Components.FilterButton.FilterButton;
import grupobala.View.Components.NavigationBar.NavigationBar;
import grupobala.View.Components.Popup.PopupComponent;
import grupobala.View.Components.Popups.FilterExtractPopup;
import grupobala.View.Components.TransactionView.TransactionViewComponent;
import grupobala.View.Pages.Page.Page;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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
    private PopupComponent popupConfirmation = new PopupComponent();
    private PopupComponent errorPopup = new PopupComponent();
    private FilterExtractPopup filterPopup = new FilterExtractPopup();
    private Locale localeBR;
    private DateFormat dateFormat;
    private VBox mainContainer;
    private VBox container;
    private ExtractLambda callback;

    @Override
    public StackPane getMainPane() {
        localeBR = new Locale("pt", "BR");
        dateFormat = new SimpleDateFormat("dd 'de' MMM yyyy", localeBR);

        ScrollPane clipContainer = new ScrollPane();
        mainContainer = new VBox();
        container = new VBox();

        mainContainer.getStyleClass().add("main-container");
        mainPane.getStyleClass().add("extract");
        container.getStyleClass().add("container");

        mainPane
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Pages/ExtractPage/ExtractPage.css"
            );

        mainPane.getChildren().addAll(
            mainContainer,
            errorPopup.getComponent(),
            popupConfirmation.getComponent(),
            filterPopup.getComponent()
        );

        mainContainer
            .getChildren()
            .addAll(navigationBar.getComponent(), clipContainer);

        try {
            loadExtract();
        } catch (Exception e) {}

        getTransactionPopup();
        clipContainer.setContent(container);
        clipContainer.setStyle("-fx-background-color: transparent;");
        clipContainer.setFitToHeight(true);
        clipContainer.setFitToWidth(true);

        return mainPane;
    }

    public void setOnMouseClicked(ExtractLambda callback) {
        this.callback = callback;
    }

    public void reloadExtract() {
        try {
            container.getChildren().clear();
            loadExtract();
        } catch (Exception e) {}
    }

    private void loadExtract() throws SQLException, ParseException {
        ExtractController extrato2 = new ExtractController();

        IExtract extract = extrato2.getExtract();
        VBox transactions = getTransactionsPreview(extract);
        container.getChildren().addAll(getTitlePage(), transactions);
    }

    private HBox getTitlePage() {

        HBox titleContainer = new HBox();
        HBox titleBox = new HBox();
        HBox filter = getFilterButton(); 

        Text title = new Text("Histórico de Transações");
        title.getStyleClass().add("extract-title");

        titleBox.getChildren().add(title);
        titleContainer.getChildren().addAll(titleBox, filter);
        titleContainer.getStyleClass().add("title-image-container");

        return titleContainer;
    }

    private HBox getFilterButton(){

        HBox filterBox = new HBox();
        filterBox.getStyleClass().add("filterButton_container");
        FilterButton filterButton = new FilterButton();

        filterBox.getChildren().add(filterButton.getComponentClick());

        filterButton
            .getComponent()
            .setOnMouseClicked(e -> {
                filterPopup.getPopup().showPopup();
            });

        return filterBox;
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

    private void getTransactionPopup() {
        setOnMouseClicked(transaction -> {
            TransactionViewComponent transactionView = new TransactionViewComponent(
                transaction
            );
            mainPane.getChildren().add(transactionView.getComponent());
            transactionView.setOnDelete(transactionToDelete -> {
                transactionView.getPopup().hidePopup();
                popupRemoveTransactionConfirmation(
                    transactionToDelete.getId(),
                    transactionToDelete.getValue()
                );
            });
            transactionView.getPopup().showPopup();
        });
    }

    private void popupRemoveTransactionConfirmation(
        int idTransaction,
        double transactionValue
    ) {
        VBox card = new CardVBoxComponent().getComponent();
        Button confirmation = new Button("Confirmar");
        Button closePopup = new Button("X");
        Text text = new Text("Deseja apagar esta movimentação?");
        VBox vbox = new VBox();

        vbox.getChildren().add(closePopup);
        card.getChildren().addAll(vbox, text, confirmation);
        popupConfirmation.getComponent().getChildren().addAll(card);

        vbox.getStyleClass().add("confirmation-box");
        card.getStyleClass().add("confirmation-card");
        closePopup.getStyleClass().add("close-popup");
        text.getStyleClass().add("confirmation-text");
        confirmation.getStyleClass().add("confirmation-button");

        popupConfirmation.showPopup();

        confirmation.setOnAction(e -> {
            popupRemoveTransactionError(idTransaction, transactionValue);
            popupConfirmation.hidePopup();
        });

        closePopup.setOnAction(e -> {
            popupConfirmation.hidePopup();
        });
    }

    private void popupRemoveTransactionError(
        int idTransaction,
        double transactionValue
    ) {
        VBox card = new CardVBoxComponent().getComponent();
        Button closePopup = new Button("X");
        Text text = new Text("Não foi possível remover a movimentação");
        VBox textVBox = new VBox();
        Image alertImg = new Image(
            "file:src/main/resources/grupobala/images/alert.png"
        );
        ImageView alert = new ImageView(alertImg);
        HBox alertHBox = new HBox();

        VBox.setVgrow(textVBox, Priority.ALWAYS);
        card.getChildren().addAll(alertHBox, text, textVBox);
        errorPopup.getComponent().getChildren().add(card);
        textVBox.getChildren().add(text);
        alertHBox.getChildren().addAll(alert, closePopup);

        alert.setFitHeight(25);
        alert.setFitWidth(25);
        alert.setPreserveRatio(true);

        alertHBox.getStyleClass().add("alert-box");
        textVBox.getStyleClass().add("text-box");

        card.getStyleClass().add("remove-card");
        closePopup.getStyleClass().add("close-popup-error");
        text.getStyleClass().add("text-error");

        try {
            TransactionController transactionController = new TransactionController();
            transactionController.removeTransaction(
                new User().getID(),
                idTransaction,
                transactionValue,
                new User().getValue()
            );
            reloadExtract();
        } catch (Exception error) {
            errorPopup.showPopup();

            closePopup.setOnAction(e -> {
                errorPopup.hidePopup();
            });
        }
    }
}
