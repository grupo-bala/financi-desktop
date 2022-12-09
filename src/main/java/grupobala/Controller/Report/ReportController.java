package grupobala.Controller.Report;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import grupobala.Controller.Extract.ExtractController;
import grupobala.Controller.Extract.IExtractController.IExtractController;
import grupobala.Controller.Report.IReportController.IReportController;
import grupobala.Entities.Extract.IExtract.IExtract;
import grupobala.Entities.Iterator.IteratorInterface;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.Entities.User.User;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ReportController implements IReportController {

    Document report = new Document(PageSize.A4);

    public ReportController() {}

    @Override
    public void generateReport() throws Exception {
        try {
            this.report.setMargins(40, 40, 40, 40);
            this.createReport();
            this.setTopContent();
            this.setTableTransactions();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void createReport() throws Exception {
        try {
            PdfWriter.getInstance(
                this.report,
                new FileOutputStream("relatorio.pdf")
            );
        } catch (Exception e) {
            throw new Exception("Erro ao criar PDF");
        }
    }

    private void setTopContent() throws Exception {
        try {
            this.report.open();
            Image financiLogo = Image.getInstance(
                "src/main/resources/grupobala/images/financi-logo.png"
            );
            financiLogo.scaleAbsolute(60, 60);
            financiLogo.setAlignment(Element.ALIGN_CENTER);

            Paragraph tittle = new Paragraph(
                new Phrase(
                    "Relatório Financeiro",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22)
                )
            );
            Paragraph userName = new Paragraph(
                new Phrase(
                    new User().getName(),
                    FontFactory.getFont(FontFactory.HELVETICA, 16)
                )
            );

            tittle.setAlignment(Element.ALIGN_CENTER);
            userName.setAlignment(Element.ALIGN_CENTER);

            addEmptyLine(userName, 2);

            this.report.add(financiLogo);
            this.report.add(new Paragraph(" "));
            this.report.add(tittle);
            this.report.add(userName);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void setTableTransactions() throws Exception {
        try {
            IExtractController extractController = new ExtractController();
            IExtract extract = extractController.getExtract();

            PdfPTable table = this.getBodyTable();
            table.setWidthPercentage(90);
            table.setWidths(new int[] { 40, 20, 20, 15 });

            IteratorInterface<ITransaction> extractIterator = extract.iterator();

            while (extractIterator.hasNext()) {
                ITransaction transaction = extractIterator.next();

                String dateTransaction = this.dateFormated(transaction);

                PdfPCell description = new PdfPCell(
                    new Phrase(
                        transaction.getTitle(),
                        FontFactory.getFont(FontFactory.HELVETICA, 11)
                    )
                );
                PdfPCell value = new PdfPCell(
                    new Phrase(
                        Double.toString(transaction.getValue()),
                        FontFactory.getFont(FontFactory.HELVETICA, 11)
                    )
                );
                PdfPCell category = new PdfPCell(
                    new Phrase(
                        transaction.getCategory().displayedName,
                        FontFactory.getFont(FontFactory.HELVETICA, 11)
                    )
                );
                PdfPCell date = new PdfPCell(
                    new Phrase(
                        dateTransaction,
                        FontFactory.getFont(FontFactory.HELVETICA, 11)
                    )
                );

                this.cellFormate(description);
                table.addCell(description);

                this.cellFormate(value);
                table.addCell(value);

                this.cellFormate(category);
                table.addCell(category);

                this.cellFormate(date);
                table.addCell(date);
            }

            Paragraph entry = new Paragraph(
                new Phrase(
                    "Total de entradas: " + extract.getEntry(),
                    FontFactory.getFont(FontFactory.HELVETICA, 11)
                )
            );
            Paragraph output = new Paragraph(
                new Phrase(
                    "Total de saídas: " + extract.getOutput(),
                    FontFactory.getFont(FontFactory.HELVETICA, 11)
                )
            );
            entry.setAlignment(Element.ALIGN_CENTER);
            output.setAlignment(Element.ALIGN_CENTER);

            this.report.add(table);
            this.report.add(new Paragraph(" "));
            this.report.add(entry);
            this.report.add(output);
            this.report.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void cellFormate(PdfPCell cell) {
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(8);
    }

    private void headCellFormate(PdfPCell cell) {
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(new BaseColor(73, 173, 90));
        cell.setPadding(8);
    }

    private String dateFormated(ITransaction transaction) {
        DateFormat formateDate = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formateDate.format(transaction.getDate());

        return dateString;
    }

    private PdfPTable getBodyTable() {
        PdfPTable tableTransactions = new PdfPTable(4);

        PdfPCell description = new PdfPCell(
            new Phrase(
                "Descrição",
                FontFactory.getFont(FontFactory.HELVETICA, 12)
            )
        );
        PdfPCell value = new PdfPCell(
            new Phrase("Valor", FontFactory.getFont(FontFactory.HELVETICA, 12))
        );
        PdfPCell category = new PdfPCell(
            new Phrase(
                "Categoria",
                FontFactory.getFont(FontFactory.HELVETICA, 12)
            )
        );
        PdfPCell date = new PdfPCell(
            new Phrase("Data", FontFactory.getFont(FontFactory.HELVETICA, 12))
        );

        this.headCellFormate(description);
        tableTransactions.addCell(description);

        this.headCellFormate(value);
        tableTransactions.addCell(value);

        this.headCellFormate(category);
        tableTransactions.addCell(category);

        this.headCellFormate(date);
        tableTransactions.addCell(date);

        tableTransactions.setHeaderRows(1);

        return tableTransactions;
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
