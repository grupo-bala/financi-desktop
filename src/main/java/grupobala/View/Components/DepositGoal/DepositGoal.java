package grupobala.View.Components.DepositGoal;

import grupobala.Controller.Goal.GoalController;
import grupobala.Controller.Goal.IGoalController.IGoalController;
import grupobala.Entities.Goal.IGoal.IGoal;
import grupobala.View.Components.Component.Component;
import grupobala.View.Components.OperationPopup.OperationLambda;
import grupobala.View.Components.Popup.PopupComponent;
import grupobala.View.Components.TextField.TextFieldComponent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DepositGoal implements Component{
    
    IGoal goal;
    private PopupComponent popup = new PopupComponent();
    private TextField valueField = new TextFieldComponent().getComponent();
    private Text feedbackError = new Text();
    Button confirm = new Button("Adicionar");

    public DepositGoal() {
        VBox vbox = getComponents();

        popup.getComponent().getChildren().add(vbox);
    }

    @Override
    public Node getComponent() {
        return popup.getComponent();
    }
    
    public PopupComponent getPopup() {
        return this.popup;
    }

    public void setGoal(IGoal goal) {
        this.goal = goal;
    }

    private VBox getComponents() {
        VBox components = new VBox();
        HBox titleExitButton = getTitleButton();

        confirm.getStyleClass().add("confirm-button");
        components.getStyleClass().add("deposit-container");

        components
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/DepositGoal/DepositGoal.css"
            );

        components.getChildren().addAll(titleExitButton, getValueInput(), feedbackError, confirm);

        return components;
    }

    private HBox getTitleButton() {
        HBox hbox = new HBox();
        Text title = new Text("Depositar");
        Button exit = getExitButton();

        title.getStyleClass().add("title");
        exit.getStyleClass().add("exit-button");
        hbox.getStyleClass().add("text-exit-button");

        hbox.getChildren().addAll(title, exit);

        exit.setOnAction(e -> {
            clearInputs();
            hideButtonLabel();
            popup.hidePopup();
        });

        return hbox;
    }

    private Button getExitButton() {
        Image image = new Image(
            "file:src/main/resources/grupobala/images/exit-icon.png"
        );
        ImageView imageView = new ImageView(image);
        Button button = new Button("", imageView);

        return button;
    }

    private VBox getValueInput() {
        VBox vBox = new VBox();
        Text label = new Text("Valor");

        valueField.getStyleClass().add("value");
        vBox.getStyleClass().add("field-label");
        label.getStyleClass().add("label-value");

        vBox.getChildren().addAll(label, valueField);

        return vBox;
    }

    private void clearInputs() {
        valueField.setText(null);
    }

    private void checkFieldMiss() throws Exception {
        String value = valueField.getText();

        if (value == null) {
            throw new Exception("Preencha o campo");
        }
    }

    public void setOnConfirm(OperationLambda callback) {
        confirm.setOnAction(e -> {
            try {
                checkFieldMiss();
                handleConfirm();
                callback.applyOperation();
            } catch (Exception error) {
                handleError(error.getMessage());
            }
        });
    }

    private void handleConfirm() throws Exception {
        String value = valueField.getText();

        try {
            depositValueGoal(value);
            System.out.println("Deposito realizado");
            clearInputs();
            hideButtonLabel();
            popup.hidePopup();
        } catch (Exception error) {
            handleError(error.getMessage());
        }
    }

    private void handleError(String messageError) {
        feedbackError.setText(messageError);
        feedbackError.getStyleClass().clear();
        feedbackError.getStyleClass().add("label-wrong");
    }

    private void hideButtonLabel() {
        feedbackError.setText("");
        feedbackError.getStyleClass().clear();
        feedbackError.getStyleClass().add("label-hide");
    }

    void depositValueGoal(String value) throws Exception{
        IGoalController goalController = new GoalController();
        double depositValue = Double.valueOf(value.replace(',', '.'));

        try {
            goalController.depositGoal(depositValue, goal);
        } catch (Exception e) {
            throw new Exception("Erro ao depositar na meta");
        }
    }
}
