package grupobala.Controller.Report.IReportController;

import grupobala.Entities.Report.IReport.IReport;

public interface IReportController {
    public void generateReport() throws Exception;

    public void setReporter(IReport reporter);
}
