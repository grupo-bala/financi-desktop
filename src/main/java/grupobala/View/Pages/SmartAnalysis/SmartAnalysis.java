package grupobala.View.Pages.SmartAnalysis;

import grupobala.Controller.SmartAnalysis.SmartAnalysisController;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.Entities.User.User;
import grupobala.View.Components.NavigationBar.NavigationBar;
import grupobala.View.Pages.Page.Page;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SmartAnalysis implements Page {

    private StackPane mainPane = new StackPane();
    private NavigationBar navigationBar = new NavigationBar();
    private SmartAnalysisController smartAnalysisController = new SmartAnalysisController();

    @Override
    public StackPane getMainPane() {
        LineCharts lineCharts = new LineCharts();
        VBox mainContainer = new VBox();
        VBox container = new VBox();
        VBox topContainer = new VBox();
        VBox entryOutputcontainer = new VBox();
        HBox analysisHBox = new HBox();
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
        VBox entryLineChartVBox = new VBox();
        ScrollPane clipContainer = new ScrollPane();
        HBox chartHbox = new HBox();
        VBox outputLineChartVBox = new VBox();
        VBox chartsContainer = new VBox();
        VBox botContainer = new VBox();

        VBox.setVgrow(entryOutputcontainer, Priority.ALWAYS);
        LineChart<Number, Number> entryLineChart = lineCharts.entrysLineChart();
        LineChart<Number, Number> outputLineChart = lineCharts.outputLineChart();
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
        Text entryChartTitle = new Text("Gráfico de entradas");
        Text outputChartTitle = new Text("Gráfico de gastos");
        Text chartTitle = new Text("Gráficos");

        container.getStyleClass().add("container");
        analysisHBox.getStyleClass().add("analysis-hbox");
        botContainer.getStyleClass().add("bot-container");
        chartTitle.getStyleClass().add("chart-title");
        chartsContainer.getStyleClass().add("charts-container");
        entryChartTitle.getStyleClass().add("entry-chart");
        outputChartTitle.getStyleClass().add("output-chart");
        chartHbox.getStyleClass().add("chart-hbox");
        outputLineChartVBox.getStyleClass().add("chart-output-vbox");
        entryLineChartVBox.getStyleClass().add("chart-entry-vbox");
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
        topContainer.getStyleClass().add("top-container");
        analysisContainer.getStyleClass().add("analysis-container");
        entryOutputcontainer.getStyleClass().add("entry-output-container");

        mainPane
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Pages/SmartAnalysis/SmartAnalysis.css"
            );

        analysisHBox.getChildren().add(entryOutputcontainer);
        chartsContainer.getChildren().addAll(chartTitle, chartHbox);
        entryLineChartVBox
            .getChildren()
            .addAll(entryChartTitle, entryLineChart);
        outputLineChartVBox
            .getChildren()
            .addAll(outputChartTitle, outputLineChart);
        chartHbox.getChildren().addAll(outputLineChartVBox, entryLineChartVBox);
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
        topContainer.getChildren().addAll(title, analysisHBox);
        botContainer.getChildren().add(chartsContainer);
        container.getChildren().addAll(topContainer, botContainer);
        mainContainer
            .getChildren()
            .addAll(navigationBar.getComponent(), clipContainer);
        mainPane.getChildren().add(mainContainer);

        clipContainer.setContent(container);
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

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public class LineCharts {

        public LineChart<Number, Number> entrysLineChart() {
            final NumberAxis timexAxis = new NumberAxis();
            final NumberAxis entrysyAxis = new NumberAxis();

            final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(
                timexAxis,
                entrysyAxis
            );

            XYChart.Series series = new XYChart.Series();

            SmartAnalysisController smartAnalysisController = new SmartAnalysisController();

            try {
                ArrayList<ITransaction> entrys = smartAnalysisController.getEntrysArrayList(
                    smartAnalysisController.getTransactions(new User().getID())
                );
                for (ITransaction entry : entrys) {
                    int mes = Integer.valueOf(
                        smartAnalysisController.convertToMonth(entry.getDate())
                    );
                    series
                        .getData()
                        .add(new XYChart.Data(mes, (entry.getValue())));
                }
            } catch (Exception error) {
                System.out.println("Nao foi possivel pegar as transacoes");
            }
            lineChart.getData().add(series);
            return lineChart;
        }

        public LineChart<Number, Number> outputLineChart() {
            final NumberAxis timexAxis = new NumberAxis();
            final NumberAxis outputsyAxis = new NumberAxis();

            final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(
                timexAxis,
                outputsyAxis
            );

            XYChart.Series series = new XYChart.Series();

            SmartAnalysisController smartAnalysisController = new SmartAnalysisController();

            try {
                ArrayList<ITransaction> outputs = smartAnalysisController.getOutputsArrayList(
                    smartAnalysisController.getTransactions(new User().getID())
                );
                for (ITransaction output : outputs) {
                    int mes = Integer.valueOf(
                        smartAnalysisController.convertToMonth(output.getDate())
                    );
                    series
                        .getData()
                        .add(
                            new XYChart.Data(mes, Math.abs((output.getValue())))
                        );
                }
            } catch (Exception error) {
                System.out.println("Nao foi possivel pegar as transacoes");
            }
            lineChart.getData().add(series);
            return lineChart;
        }
    }
}
