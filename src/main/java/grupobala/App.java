package grupobala;

import grupobala.View.PageManager;
import grupobala.View.Pages.Authentication.SignIn.SignInPage;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        SignInPage page = new SignInPage();
        new PageManager(stage, page);
    }

    public static void uiMain(String[] args) {
        launch();
    }
}
