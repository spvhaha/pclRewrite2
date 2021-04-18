package persistence.ds.impl;

import persistence.util.IDataSourceProvider;

public class SqlStatement {

   private final String FMLYHLD_DEFAULT = "FMLYHLD";
   private final String RECIPHLD_DEFAULT = "RECIPHLD";
   private final String APPNUMBER_DEFAULT = "APPNUMBER";
   private final String APPNUMTYPE_DEFAULT = "APPNUMTYPE";
   private final String APPNUMSTAT_DEFAULT = "APPNUMSTAT";
   private final String APPNOTE_DEFAULT = "APPNOTE";
   private final String TRACTIVITY_DEFAULT = "TRACTIVITY";
   private final String DELEGATE_DEFAULT = "DELEGATE";
   private final String APPALERTS_DEFAULT = "APPALERTS";
   private final String V1APPNUMBER_DEFAULT = "V1APPNUMBER";
   private final String V3FAMILY_DEFAULT = "V3FAMILY";
   private final String BATCHJOBEXCEPTION_DEFAULT = "BATCHJOBEXCEPTION";
   private final String FMLYHLD_PROPNAME = "tableName.fmlyhld";
   private final String RECIPHLD_PROPNAME = "tableName.reciphld";
   private final String APPNUMBER_PROPNAME = "tableName.appnumber";
   private final String APPNUMTYPE_PROPNAME = "tableName.appnumtype";
   private final String APPNUMSTAT_PROPNAME = "tableName.appnumstat";
   private final String APPNOTE_PROPNAME = "tableName.appnote";
   private final String TRACTIVITY_PROPNAME = "tableName.tractivity";
   private final String DELEGATE_PROPNAME = "tableName.delegate";
   private final String APPALERTS_PROPNAME = "tableName.appalerts";
   private final String V1APPNUMBER_PROPNAME = "tableName.v1appnumber";
   private final String V3FAMILY_PROPNAME = "tableName.v3family";
   private final String BATCHJOBEXCEPTION_PROPNAME = "tableName.batchjobexception";
   private String reciphld;
   private String fmlyhld;
   private String appnumber;
   private String appnumtype;
   private String appnumstat;
   private String appnote;
   private String tractivity;
   private String delegate;
   private String appalerts;
   private String v1appnumber;
   private String v3family;
   private String batchjobexception;
   private String updateAppnumberForAppnum;
   private String updateAppnumberAddress;
   private String selectTtsHousehold;
   private String updateAppnumberStatusCode;
   private String appnumberContactSql;
   private String insertNoteSql;
   private String tractivitySql;
   private String appnumTdp003Update1Sql;
   private String appnumTdp003Update2Sql;
   private String delegateContactAddressSql;
   private String apllicationAlertsSql;
   private String apllicationRenewalPendinListSql;
   private String applTerminationListSql;
   private String applCommittedListSql;
   private String appNumberNoEndYearSetSql;
   private String appNumStatSql;
   private String appNumTypeSql;
   private String insertSoftDeleteSql;
   private String appnumberSql;
   private String insertRenewAppNumberSql;
   private String renewAlertsSql;
   private String getAlertsSql;
   private String insertRenewAlertsSql;
   private String pendingRenewalListTimestampSql;
   private String getTDP004ApplNumbersSql;
   private String insertExceptionReport;


