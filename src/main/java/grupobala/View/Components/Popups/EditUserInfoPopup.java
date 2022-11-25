package grupobala.View.Components.Popups;

import grupobala.Controller.User.IUserController.IUserController;
import grupobala.Controller.User.UserController;
import grupobala.Entities.User.IUser.IUser;
import grupobala.Entities.User.User;
import grupobala.View.Components.Component.Component;
import grupobala.View.Components.LinkButton.LinkButtonComponent;
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

public class EditUserInfoPopup implements Component {

    IUser user = new User();
    private PopupComponent popup = new PopupComponent();
    private TextField usernameInput = new TextFieldComponent().getComponent();
    private TextField nameInput = new TextFieldComponent().getComponent();
    private Button changePassword = new LinkButtonComponent().getComponent();
    private Button confirm = new Button("   Confirmar   ");
    private Text feedbackError = new Text();

    public EditUserInfoPopup() {
        this.popup.getComponent().getChildren().addAll(getComponents());
        this.setFields();
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
        VBox usernameBox = getUsernameInput();
        VBox nameBox = getNameInput();
        changePassword.setText("Alterar senha");

        components
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/OperationPopup/EditUserInfoPopup.css"
            );

        components.getStyleClass().add("op-container");
        usernameBox.getStyleClass().add("inputs");
        nameBox.getStyleClass().add("inputs");
        changePassword.getStyleClass().add("change-password-button");
        confirm.getStyleClass().add("confirm-button");

        components
            .getChildren()
            .addAll(
                titleExitButton,
                usernameBox,
                nameBox,
                feedbackError,
                confirm,
                changePassword
            );

        changePassword.setOnMouseClicked(e -> {
            this.popup.hidePopup();
        });

        return components;
    }

    private HBox getTitleButton() {
        HBox hBox = new HBox();
        Text title = new Text("Editar dados");
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

    private VBox getUsernameInput() {
        VBox vBox = new VBox();
        Text label = new Text("Username");

        usernameInput.getStyleClass().add("input-field");
        vBox.getStyleClass().add("field-label");
        label.getStyleClass().add("label");

        vBox.getChildren().addAll(label, usernameInput);

        return vBox;
    }

    private VBox getNameInput() {
        VBox vBox = new VBox();
        Text label = new Text("Nome       ");

        nameInput.getStyleClass().add("input-field");
        vBox.getStyleClass().add("field-label");
        label.getStyleClass().add("label");

        vBox.getChildren().addAll(label, nameInput);

        return vBox;
    }

    public void setOnConfirm(OperationLambda callback) {
        confirm.setOnMouseClicked(e -> {
            try {
                checkField();
                handleConfirm();
                callback.applyOperation();
            } catch (Exception error) {
                handleError(error.getMessage());
            }
        });
    }

    public void setOnChangePassword(OperationLambda callback) {
        changePassword.setOnMouseClicked(e -> {
            this.popup.hidePopup();
            callback.applyOperation();
        });
    }

    private void setFields() {
        this.usernameInput.setText(user.getUsername());
        this.nameInput.setText(user.getName());
    }

    private void checkField() throws Exception {
        if (
            this.usernameInput.getText() == "" || this.nameInput.getText() == ""
        ) {
            throw new Exception("Preecha todos os campos");
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

    private void handleConfirm() {
        try {
            editInfo();
            System.out.println("Dados editados");
            this.popup.hidePopup();
        } catch (Exception e) {
            handleError(e.getMessage());
        }
    }

    private void editInfo() throws Exception {
        try {
            IUserController userController = new UserController();
            userController.editUserInfo(
                this.usernameInput.getText(),
                this.nameInput.getText()
            );
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
