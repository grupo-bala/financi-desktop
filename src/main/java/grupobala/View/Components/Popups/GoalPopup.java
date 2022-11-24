package grupobala.View.Components.Popups;

import grupobala.Controller.Goal.GoalController;
import grupobala.Controller.Goal.IGoalController.IGoalController;
import grupobala.Entities.User.IUser.IUser;
import grupobala.Entities.User.User;
import grupobala.View.Components.Component.Component;
import grupobala.View.Components.Popup.PopupComponent;
import grupobala.View.Components.TextField.TextFieldComponent;
import java.time.LocalDate;
import java.util.Calendar;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GoalPopup implements Component {

    private PopupComponent popup = new PopupComponent();
    private TextField descriptionField = new TextFieldComponent()
        .getComponent();
    private TextField valueField = new TextFieldComponent().getComponent();
    private DatePicker dateField = new DatePicker();
    private Text feedbackError = new Text();
    Button confirm = new Button("Adicionar");

    public GoalPopup() {
        VBox components = getComponents();

        this.popup.getComponent().getChildren().add(components);
    }

    @Override
    public Node getComponent() {
        return popup.getComponent();
    }

    public PopupComponent getPopup() {
        return this.popup;
    }

    private VBox getComponents() {
        VBox components = new VBox();
        HBox titleExitButton = getTitleButton();
        VBox description = getDescriptionInput();
        HBox valueDate = getvalueDate();

        description.getStyleClass().add("inputs");
        valueDate.getStyleClass().add("inputs");
        confirm.getStyleClass().add("confirm-button");
        components.getStyleClass().add("goal-container");

        components
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/OperationPopup/OperationPopup.css"
            );

        components
            .getChildren()
            .addAll(
                titleExitButton,
                description,
                valueDate,
                feedbackError,
                confirm
            );

        return components;
    }

    private HBox getTitleButton() {
        HBox hbox = new HBox();
        Text title = new Text("Nova meta    ");
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

    private VBox getDescriptionInput() {
        VBox vBox = new VBox();
        Text label = new Text("Descrição");

        descriptionField.getStyleClass().add("description-field");
        vBox.getStyleClass().add("field-label");
        label.getStyleClass().add("label-description");

        vBox.getChildren().addAll(label, descriptionField);

        return vBox;
    }

    private HBox getvalueDate() {
        HBox hBox = new HBox();
        VBox value = getValueInput();
        VBox date = getDate();

        value.getStyleClass().add("value-field");
        date.getStyleClass().add("date-field");
        hBox.getStyleClass().add("value-date");

        hBox.getChildren().addAll(value, date);

        return hBox;
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

    private VBox getDate() {
        VBox vBox = new VBox();
        Text label = new Text("Data");

        dateField
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/OperationPopup/Calendar.css"
            );

        vBox.getStyleClass().add("field-label");
        label.getStyleClass().add("label-date");

        vBox.getChildren().addAll(label, dateField);

        return vBox;
    }

    private void clearInputs() {
        descriptionField.setText(null);
        valueField.setText(null);
        dateField.setValue(null);
    }

    private void hideButtonLabel() {
        feedbackError.setText("");
        feedbackError.getStyleClass().clear();
        feedbackError.getStyleClass().add("label-hide");
    }

    private void checkFieldMiss() throws Exception {
        String description = descriptionField.getText();
        String value = valueField.getText();
        LocalDate date = dateField.getValue();

        if (description == null || value == null || date == null) {
            throw new Exception("Preencha todos os campos");
        }
    }

    private void addGoal(String description, String value, LocalDate dateLocal)
        throws Exception {
        IGoalController goalController = new GoalController();
        IUser user = new User();
        double objectiveValue = Double.valueOf(value.replace(',', '.'));
        Calendar dateObjective = Calendar.getInstance();

        dateObjective.set(
            dateLocal.getYear(),
            dateLocal.getMonthValue() - 1,
            dateLocal.getDayOfMonth()
        );

        try {
            goalController.addGoal(
                user.getID(),
                description,
                objectiveValue,
                dateObjective
            );
        } catch (Exception e) {
            throw new Exception("Erro ao adicionar meta");
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

    private void handleConfirm() {
        String description = descriptionField.getText();
        String value = valueField.getText();
        LocalDate date = dateField.getValue();

        try {
            addGoal(description, value, date);
            System.out.println("Meta adicionada");
            clearInputs();
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
}