   public SqlStatement(IDataSourceProvider dataSourceProvider) {
      this.reciphld = dataSourceProvider.getPrefixProp("tableName.reciphld", "RECIPHLD");
      this.fmlyhld = dataSourceProvider.getPrefixProp("tableName.fmlyhld", "FMLYHLD");
      this.appnumber = dataSourceProvider.getPrefixProp("tableName.appnumber", "APPNUMBER");
      this.appnumtype = dataSourceProvider.getPrefixProp("tableName.appnumtype", "APPNUMTYPE");
      this.appnumstat = dataSourceProvider.getPrefixProp("tableName.appnumstat", "APPNUMSTAT");
      this.appnote = dataSourceProvider.getPrefixProp("tableName.appnote", "APPNOTE");
      this.tractivity = dataSourceProvider.getPrefixProp("tableName.tractivity", "TRACTIVITY");
      this.delegate = dataSourceProvider.getPrefixProp("tableName.delegate", "DELEGATE");
      this.appalerts = dataSourceProvider.getPrefixProp("tableName.appalerts", "APPALERTS");
      this.v1appnumber = dataSourceProvider.getPrefixProp("tableName.v1appnumber", "V1APPNUMBER");
      this.v3family = dataSourceProvider.getPrefixProp("tableName.v3family", "V3FAMILY");
      this.batchjobexception = dataSourceProvider.getPrefixProp("tableName.batchjobexception", "BATCHJOBEXCEPTION");
   }

   public String getUpdateAppnumberForAppnum() {
      if(this.updateAppnumberForAppnum == null) {
         this.updateAppnumberForAppnum = " UPDATE " + this.appnumber + " SET status_code = ?, " + " contact_odb_elig_no = ?, " + " contact_first_name = ?, " + " contact_middle_name = ?, " + " contact_last_name = ?, " + " contact_addr_1 = ?, " + " contact_addr_2 = ?, " + " contact_city = ?, " + " contact_prov_cd = ?, " + " contact_postal_cd = ?, " + " contact_language_pref = ?, " + " contact_home_phone = ?, " + " contact_business_phone = ?, " + " contact_business_extension = ?, " + " country_cd = ?, " + " last_update_moduleid = ?, " + " last_update_timestamp = current, " + " app_registration_dt = current_date " + " WHERE application_number = ? " + " AND rec_inactive_timestamp IS NULL " + " AND status_code NOT IN (\'D\',\'T\') ";
      }

      return this.updateAppnumberForAppnum;
   }

   public String getUpdateAppnumberAddress() {
      if(this.updateAppnumberAddress == null) {
         this.updateAppnumberAddress = " UPDATE " + this.appnumber + " SET contact_first_name = ?, " + " contact_middle_name = ?, " + " contact_last_name = ?, " + " contact_addr_1 = ?, " + " contact_addr_2 = ?, " + " contact_city = ?, " + " contact_prov_cd = ?, " + " contact_postal_cd = ?, " + " contact_language_pref = ?, " + " contact_home_phone = ?, " + " contact_business_phone = ?, " + " contact_business_extension = ?, " + " country_cd = ?, " + " last_update_moduleid = ?, " + " last_update_timestamp = current " + " WHERE contact_odb_elig_no = ? " + " AND rec_inactive_timestamp IS NULL " + " AND STATUS_CODE = \'C\' " + " AND (contact_odb_elig_no <> 0 OR application_number = ?) ";
      }

      return this.updateAppnumberAddress;
   }

   public String getSelectTtsHousehold() {
      if(this.selectTtsHousehold == null) {
         this.selectTtsHousehold = " SELECT A.type_code, B.type_desc, A.status_code,  C.status_desc, A.benefit_year,  A.app_processed_dt, A.use_delegate_ind  FROM " + this.appnumber + " A, " + this.appnumtype + " B, " + this.appnumstat + " C " + " WHERE A.application_number = ? " + " AND A.status_code = C.status_code " + " AND A.type_code = B.type_code " + " AND A.rec_inactive_timestamp IS NULL " + " BROWSE ACCESS ";
      }

      return this.selectTtsHousehold;
   }

   public String getUpdateAppnumberStatusCode() {
      if(this.updateAppnumberStatusCode == null) {
         this.updateAppnumberStatusCode = " UPDATE " + this.appnumber + " SET status_code = ?, " + " last_update_moduleid = ?, " + " last_update_timestamp = current " + " WHERE application_number = ? ";
      }

      return this.updateAppnumberStatusCode;
   }

