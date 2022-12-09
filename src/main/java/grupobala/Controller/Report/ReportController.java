package grupobala.Controller.Report;

import grupobala.Controller.Report.IReportController.IReportController;
import grupobala.Entities.Report.IReport.IReport;

public class ReportController implements IReportController {

    IReport reporter;

    public ReportController() {}

    @Override
    public void generateReport() throws Exception {
        try {
            this.reporter.createReport();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void setReporter(IReport reporter) {
        this.reporter = reporter;
    }
}