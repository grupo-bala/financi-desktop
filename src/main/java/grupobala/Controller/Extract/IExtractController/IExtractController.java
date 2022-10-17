package grupobala.Controller.Extract.IExtractController;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import grupobala.Entities.Extract.IExtract.IExtract;

public interface IExtractController {
    
    public IExtract getExtract(int id, Date initial, Date end) throws SQLException, ParseException;
}
