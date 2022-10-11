package grupobala.View.Components;

import grupobala.View.Components.ComponentFactory.ComponentFactory;
import javafx.scene.control.TextField;

public class TextFieldFactory implements ComponentFactory {

    public TextField getComponent() {
        TextField textField = new TextField();
        textField.getStyleClass().add("financi-text-field");
        textField
            .getStylesheets()
            .add(
                "file:src/main/java/grupobala/View/Components/TextFieldFactory.css"
            );

        return textField;
    }
}
