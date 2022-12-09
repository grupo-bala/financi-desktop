package grupobala.View.Components.DocumentButton;

import grupobala.View.Components.Component.Component;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DocumentButton implements Component {

    private HBox hBox = new HBox();

    public DocumentButton(IconType type) {
        this.setComponent(type);
    }

    @Override
    public HBox getComponent() {
        return this.hBox;
    }

    private void setComponent(IconType type) {
        Image filterImage = new Image(type.path);
        ImageView filterIcon = new ImageView(filterImage);

        this.hBox.setStyle("-fx-cursor: hand;");
        this.hBox.getChildren().add(filterIcon);
    }

    public enum IconType {
        PDF("file:src/main/resources/grupobala/images/export-pdf.png"),
        CSV("file:src/main/resources/grupobala/images/export-csv.png");

        public final String path;

        private IconType(String path) {
            this.path = path;
        }
    }
}
