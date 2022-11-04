package grupobala.View.Components.TextField;

import grupobala.View.Components.Component.Component;
import javafx.scene.control.PasswordField;

public class PasswordFieldComponent implements Component {

    PasswordField passwordField = new PasswordField();

    public PasswordFieldComponent() {
        passwordField.getStyleClass().add("financi-text-field");
        passwordField
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/TextField/TextField.css"
            );
    }

    @Override
    public PasswordField getComponent() {
        return passwordField;
    }
}
