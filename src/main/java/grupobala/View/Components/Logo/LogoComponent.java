package grupobala.View.Components.Logo;

import grupobala.View.Components.Component.Component;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LogoComponent implements Component {

    @Override
    public VBox getComponent() {
        VBox logoVBox = new VBox();
        Image logoImg = new Image(
            "file:src/main/resources/grupobala/images/financi-logo.png"
        );
        ImageView logo = new ImageView(logoImg);
        Text financiTitle = new Text("Financi");

        logoVBox
            .getStylesheets()
            .add("file:src/main/java/grupobala/View/Components/Logo/Logo.css");

        logoVBox.getStyleClass().add("logo-container");
        financiTitle.getStyleClass().add("title");

        logoVBox.getChildren().addAll(logo, financiTitle);

        return logoVBox;
    }
}