   public String getFindAppnumberContactSql() {
      if(this.appnumberContactSql == null) {
         this.appnumberContactSql = " SELECT CONTACT_FIRST_NAME,CONTACT_MIDDLE_NAME, CONTACT_LAST_NAME,CONTACT_ADDR_1,CONTACT_ADDR_2,CONTACT_CITY,  CONTACT_PROV_CD,CONTACT_POSTAL_CD,COUNTRY_CD,CONTACT_LANGUAGE_PREF,   USE_DELEGATE_IND FROM " + this.appnumber + " WHERE application_number = ? ";
      }

      return this.appnumberContactSql;
   }

   public String getInsertNoteSql() {
      if(this.insertNoteSql == null) {
         this.insertNoteSql = "INSERT INTO " + this.appnote + "(APPLICATION_NUMBER   , " + "CATEGORY_CODE        , " + "ACTIVITY_CODE        , " + "ACTIVITY_DATE        , " + "OTHER_ACTIVITY_NAME  , " + "NOTE                 , " + "REC_CREATE_MODULEID  , " + "REC_CREATE_TIMESTAMP   , " + "BENEFIT_YEAR\t\t  , " + "REC_CREATE_USERID    , " + "LAST_UPDATE_TIMESTAMP    , " + "LAST_UPDATE_USERID   , " + "LAST_UPDATE_MODULEID) " + " VALUES(?, ?,?, ?,null, ?,?, current_timestamp,?, ?,current_timestamp, ?,?);";
      }

      return this.insertNoteSql;
   }

   public String getTractivitySql() {
      if(this.tractivitySql == null) {
         this.tractivitySql = "SELECT ACTIVITY_NAME  FROM  " + this.tractivity + " WHERE  ACTIVITY_CODE = ? " + " AND  CATEGORY_CODE = ? " + " AND  (    END_DATE > ? " + " OR END_DATE IS NULL)\t " + " BROWSE ACCESS;";
      }

      return this.tractivitySql;
   }

   public String getAppnumTdp003Update1Sql() {
      if(this.appnumTdp003Update1Sql == null) {
         this.appnumTdp003Update1Sql = "UPDATE " + this.appnumber + " SET \t\t\t\t\t" + "STATUS_CODE =? ,\t\t" + "CONTACT_ODB_ELIG_NO  = ?,     " + "CONTACT_FIRST_NAME  = ?,  \t" + "CONTACT_MIDDLE_NAME  = ?, \t" + "CONTACT_LAST_NAME   = ?,  \t" + "CONTACT_ADDR_1  = ?, \t\t" + "CONTACT_ADDR_2  = ?, \t\t" + "COUNTRY_CD  = ?,   \t\t" + "CONTACT_CITY  = ?,   \t\t" + "CONTACT_PROV_CD  = ?,\t\t" + "CONTACT_POSTAL_CD  = ?,   \t" + "CONTACT_LANGUAGE_PREF  = ?,    " + "CONTACT_HOME_PHONE  = ?,  \t" + "CONTACT_BUSINESS_PHONE  = ?,   " + "CONTACT_BUSINESS_EXTENSION  = ?," + "LAST_UPDATE_MODULEID\t= ?,\t" + "LAST_UPDATE_USERID\t= ?,\t" + "LAST_UPDATE_TIMESTAMP = current, " + "APP_PROCESSED_DT \t= current_date\t " + "WHERE APPLICATION_NUMBER = ? AND  " + "REC_INACTIVE_TIMESTAMP IS NULL AND " + "STATUS_CODE NOT IN ( \'D\', \'T\'); ";
      }

      return this.appnumTdp003Update1Sql;
   }

