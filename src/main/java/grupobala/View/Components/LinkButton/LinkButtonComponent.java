package grupobala.View.Components.LinkButton;

import grupobala.View.Components.Component.Component;
import javafx.scene.control.Button;

public class LinkButtonComponent implements Component {

    Button button = new Button();

    public LinkButtonComponent() {
        button.getStyleClass().add("financi-link-button");
        button
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/LinkButton/LinkButton.css"
            );
    }

    public Button getComponent() {
        return button;
    }
}
