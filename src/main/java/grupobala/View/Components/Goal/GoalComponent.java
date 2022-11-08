package grupobala.View.Components.Goal;

import grupobala.Entities.Goal.IGoal.IGoal;
import grupobala.View.Components.Card.CardHBoxComponent;
import grupobala.View.Components.Component.Component;
import grupobala.View.Components.GoalList.GoalLambda;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GoalComponent implements Component {

    IGoal goal;
    GoalLambda onClickCallback;

    HBox mainPane = new CardHBoxComponent().getComponent();

    public HBox getComponent() {
        return this.mainPane;
    }

    public GoalComponent(IGoal goal) {
        this.goal = goal;
        buildComponent();
    }

    public void setOnClick(GoalLambda onClickCallback) {
        this.onClickCallback = onClickCallback;
        buildComponent();
    }

    private void buildComponent() {
        this.mainPane = new HBox();

        this.mainPane.getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/Goal/GoalComponent.css"
            );

        this.mainPane.getStyleClass().add("financi-goal-container");

        HBox leftSideContainer = new HBox();
        VBox leftSide = getLeftSide();
        VBox rightSide = getRightSide();

        HBox.setHgrow(leftSideContainer, Priority.ALWAYS);
        leftSideContainer.getChildren().add(leftSide);
        this.mainPane.getChildren().addAll(leftSideContainer, rightSide);

        this.mainPane.setOnMouseClicked(e -> {
            this.onClickCallback.onClick(this.goal);
        });
    }

    private VBox getLeftSide() {
        VBox container = new VBox();
        HBox bottomContainer = getGoalInfo();
        Text title = new Text(this.goal.getTitle());

        container.getStyleClass().add("financi-goal-left");
        title.getStyleClass().add("financi-goal-title");

        container.getChildren().addAll(title, bottomContainer);

        return container;
    }

    private HBox getGoalInfo() {
        HBox container = new HBox();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(this.goal.getExpectedDate());
        VBox goalValue = getInfoUnit(
            "Objetivo",
            String.format("R$ %.2f", this.goal.getObjective()),
            "#ffffffaa",
            "#168CC0"
        );
        VBox dateValue = getInfoUnit(
            "Data estimada",
            formatter.format(dateCalendar.getTime()),
            "#ffffffaa",
            "white"
        );
        VBox idealValue = getInfoUnit(
            "Ideal por mês",
            String.format("R$ %.2f", this.goal.getIdealValuePerMonth()),
            "#ffffffaa",
            "#168CC0"
        );

        container.getStyleClass().add("financi-goal-info");

        container.getChildren().addAll(goalValue, dateValue, idealValue);

        return container;
    }

    private VBox getInfoUnit(
        String top,
        String bottom,
        String topColor,
        String bottomColor
    ) {
        VBox container = new VBox();
        Text topText = new Text(top);
        Text bottomText = new Text(bottom);

        container.getStyleClass().add("financi-goal-info-unit");

        topText.setStyle(
            String.format("-fx-fill: %s; -fx-font-weight: bold;", topColor)
        );
        bottomText.setStyle(String.format("-fx-fill: %s;", bottomColor));

        container.getChildren().addAll(topText, bottomText);

        return container;
    }

    private VBox getRightSide() {
        VBox container = new VBox();
        double completePercentage =
            ((this.goal.getAmountDeposited() * 100.0) / this.goal.getObjective()) / 100.0;
        Text percentageText = new Text(
            String.format("%.1f%% atingido", completePercentage * 100.0)
        );
        Text depositedText = new Text(
            String.format("R$ %.2f", this.goal.getAmountDeposited())
        );
        Text remainingText = new Text(
            String.format("Faltam R$ %.2f", this.goal.getObjective() - this.goal.getAmountDeposited())
        );
        HBox progressBox = getProgressBar(completePercentage);

        container.getStyleClass().add("financi-goal-right");
        percentageText.setStyle(
            "-fx-fill: #168CC0; -fx-font-weight: bold; -fx-font-size: 14px;"
        );
        depositedText.setStyle(
            "-fx-fill: #168CC0; -fx-font-weight: bold; -fx-font-size: 18px;"
        );
        remainingText.setStyle("-fx-fill: #ffffffaa; -fx-font-size: 13px;");

        container
            .getChildren()
            .addAll(percentageText, progressBox, depositedText, remainingText);

        return container;
    }

    private HBox getProgressBar(double percentage) {
        HBox container = new HBox();
        Region inner = new Region();

        container.getStyleClass().add("financi-goal-progress");
        inner.getStyleClass().add("financi-goal-inner");
        inner.setMinWidth(200 * percentage);

        container.getChildren().add(inner);

        return container;
    }
}
