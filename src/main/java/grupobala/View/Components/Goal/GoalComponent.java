package grupobala.View.Components.Goal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import grupobala.View.Components.Card.CardHBoxComponent;
import grupobala.View.Components.Component.Component;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GoalComponent implements Component {
    String title;
    double goalValue;
    Date goalDate;
    double idealValuePerMonth;
    double currentValue;

    HBox mainPane = new CardHBoxComponent().getComponent();

    public HBox getComponent() {
        return this.mainPane;
    }

    public GoalComponent(String title, double goalValue, Date goalDate, double idealValuePerMonth, double currentValue) {
        this.title = title;
        this.goalValue = goalValue;
        this.goalDate = goalDate;
        this.idealValuePerMonth = idealValuePerMonth;
        this.currentValue = currentValue;

        this.mainPane.getStylesheets().add(
            "file:src/main/resources/grupobala/css/Components/Goal/GoalComponent.css"
        );

        this.mainPane.getStyleClass().add("financi-goal-container");
        buildComponent();
    }

    private void buildComponent() {
        HBox leftSideContainer = new HBox();
        VBox leftSide = getLeftSide();
        VBox rightSide = getRightSide();

        HBox.setHgrow(leftSideContainer, Priority.ALWAYS);
        leftSideContainer.getChildren().add(leftSide);
        this.mainPane.getChildren().addAll(
            leftSideContainer,
            rightSide
        );
    }

    private VBox getLeftSide() {
        VBox container = new VBox();
        HBox bottomContainer = getGoalInfo();
        Text title = new Text(this.title);

        container.getStyleClass().add("financi-goal-left");
        title.getStyleClass().add("financi-goal-title");

        container.getChildren().addAll(
            title,
            bottomContainer
        );

        return container;
    }

    private HBox getGoalInfo() {
        HBox container = new HBox();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(this.goalDate);
        VBox goalValue = getInfoUnit("Objetivo", String.format("R$ %.2f", this.goalValue), "#ffffffaa", "#168CC0");
        VBox dateValue = getInfoUnit("Data estimada", formatter.format(dateCalendar.getTime()), "#ffffffaa", "white");
        VBox idealValue = getInfoUnit("Ideal por mês", String.format("R$ %.2f", this.idealValuePerMonth), "#ffffffaa", "#168CC0");

        container.getStyleClass().add("financi-goal-info");

        container.getChildren().addAll(
            goalValue,
            dateValue,
            idealValue
        );

        return container;
    }

    private VBox getInfoUnit(String top, String bottom, String topColor, String bottomColor) {
        VBox container = new VBox();
        Text topText = new Text(top);
        Text bottomText = new Text(bottom);

        container.getStyleClass().add("financi-goal-info-unit");

        topText.setStyle(String.format("-fx-fill: %s; -fx-font-weight: bold;", topColor));
        bottomText.setStyle(String.format("-fx-fill: %s;", bottomColor));

        container.getChildren().addAll(
            topText,
            bottomText
        );

        return container;
    }

    private VBox getRightSide() {
        VBox container = new VBox();
        double completePercentage = ((this.currentValue * 100.0) / this.goalValue) / 100.0;
        Text percentageText = new Text(String.format("%.1f%% atingido", completePercentage * 100.0));
        Text depositedText = new Text(String.format("R$ %.2f", this.currentValue));
        Text remainingText = new Text(String.format("Faltam R$ %.2f", this.goalValue - this.currentValue));
        ProgressBar progressBar = new ProgressBar(completePercentage);

        container.getStyleClass().add("financi-goal-right");

        container.getChildren().addAll(
            percentageText,
            progressBar,
            depositedText,
            remainingText
        );

        return container;
    }

    private HBox getProgressBar() {
        HBox container = new HBox();
        Region inner = new Region();

        // inner.getStyleClass().add()

        return container;
    }

}
