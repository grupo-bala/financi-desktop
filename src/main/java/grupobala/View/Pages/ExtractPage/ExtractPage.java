package grupobala.View.Pages.ExtractPage;

import grupobala.Controller.Extract.ExtractController;
import grupobala.Controller.Report.IReportController.IReportController;
import grupobala.Controller.Report.ReportController;
import grupobala.Controller.Transaction.TransactionController;
import grupobala.Entities.Extract.Filter.IFilter.IFilter;
import grupobala.Entities.Extract.IExtract.IExtract;
import grupobala.Entities.Iterator.IteratorEnum.IteratorEnum;
import grupobala.Entities.Iterator.IteratorInterface.IteratorInterface;
import grupobala.Entities.Report.CSVReport;
import grupobala.Entities.Report.PDFReport;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.Entities.User.User;
import grupobala.View.Components.Card.CardVBoxComponent;
import grupobala.View.Components.DocumentButton.DocumentButton;
import grupobala.View.Components.DocumentButton.DocumentButton.IconType;
import grupobala.View.Components.ExtractList.ExtractLambda;
import grupobala.View.Components.FilterButton.FilterButton;
import grupobala.View.Components.NavigationBar.NavigationBar;
import grupobala.View.Components.Popup.PopupComponent;
import grupobala.View.Components.Popups.EditTransactionPopup;
import grupobala.View.Components.Popups.FilterExtractPopup;
import grupobala.View.Components.TransactionView.TransactionViewComponent;
import grupobala.View.Pages.Page.Page;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javafx.geometry.Pos;
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

    private EditTransactionPopup editTransaction = new EditTransactionPopup();
    private NavigationBar navigationBar = new NavigationBar();
    private PopupComponent popupConfirmation = new PopupComponent();
    private PopupComponent errorPopup = new PopupComponent();
    private FilterExtractPopup filterPopup = new FilterExtractPopup();
    private IReportController documentReport = new ReportController();
    private PopupComponent generateDocumentPopup = new PopupComponent();
    private Locale localeBR;
    private DateFormat dateFormat;
    private VBox mainContainer;
    private VBox container;
    private ExtractLambda callback;
    private IFilter currentFilter = null;

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

        filterPopup.setOnConfirm(filter -> {
            this.currentFilter = filter;
            this.reloadExtract();
        });

        mainPane
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Pages/ExtractPage/ExtractPage.css"
            );

        mainPane
            .getChildren()
            .addAll(
                mainContainer,
                errorPopup.getComponent(),
                popupConfirmation.getComponent(),
                filterPopup.getComponent(),
                generateDocumentPopup.getComponent(),
                editTransaction.getComponent()
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

        IExtract extract = extrato2.getCompleteExtract();
        VBox transactions = getTransactionsPreview(extract);
        container.getChildren().addAll(getTitlePage(), transactions);
    }

    private HBox getTitlePage() {
        HBox titleContainer = new HBox();
        HBox titleBox = new HBox();
        HBox filter = getFilterButton();
        HBox document = getDocumentButton();

        HBox.setHgrow(filter, Priority.ALWAYS);

        Text title = new Text("Histórico de Transações");
        title.getStyleClass().add("extract-title");

        titleBox.getChildren().add(title);
        titleContainer.getChildren().addAll(titleBox, document, filter);
        titleContainer.getStyleClass().add("title-image-container");

        return titleContainer;
    }

    private HBox getFilterButton() {
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

    private HBox getDocumentButton() {
        HBox DocumentBox = new HBox();
        DocumentBox.getStyleClass().add("filter_Document_container");
        DocumentButton exportPDF = new DocumentButton(IconType.PDF);
        DocumentButton exportCSV = new DocumentButton(IconType.CSV);

        DocumentBox
            .getChildren()
            .addAll(exportPDF.getComponent(), exportCSV.getComponent());

        exportPDF
            .getComponent()
            .setOnMouseClicked(e -> {
                try {
                    documentReport.setReporter(new PDFReport());
                    documentReport.generateReport();
                    DocumentConfirmationPopUp();
                } catch (Exception e1) {
                    System.out.println("Documento já foi gerado");
                    DocumentErrorPopUp();
                }
            });

        exportCSV
            .getComponent()
            .setOnMouseClicked(e -> {
                try {
                    documentReport.setReporter(new CSVReport());
                    documentReport.generateReport();
                    DocumentConfirmationPopUp();
                } catch (Exception e1) {
                    System.out.println("Documento já foi gerado");
                    DocumentErrorPopUp();
                }
            });

        return DocumentBox;
    }

    private void DocumentConfirmationPopUp() {
        VBox card = new CardVBoxComponent().getComponent();
        Button closePopup = new Button("Concluir");
        Text text = new Text(
            "Relatório Gerado! Vá para o diretório raiz e acesse-o."
        );
        VBox textVBox = new VBox();

        HBox alertHBox = new HBox();

        card.getChildren().addAll(text, textVBox, alertHBox);
        generateDocumentPopup.getComponent().getChildren().add(card);
        textVBox.getChildren().add(text);
        alertHBox.getChildren().addAll(closePopup);

        alertHBox.setAlignment(Pos.BASELINE_CENTER);

        textVBox.getStyleClass().add("text-box");

        card.getStyleClass().add("document-card");
        closePopup.getStyleClass().add("close-popup-documentConfirmation");
        text.getStyleClass().add("text-documento-pop");

        generateDocumentPopup.showPopup();
        closePopup.setOnAction(e -> {
            generateDocumentPopup.hidePopup();
        });
    }

    private void DocumentErrorPopUp() {
        VBox card = new CardVBoxComponent().getComponent();
        Button closePopup = new Button("Concluir");
        Text text = new Text(
            "Relatório já foi gerado! Vá para o diretório raiz e acesse-o."
        );
        VBox textVBox = new VBox();

        HBox alertHBox = new HBox();

        card.getChildren().addAll(text, textVBox, alertHBox);
        generateDocumentPopup.getComponent().getChildren().add(card);
        textVBox.getChildren().add(text);
        alertHBox.getChildren().addAll(closePopup);

        alertHBox.setAlignment(Pos.BASELINE_CENTER);

        textVBox.getStyleClass().add("text-box");

        card.getStyleClass().add("document-card");
        closePopup.getStyleClass().add("close-popup-documentConfirmation");
        text.getStyleClass().add("text-documento-pop");

        generateDocumentPopup.showPopup();
        closePopup.setOnAction(e -> {
            generateDocumentPopup.hidePopup();
        });
    }

    private VBox getTransactionsPreview(IExtract extract) {
        VBox outputs = new VBox();

        IteratorInterface<ITransaction> extractIterator = extract.iterator(
            IteratorEnum.REVERSE
        );
        while (extractIterator.hasNext()) {
            ITransaction transaction = extractIterator.next();
            if (
                (
                    this.currentFilter != null &&
                    this.currentFilter.matchesFilter(transaction)
                ) ||
                (this.currentFilter == null)
            ) {
                VBox tview = compilingTransactionPreview(transaction);
                outputs.getChildren().add(tview);
            }
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

            transactionView.getPopup().showPopup();

            transactionView.setOnDelete(transactionToDelete -> {
                transactionView.getPopup().hidePopup();
                popupRemoveTransactionConfirmation(
                    transactionToDelete.getId(),
                    transactionToDelete.getValue()
                );
            });

            transactionView.setOnEdit(transactionToEdit -> {
                transactionView.getPopup().hidePopup();
                editTransaction.setTransaction(transaction);
                editTransaction.getPopup().showPopup();
                editTransaction.setOnConfirm(() -> {
                    reloadExtract();
                });
            });
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
