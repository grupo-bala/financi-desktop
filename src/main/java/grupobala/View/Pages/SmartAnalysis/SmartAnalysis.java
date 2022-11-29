package grupobala.View.Pages.SmartAnalysis;

import grupobala.Controller.SmartAnalysis.SmartAnalysisController;
import grupobala.Entities.User.User;
import grupobala.View.Components.NavigationBar.NavigationBar;
import grupobala.View.Pages.Page.Page;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SmartAnalysis implements Page {

    private StackPane mainPane = new StackPane();
    private NavigationBar navigationBar = new NavigationBar();
    private SmartAnalysisController smartAnalysisController = new SmartAnalysisController();

    @Override
    public StackPane getMainPane() {
        VBox mainContainer = new VBox();
        VBox container = new VBox();
        VBox entryOutputcontainer = new VBox();
        HBox analysisContainer = new HBox();
        VBox title = getTitlePage();
        VBox analysisTextVBox = new VBox();
        VBox holdHigherOutput = new VBox();
        VBox holdHigherEntry = new VBox();
        VBox outputPercentage = new VBox();
        VBox entryPercentage = new VBox();
        VBox hint = new VBox();
        String hintString = "";
        Text higherOutputText = new Text("Categoria com maior gasto:");
        Text higherEntryText = new Text("Categoria com maior entrada:");
        String stringOutput = "";
        String stringEntry = "";
        Text analysisText = new Text("Análise");
        ScrollPane clipContainer = new ScrollPane();

        try {
            hintString = smartAnalysisController.getHint(new User().getID());
        } catch (Exception error) {
            System.err.println("Erro ao pegar dica");
        }
        try {
            stringOutput =
                smartAnalysisController.convertOutputToString(
                    new User().getID()
                );
        } catch (Exception error) {
            System.err.println("Erro na conversao para string");
        }

        try {
            stringEntry =
                smartAnalysisController.convertEntryToString(
                    new User().getID()
                );
        } catch (Exception error) {
            System.err.println("Erro na conversao para string");
        }

        Text hintText = new Text(hintString);
        Text textOutput = new Text(stringOutput);
        Text textEntry = new Text(stringEntry);
        Text hintTitle = new Text("Dica:");

        holdHigherEntry.getStyleClass().add("hold-higher");
        holdHigherOutput.getStyleClass().add("hold-higher");
        hint.getStyleClass().add("hint");
        hintTitle.getStyleClass().add("hint-title");
        hintText.getStyleClass().add("hint-text");
        analysisTextVBox.getStyleClass().add("analysis-text-vbox");
        higherEntryText.getStyleClass().add("higher-entry-text");
        higherOutputText.getStyleClass().add("higher-output-text");
        analysisText.getStyleClass().add("analysis-text");
        textOutput.getStyleClass().add("text-output");
        textEntry.getStyleClass().add("text-entry");
        outputPercentage.getStyleClass().add("output-percentage");
        entryPercentage.getStyleClass().add("entry-percentage");
        title.getStyleClass().add("smart-analysis-title");
        mainContainer.getStyleClass().add("main-container");
        mainPane.getStyleClass().add("smart-analysis");
        container.getStyleClass().add("container");
        analysisContainer.getStyleClass().add("analysis-container");
        entryOutputcontainer.getStyleClass().add("entry-output-container");

        mainPane
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Pages/SmartAnalysis/SmartAnalysis.css"
            );

        hint.getChildren().addAll(hintTitle, hintText);
        analysisTextVBox.getChildren().add(analysisText);
        entryOutputcontainer
            .getChildren()
            .addAll(analysisTextVBox, analysisContainer, hint);
        holdHigherEntry.getChildren().add(higherEntryText);
        holdHigherOutput.getChildren().add(higherOutputText);
        outputPercentage.getChildren().addAll(holdHigherOutput, textOutput);
        entryPercentage.getChildren().addAll(holdHigherEntry, textEntry);
        analysisContainer
            .getChildren()
            .addAll(outputPercentage, entryPercentage);
        container.getChildren().addAll(entryOutputcontainer);
        mainContainer
            .getChildren()
            .addAll(navigationBar.getComponent(), title, container);
        mainPane.getChildren().addAll(mainContainer, clipContainer);

        clipContainer.setContent(mainContainer);
        clipContainer.setStyle("-fx-background-color: transparent;");
        clipContainer.setFitToHeight(true);
        clipContainer.setFitToWidth(true);

        return mainPane;
    }

    private VBox getTitlePage() {
        VBox container = new VBox();
        Text title = new Text("Revisão de gastos");
        title.getStyleClass().add("smart-analysis-title");

        container.getChildren().add(title);
        container.setAlignment(Pos.TOP_CENTER);

        return container;
    }
}
