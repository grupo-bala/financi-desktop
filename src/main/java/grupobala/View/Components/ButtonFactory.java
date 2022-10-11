package grupobala.View.Components;

import grupobala.View.Components.ComponentFactory.ComponentFactory;
import javafx.scene.control.Button;

public class ButtonFactory implements ComponentFactory {

    public Button getComponent() {
        Button button = new Button();
        button.getStyleClass().add("financi-button");
        button
            .getStylesheets()
            .add(
                "file:src/main/java/grupobala/View/Components/ButtonFactory.css"
            );

        return button;
    }
}
