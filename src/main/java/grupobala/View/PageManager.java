package grupobala.View;

import grupobala.View.Page.Page;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class PageManager {
    private static boolean isInitialized;
    private static Page currentPage;
    private static Stage currentStage;

    public PageManager(Stage stage) {
        if (!isInitialized) {
            currentStage = stage;
            isInitialized = true;

            Rectangle2D screenBounds = Screen.getPrimary().getBounds();
            stage.setWidth(screenBounds.getWidth());
            stage.setHeight(screenBounds.getHeight());
            stage.setTitle("Financi");
        }
    }

    public PageManager() throws Exception {
        if (currentStage == null) {
            throw new Exception("É necessário inicializar um Stage");
        }
    }

    public void setCurrentPage(Page page) {
        currentPage = page;
        currentStage.setScene(currentPage.getScene());
        currentStage.show();
    }
}
