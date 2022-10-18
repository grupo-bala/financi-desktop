package grupobala.Controller.Extract.IExtractController;

import grupobala.Entities.Extract.IExtract.IExtract;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public interface IExtractController {
    public IExtract getExtract(int id, Date initial, Date end)
        throws SQLException, ParseException;
}
