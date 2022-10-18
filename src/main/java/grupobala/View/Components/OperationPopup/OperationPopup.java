package grupobala.View.Components.OperationPopup;

import grupobala.Entities.Category.CategoryEnum;
import grupobala.View.Components.Component.Component;
import grupobala.View.Components.Popup.PopupComponent;
import grupobala.View.Components.TextField.TextFieldComponent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class OperationPopup implements Component {

    private PopupComponent popup = new PopupComponent();

    public OperationPopup() {
        VBox components = getComponents();

        popup.getComponent().getChildren().add(components);
    }

    @Override
    public Node getComponent() {
        return popup.getComponent();
    }

    public VBox getComponents() {
        VBox components = new VBox();
        VBox description = getInput("Descrição", "description");
        HBox valueDate = getvalueDate();
        VBox category = getLabelCategory();
        Button confirm = getButton(
            "file:src/main/resources/grupobala/images/confirm-icon.png"
        );
        HBox titleExitButton = getTitleButton("Nova entrada");

        components.getStyleClass().add("container");
        valueDate.getStyleClass().add("inputs");
        description.getStyleClass().add("inputs");
        confirm.getStyleClass().add("confirm-button");

        components
            .getStylesheets()
            .add(
                "file:src/main/java/grupobala/View/Components/OperationPopup/OperationPopup.css"
            );

        components
            .getChildren()
            .addAll(titleExitButton, description, valueDate, category, confirm);

        return components;
    }

    private VBox getInput(String labelField, String classCss) {
        VBox vBox = new VBox();
        Text label = new Text(labelField);
        TextFieldComponent field = new TextFieldComponent();

        field.getComponent().getStyleClass().add(classCss);
        vBox.getStyleClass().add("field-label");
        label.getStyleClass().add("label");

        vBox.getChildren().addAll(label, field.getComponent());

        return vBox;
    }

    private HBox getvalueDate() {
        HBox hBox = new HBox();
        VBox value = getInput("Valor", "value");
        VBox date = getDate();

        value.getStyleClass().add("value-field");
        date.getStyleClass().add("date-field");
        hBox.getStyleClass().add("value-date");

        hBox.getChildren().addAll(value, date);

        return hBox;
    }

    private VBox getDate() {
        VBox vBox = new VBox();
        DatePicker date = new DatePicker();
        Text label = new Text("Data");

        date
            .getStylesheets()
            .add(
                "file:src/main/java/grupobala/View/Components/OperationPopup/Calendar.css"
            );

        vBox.getStyleClass().add("field-label");
        label.getStyleClass().add("label");

        vBox.getChildren().addAll(label, date);

        return vBox;
    }

    private ChoiceBox<String> getCategoryBox() {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();

        choiceBox
            .getStylesheets()
            .add(
                "file:src/main/java/grupobala/View/Components/OperationPopup/ChoiceBox.css"
            );

        choiceBox
            .getItems()
            .addAll(
                CategoryEnum.CLOTHING.displayedName,
                CategoryEnum.ENTERTAINMENT.displayedName,
                CategoryEnum.FOOD.displayedName,
                CategoryEnum.HEALTH.displayedName,
                CategoryEnum.PAYMENTS.displayedName,
                CategoryEnum.OTHERS.displayedName
            );

        return choiceBox;
    }

    private VBox getLabelCategory() {
        VBox vBox = new VBox();
        Text label = new Text("Categoria");
        ChoiceBox<String> category = getCategoryBox();

        category.getStyleClass().add("category-box");
        label.getStyleClass().add("label");
        vBox.getStyleClass().add("field-label");

        vBox.getChildren().addAll(label, category);

        return vBox;
    }

    private HBox getTitleButton(String text) {
        HBox hBox = new HBox();
        Text title = new Text(text);
        Button exit = getButton(
            "file:src/main/resources/grupobala/images/exit-icon.png"
        );

        title.getStyleClass().add("title");
        hBox.getStyleClass().add("text-exit-button");
        exit.getStyleClass().add("exit-button");

        hBox.getChildren().addAll(title, exit);

        return hBox;
    }

    private Button getButton(String pathImage) {
        Image image = new Image(pathImage);
        ImageView imageView = new ImageView(image);
        Button button = new Button("", imageView);

        return button;
    }

    public PopupComponent getPopup() {
        return this.popup;
    }
}
