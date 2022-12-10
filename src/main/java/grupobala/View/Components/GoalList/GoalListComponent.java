package grupobala.View.Components.GoalList;

import grupobala.Controller.Goal.GoalController;
import grupobala.Controller.Goal.IGoalController.IGoalController;
import grupobala.Entities.Goal.IGoal.IGoal;
import grupobala.View.Components.Card.CardVBoxComponent;
import grupobala.View.Components.Component.Component;
import grupobala.View.Components.Goal.GoalComponent;
import java.util.ArrayList;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GoalListComponent implements Component {

    ArrayList<IGoal> goals;
    GoalLambda onClickCallback;
    VBox mainPane = new CardVBoxComponent().getComponent();

    public GoalListComponent() {
        IGoalController goalController = new GoalController();
        try {
            this.goals = goalController.getGoals();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            this.goals = new ArrayList<>();
        }

        buildComponent();
    }

    public VBox getComponent() {
        return this.mainPane;
    }

    public void setOnClick(GoalLambda onClickCallback) {
        this.onClickCallback = onClickCallback;
        buildComponent();
    }

    public void buildComponent() {
        this.mainPane = new VBox();

        this.mainPane.getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/GoalList/GoalListComponent.css"
            );

        this.mainPane.getStyleClass().add("financi-goals-container");

        loadGoals();
    }

    private void loadGoals() {
        IGoalController goalController = new GoalController();
        try {
            this.goals = goalController.getGoals();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            this.goals = new ArrayList<>();
        }

        Text title = new Text("Metas");
        title.getStyleClass().add("financi-goals-title");
        this.mainPane.getChildren().add(title);

        for (IGoal goal : this.goals) {
            GoalComponent goalComponent = new GoalComponent(goal);
            goalComponent.setOnClick(this.onClickCallback);
            this.mainPane.getChildren().add(goalComponent.getComponent());
        }
    }

    public void reloadGoals() {
        mainPane.getChildren().clear();
        loadGoals();
    }
}
