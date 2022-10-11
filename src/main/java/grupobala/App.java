package grupobala;

import grupobala.View.PageManager;
import grupobala.View.Pages.Authentication.SignInPage;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        PageManager pageManager = new PageManager(stage);
        pageManager.setCurrentPage(new SignInPage());
    }

    public static void uiMain(String[] args) {
        launch();
    }
}
