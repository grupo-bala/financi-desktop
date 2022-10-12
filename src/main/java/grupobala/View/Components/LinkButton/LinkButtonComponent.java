package grupobala.View.Components.LinkButton;

import grupobala.View.Components.Component.Component;
import javafx.scene.control.Button;

public class LinkButtonComponent implements Component {

    public Button getComponent() {
        Button button = new Button();

        button.getStyleClass().add("financi-link-button");
        button
            .getStylesheets()
            .add(
                "file:src/main/java/grupobala/View/Components/LinkButton/LinkButton.css"
            );

        return button;
    }
}
