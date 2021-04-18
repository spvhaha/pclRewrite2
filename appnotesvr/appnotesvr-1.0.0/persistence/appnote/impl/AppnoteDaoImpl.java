package persistence.appnote.impl;

import appnote.ddl.IO_Appnotesrv_rep;
import appnote.ddl.IO_Appnotesrv_req;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.appnote.AppnoteDao;
import persistence.core.JdbcTemplate;
import persistence.core.PreparedStatement;

public class AppnoteDaoImpl implements AppnoteDao {

   private static final String INSERT_APPNOTE = "INSERT INTO APPNOTE(APPLICATION_NUMBER   , CATEGORY_CODE        , ACTIVITY_CODE        , ACTIVITY_DATE        , OTHER_ACTIVITY_NAME  , NOTE                 , REC_CREATE_MODULEID  , REC_CREATE_TIMESTAMP   , BENEFIT_YEAR\t\t  , REC_CREATE_USERID    , LAST_UPDATE_TIMESTAMP    , LAST_UPDATE_USERID   , LAST_UPDATE_MODULEID)  VALUES(?, ?,?, ?,null, ?,?, current_timestamp,?, ?,current_timestamp, ?,?);";
   private static final String NO_ERROR_CODE = "000";
   private static final String GENERAL_ERROR_CODE = "001";
   private int queryTimeOut = 0;
   private final Logger logger = Logger.getLogger(AppnoteDaoImpl.class.getName());
   private JdbcTemplate jdbcTemplate;


   public AppnoteDaoImpl(JdbcTemplate jdbcTemplate) {
      this.jdbcTemplate = jdbcTemplate;
   }

   public IO_Appnotesrv_rep insertAppnote(IO_Appnotesrv_req appnoteSrv_req) {
      IO_Appnotesrv_rep messageOut = new IO_Appnotesrv_rep();
      if(this.logger.isLoggable(Level.FINE)) {
         this.logger.log(Level.FINE, "APPNOTE table: insertxx");
      }

      try {
         PreparedStatement e = this.jdbcTemplate.newPreparedStatement("INSERT INTO APPNOTE(APPLICATION_NUMBER   , CATEGORY_CODE        , ACTIVITY_CODE        , ACTIVITY_DATE        , OTHER_ACTIVITY_NAME  , NOTE                 , REC_CREATE_MODULEID  , REC_CREATE_TIMESTAMP   , BENEFIT_YEAR\t\t  , REC_CREATE_USERID    , LAST_UPDATE_TIMESTAMP    , LAST_UPDATE_USERID   , LAST_UPDATE_MODULEID)  VALUES(?, ?,?, ?,null, ?,?, current_timestamp,?, ?,current_timestamp, ?,?);").addStringParam(this.setBlank(appnoteSrv_req.getApplicationnumber())).addStringParam(appnoteSrv_req.getCategorycode()).addStringParam(appnoteSrv_req.getActivitycode()).addDateParam(appnoteSrv_req.getActivitydate()).addStringParam(this.setBlank(appnoteSrv_req.getMsgtext())).addStringParam(this.setBlank(appnoteSrv_req.getCreatemoduleid())).addStringParam(appnoteSrv_req.getBenftyr()).addStringParam(this.setBlank(appnoteSrv_req.getCreatemoduleid())).addStringParam(this.setBlank(appnoteSrv_req.getCreatemoduleid())).addStringParam(this.setBlank(appnoteSrv_req.getCreatemoduleid()));
         this.logger.log(Level.FINE, "APPNOTE table" + appnoteSrv_req.getApplicationnumber() + " Categorycode  " + appnoteSrv_req.getCategorycode() + "  Activitycode " + appnoteSrv_req.getActivitycode() + " Activitydate " + appnoteSrv_req.getActivitydate() + " Msgtext " + appnoteSrv_req.getMsgtext() + " Createmoduleid " + appnoteSrv_req.getCreatemoduleid() + " Benftyr " + appnoteSrv_req.getBenftyr());
         e.setQueryTimeout(this.queryTimeOut);
         if(this.logger.isLoggable(Level.FINE)) {
            this.logger.log(Level.FINE, "APPNOTE table: executexx insert");
         }

         int rtnCode = e.executeUpdate();
         if(this.logger.isLoggable(Level.FINE)) {
            this.logger.log(Level.FINE, "rtnCode: " + rtnCode);
         }

         if(rtnCode == 0) {
            throw new RuntimeException(" insert note failure rtnCode: " + rtnCode);
         }

         if(this.logger.isLoggable(Level.FINE)) {
            this.logger.log(Level.FINE, "APPNOTE table: inserted record " + appnoteSrv_req.getApplicationnumber());
         }

         messageOut.clear_All();
         messageOut.setErrorcode("000");
         messageOut.setApplicationnumber(appnoteSrv_req.getApplicationnumber());
      } catch (Exception var5) {
         this.logger.log(Level.SEVERE, "Exception- APPNOTE table insert error: application_number: " + appnoteSrv_req.getApplicationnumber());
         this.logger.log(Level.SEVERE, "Exception error: " + var5.toString() + " length " + var5.getMessage().length());
         messageOut.clear_All();
         messageOut.setErrorid("ERROR");
         messageOut.setErrorcode("001");
         if(var5.getMessage().length() < 400) {
            messageOut.setErrordesc(var5.getMessage());
         } else {
            messageOut.setErrordesc(var5.getMessage().substring(0, 399));
         }

         messageOut.setApplicationnumber(appnoteSrv_req.getApplicationnumber());
      }

      if(this.logger.isLoggable(Level.FINE)) {
         this.logger.log(Level.FINE, "End: " + System.currentTimeMillis());
      }

      return messageOut;
   }

   private String setBlank(String target) {
      return target != null && !target.trim().equals("")?target.trim():" ";
   }
}