   public String getAppnumTdp003Update2Sql() {
      if(this.appnumTdp003Update2Sql == null) {
         this.appnumTdp003Update2Sql = "UPDATE " + this.appnumber + " SET \t\t\t\t\t\t\t" + "CONTACT_FIRST_NAME  = ?,  \t\t" + "CONTACT_MIDDLE_NAME  = ?, \t\t" + "CONTACT_LAST_NAME   = ?,  \t\t" + "CONTACT_ADDR_1  = ?, \t\t\t" + "CONTACT_ADDR_2  = ?, \t\t\t" + "CONTACT_CITY  = ?,   \t\t\t" + "COUNTRY_CD  = ?,   \t\t\t" + "CONTACT_PROV_CD  = ?,\t\t\t" + "CONTACT_POSTAL_CD  = ?,   \t\t" + "CONTACT_LANGUAGE_PREF  = ?,    " + "CONTACT_HOME_PHONE  = ?,  \t\t" + "CONTACT_BUSINESS_PHONE  = ?,   \t" + "CONTACT_BUSINESS_EXTENSION  = ?,\t" + "LAST_UPDATE_MODULEID\t= ?,\t\t" + "LAST_UPDATE_USERID\t= ?,\t\t" + "LAST_UPDATE_TIMESTAMP = current\t" + "WHERE CONTACT_ODB_ELIG_NO = ?   \t" + "AND REC_INACTIVE_TIMESTAMP IS NULL \t" + "AND (STATUS_CODE = \'C\' OR STATUS_CODE = \'P\') " + "AND (CONTACT_ODB_ELIG_NO <> 0 OR APPLICATION_NUMBER = ? ) " + "AND ? <> 9999999998;";
      }

      return this.appnumTdp003Update2Sql;
   }

   public String getDelegateContactAddressSql() {
      if(this.delegateContactAddressSql == null) {
         this.delegateContactAddressSql = "SELECT  FIRST_NAME, MIDDLE_NAME,LAST_NAME, ADDR_1,  ADDR_2, CITY , COUNTRY_CD , PROV_CD, POSTAL_CD,  LANGUAGE_PREF  FROM  " + this.delegate + " WHERE APPLICATION_NUMBER = ?  " + " and REC_EFF_DT    <= current_date " + " and (END_DATE    > current_date  or END_DATE is null) " + " and REC_INACTIVE_TIMESTAMP IS NULL " + " ORDER BY " + " APPLICATION_NUMBER, " + " REC_EFF_DT           DESC, " + " REC_CREATE_TIMESTAMP DESC " + " BROWSE ACCESS";
      }

      return this.delegateContactAddressSql;
   }

   public String getApllicationAlertsSql() {
      if(this.apllicationAlertsSql == null) {
         this.apllicationAlertsSql = "SELECT *\tFROM   \t" + this.appalerts + " WHERE  APPLICATION_NUMBER = ? " + " AND  ACTIVITY_CODE = ? " + " AND  CATEGORY_CODE = ? " + " AND  REC_INACTIVE_TIMESTAMP IS NULL " + " BROWSE ACCESS; ";
      }

      return this.apllicationAlertsSql;
   }

   public String getApplicationRenewalPendinListSql() {
      if(this.apllicationRenewalPendinListSql == null) {
         this.apllicationRenewalPendinListSql = " SELECT APPLICATION_NUMBER, BENEFIT_YEAR FROM " + this.v1appnumber + " WHERE STATUS_CODE = ? AND " + " TYPE_CODE = ? AND " + " BENEFIT_YEAR <= ? AND " + " BENEFIT_YEAR > 2010 AND " + " REC_INACTIVE_TIMESTAMP IS NULL " + " ORDER BY APPLICATION_NUMBER ASC BROWSE ACCESS;";
      }

      return this.apllicationRenewalPendinListSql;
   }

