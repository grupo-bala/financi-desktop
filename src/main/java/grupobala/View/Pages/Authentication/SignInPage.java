package grupobala.View.Pages.Authentication;

import grupobala.View.Components.ButtonFactory;
import grupobala.View.Components.TextFieldFactory;
import grupobala.View.Pages.Page.Page;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SignInPage implements Page {
    public Scene getScene() {
        VBox container = new VBox();
        Scene scene = new Scene(container);
        VBox logoVBox = getLogoVBox();
        VBox loginVBox = getLoginFormBox();
        
        container.getStyleClass().add("container");
        container.getChildren().addAll(
            logoVBox,
            loginVBox
        );

        scene.getStylesheets()
            .add("file:src/main/java/grupobala/View/Pages/Authentication/SignInPage.css");

        return scene;
    }

    private VBox getLogoVBox() {
        VBox logoVBox = new VBox();
        Image logoImg = new Image("file:src/main/resources/grupobala/images/financi-logo.png");
        ImageView logo = new ImageView(logoImg);
        Text financiTitle = new Text("Financi");

        logoVBox.getStyleClass().add("logo-container");
        financiTitle.getStyleClass().add("title");

        logoVBox.getChildren().addAll(
            logo,
            financiTitle
        );

        return logoVBox;
    }

    private VBox getLoginFormBox() {
        VBox loginFormVBox = new VBox();
        TextField usernameField = new TextFieldFactory().getComponent();
        TextField passwordField = new TextFieldFactory().getComponent();
        Button loginButton = new ButtonFactory().getComponent();

        loginFormVBox.getStyleClass().add("login-container");
        usernameField.setPromptText("Nome de usuário");
        usernameField.getStyleClass().add("input");
        passwordField.setPromptText("Senha");
        passwordField.getStyleClass().add("input");
        loginButton.setText("Entrar");
        loginButton.getStyleClass().add("login-button");

        loginFormVBox.getChildren().addAll(
            usernameField,
            passwordField,
            loginButton
        );

        return loginFormVBox;
    }
}
