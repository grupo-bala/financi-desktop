package grupobala.View.Pages.Authentication.SignIn;

import grupobala.Controller.Authentication.AuthenticationController;
import grupobala.View.Components.Button.ButtonComponent;
import grupobala.View.Components.LinkButton.LinkButtonComponent;
import grupobala.View.Components.Logo.LogoComponent;
import grupobala.View.Components.TextField.PasswordFieldComponent;
import grupobala.View.Components.TextField.TextFieldComponent;
import grupobala.View.PageManager;
import grupobala.View.Pages.Authentication.SignUp.SignUpPage;
import grupobala.View.Pages.Dashboard.Dashboard;
import grupobala.View.Pages.Page.Page;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SignInPage implements Page {

    TextField usernameField = new TextFieldComponent().getComponent();
    PasswordField passwordField = new PasswordFieldComponent().getComponent();
    Button loginButton = new ButtonComponent().getComponent();
    Text buttonLabel = new Text();

    @Override
    public Pane getMainPane() {
        VBox container = new VBox();
        VBox logoVBox = getLogoVBox();
        VBox loginVBox = getLoginFormBox();

        container.getStyleClass().add("container");
        container.getChildren().addAll(logoVBox, loginVBox);

        container
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Pages/Authentication/SignInPage.css"
            );

        return container;
    }

    private VBox getLogoVBox() {
        VBox logoComponent = new LogoComponent().getComponent();
        return logoComponent;
    }

    private VBox getLoginFormBox() {
        VBox loginFormVBox = new VBox();
        VBox buttonVBox = getButtonVBox();

        loginFormVBox.getStyleClass().add("login-container");
        usernameField.setPromptText("Nome de usuário");
        usernameField.getStyleClass().add("input");
        passwordField.setPromptText("Senha");
        passwordField.getStyleClass().add("input");

        usernameField.setPromptText("Nome de usuário");
        passwordField.setPromptText("Senha");

        loginFormVBox
            .getChildren()
            .addAll(usernameField, passwordField, buttonVBox);

        return loginFormVBox;
    }

    private VBox getButtonVBox() {
        VBox buttonVBox = new VBox();
        Button signUpButton = new LinkButtonComponent().getComponent();

        signUpButton.setText("Criar uma conta");
        loginButton.setText("Entrar");
        loginButton.getStyleClass().add("login-button");

        loginButton.setOnAction(e -> {
            handleLogin();
        });

        signUpButton.setOnAction(e -> {
            PageManager pageManager = new PageManager();
            pageManager.setCurrentPage(new SignUpPage());
        });

        buttonVBox.getStyleClass().add("button-vbox");
        signUpButton.getStyleClass().add("signup-button");

        buttonVBox.getChildren().addAll(buttonLabel, loginButton, signUpButton);

        return buttonVBox;
    }

    private void handleLogin() {
        AuthenticationController authController = new AuthenticationController();
        String username = usernameField.getText();
        String password = passwordField.getText();

        hideButtonLabel();

        try {
            authController.signIn(username, password);
            new PageManager().setCurrentPage(new Dashboard());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            handleLoginError();
        }
    }

    private void handleLoginError() {
        buttonLabel.setText("Usuário ou senha incorretos");
        buttonLabel.getStyleClass().clear();
        buttonLabel.getStyleClass().add("label-wrong");
    }

    private void hideButtonLabel() {
        buttonLabel.setText("");
        buttonLabel.getStyleClass().clear();
        buttonLabel.getStyleClass().add("label-hide");
    }
}
