package grupobala.View.Pages.Authentication.SignUp;

import grupobala.Controller.Authentication.AuthenticationController;
import grupobala.View.Components.Button.ButtonComponent;
import grupobala.View.Components.LinkButton.LinkButtonComponent;
import grupobala.View.Components.Logo.LogoComponent;
import grupobala.View.Components.TextField.PasswordFieldComponent;
import grupobala.View.Components.TextField.TextFieldComponent;
import grupobala.View.PageManager;
import grupobala.View.Pages.Authentication.SignIn.SignInPage;
import grupobala.View.Pages.Page.Page;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SignUpPage implements Page {

    TextField nameField = new TextFieldComponent().getComponent();
    TextField usernameField = new TextFieldComponent().getComponent();
    TextField wageField = new TextFieldComponent().getComponent();
    PasswordField passwordField = new PasswordFieldComponent().getComponent();
    Button signUpButton = new ButtonComponent().getComponent();
    Text buttonLabel = new Text();

    @Override
    public Pane getMainPane() {
        VBox container = new VBox();
        VBox logoVBox = getLogoVBox();
        VBox signUpVBox = getSignUpVBox();

        container.getStyleClass().add("container");
        container
            .getStylesheets()
            .add(
                "file:src/main/java/grupobala/View/Pages/Authentication/SignUp/SignUpPage.css"
            );

        container.getChildren().addAll(logoVBox, signUpVBox);

        return container;
    }

    private VBox getLogoVBox() {
        VBox logoVBox = new LogoComponent().getComponent();
        return logoVBox;
    }

    private VBox getSignUpVBox() {
        VBox signUpVBox = new VBox();
        VBox buttonVBox = getButtonVBox();

        nameField.setPromptText("Insira o seu nome");
        usernameField.setPromptText("Insira o seu nome de usuário");
        wageField.setPromptText("Insira a sua renda mensal");
        passwordField.setPromptText("Insira a sua senha");

        nameField.getStyleClass().add("input");
        usernameField.getStyleClass().add("input");
        wageField.getStyleClass().add("input");
        passwordField.getStyleClass().add("input");
        signUpVBox.getStyleClass().add("signup-vbox");

        signUpVBox
            .getChildren()
            .addAll(
                nameField,
                usernameField,
                wageField,
                passwordField,
                buttonVBox
            );

        return signUpVBox;
    }

    private VBox getButtonVBox() {
        VBox buttonVBox = new VBox();
        Button signInButton = new LinkButtonComponent().getComponent();

        signUpButton.setText("Registrar");
        signInButton.setText("Já possuo uma conta");
        signUpButton.getStyleClass().add("signup-button");
        signInButton.getStyleClass().add("signin-button");
        buttonVBox.getStyleClass().add("button-vbox");

        signUpButton.setOnAction(e -> {
            handleSignUp();
        });

        signInButton.setOnAction(e -> {
            PageManager pageManager = new PageManager();
            pageManager.setCurrentPage(new SignInPage());
        });

        buttonVBox
            .getChildren()
            .addAll(buttonLabel, signUpButton, signInButton);

        return buttonVBox;
    }

    private void handleSignUp() {
        AuthenticationController authController = new AuthenticationController();
        String username = usernameField.getText();
        String name = nameField.getText();
        String password = passwordField.getText();
        float wage = Float.parseFloat(wageField.getText());

        hideButtonLabel();

        try {
            authController.signUp(username, password, name, wage);
            new PageManager().setCurrentPage(new SignInPage());
            System.out.println("Registrado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            handleSignUpError();
        }
    }

    private void handleSignUpError() {
        buttonLabel.setText("Nome de usuário em uso");
        buttonLabel.getStyleClass().clear();
        buttonLabel.getStyleClass().add("label-wrong");
    }

    private void hideButtonLabel() {
        buttonLabel.setText("");
        buttonLabel.getStyleClass().clear();
        buttonLabel.getStyleClass().add("label-hide");
    }
}