   public String getApplTerminationListSql() {
      if(this.applTerminationListSql == null) {
         this.applTerminationListSql = "SELECT v.application_number,   \tn.activity_code,   \t\tn.rec_create_timestamp,   \tv.use_delegate_ind    \t\tFROM " + this.appnote + " n," + this.v1appnumber + " v " + "WHERE n.benefit_year = ?  \t\t\t" + "AND n.rec_create_timestamp <= ?   \t" + "AND n.category_code  = 26\t  \t\t" + "AND n.activity_code IN (463,464)  \t" + "AND n.benefit_year   = v.benefit_year \t" + "AND n.application_number = v.application_number " + "AND v.status_code =\'T\'\t    \t\t\t \t" + "AND n.rec_inactive_timestamp IS NULL    \t" + "AND v.rec_inactive_timestamp IS NULL    \t" + "AND ((n.REC_CREATE_TIMESTAMP, n.APPLICATION_NUMBER) > (?,?))     " + "AND n.rec_create_timestamp = (select MAX(n1.rec_create_timestamp) " + "from " + this.appnote + " n1 " + "WHERE n1.application_number = n.application_number  \t" + "AND n1.benefit_year= ?     \t\t\t\t\t\t\t" + "AND n1.category_code =26    \t\t\t\t\t\t\t" + "AND n1.activity_code = n.activity_code   \t\t\t\t" + "AND n1.rec_create_timestamp >= ?     \t\t\t\t\t" + "AND n1.rec_create_timestamp <= ?  \t\t\t\t\t\t" + "AND n1.rec_inactive_timestamp IS NULL   \t\t\t\t" + "BROWSE ACCESS )  \t\t" + "ORDER BY n.REC_CREATE_TIMESTAMP, n.APPLICATION_NUMBER ASC   " + " BROWSE ACCESS; ";
      }

      return this.applTerminationListSql;
   }

   public String getApplCommittedListSql() {
      if(this.applCommittedListSql == null) {
         this.applCommittedListSql = "SELECT application_number, MIN(rec_create_timestamp) FROM " + this.v3family + " WHERE benefit_yr = ? " + "AND rec_inactive_timestamp IS NULL " + "AND application_number NOT IN " + "    (SELECT application_number " + "     FROM " + this.v1appnumber + " WHERE benefit_year = ? " + "     AND status_code = \'E\' " + "     AND rec_inactive_timestamp IS NULL BROWSE ACCESS) " + "GROUP BY application_number " + "HAVING MIN(rec_create_timestamp) > ? " + "ORDER BY 2 " + "BROWSE ACCESS ;";
      }

      return this.applCommittedListSql;
   }

   public String getAppNumStatSql() {
      if(this.appNumStatSql == null) {
         this.appNumStatSql = "SELECT STATUS_CODE FROM " + this.appnumstat + " WHERE STATUS_CODE = ?  " + "BROWSE ACCESS;";
      }

      return this.appNumStatSql;
   }

   public String getAppNumTypeSql() {
      if(this.appNumTypeSql == null) {
         this.appNumTypeSql = "SELECT TYPE_CODE FROM " + this.appnumtype + " WHERE TYPE_CODE = ?  " + "BROWSE ACCESS;";
      }

      return this.appNumTypeSql;
   }

   public String getAppNumberNoEndYearSetSql() {
      if(this.appNumberNoEndYearSetSql == null) {
         this.appNumberNoEndYearSetSql = "SELECT DISTINCT(SUBSTRING(APPLICATION_NUMBER FROM 1 FOR 9) ) FROM " + this.v1appnumber + " WHERE BENEFIT_YEAR < ? AND " + "BENEFIT_YEAR >= ? AND " + "STATUS_CODE = ? AND " + "TYPE_CODE = ? " + "BROWSE ACCESS;";
      }

      return this.appNumberNoEndYearSetSql;
   }

   public String getSelectAppnumberSql() {
      if(this.appnumberSql == null) {
         this.appnumberSql = "SELECT * FROM " + this.appnumber + " WHERE APPLICATION_NUMBER =? " + "BROWSE ACCESS;";
      }

      return this.appnumberSql;
   }

