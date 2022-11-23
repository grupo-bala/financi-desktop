package grupobala.View.Components.Popups;

import grupobala.Controller.Transaction.ITransactionController.ITransactionController;
import grupobala.Controller.Transaction.TransactionController;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.User.User;
import grupobala.View.Components.Component.Component;
import grupobala.View.Components.Popup.PopupComponent;
import grupobala.View.Components.TextField.TextFieldComponent;
import java.time.LocalDate;
import java.util.Calendar;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TransactionPopup implements Component {

    private PopupComponent popup = new PopupComponent();
    private TextField descriptionField = new TextFieldComponent()
        .getComponent();
    private TextField valueField = new TextFieldComponent().getComponent();
    private DatePicker dateField = new DatePicker();
    private ChoiceBox<String> categoryField = new ChoiceBox<>();
    private Text buttonLabel = new Text();
    Button confirm = new Button("Adicionar");
    private boolean isIncoming = false;

    public TransactionPopup(String title) {
        VBox components = getComponents(title);

        popup.getComponent().getChildren().add(components);
    }

    @Override
    public Node getComponent() {
        return popup.getComponent();
    }

    public VBox getComponents(String title) {
        VBox components = new VBox();
        HBox titleExitButton = getTitleButton(title);
        VBox description = getDescriptionInput();
        HBox valueDate = getvalueDate();
        VBox category = getLabelCategory();

        valueDate.getStyleClass().add("inputs");
        description.getStyleClass().add("inputs");
        components.getStyleClass().add("op-container");
        confirm.getStyleClass().add("confirm-button");

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
                category,
                buttonLabel,
                confirm
            );

        return components;
    }

    public void setOnConfirm(OperationLambda callback) {
        confirm.setOnAction(e -> {
            try {
                checkFieldMiss();
                handleConfirm();
                callback.applyOperation();
            } catch (Exception error) {
                handleMissField(error.getMessage());
            }
        });
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

    private VBox getValueInput() {
        VBox vBox = new VBox();
        Text label = new Text("Valor");

        valueField.getStyleClass().add("value");
        vBox.getStyleClass().add("field-label");
        label.getStyleClass().add("label-value");

        vBox.getChildren().addAll(label, valueField);

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

    private ChoiceBox<String> getCategoryBox() {
        categoryField
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/OperationPopup/ChoiceBox.css"
            );

        categoryField
            .getItems()
            .addAll(
                CategoryEnum.CLOTHING.displayedName,
                CategoryEnum.ENTERTAINMENT.displayedName,
                CategoryEnum.FOOD.displayedName,
                CategoryEnum.HEALTH.displayedName,
                CategoryEnum.PAYMENTS.displayedName,
                CategoryEnum.OTHERS.displayedName
            );

        return categoryField;
    }

    private VBox getLabelCategory() {
        VBox vBox = new VBox();
        Text label = new Text("Categoria");
        ChoiceBox<String> category = getCategoryBox();

        category.getStyleClass().add("category-box");
        label.getStyleClass().add("label-category");
        vBox.getStyleClass().add("field-label");

        vBox.getChildren().addAll(label, category);

        return vBox;
    }

    private HBox getTitleButton(String text) {
        HBox hBox = new HBox();
        Text title = new Text(text);
        Button exit = getExitButton();

        if (text.equals("Nova entrada")) {
            this.isIncoming = true;
        }

        title.getStyleClass().add("title");
        hBox.getStyleClass().add("text-exit-button");
        exit.getStyleClass().add("exit-button");

        hBox.getChildren().addAll(title, exit);

        exit.setOnAction(e -> {
            clearInputs();
            hideButtonLabel();
            popup.hidePopup();
        });

        return hBox;
    }

    private Button getExitButton() {
        Image image = new Image(
            "file:src/main/resources/grupobala/images/exit-icon.png"
        );
        ImageView imageView = new ImageView(image);
        Button button = new Button("", imageView);

        return button;
    }

    private void clearInputs() {
        descriptionField.setText(null);
        valueField.setText(null);
        dateField.setValue(null);
        categoryField.setValue(null);
    }

    private void addTransaction(
        String description,
        String wage,
        LocalDate dateLocal,
        String category
    ) throws Exception {
        ITransactionController transactionController = new TransactionController();
        User user = new User();
        CategoryEnum categoryEnum = CategoryEnum.getCategory(category);
        double value = Double.valueOf(wage.replace(',', '.'));
        Calendar dateCalendar = Calendar.getInstance();

        if (!isIncoming) {
            value *= -1;
        }

        dateCalendar.set(
            dateLocal.getYear(),
            dateLocal.getMonthValue() - 1,
            dateLocal.getDayOfMonth()
        );

        try {
            transactionController.addTransaction(
                user.getID(),
                value,
                description,
                categoryEnum,
                dateCalendar.getTime()
            );
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void handleConfirm() {
        String description = descriptionField.getText();
        String value = valueField.getText();
        LocalDate date = dateField.getValue();
        String category = categoryField.getValue();

        try {
            addTransaction(description, value, date, category);
            System.out.println("Transação adicionada");
            clearInputs();
            popup.hidePopup();
        } catch (Exception error) {
            handleTransactionError(error.getMessage());
        }
    }

    private void checkFieldMiss() throws Exception {
        String description = descriptionField.getText();
        String value = valueField.getText();
        LocalDate date = dateField.getValue();
        String category = categoryField.getValue();

        if (
            description == null ||
            value == null ||
            date == null ||
            category == null
        ) {
            throw new Exception("Preencha todos os campos");
        }
    }

    private void handleMissField(String errorMsg) {
        System.out.println(errorMsg);
        buttonLabel.setText(errorMsg);
        buttonLabel.getStyleClass().clear();
        buttonLabel.getStyleClass().add("label-wrong");
    }

    private void handleTransactionError(String errorMsg) {
        buttonLabel.setText("Erro ao adicionar transação");
        buttonLabel.getStyleClass().clear();
        buttonLabel.getStyleClass().add("label-wrong");
    }

    private void hideButtonLabel() {
        buttonLabel.setText("");
        buttonLabel.getStyleClass().clear();
        buttonLabel.getStyleClass().add("label-hide");
    }

    public PopupComponent getPopup() {
        return this.popup;
    }
}
