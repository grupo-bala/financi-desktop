package grupobala.View.Components.FilterButton;

import grupobala.View.Components.Component.Component;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class FilterButton implements Component {

    private HBox HBox = new HBox();

    @Override
    public HBox getComponent() {
        return this.HBox;
    }

    
    public HBox getComponentClick() {

        Image filterImage = new Image("file:src/main/resources/grupobala/images/FilterExtract.png");
        ImageView filterIcon = new ImageView(filterImage);

        HBox.getChildren().add(filterIcon);

        return this.HBox;
    }
}
