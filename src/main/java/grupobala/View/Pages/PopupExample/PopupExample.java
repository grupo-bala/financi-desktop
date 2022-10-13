package grupobala.View.Pages.PopupExample;

import grupobala.View.Components.Popup.PopupComponent;
import grupobala.View.Pages.Page.Page;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class PopupExample implements Page {

    // Needs to be a StackPane
    private StackPane mainPane = new StackPane();

    public StackPane getMainPane() {
        Text title = new Text();
        Button button = new Button("open popup");
        PopupComponent popup = new PopupComponent();

        StackPane card = new StackPane();
        Button buttonPopup = new Button("close popup");
        card.getChildren().add(buttonPopup);
        card.getStyleClass().add("card");
        // getComponent() returns the javafx intern node
        popup.getComponent().getChildren().add(card);

        mainPane
            .getStylesheets()
            .add(
                "file:src/main/java/grupobala/View/Pages/PopupExample/PopupExample.css"
            );
        mainPane.getStyleClass().add("container");
        title.setText("Teste");

        button.setOnAction(e -> {
            // if you need to use show or hide in other methodes
            // make the popup a class attribute like the mainPane
            popup.showPopup();
        });

        buttonPopup.setOnAction(e -> {
            popup.hidePopup();
        });

        mainPane.getChildren().addAll(title, button, popup.getComponent());

        return mainPane;
    }
}
