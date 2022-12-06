package grupobala.Controller.Extract.IExtractController;

import grupobala.Entities.Extract.IExtract.IExtract;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public interface IExtractController {
    public IExtract getExtract(Date initial, Date end)
        throws SQLException, ParseException;

    public IExtract getExtract() throws SQLException, ParseException;
    public IExtract getCompleteExtract() throws SQLException, ParseException;
}
