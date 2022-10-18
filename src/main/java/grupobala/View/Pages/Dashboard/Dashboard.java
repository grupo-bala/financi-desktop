package grupobala.View.Pages.Dashboard;

import grupobala.Entities.User.User;
import grupobala.View.Components.AvatarCard.AvatarCardComponent;
import grupobala.View.Components.Card.CardHBoxComponent;
import grupobala.View.Components.Card.CardVBoxComponent;
import grupobala.View.Components.OperationButton.OperationButton;
import grupobala.View.Components.OperationButton.OperationButton.IconEnum;
import grupobala.View.Components.OperationPopup.OperationPopup;
import grupobala.View.Pages.Page.Page;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Dashboard implements Page {

    private StackPane mainPane = new StackPane();
    private OperationPopup incomingPopup = new OperationPopup();

    @Override
    public StackPane getMainPane() {
        VBox container = new VBox();
        CardHBoxComponent summaryCard = getSummaryCard();

        mainPane.getStyleClass().add("dashboard");
        container.getStyleClass().add("container");
        mainPane
            .getStylesheets()
            .add(
                "file:src/main/java/grupobala/View/Pages/Dashboard/Dashboard.css"
            );

        mainPane.getChildren().addAll(container, incomingPopup.getComponent());
        container.getChildren().addAll(summaryCard.getComponent());

        return mainPane;
    }

    private CardHBoxComponent getSummaryCard() {
        CardHBoxComponent hBox = new CardHBoxComponent();
        VBox leftSummary = getLeftSummaryCard();
        VBox rightSummary = getRightSummaryCard();

        hBox.getComponent().getStyleClass().add("summary-card");

        hBox.getComponent().getChildren().addAll(leftSummary, rightSummary);

        return hBox;
    }

    private VBox getLeftSummaryCard() {
        VBox leftSummaryCard = new VBox();
        HBox topSide = new HBox();
        HBox bottomSide = new HBox();
        AvatarCardComponent avatarCard = new AvatarCardComponent();
        VBox balanceBox = getBalanceBox();
        CardVBoxComponent bottomCardIn = getLeftSummaryBottomCard(
            "Receita mensal",
            new User().getValue(),
            "#49AD5A"
        );
        CardVBoxComponent bottomCardOut = getLeftSummaryBottomCard(
            "Despesa mensal",
            new User().getValue(),
            "#C54646"
        );

        leftSummaryCard.getStyleClass().add("left-summary");
        topSide.getStyleClass().add("summary-card-top");
        bottomSide.getStyleClass().add("summary-card-bot");

        topSide.getChildren().addAll(avatarCard.getComponent(), balanceBox);

        bottomSide
            .getChildren()
            .addAll(bottomCardIn.getComponent(), bottomCardOut.getComponent());

        leftSummaryCard.getChildren().addAll(topSide, bottomSide);

        return leftSummaryCard;
    }

    private VBox getBalanceBox() {
        VBox vBox = new VBox();
        Text title = new Text("Saldo geral");
        Text balance = new Text(
            String.format("R$ %.2f", new User().getValue())
        );

        vBox.getStyleClass().add("balance-box");
        title.getStyleClass().add("balance-title");
        balance.getStyleClass().add("balance");

        vBox.getChildren().addAll(title, balance);

        return vBox;
    }

    private CardVBoxComponent getLeftSummaryBottomCard(
        String title,
        double value,
        String color
    ) {
        CardVBoxComponent cardVBoxComponent = new CardVBoxComponent();
        Text titleText = new Text(title);
        Text valueText = new Text(String.format("R$ %.2f", value));

        cardVBoxComponent
            .getComponent()
            .getStyleClass()
            .add("left-summary-bottom-card");
        cardVBoxComponent
            .getComponent()
            .getChildren()
            .addAll(titleText, valueText);

        titleText.getStyleClass().add("left-summary-bottom-title");

        valueText.setStyle(
            String.format(
                "-fx-fill: %s; -fx-font-size: 16px; -fx-font-weight: bold",
                color
            )
        );

        return cardVBoxComponent;
    }
    
    private VBox getRightSummaryCard() {
        VBox rightSummaryCard = new VBox();

        Text title = new Text("Acesso rápido");
        TilePane quickActions = getQuickActions();

        rightSummaryCard.getStyleClass().add("summary-card-right");
        title.getStyleClass().add("quick-access-title");

        rightSummaryCard.getChildren().addAll(title, quickActions);

        return rightSummaryCard;
    }

    private TilePane getQuickActions() {
        TilePane quickActions = new TilePane();
        OperationButton outputButton = new OperationButton();
        OperationButton incomingButton = new OperationButton();
        OperationButton goalButton = new OperationButton();

        quickActions.getStyleClass().add("quick-actions");
        
        quickActions
            .getChildren()
            .addAll(
                outputButton.getComponent("SAÍDA", IconEnum.SAIDA), 
                incomingButton.getComponent("ENTRADA", IconEnum.ENTRADA),
                goalButton.getComponent("META", IconEnum.META)
            );

        incomingButton.getComponent().setOnMouseClicked(e -> {
            incomingPopup.getPopup().showPopup();
        });
        
        return quickActions;
    }
}