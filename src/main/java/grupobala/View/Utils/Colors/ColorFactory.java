package grupobala.View.Utils.Colors;

import javafx.scene.paint.Color;

public class ColorFactory {
    public static Color fromRGBA(int r, int g, int b, int a) {
        return new Color(
            r / 255.0,
            g / 255.0,
            b / 255.0,
            a / 255.0
        );
    }
}
