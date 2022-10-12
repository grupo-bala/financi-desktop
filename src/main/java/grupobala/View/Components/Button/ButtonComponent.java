package grupobala.View.Components.Button;

import grupobala.View.Components.Component.Component;
import javafx.scene.control.Button;

public class ButtonComponent implements Component {

    @Override
    public Button getComponent() {
        Button button = new Button();
        button.getStyleClass().add("financi-button");
        button
            .getStylesheets()
            .add(
                "file:src/main/java/grupobala/View/Components/Button/Button.css"
            );

        return button;
    }
}
