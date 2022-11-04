package grupobala.View.Components.Popup;

import grupobala.View.Components.Component.Component;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class PopupComponent implements Component {

    private StackPane container = new StackPane();
    private Region background = new Region();

    public PopupComponent() {
        this.hidePopup();
        background.getStyleClass().add("financi-popup-rect");
        container.getStyleClass().addAll("financi-popup-container");
        container
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/Popup/Popup.css"
            );

        container.getChildren().add(background);
        background.setOnMouseClicked(e -> {
            this.hidePopup();
        });
    }

    public StackPane getComponent() {
        return container;
    }

    public void showPopup() {
        container.setVisible(true);
    }

    public void hidePopup() {
        container.setVisible(false);
    }
}
