package grupobala.Entities.Report;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.opencsv.CSVWriter;

import grupobala.Controller.Extract.ExtractController;
import grupobala.Controller.Extract.IExtractController.IExtractController;
import grupobala.Entities.Extract.IExtract.IExtract;
import grupobala.Entities.Report.IReport.IReport;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.Entities.User.User;

public class CSVReport implements IReport{

    File csvFile;
    IExtractController extractController;
    IExtract extract;

    public CSVReport() throws Exception {
        try {
            this.csvFile = new File("relatorio.csv");
            this.extractController = new ExtractController();
            this.extract = extractController.getExtract();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    public void createReport() throws Exception {
        try {
            this.setContent();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    private void setContent() throws Exception {
        PrintWriter pw = new PrintWriter(csvFile);
        CSVWriter cw = new CSVWriter(pw);
        ArrayList<String[]> data = new ArrayList<>();

        String[] headers = {"Titulo", "Valor", "Categoria", "Data"};
        String[] empytLine = {"", "", "", "", ""};
        String[] info = {new User().getName(), "Total de entradas", Double.toString(extract.getEntry()), "Total de saídas", Double.toString(extract.getOutput())};
        data.add(headers);

        for (ITransaction transaction : extract) {
            String[] dataTransaction = {transaction.getTitle(), Double.toString(transaction.getValue()), transaction.getCategory().displayedName, dateFormated(transaction.getDate())};
            data.add(dataTransaction);
        }

        data.add(empytLine);
        data.add(info);
    
        cw.writeAll(data);

        pw.close();
        cw.close();
    }

    private String dateFormated(Date date) {
        DateFormat formateDate = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = formateDate.format(date);

        return dateString;
    }

}
