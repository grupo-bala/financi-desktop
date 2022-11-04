package grupobala;

import grupobala.View.PageManager;
import grupobala.View.Pages.Authentication.SignIn.SignInPage;
import grupobala.View.Pages.Dashboard.Dashboard;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        SignInPage signInPage = new SignInPage();
        new PageManager(stage, signInPage);
    }

    public static void uiMain(String[] args) {
        launch();
    }
}
