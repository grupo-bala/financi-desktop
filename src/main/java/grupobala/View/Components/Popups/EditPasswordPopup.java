package grupobala.View.Components.Popups;

import grupobala.Controller.User.IUserController.IUserController;
import grupobala.Controller.User.UserController;
import grupobala.View.Components.Component.Component;
import grupobala.View.Components.Popup.PopupComponent;
import grupobala.View.Components.TextField.PasswordFieldComponent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EditPasswordPopup implements Component {

    private PopupComponent popup = new PopupComponent();
    private PasswordField oldpassword = new PasswordFieldComponent()
        .getComponent();
    private PasswordField newPassword = new PasswordFieldComponent()
        .getComponent();
    private PasswordField newPasswordConfirmation = new PasswordFieldComponent()
        .getComponent();
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

        components
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/OperationPopup/EditPasswordPopup.css"
            );

        components.getStyleClass().add("op-container");
        oldpassword.getStyleClass().add("inputs");
        newPassword.getStyleClass().add("inputs");
        newPasswordConfirmation.getStyleClass().add("inputs");
        confirmationButton.getStyleClass().add("confirm-button");

        components
            .getChildren()
            .addAll(
                titleExitButton,
                getOldPasswordInput(),
                getNewPasswordInput(),
                getNewPasswordConfirmationInput(),
                feedbackError,
                confirmationButton
            );

        confirmationButton.setOnMouseClicked(e -> {
            try {
                checkFieldMiss();
                handleConfirm();
            } catch (Exception error) {
                handleError(error.getMessage());
            }
        });

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

    private void editPassword() throws Exception {
        IUserController userController = new UserController();

        String oldPassword = this.oldpassword.getText();
        String newPassword = this.newPassword.getText();
        String newPasswordConfirm = this.newPasswordConfirmation.getText();

        if (!newPassword.equals(newPasswordConfirm)) {
            throw new Exception("Senhas não correspondentes");
        } else {
            try {
                userController.updatePassword(oldPassword, newPasswordConfirm);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
    }

    private void checkFieldMiss() throws Exception {
        if (
            this.oldpassword.getText() == "" ||
            this.newPassword.getText() == "" ||
            this.newPasswordConfirmation.getText() == ""
        ) {
            throw new Exception("Preencha todos os campos");
        }
    }

    private void clearFields() {
        this.oldpassword.clear();
        this.newPassword.clear();
        this.newPasswordConfirmation.clear();
    }

    private void handleConfirm() {
        try {
            editPassword();
            System.out.println("Senha alterada");
            this.popup.hidePopup();
            clearFields();
        } catch (Exception e) {
            handleError(e.getMessage());
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
}
