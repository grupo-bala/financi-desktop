package grupobala.View.Components.Popups;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

import grupobala.Controller.Transaction.TransactionController;
import grupobala.Controller.Transaction.ITransactionController.ITransactionController;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.Entities.User.User;
import grupobala.View.Components.Component.Component;
import grupobala.View.Components.Popup.PopupComponent;
import grupobala.View.Components.TextField.TextFieldComponent;
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

public class EditTransactionPopup implements Component{

    private ITransaction transaction;
    private PopupComponent popup = new PopupComponent();
    private TextField descriptionField = new TextFieldComponent()
        .getComponent();
    private TextField valueField = new TextFieldComponent().getComponent();
    private DatePicker dateField = new DatePicker();
    private ChoiceBox<String> categoryField = new ChoiceBox<>();
    private Text feedbackError = new Text();
    private Button confirm = new Button("Confirmar");

    public EditTransactionPopup() {
        popup.getComponent().getChildren().addAll(this.getComponents());
    }

    @Override
    public Node getComponent() {
        return this.popup.getComponent();
    }

    public PopupComponent getPopup() {
        return this.popup;
    }

    public void setTransaction(ITransaction transaction) {
        this.transaction = transaction;
        loadValues();
    }

    private VBox getComponents() {
        VBox components = new VBox();

        HBox titleExitButton = getTitleButton();
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
                feedbackError,
                confirm
            );

        return components;
    }

    private HBox getTitleButton() {
        HBox hBox = new HBox();
        Text title = new Text("Editar transação");
        Button exit = getExitButton();

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

    private void clearInputs() {
        descriptionField.setText(null);
        valueField.setText(null);
        dateField.setValue(null);
        categoryField.setValue(null);
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

    private void editTransaction(String title, String wage, LocalDate dateLocal, String category) throws Exception{
        ITransactionController transactionController = new TransactionController();
        User user = new User();
        
        CategoryEnum categoryEnum = CategoryEnum.getCategory(category);
        double value = Double.valueOf(wage.replace(',', '.'));
        Calendar dateCalendar = Calendar.getInstance();

        dateCalendar.set(
            dateLocal.getYear(),
            dateLocal.getMonthValue() - 1,
            dateLocal.getDayOfMonth()
        );

        if (dateCalendar.getTime().after(Calendar.getInstance().getTime())) {
            throw new Exception("Data indisponível");
        } 
        else {
            transaction.setTitle(title);
            transaction.setValue(value);
            transaction.setDate(dateCalendar.getTime());
            transaction.setCategory(categoryEnum);

            try {
                transactionController.updateTransaction(user.getID(), transaction);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }   
    }

    private void handleConfirm() {
        String description = descriptionField.getText();
        String value = valueField.getText();
        LocalDate date = dateField.getValue();
        String category = categoryField.getValue();

        try {
            editTransaction(description, value, date, category);
            System.out.println("Meta editada");
            clearInputs();
            popup.hidePopup();
        } catch (Exception error) {
            handleError(error.getMessage());
        }
    }

    private void loadValues() {
        this.descriptionField.setText(transaction.getTitle());
        this.valueField.setText(
                String.format(Locale.US, "%.2f", transaction.getValue())
            );

        DateFormat formateDate = new SimpleDateFormat("yyyy-MM-dd");

        String dateString = formateDate.format(this.transaction.getDate());

        this.dateField.setValue(
                LocalDate.parse(
                    dateString,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")
                )
            );

        this.categoryField.setValue(transaction.getCategory().displayedName);
    }
}
