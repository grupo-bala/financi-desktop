package grupobala.View.Pages.Settings;

import grupobala.View.Components.NavigationBar.NavigationBar;
import grupobala.View.Pages.Page.Page;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Settings implements Page{

    private StackPane mainPane = new StackPane();
    private NavigationBar navigationBar = new NavigationBar();

    @Override
    public Pane getMainPane() {
        VBox mainContainer = new VBox();

        mainContainer.getChildren().addAll(navigationBar.getComponent());

        mainPane.getChildren().addAll(mainContainer);

        return mainPane;
    }
    
}
