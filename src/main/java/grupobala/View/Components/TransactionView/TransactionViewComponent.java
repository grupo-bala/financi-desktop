package grupobala.View.Components.TransactionView;

import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.View.Components.Button.ButtonComponent;
import grupobala.View.Components.Card.CardVBoxComponent;
import grupobala.View.Components.Component.Component;
import grupobala.View.Components.ExtractList.ExtractLambda;
import grupobala.View.Components.Popup.PopupComponent;
import grupobala.View.Components.TextWithLabel.TextWithLabelComponent;
import java.text.SimpleDateFormat;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TransactionViewComponent implements Component {
    ExtractLambda callback;
    VBox container = new CardVBoxComponent().getComponent();
    PopupComponent transactionPopup = new PopupComponent();
    ITransaction transaction;

    public TransactionViewComponent(ITransaction transaction) {
        this.transaction = transaction;

        container
            .getStylesheets()
            .add(
                "file:src/main/java/grupobala/View/Components/TransactionView/TransactionView.css"
            );

        container.getStyleClass().add("transaction-view-container");

        VBox topBox = getTopBox();
        HBox bottomBox = getBottomBox();
        container.getChildren().addAll(topBox, bottomBox);

        transactionPopup.getComponent().getChildren().add(container);
    }

    public void setOnDelete(ExtractLambda callback){
        this.callback = callback;
    }

    @Override
    public StackPane getComponent() {
        return transactionPopup.getComponent();
    }

    public PopupComponent getPopup() {
        return transactionPopup;
    }

    public void setTransaction(ITransaction transaction) {
        this.transaction = transaction;
    }

    private VBox getTopBox() {
        VBox topBox = new VBox();
        HBox titleBox = new HBox();
        Text title = new Text(transaction.getTitle());
        Text value = new Text(String.format("R$ %.2f", transaction.getValue()));
        Image trashIcon = new Image(
            "file:src/main/resources/grupobala/images/trash.png"
        );
        ImageView trash = new ImageView(trashIcon);

        trash.setOnMouseClicked(e -> {
            callback.onClick(transaction);
        });
        topBox.getStyleClass().add("top-box");
        titleBox.getStyleClass().add("top-box-titlebox");
        title.getStyleClass().add("top-box-title");
        value.getStyleClass().add("top-box-value");
        trash.getStyleClass().add("top-box-trash");
        setValueColor(value, transaction.getValue());

        titleBox.getChildren().addAll(title, trash);
        topBox.getChildren().addAll(titleBox, value);

        return topBox;
    }

    private HBox getBottomBox() {
        HBox bottomBox = new HBox();
        VBox leftSide = new VBox();
        VBox rightSide = new VBox();
        TextWithLabelComponent date = new TextWithLabelComponent(
            "Data",
            new SimpleDateFormat("dd/mm/yyyy").format(transaction.getDate())
        );
        TextWithLabelComponent category = new TextWithLabelComponent(
            "Categoria",
            transaction.getCategory().displayedName
        );
        TextWithLabelComponent isRecurring = new TextWithLabelComponent(
            "Recorrente?",
            "Não"
        );
        Button editButton = new ButtonComponent().getComponent();
        HBox rightSideAlignment = new HBox();

        editButton.setStyle(
            "-fx-background-color: #2B2F2B; -fx-text-fill: #EF5350; -fx-border-width: 2; -fx-border-color: #ffffff20; -fx-cursor: hand; -fx-border-radius: 3;"
        );
        leftSide.getStyleClass().add("bot-box-side");
        rightSide.getStyleClass().add("bot-box-side");

        editButton.setText("Editar");
        HBox.setHgrow(rightSideAlignment, Priority.ALWAYS);
        rightSideAlignment.setAlignment(Pos.BASELINE_RIGHT);

        leftSide
            .getChildren()
            .addAll(date.getComponent(), isRecurring.getComponent());
        rightSide.getChildren().addAll(category.getComponent(), editButton);
        bottomBox.getStyleClass().add("bot-box");

        rightSideAlignment.getChildren().add(rightSide);
        bottomBox.getChildren().addAll(leftSide, rightSideAlignment);

        return bottomBox;
    }

    private void setValueColor(Text text, double value) {
        if (value < 0) {
            text.setStyle("-fx-fill: #C54646");
        } else {
            text.setStyle("-fx-fill: #49AD5A");
        }

        System.out.println(value);
    }
}
