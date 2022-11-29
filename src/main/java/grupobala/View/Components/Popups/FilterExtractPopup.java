package grupobala.View.Components.Popups;

import grupobala.Entities.Extract.Filter.DateFilter;
import grupobala.Entities.Extract.Filter.IFilter.IFilter;
import grupobala.View.Components.Component.Component;
import grupobala.View.Components.ExtractList.ExtractLambda;
import grupobala.View.Components.Popup.PopupComponent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class FilterExtractPopup implements Component {

    private PopupComponent popup = new PopupComponent();
    private DatePicker dateField = new DatePicker();
    private DatePicker dateFieldLeft = new DatePicker();
    private Text buttonLabel = new Text();
    Button confirm = new Button("Filtrar");

    public FilterExtractPopup() {
        VBox components = getComponents("Filtrar Transações");

        popup.getComponent().getChildren().add(components);
    }

    @Override
    public Node getComponent() {
        return popup.getComponent();
    }

    public VBox getComponents(String title) {
        VBox components = new VBox();
        HBox titleExitButton = getTitleButton(title);
        HBox valueDate = getvalueDate();

        valueDate.getStyleClass().add("inputs");
        components.getStyleClass().add("op-container");
        confirm.getStyleClass().add("confirm-button");

        components
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/FilterExtractPopup/FilterExtractPopup.css"
            );

        components
            .getChildren()
            .addAll(titleExitButton, valueDate, buttonLabel, confirm);

        return components;
    }

    public void setOnConfirm(FilterLambda callback) {
        confirm.setOnAction(e -> {
            try {
                checkFieldMiss();
                IFilter filter = new DateFilter(
                    Date.from(this.dateFieldLeft.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Date.from(this.dateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())
                );

                this.popup.hidePopup();
                callback.applyOperation(filter);
            } catch (Exception error) {
                handleMissField(error.getMessage());
            }
        });
    }

    private HBox getvalueDate() {
        HBox hBox = new HBox();
        VBox leftDate = getDateLeft("Data Inicial");
        VBox rightDate = getDate("Data Final");

        hBox.getStyleClass().add("dates-container");

        leftDate.getStyleClass().add("left-date-container");
        rightDate.getStyleClass().add("right-date-container");

        hBox.getChildren().addAll(leftDate, rightDate);

        return hBox;
    }

    private VBox getDate(String title) {
        VBox vBox = new VBox();
        Text label = new Text(title);

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

    private VBox getDateLeft(String title) {
        VBox vBox = new VBox();
        Text label = new Text(title);

        dateFieldLeft
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/OperationPopup/Calendar.css"
            );

        vBox.getStyleClass().add("field-label");
        label.getStyleClass().add("label-date");

        vBox.getChildren().addAll(label, dateFieldLeft);

        return vBox;
    }

    private HBox getTitleButton(String text) {
        HBox hBox = new HBox();
        Text title = new Text(text);
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

    private void clearInputs() {
        dateField.setValue(null);
        dateFieldLeft.setValue(null);
    }

    private void checkFieldMiss() throws Exception {
        LocalDate rightDate = dateField.getValue();
        LocalDate leftDate = dateFieldLeft.getValue();
        if (rightDate == null || leftDate == null) {
            throw new Exception("Preencha a data final e inicial");
        }
    }

    private void handleMissField(String errorMsg) {
        System.out.println(errorMsg);
        buttonLabel.setText(errorMsg);
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
