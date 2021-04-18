package pathwayserver.appnote;

import appnote.ddl.IO_Appnotesrv_rep;
import appnote.ddl.IO_Appnotesrv_req;
import java.util.logging.Level;
import java.util.logging.Logger;
import pathwayserver.appnote.MessageHandler;
import persistence.appnote.impl.AppnoteDaoImpl;
import persistence.core.JdbcTemplate;
import persistence.util.impl.SqlMxDataSourceProvider;
import util.exception.TandemError;
import util.exception.TandemErrorMap;
import util.logging.LogUtil;
import util.pwayframework.PathwayServiceProvider;

public class AppNoteServer {

   private final String PROGRAM_NAME = "APPNOTESRV";
   private final String VERSION = "01.00.00";
   private final String PREFIX = "sqlmx";
   private final String PROPERTIES_FILE_NAME = "/settings.properties";
   private final String ERROR_FILE = "/error.xml";
   private static final Logger logger = Logger.getLogger(AppNoteServer.class.getName());
   private static TandemErrorMap tandemErrorMap;


   public static void main(String[] args) {
      (new AppNoteServer()).AppNoteStart(args);
   }

   private void AppNoteStart(String[] args) {
      try {
         tandemErrorMap = new TandemErrorMap("GS", "TRILLIUM", "APPNOTESRV", "01.00.00");
         tandemErrorMap.loadErrorsFromXmlFile("/error.xml");

         for(int t = 0; t < args.length; ++t) {
            logger.log(Level.FINE, "param " + args[t]);
         }

         logger.log(Level.FINE, "Started AppNoteServer ......" + args.length + "  NO2 = " + args);
         PathwayServiceProvider var5 = PathwayServiceProvider.newPathwayServiceProvider(new AppNoteServer(), new MessageHandler(), 32767, tandemErrorMap);
         var5.setServiceNameOffset(0);
         var5.setServiceNameLength(0);
         var5.start();
      } catch (Throwable var4) {
         logger.log(Level.SEVERE, var4.getMessage(), var4);
         TandemError error = tandemErrorMap.mapException(var4, "Default");
         logger.log(error.getLevel(), error.getText(), error);
         LogUtil.logStackTrace(logger, Level.SEVERE, var4);
         System.exit(1);
      }

   }

   public IO_Appnotesrv_rep GENSERVICE(IO_Appnotesrv_req iMessageIn) {
      if(logger.isLoggable(Level.FINE)) {
         logger.log(Level.FINE, "Start AppNoteServer.GENSERVICE(): " + System.currentTimeMillis());
      }

      logger.log(Level.FINE, "here 0: ");
      SqlMxDataSourceProvider dataSourceProvider = new SqlMxDataSourceProvider();
      dataSourceProvider.setPropertiesFileName("/settings.properties");
      dataSourceProvider.setPrefix("sqlmx");
      JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceProvider.getDataSource());
      AppnoteDaoImpl appnoteDao = new AppnoteDaoImpl(jdbcTemplate);
      return appnoteDao.insertAppnote(iMessageIn);
   }

}
