package grupobala;

import grupobala.View.PageManager;
import grupobala.View.Pages.Authentication.SignIn.SignInPage;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    private static App instance;

    public static App getInstance() {
        return App.instance;
    }

    @Override
    public void start(Stage stage) throws IOException {
        SignInPage signInPage = new SignInPage();
        new PageManager(stage, signInPage);
    }

    public static void uiMain(String[] args) {
        App.instance = new App();
        App.launch();
    }
}
