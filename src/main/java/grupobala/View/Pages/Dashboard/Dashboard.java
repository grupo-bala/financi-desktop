package grupobala.View.Pages.Dashboard;

import grupobala.Entities.User.User;
import grupobala.View.Components.AvatarCard.AvatarCardComponent;
import grupobala.View.Components.Card.CardHBoxComponent;
import grupobala.View.Pages.Page.Page;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Dashboard implements Page {

    private StackPane mainPane = new StackPane();

    @Override
    public StackPane getMainPane() {
        VBox container = new VBox();
        CardHBoxComponent summaryCard = getSummaryCard();

        mainPane.getStyleClass().add("dashboard");
        container.getStyleClass().add("container");
        mainPane.getStylesheets().add(
            "file:src/main/java/grupobala/View/Pages/Dashboard/Dashboard.css"
        );

        mainPane.getChildren().add(container);
        container.getChildren().addAll(
            summaryCard.getComponent()
        );

        return mainPane;
    }

    private CardHBoxComponent getSummaryCard() {
        CardHBoxComponent hBox = new CardHBoxComponent();
        TilePane leftSummary = getLeftSummaryCard();

        hBox.getComponent().getStyleClass().add("summary-card");

        hBox.getComponent().getChildren().addAll(
            leftSummary
        );

        return hBox;
    }

    private TilePane getLeftSummaryCard() {
        TilePane leftSummaryCard = new TilePane();
        AvatarCardComponent avatarCard = new AvatarCardComponent();
        VBox balanceBox = getBalanceBox();

        leftSummaryCard.getStyleClass().add("summary-card-left");

        leftSummaryCard.getChildren().addAll(
            avatarCard.getComponent(),
            balanceBox
        );

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

        vBox.getChildren().addAll(
            title,
            balance
        );

        return vBox;
    }
}
