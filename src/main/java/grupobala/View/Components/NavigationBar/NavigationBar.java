package grupobala.View.Components.NavigationBar;

import grupobala.View.Components.Component.Component;
import grupobala.View.PageManager;
import grupobala.View.Pages.Courses.Courses;
import grupobala.View.Pages.Dashboard.Dashboard;
import grupobala.View.Pages.ExtractPage.ExtractPage;
import grupobala.View.Pages.Settings.Settings;
import grupobala.View.Pages.SmartAnalysis.SmartAnalysis;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class NavigationBar implements Component {

    private HBox bar = new HBox();
    private ImageView logo;
    private HBox titles;
    private ImageView settings;
    private PageManager pageManager = new PageManager();
    Text dashboard = new Text("início");
    Text extract = new Text("transações");
    Text classes = new Text("aulas");
    Text analysis = new Text("análise inteligente");

    public NavigationBar() {
        VBox vbox = new VBox();
        Image iconSetting = new Image(
            "file:src/main/resources/grupobala/images/settings.png"
        );
        Image logoImage = new Image(
            "file:src/main/resources/grupobala/images/financi-logo-white.png"
        );
        settings = new ImageView(iconSetting);
        logo = new ImageView(logoImage);
        titles = getTitles();

        vbox.setOnMouseClicked(e -> {
            pageManager.setCurrentPage(new Settings());
        });

        bar
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/NavigationBar/NavigationBar.css"
            );
        bar.getStyleClass().add("navigation-bar");
        logo.getStyleClass().add("logo");
        vbox.getStyleClass().add("settings");

        HBox.setHgrow(titles, Priority.ALWAYS);

        vbox.getChildren().add(settings);
        bar.getChildren().addAll(logo, titles, vbox);
    }

    @Override
    public Node getComponent() {
        return this.bar;
    }

    private HBox getTitles() {
        HBox hbox = new HBox();

        hbox.getStyleClass().add("titles-box");
        dashboard.getStyleClass().add("title");
        extract.getStyleClass().add("title");
        classes.getStyleClass().add("title");
        analysis.getStyleClass().add("title");

        hbox.getChildren().addAll(dashboard, extract, classes, analysis);

        dashboard.setOnMouseClicked(e -> {
            pageManager.setCurrentPage(new Dashboard());
        });

        extract.setOnMouseClicked(e -> {
            pageManager.setCurrentPage(new ExtractPage());
        });

        classes.setOnMouseClicked(e -> {
            pageManager.setCurrentPage(new Courses());
        });

        analysis.setOnMouseClicked(e -> {
            PageManager pageManager = new PageManager();
            pageManager.setCurrentPage(new SmartAnalysis());
        });

        return hbox;
    }
}
