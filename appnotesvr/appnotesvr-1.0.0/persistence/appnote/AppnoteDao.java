package persistence.appnote;

import appnote.ddl.IO_Appnotesrv_rep;
import appnote.ddl.IO_Appnotesrv_req;

public interface AppnoteDao {

   IO_Appnotesrv_rep insertAppnote(IO_Appnotesrv_req var1);
}