   public String getInsertSoftDeleteSql() {
      if(this.insertSoftDeleteSql == null) {
         this.insertSoftDeleteSql = "INSERT INTO " + this.appnumber + " (APPLICATION_NUMBER," + "TYPE_CODE ," + "STATUS_CODE   ," + "BENEFIT_YEAR  ," + "CONTACT_FIRST_NAME," + "CONTACT_MIDDLE_NAME   ," + "CONTACT_LAST_NAME ," + "CONTACT_ODB_ELIG_NO   ," + "CONTACT_ADDR_1," + "CONTACT_ADDR_2," + "CONTACT_CITY  ," + "CONTACT_PROV_CD   ," + "CONTACT_POSTAL_CD ," + "CONTACT_LANGUAGE_PREF ," + "CONTACT_HOME_PHONE," + "CONTACT_BUSINESS_PHONE," + "CONTACT_BUSINESS_EXTENSION," + "REC_CREATE_MODULEID   ," + "REC_CREATE_USERID   ," + "REC_CREATE_TIMESTAMP ," + "PROGRAM_GRP   ," + "COUNTRY_CD ," + "USE_DELEGATE_IND   ," + "APP_PROCESSED_DT   ," + "APP_REGISTRATION_DT ," + "LAST_UPDATE_MODULEID ," + "LAST_UPDATE_USERID    ," + "LAST_UPDATE_TIMESTAMP ) " + "VALUES" + "( ?," + " \'R\', " + " \'T\', " + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "current_timestamp," + "?," + "?," + "?," + "current_date," + "null," + "?," + "?," + "current_timestamp)";
      }

      return this.insertSoftDeleteSql;
   }

   public String getInsertRenewAppNumberSql() {
      if(this.insertRenewAppNumberSql == null) {
         this.insertRenewAppNumberSql = "INSERT INTO " + this.appnumber + " (APPLICATION_NUMBER," + "TYPE_CODE ," + "STATUS_CODE   ," + "BENEFIT_YEAR  ," + "CONTACT_FIRST_NAME," + "CONTACT_MIDDLE_NAME   ," + "CONTACT_LAST_NAME ," + "CONTACT_ODB_ELIG_NO   ," + "CONTACT_ADDR_1," + "CONTACT_ADDR_2," + "CONTACT_CITY  ," + "CONTACT_PROV_CD   ," + "CONTACT_POSTAL_CD ," + "CONTACT_LANGUAGE_PREF ," + "CONTACT_HOME_PHONE," + "CONTACT_BUSINESS_PHONE," + "CONTACT_BUSINESS_EXTENSION," + "REC_CREATE_MODULEID   ," + "REC_CREATE_USERID   ," + "REC_CREATE_TIMESTAMP ," + "PROGRAM_GRP   ," + "COUNTRY_CD ," + "USE_DELEGATE_IND   ," + "APP_PROCESSED_DT," + "APP_REGISTRATION_DT," + "LAST_UPDATE_MODULEID ," + "LAST_UPDATE_USERID    ," + "LAST_UPDATE_TIMESTAMP ) " + "VALUES" + "( ?," + " \'R\'," + " \'P\'," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "current_timestamp," + "?," + "?," + "?," + "null," + "null," + "?," + "?," + "current_timestamp)";
      }

      return this.insertRenewAppNumberSql;
   }

   public String getRenewAlertsSql() {
      if(this.renewAlertsSql == null) {
         this.renewAlertsSql = "SELECT  * FROM " + this.appalerts + " WHERE  APPLICATION_NUMBER = ? " + "AND  REC_INACTIVE_TIMESTAMP IS NULL " + "BROWSE ACCESS;";
      }

      return this.renewAlertsSql;
   }

