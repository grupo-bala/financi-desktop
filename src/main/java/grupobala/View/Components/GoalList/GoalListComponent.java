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
    VBox mainPane = new CardVBoxComponent().getComponent();

    public GoalListComponent() {
        IGoalController goalController = new GoalController();
        try {
            this.goals = goalController.getGoals();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            this.goals = new ArrayList<>();
        }

        this.mainPane.getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/GoalList/GoalListComponent.css"
            );
        buildComponent();
    }

    public VBox getComponent() {
        return this.mainPane;
    }

    public void buildComponent() {
        Text title = new Text("Metas");

        this.mainPane.getChildren().add(title);
        this.mainPane.getStyleClass().add("financi-goals-container");
        title.getStyleClass().add("financi-goals-title");

        for (IGoal goal : this.goals) {
            this.mainPane.getChildren()
                .add(
                    new GoalComponent(
                        goal.getTitle(),
                        goal.getObjective(),
                        goal.getExpectedDate(),
                        goal.getIdealValuePerMonth(),
                        goal.getAmountDeposited()
                    )
                        .getComponent()
                );
        }
    }
}
