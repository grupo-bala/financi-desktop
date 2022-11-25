package grupobala.View.Components.Popups;

import grupobala.View.Components.Component.Component;
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

public class EditPasswordPopup implements Component {

    private PopupComponent popup = new PopupComponent();
    private TextField oldpassword = new TextFieldComponent().getComponent();;
    private TextField newPassword = new TextFieldComponent().getComponent();;
    private TextField newPasswordConfirmation = new TextFieldComponent().getComponent();;
    private Button confirmationButton = new Button("Confirmar");
    private Text feedbackError = new Text();

    public EditPasswordPopup() {
        this.popup.getComponent().getChildren().add(getComponents());
    }
    
    @Override
    public Node getComponent() {
        return this.popup.getComponent();
    }

    public PopupComponent getPopup() {
        return this.popup;
    }
    
    private VBox getComponents() {
        VBox components = new VBox();
        HBox titleExitButton = getTitleButton();

        components.getStylesheets().add("file:src/main/resources/grupobala/css/Components/OperationPopup/EditPasswordPopup.css");
    
        components.getStyleClass().add("op-container");
        oldpassword.getStyleClass().add("inputs");
        newPassword.getStyleClass().add("inputs");
        newPasswordConfirmation.getStyleClass().add("inputs");
        confirmationButton.getStyleClass().add("confirm-button");
        
        components.getChildren().addAll(titleExitButton, getOldPasswordInput(), getNewPasswordInput(), getNewPasswordConfirmationInput(), feedbackError, confirmationButton);

        return components;
    }

    private HBox getTitleButton() {
        HBox hBox = new HBox();
        Text title = new Text("Editar senha");
        Button exit = getExitButton();

        title.getStyleClass().add("title");
        hBox.getStyleClass().add("text-exit-button");
        exit.getStyleClass().add("exit-button");

        hBox.getChildren().addAll(title, exit);

        exit.setOnAction(e -> {
            //hideButtonLabel();
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

    private VBox getOldPasswordInput() {
        VBox vBox = new VBox();
        Text label = new Text("Senha antiga             ");

        oldpassword.getStyleClass().add("input-field");
        vBox.getStyleClass().add("field-label");
        label.getStyleClass().add("label");

        vBox.getChildren().addAll(label, oldpassword);

        return vBox;
    }

    private VBox getNewPasswordInput() {
        VBox vBox = new VBox();
        Text label = new Text("Senha nova               ");

        newPassword.getStyleClass().add("input-field");
        vBox.getStyleClass().add("field-label");
        label.getStyleClass().add("label");

        vBox.getChildren().addAll(label, newPassword);

        return vBox;
    }

    private VBox getNewPasswordConfirmationInput() {
        VBox vBox = new VBox();
        Text label = new Text("Repita a senha nova");

        newPasswordConfirmation.getStyleClass().add("input-field");
        vBox.getStyleClass().add("field-label");
        label.getStyleClass().add("label");

        vBox.getChildren().addAll(label, newPasswordConfirmation);

        return vBox;
    }
}
