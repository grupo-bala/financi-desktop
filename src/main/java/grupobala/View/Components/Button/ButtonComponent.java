package grupobala.View.Components.Button;

import grupobala.View.Components.Component.Component;
import javafx.scene.control.Button;

public class ButtonComponent implements Component {

    Button button = new Button();

    public ButtonComponent() {
        button.getStyleClass().add("financi-button");
        button
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/Button/Button.css"
            );
    }

    @Override
    public Button getComponent() {
        return button;
    }
}