   public String getAlertsSql() {
      if(this.getAlertsSql == null) {
         this.getAlertsSql = "SELECT  * FROM " + this.appalerts + " WHERE  APPLICATION_NUMBER = ? " + " and CATEGORY_CODE = ? " + " and ACTIVITY_CODE = ? " + " AND  REC_INACTIVE_TIMESTAMP IS NULL " + "BROWSE ACCESS;";
      }

      return this.getAlertsSql;
   }

   public String getInsertRenewAlertsSql() {
      if(this.insertRenewAlertsSql == null) {
         this.insertRenewAlertsSql = "INSERT INTO " + this.appalerts + " (APPLICATION_NUMBER,       " + "REC_CREATE_TIMESTAMP,     " + "ACTIVITY_CODE,            " + "CATEGORY_CODE ,           " + "REC_CREATE_MODULEID,      " + "REC_CREATE_USERID,        " + "LAST_UPDATE_MODULEID ,    " + "LAST_UPDATE_USERID,       " + "LAST_UPDATE_TIMESTAMP,    " + "REC_INACTIVE_MODULEID,    " + "REC_INACTIVE_USERID ,     " + "REC_INACTIVE_TIMESTAMP )   " + "VALUES ( ? ," + "current_timestamp,  " + "?,  " + "?,  " + "?,  " + "?,  " + "?,  " + "?,  " + "current_timestamp,  " + "?,  " + "?,  " + "? );";
      }

      return this.insertRenewAlertsSql;
   }

   public String getPendingRenewalListTimestampSql() {
      if(this.pendingRenewalListTimestampSql == null) {
         this.pendingRenewalListTimestampSql = "SELECT APPLICATION_NUMBER, REC_CREATE_TIMESTAMP FROM " + this.v1appnumber + " WHERE BENEFIT_YEAR = ? AND " + "STATUS_CODE = ? AND " + "TYPE_CODE = ? AND " + "REC_CREATE_TIMESTAMP <= ?  AND " + "((REC_CREATE_TIMESTAMP,APPLICATION_NUMBER) > (?,?)) AND " + "REC_INACTIVE_TIMESTAMP IS NULL " + "ORDER BY REC_CREATE_TIMESTAMP ASC,APPLICATION_NUMBER ASC " + "BROWSE ACCESS;";
      }

      return this.pendingRenewalListTimestampSql;
   }

   public String getTDP004ApplNumbersSql() {
      if(this.getTDP004ApplNumbersSql == null) {
         this.getTDP004ApplNumbersSql = " SELECT APPLICATION_NUMBER, REC_CREATE_TIMESTAMP  FROM V1APPNUMBER  WHERE BENEFIT_YEAR = ?  AND TYPE_CODE = \'R\'  AND STATUS_CODE IN (\'R\', \'I\')  AND REC_INACTIVE_TIMESTAMP IS NULL  AND APPLICATION_NUMBER NOT IN  (SELECT APPLICATION_NUMBER FROM appnote WHERE  ACTIVITY_CODE  = 474  AND CATEGORY_CODE = 26  AND BENEFIT_YEAR = ?  AND REC_INACTIVE_TIMESTAMP IS NULL BROWSE ACCESS ) ORDER BY APPLICATION_NUMBER ASC  BROWSE ACCESS ";
      }

      return this.getTDP004ApplNumbersSql;
   }

   public String getExcptionReportSql() {
      if(this.insertExceptionReport == null) {
         this.insertExceptionReport = "INSERT INTO " + this.batchjobexception + "(BATCHJOB_EXCEPTION_ID   , " + "BATCHJOB_ID        , " + "EXCEPTION_NO        , " + "DESCRIPTION        , " + "APPLICATION_NUMBER  , " + "ODB_ELIG_NO         , " + "REC_CREATE_MODULEID , " + "REC_CREATE_USERID , " + "LAST_UPDATE_MODULEID, " + "LAST_UPDATE_USERID) " + " VALUES(?,?,?,?,?,?,?,?,?,?);";
      }

      return this.insertExceptionReport;
   }
}
