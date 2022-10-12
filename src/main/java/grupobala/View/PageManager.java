package grupobala.View;

import grupobala.View.Pages.Page.Page;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class PageManager {

    private static boolean isInitialized;
    private static Page currentPage;
    private static Stage currentStage;
    private static Scene scene;

    public PageManager(Stage stage, Page initialPage) {
        if (!isInitialized) {
            currentStage = stage;
            isInitialized = true;
            scene = new Scene(initialPage.getMainPane());

            Rectangle2D screenBounds = Screen.getPrimary().getBounds();
            stage.setWidth(screenBounds.getWidth());
            stage.setHeight(screenBounds.getHeight());
            stage.setTitle("Financi");
            stage.setScene(scene);
            stage.show();
        }
    }

    public PageManager() throws RuntimeException {
        if (currentStage == null) {
            throw new RuntimeException("É necessário inicializar um Stage");
        }
    }

    public void setCurrentPage(Page page) {
        currentPage = page;
        scene.setRoot(currentPage.getMainPane());
        currentStage.show();
    }
}
