package grupobala.View.Components.GoalView;

import grupobala.Entities.Goal.IGoal.IGoal;
import grupobala.View.Components.Button.ButtonComponent;
import grupobala.View.Components.Card.CardVBoxComponent;
import grupobala.View.Components.Component.Component;
import grupobala.View.Components.GoalList.GoalLambda;
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

public class GoalViewComponent implements Component {

    GoalLambda deleteCallback;
    VBox container = new CardVBoxComponent().getComponent();
    PopupComponent goalPopup = new PopupComponent();
    IGoal goal;

    public GoalViewComponent(IGoal goal) {
        this.goal = goal;

        container
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/GoalView/GoalViewComponent.css"
            );

        container.getStyleClass().add("goal-view-container");

        VBox topBox = getTopBox();
        HBox bottomBox = getBottomBox();
        container.getChildren().addAll(topBox, bottomBox);

        goalPopup.getComponent().getChildren().add(container);
    }

    public void setOnDelete(GoalLambda deleteCallback) {
        this.deleteCallback = deleteCallback;
    }

    @Override
    public StackPane getComponent() {
        return goalPopup.getComponent();
    }

    public PopupComponent getPopup() {
        return goalPopup;
    }

    public void setgoal(IGoal goal) {
        this.goal = goal;
    }

    private VBox getTopBox() {
        VBox topBox = new VBox();
        HBox titleBox = new HBox();
        Text title = new Text(goal.getTitle());
        Text value = new Text(
            String.format("R$ %.2f", goal.getAmountDeposited())
        );
        Image trashIcon = new Image(
            "file:src/main/resources/grupobala/images/trash.png"
        );
        ImageView trash = new ImageView(trashIcon);

        trash.setOnMouseClicked(e -> {
            deleteCallback.onClick(this.goal);
        });
        topBox.getStyleClass().add("top-box");
        titleBox.getStyleClass().add("top-box-titlebox");
        title.getStyleClass().add("top-box-title");
        value.getStyleClass().add("top-box-value");
        trash.getStyleClass().add("top-box-trash");

        titleBox.getChildren().addAll(title, trash);
        topBox.getChildren().addAll(titleBox, value);

        return topBox;
    }

    private HBox getBottomBox() {
        HBox bottomBox = new HBox();
        VBox leftSide = new VBox();
        VBox rightSide = new VBox();
        TextWithLabelComponent date = new TextWithLabelComponent(
            "Pretendo alcançar em",
            new SimpleDateFormat("dd/MM/yyyy").format(goal.getExpectedDate())
        );
        TextWithLabelComponent target = new TextWithLabelComponent(
            "Objetivo total",
            String.format("R$ %.2f", goal.getObjective())
        );
        TextWithLabelComponent idealMonth = new TextWithLabelComponent(
            "Ideal por mês",
            String.format("R$ %.2f", goal.getIdealValuePerMonth())
        );
        Button editButton = new ButtonComponent().getComponent();
        Button depositButton = new ButtonComponent().getComponent();
        Button completeButton = new ButtonComponent().getComponent();
        HBox rightSideAlignment = new HBox();

        editButton.setStyle(
            "-fx-background-color: #2B2F2B; -fx-text-fill: #168CC0; -fx-border-width: 2; -fx-border-color: #ffffff20; -fx-cursor: hand; -fx-border-radius: 3; -fx-min-width: 80px;"
        );
        depositButton.setStyle(
            "-fx-background-color: #168CC0; -fx-text-fill: #000000AA; -fx-cursor: hand; -fx-border-radius: 4px; -fx-background-radius: 4px; -fx-min-width: 80px; -fx-font-weight: bold;"
        );
        completeButton.setStyle(
            "-fx-background-color: #2B2F2B; -fx-text-fill: #168CC0; -fx-border-width: 2; -fx-border-color: #ffffff20; -fx-cursor: hand; -fx-border-radius: 3; -fx-min-width: 80px;"
        );
        leftSide.getStyleClass().add("bot-box-side");
        rightSide.getStyleClass().add("bot-box-side");

        editButton.setText("Editar");
        depositButton.setText("Depositar");
        completeButton.setText("Concluir");
        HBox.setHgrow(rightSideAlignment, Priority.ALWAYS);
        rightSideAlignment.setAlignment(Pos.BASELINE_RIGHT);

        leftSide
            .getChildren()
            .addAll(
                date.getComponent(),
                idealMonth.getComponent(),
                depositButton
            );
        rightSide
            .getChildren()
            .addAll(target.getComponent(), editButton, completeButton);
        bottomBox.getStyleClass().add("bot-box");

        rightSideAlignment.getChildren().add(rightSide);
        bottomBox.getChildren().addAll(leftSide, rightSideAlignment);

        return bottomBox;
    }
}
