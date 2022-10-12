package grupobala.View.Components.TextField;

import grupobala.View.Components.Component.Component;
import javafx.scene.control.TextField;

public class TextFieldComponent implements Component {

    @Override
    public TextField getComponent() {
        TextField textField = new TextField();
        textField.getStyleClass().add("financi-text-field");
        textField
            .getStylesheets()
            .add(
                "file:src/main/java/grupobala/View/Components/TextField/TextField.css"
            );

        return textField;
    }
}
