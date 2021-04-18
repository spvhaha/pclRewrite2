package persistence.ds.impl;

import business.objects.trillium.TrilliumHousehold;
import business.objects.trillium.TrilliumMember;
import exception.ds.DataServiceException;
import exception.ds.DataserviceUniqueConstraintException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.core.Cursor;
import persistence.core.JdbcTemplate;
import persistence.core.PreparedStatement;
import persistence.core.Row;
import persistence.core.RowSet;
import persistence.ds.IDataServiceDao;
import persistence.ds.impl.AppCommittedComposite;
import persistence.ds.impl.AppNumberComposite;
import persistence.ds.impl.AppTerminationListComposite;
import persistence.ds.impl.SqlStatement;
import persistence.util.IDataSourceProvider;
import util.Context;

public class DataServiceDao implements IDataServiceDao {

   private static final String QUERY_TIMEOUT_PROPNAME = "queryTimeOut";
   private static final String DEFAULT_QUERY_TIMEOUT = "0";
   private static final int CATEGORY_CODE = 25;
   private static final int ACTIVITY_CODE = 458;
   private int queryTimeOut;
   private IDataSourceProvider dsp;
   private JdbcTemplate template;
   private SqlStatement sqlStatement;
   private static final String FORMAT_LPTS = "yyyy-MM-dd HH:mm:ss.ssssss";
   private static final String TTS_STATUS_CODE_PEND = "P";
   private static final String TTS_TYPE_CODE_RENEWAL = "R";
   public static final Logger logger = Logger.getLogger(DataServiceDao.class.getName());


   public void setDsp(IDataSourceProvider dataSourceProvider) {
      this.dsp = dataSourceProvider;
      this.template = new JdbcTemplate();
      this.template.setDataSource(dataSourceProvider.getDataSource());
      this.queryTimeOut = Integer.parseInt(this.dsp.getPrefixProp("queryTimeOut", "0"));
      this.sqlStatement = new SqlStatement(dataSourceProvider);
   }

   public int updateAppnumberForAppnum(TrilliumHousehold trilliumHousehold) {
      TrilliumMember trilliumMember = (TrilliumMember)trilliumHousehold.getMembers().get(trilliumHousehold.findContactPersonNo());
      PreparedStatement ps = this.template.newPreparedStatement(this.sqlStatement.getUpdateAppnumberForAppnum()).addStringParam(trilliumHousehold.getStatusCode()).addLongParam(Long.parseLong(trilliumMember.getFmbrOdbEligNo())).addStringParam(this.setBlank(trilliumMember.getRecpFirstName()).toUpperCase()).addStringParam(this.setBlank(trilliumMember.getRecpMiddleName()).toUpperCase()).addStringParam(this.setBlank(trilliumMember.getRecpLastName()).toUpperCase()).addStringParam(this.setBlank(trilliumMember.getRpdbAddr1()).toUpperCase()).addStringParam(this.setBlank(trilliumMember.getRpdbAddr2()).toUpperCase()).addStringParam(this.setBlank(trilliumMember.getRpdbCity()).toUpperCase()).addStringParam(this.setBlank(trilliumMember.getRpdbProvCd()).toUpperCase()).addStringParam(this.postalCodeWithBlank(trilliumMember.getRpdbPostalCd(), trilliumMember.getRpdbCountryCd())).addStringParam(this.setBlank(trilliumMember.getRecpLanguagePref()).toUpperCase()).addStringParam(this.setBlank(trilliumMember.getPhoneAreaCdTam()) + this.setBlank(trilliumMember.getPhoneTam())).addStringParam(this.setBlank(trilliumMember.getBusPhoneAreaCdTam()) + this.setBlank(trilliumMember.getBusPhoneTam())).addStringParam(this.setBlank(trilliumMember.getBusPhoneExtTam())).addStringParam(this.setBlank(trilliumMember.getRpdbCountryCd())).addStringParam(Context.getProperty("moduleId")).addStringParam(trilliumHousehold.getApplicationNumber());
      ps.setQueryTimeout(this.queryTimeOut);
      return ps.executeUpdate();
   }

   public int updateAppnumberAddress(TrilliumHousehold trilliumHousehold) {
      TrilliumMember trilliumMember = (TrilliumMember)trilliumHousehold.getMembers().get(trilliumHousehold.findContactPersonNo());
      PreparedStatement ps = this.template.newPreparedStatement(this.sqlStatement.getUpdateAppnumberAddress()).addStringParam(this.setBlank(trilliumMember.getRecpFirstName()).toUpperCase()).addStringParam(this.setBlank(trilliumMember.getRecpMiddleName()).toUpperCase()).addStringParam(this.setBlank(trilliumMember.getRecpLastName()).toUpperCase()).addStringParam(this.setBlank(trilliumMember.getRpdbAddr1()).toUpperCase()).addStringParam(this.setBlank(trilliumMember.getRpdbAddr2()).toUpperCase()).addStringParam(this.setBlank(trilliumMember.getRpdbCity()).toUpperCase()).addStringParam(this.setBlank(trilliumMember.getRpdbProvCd()).toUpperCase()).addStringParam(this.postalCodeWithBlank(trilliumMember.getRpdbPostalCd(), trilliumMember.getRpdbCountryCd())).addStringParam(this.setBlank(trilliumMember.getRecpLanguagePref()).toUpperCase()).addStringParam(this.setBlank(trilliumMember.getPhoneAreaCdTam()) + this.setBlank(trilliumMember.getPhoneTam())).addStringParam(this.setBlank(trilliumMember.getBusPhoneAreaCdTam()) + this.setBlank(trilliumMember.getBusPhoneTam())).addStringParam(this.setBlank(trilliumMember.getBusPhoneExtTam())).addStringParam(this.setBlank(trilliumMember.getRpdbCountryCd())).addStringParam(Context.getProperty("moduleId")).addLongParam(Long.parseLong(trilliumMember.getFmbrOdbEligNo())).addStringParam(trilliumHousehold.getApplicationNumber());
      ps.setQueryTimeout(this.queryTimeOut);
      return ps.executeUpdate();
   }

   public TrilliumHousehold getTtsHousehold(String applicationNumber, TrilliumHousehold trilliumHousehold) {
      PreparedStatement ps = this.template.newPreparedStatement(this.sqlStatement.getSelectTtsHousehold()).addStringParam(applicationNumber);
      ps.setQueryTimeout(this.queryTimeOut);
      RowSet rs = ps.executeQuery();
      if(rs.hasNext()) {
         Row row = rs.next();
         trilliumHousehold.setApplicationNumber(applicationNumber);
         trilliumHousehold.setTypeCode(row.getColumnAsTrimmedString("TYPE_CODE"));
         trilliumHousehold.setTypeDescription(row.getColumnAsTrimmedString("TYPE_DESC"));
         trilliumHousehold.setStatusCode(row.getColumnAsTrimmedString("STATUS_CODE"));
         trilliumHousehold.setStatusDescription(row.getColumnAsTrimmedString("STATUS_DESC"));
         trilliumHousehold.setBenefitYear(row.getColumnAsTrimmedString("BENEFIT_YEAR"));
         trilliumHousehold.setFmlyAppProcessDt(row.getColumnAsTrimmedString("APP_PROCESSED_DT"));
         trilliumHousehold.setFmlyUseDelegateInd(row.getColumnAsTrimmedString("USE_DELEGATE_IND"));
         trilliumHousehold.setTtsSummary(true);
      } else {
         trilliumHousehold.setTtsSummary(false);
      }

      return trilliumHousehold;
   }

   public int updateAppnumberStatusCode(String applicationNumber, String statusCode) {
      PreparedStatement ps = this.template.newPreparedStatement(this.sqlStatement.getUpdateAppnumberStatusCode()).addStringParam(statusCode).addStringParam(Context.getProperty("moduleId")).addStringParam(applicationNumber);
      ps.setQueryTimeout(this.queryTimeOut);
      return ps.executeUpdate();
   }

   private String setBlank(String target) {
      return target != null && !target.trim().equals("")?target.trim():" ";
   }

   private String postalCodeWithBlank(String target, String country) {
      StringBuilder result = new StringBuilder(target);
      if(target != null && target.length() != 0) {
         if(!target.substring(3, 4).equals(" ") && country.equals("CAN")) {
            result = result.insert(3, " ");
            if(result.length() > 7) {
               result = result.deleteCharAt(7);
            }
         }
      } else {
         result.append(" ");
      }

      return result.toString();
   }

   public TrilliumMember getApplicationContact(String applicationNumber) {
      PreparedStatement ps = this.template.newPreparedStatement(this.sqlStatement.getFindAppnumberContactSql()).addStringParam(applicationNumber);
      ps.setQueryTimeout(this.queryTimeOut);
      RowSet rs = ps.executeQuery();
      TrilliumMember contact = new TrilliumMember();
      if(rs.hasNext()) {
         Row row = rs.next();
         contact.setRecpFirstName(row.getColumnAsTrimmedString("CONTACT_FIRST_NAME"));
         contact.setRecpMiddleName(row.getColumnAsTrimmedString("CONTACT_MIDDLE_NAME"));
         contact.setRecpLastName(row.getColumnAsTrimmedString("CONTACT_LAST_NAME"));
         contact.setRpdbAddr1(row.getColumnAsTrimmedString("CONTACT_ADDR_1"));
         contact.setRpdbAddr2(row.getColumnAsTrimmedString("CONTACT_ADDR_2"));
         contact.setRpdbCity(row.getColumnAsTrimmedString("CONTACT_CITY"));
         contact.setRpdbProvCd(row.getColumnAsTrimmedString("CONTACT_PROV_CD"));
         contact.setRpdbPostalCd(row.getColumnAsTrimmedString("CONTACT_POSTAL_CD"));
         contact.setRpdbCountryCd(row.getColumnAsTrimmedString("COUNTRY_CD"));
         contact.setRecpLanguagePref(row.getColumnAsTrimmedString("CONTACT_LANGUAGE_PREF"));
         contact.setUseDelegateInd(row.getColumnAsTrimmedString("USE_DELEGATE_IND"));
      } else {
         contact = null;
      }

      return contact;
   }

   public void writeInsertNote(int benftyr, String applicationNumber, String note, int categoryCode, int activityCode, String createModuleId, String recordCreateUserId) {
      PreparedStatement ps1 = this.template.newPreparedStatement(this.sqlStatement.getTractivitySql()).addIntParam(activityCode).addIntParam(categoryCode).addTimestampParam(new Timestamp((new Date()).getTime()));
      ps1.setQueryTimeout(this.queryTimeOut);
      RowSet rs = ps1.executeQuery();
      int recs = 0;
      if(rs.hasNext()) {
         Row ps2 = rs.next();
         ++recs;
      }

      if(recs == 0) {
         throw new RuntimeException(" dataService insert note failure tracktivity not found activityCode/activityCode: " + activityCode + activityCode);
      } else {
         PreparedStatement var13 = this.template.newPreparedStatement(this.sqlStatement.getInsertNoteSql()).addStringParam(this.setBlank(applicationNumber)).addIntParam(categoryCode).addIntParam(activityCode).addTimestampParam(new Timestamp((new Date()).getTime())).addStringParam(this.setBlank(note)).addStringParam(this.setBlank(createModuleId)).addIntParam(benftyr).addStringParam(this.setBlank(createModuleId)).addStringParam(this.setBlank(createModuleId)).addStringParam(this.setBlank(createModuleId));
         var13.setQueryTimeout(this.queryTimeOut);
         int rtnCode = var13.executeUpdate();
         if(rtnCode == 0) {
            throw new RuntimeException(" dataService insert note failure : ");
         }
      }
   }

   public void updateHouseholdTdp003(TrilliumHousehold trilliumHousehold, String updateModuleId, String userIdAlias) {
      TrilliumMember trilliumMember = (TrilliumMember)trilliumHousehold.getMembers().get(trilliumHousehold.findContactPersonNo());
      int contactMemberNumber = trilliumHousehold.findContactPersonNo();
      List trilliumMembers = trilliumHousehold.getMembers();
      BigDecimal number = new BigDecimal(((TrilliumMember)trilliumMembers.get(contactMemberNumber)).getFmbrOdbEligNo());
      String phoneAreaCdTam = "";
      if(trilliumMember.getPhoneAreaCdTam() != null && trilliumMember.getPhoneAreaCdTam().trim().length() > 0) {
         phoneAreaCdTam = trilliumMember.getPhoneAreaCdTam().trim();
      }

      String phoneTam = "";
      if(trilliumMember.getPhoneTam() != null && trilliumMember.getPhoneTam().trim().length() > 0) {
         phoneTam = trilliumMember.getPhoneTam().trim();
      }

      phoneAreaCdTam = phoneAreaCdTam + phoneTam;
      String busPhoneAreaCdTam = "";
      if(trilliumMember.getBusPhoneAreaCdTam() != null && trilliumMember.getBusPhoneAreaCdTam().trim().length() > 0) {
         busPhoneAreaCdTam = trilliumMember.getBusPhoneAreaCdTam().trim();
      }

      String busPhoneTam = "";
      if(trilliumMember.getBusPhoneTam() != null && trilliumMember.getBusPhoneTam().trim().length() > 0) {
         busPhoneTam = trilliumMember.getBusPhoneTam().trim();
      }

      busPhoneAreaCdTam = busPhoneAreaCdTam + busPhoneTam;
      String lanRef = " ";
      if(trilliumMember.getRecpLanguagePref() != null && trilliumMember.getRecpLanguagePref().trim().length() > 0) {
         lanRef = trilliumMember.getRecpLanguagePref().trim().toUpperCase();
      }

      String countryCd = " ";
      if(trilliumMember.getRpdbCountryCd() != null && trilliumMember.getRpdbCountryCd().trim().length() > 0) {
         countryCd = trilliumMember.getRpdbCountryCd();
      }

      PreparedStatement ps1 = this.template.newPreparedStatement(this.sqlStatement.getAppnumTdp003Update1Sql()).addStringParam(trilliumHousehold.getStatusCode()).addDecimalParam(number).addStringParam(this.setBlank(trilliumMember.getRecpFirstName()).trim().toUpperCase()).addStringParam(trilliumMember.getRecpMiddleName() != null && trilliumMember.getRecpMiddleName().trim().length() != 0?trilliumMember.getRecpMiddleName().trim():null).addStringParam(this.setBlank(trilliumMember.getRecpLastName()).toUpperCase()).addStringParam(this.setBlank(trilliumMember.getRpdbAddr1()).toUpperCase()).addStringParam(trilliumMember.getRpdbAddr2() != null && trilliumMember.getRpdbAddr2().trim().length() != 0?trilliumMember.getRpdbAddr2().trim():null).addStringParam(this.setBlank(countryCd.trim().toUpperCase())).addStringParam(this.setBlank(trilliumMember.getRpdbCity().trim().toUpperCase())).addStringParam(this.setBlank(trilliumMember.getRpdbProvCd().trim().toUpperCase())).addStringParam(this.postalCodeWithBlank(trilliumMember.getRpdbPostalCd(), trilliumMember.getRpdbCountryCd())).addStringParam(this.setBlank(lanRef)).addStringParam(phoneAreaCdTam != null && phoneAreaCdTam.trim().length() != 0?phoneAreaCdTam.trim():null).addStringParam(busPhoneAreaCdTam != null && busPhoneAreaCdTam.trim().length() != 0?busPhoneAreaCdTam.trim():null).addStringParam(trilliumMember.getBusPhoneExtTam() != null && trilliumMember.getBusPhoneExtTam().trim().length() != 0?trilliumMember.getBusPhoneExtTam().trim():null).addStringParam(this.setBlank(updateModuleId)).addStringParam(this.setBlank(updateModuleId)).addStringParam(this.setBlank(trilliumHousehold.getApplicationNumber()));
      ps1.setQueryTimeout(this.queryTimeOut);
      int rtnCode = ps1.executeUpdate();
      if(rtnCode == 0) {
         throw new RuntimeException(" dataService update failure application: " + trilliumHousehold.getApplicationNumber());
      } else {
         PreparedStatement ps2 = this.template.newPreparedStatement(this.sqlStatement.getAppnumTdp003Update2Sql()).addStringParam(this.setBlank(trilliumMember.getRecpFirstName()).trim().toUpperCase()).addStringParam(trilliumMember.getRecpMiddleName() != null && trilliumMember.getRecpMiddleName().trim().length() != 0?trilliumMember.getRecpMiddleName().trim():null).addStringParam(this.setBlank(trilliumMember.getRecpLastName()).toUpperCase()).addStringParam(this.setBlank(trilliumMember.getRpdbAddr1()).toUpperCase()).addStringParam(trilliumMember.getRpdbAddr2() != null && trilliumMember.getRpdbAddr2().trim().length() != 0?trilliumMember.getRpdbAddr2().trim():null).addStringParam(this.setBlank(trilliumMember.getRpdbCity().trim().toUpperCase())).addStringParam(this.setBlank(countryCd.trim().toUpperCase())).addStringParam(this.setBlank(trilliumMember.getRpdbProvCd().trim().toUpperCase())).addStringParam(this.postalCodeWithBlank(trilliumMember.getRpdbPostalCd(), trilliumMember.getRpdbCountryCd())).addStringParam(this.setBlank(lanRef)).addStringParam(phoneAreaCdTam != null && phoneAreaCdTam.trim().length() != 0?phoneAreaCdTam.trim():null).addStringParam(busPhoneAreaCdTam != null && busPhoneAreaCdTam.trim().length() != 0?busPhoneAreaCdTam.trim():null).addStringParam(trilliumMember.getBusPhoneExtTam() != null && trilliumMember.getBusPhoneExtTam().trim().length() != 0?trilliumMember.getBusPhoneExtTam().trim():null).addStringParam(this.setBlank(updateModuleId)).addStringParam(this.setBlank(updateModuleId)).addDecimalParam(number).addStringParam(this.setBlank(trilliumHousehold.getApplicationNumber())).addDecimalParam(number);
         ps2.setQueryTimeout(this.queryTimeOut);
         rtnCode = ps2.executeUpdate();
         if(logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, " query 2 updated  " + trilliumHousehold.getApplicationNumber() + " return code: " + rtnCode);
         }

      }
   }

   public TrilliumMember getDelegateContactAddress(String applicationNumber) {
      PreparedStatement ps = this.template.newPreparedStatement(this.sqlStatement.getDelegateContactAddressSql()).addStringParam(applicationNumber.substring(0, 9));
      ps.setQueryTimeout(this.queryTimeOut);
      RowSet rs = ps.executeQuery();
      TrilliumMember delegate = new TrilliumMember();
      if(rs.hasNext()) {
         Row row = rs.next();
         delegate.setRecpFirstName(row.getColumnAsTrimmedString("FIRST_NAME"));
         delegate.setRecpMiddleName(row.getColumnAsTrimmedString("MIDDLE_NAME"));
         delegate.setRecpLastName(row.getColumnAsTrimmedString("LAST_NAME"));
         delegate.setRpdbAddr1(row.getColumnAsTrimmedString("ADDR_1"));
         delegate.setRpdbAddr2(row.getColumnAsTrimmedString("ADDR_2"));
         delegate.setRpdbCity(row.getColumnAsTrimmedString("CITY"));
         delegate.setRpdbProvCd(row.getColumnAsTrimmedString("PROV_CD"));
         delegate.setRpdbPostalCd(row.getColumnAsTrimmedString("POSTAL_CD"));
         delegate.setRpdbCountryCd(row.getColumnAsTrimmedString("COUNTRY_CD"));
         delegate.setRecpLanguagePref(row.getColumnAsTrimmedString("LANGUAGE_PREF"));
      } else {
         delegate = null;
      }

      return delegate;
   }

   public int getApplicationAlerts(String applicationNumber, int alertsActivityCode, int alertsCategoryCod) {
      PreparedStatement ps = this.template.newPreparedStatement(this.sqlStatement.getApllicationAlertsSql()).addStringParam(applicationNumber).addIntParam(alertsActivityCode).addIntParam(alertsCategoryCod);
      ps.setQueryTimeout(this.queryTimeOut);
      RowSet rs = ps.executeQuery();
      byte alertsFound = 100;
      if(rs.hasNext()) {
         Row row = rs.next();
         alertsFound = 1;
      }

      if(logger.isLoggable(Level.FINE)) {
         logger.log(Level.FINE, "\n getApplicationAlerts: appnumber " + applicationNumber + " found: " + alertsFound);
      }

      return alertsFound;
   }

   public SortedMap getApplicationRenewalPendinList(String statusCode, String typeCode, int benYear) {
      PreparedStatement ps = this.template.newPreparedStatement(this.sqlStatement.getApplicationRenewalPendinListSql()).addStringParam(statusCode).addStringParam(typeCode).addIntParam(benYear);
      ps.setQueryTimeout(this.queryTimeOut);
      TreeMap map = new TreeMap();

      try {
         Cursor e = ps.executeQueryCursor();

         while(e.next()) {
            map.put(e.getString(1), e.getString(2));
         }

         e.close();
         return map;
      } catch (SQLException var7) {
         logger.log(Level.SEVERE, var7.toString(), var7);
         throw new DataServiceException("dataservice getPendingRenewalList failed getting renewal list: ", var7);
      }
   }

   public List applTerminationList(int benefitYr, String appNum, Timestamp startDate, Timestamp endDate) {
      PreparedStatement ps = this.template.newPreparedStatement(this.sqlStatement.getApplTerminationListSql()).addIntParam(benefitYr).addTimestampParam(endDate).addTimestampParam(startDate).addStringParam(appNum).addIntParam(benefitYr).addTimestampParam(startDate).addTimestampParam(endDate);
      ps.setQueryTimeout(this.queryTimeOut);
      ArrayList list = new ArrayList();
      RowSet rs = ps.executeQuery();

      while(rs.hasNext()) {
         Row row = rs.next();
         AppTerminationListComposite appNumberComposite = new AppTerminationListComposite();
         appNumberComposite.setAppl_number(row.getColumnAsTrimmedString("APPLICATION_NUMBER"));
         appNumberComposite.setActivity_code(row.getColumnAsInt("ACTIVITY_CODE"));
         appNumberComposite.setRec_create_timestamp(row.getColumnAsTimestamp("REC_CREATE_TIMESTAMP").toString());
         appNumberComposite.setUse_delegate_ind(row.getColumnAsTrimmedString("USE_DELEGATE_IND"));
         list.add(appNumberComposite);
      }

      return list;
   }

   public List applCommittedList(int benefitYear, Timestamp recCreateTimestamp) {
      PreparedStatement ps = this.template.newPreparedStatement(this.sqlStatement.getApplCommittedListSql()).addIntParam(benefitYear).addIntParam(benefitYear).addTimestampParam(recCreateTimestamp);
      ps.setQueryTimeout(this.queryTimeOut);
      Cursor rs = ps.executeQueryCursor();
      ArrayList list = new ArrayList();

      try {
         while(rs.next()) {
            AppCommittedComposite e = new AppCommittedComposite();
            e.setApplicationNumber(rs.getString(1));
            String tmp = rs.getString(2);
            Timestamp ts2 = Timestamp.valueOf(tmp);
            e.setRecCreateTimeStamp(ts2);
            list.add(e);
         }

         rs.close();
         return list;
      } catch (Exception var9) {
         throw new RuntimeException(" dataService applCommittedList failure : ");
      }
   }

   public Set appNumberNoEndYearSet(int benefitYear, int etpar, String typeCode, String statusCode) {
      PreparedStatement psAppNumStat = this.template.newPreparedStatement(this.sqlStatement.getAppNumStatSql()).addStringParam(statusCode);
      psAppNumStat.setQueryTimeout(this.queryTimeOut);
      RowSet rsAppNumStat = psAppNumStat.executeQuery();
      if(!rsAppNumStat.hasNext()) {
         logger.log(Level.FINE, "dataService: AppNumStat table - no records found in the MX/SQL database for benefir year: " + benefitYear + " and etpar: " + etpar + " and typeCode: " + typeCode + " and statusCode: " + statusCode);
         return null;
      } else {
         PreparedStatement psAppNumType = this.template.newPreparedStatement(this.sqlStatement.getAppNumTypeSql()).addStringParam(typeCode);
         psAppNumType.setQueryTimeout(this.queryTimeOut);
         RowSet rsAppNumType = psAppNumStat.executeQuery();
         if(!rsAppNumType.hasNext()) {
            logger.log(Level.FINE, "dataService: AppNumType table - no records found in the MX/SQL database for benefir year: " + benefitYear + " and etpar: " + etpar + " and typeCode: " + typeCode + " and statusCode: " + statusCode);
            return null;
         } else {
            int EtparBenftYear = benefitYear - etpar;
            PreparedStatement ps = this.template.newPreparedStatement(this.sqlStatement.getAppNumberNoEndYearSetSql()).addIntParam(benefitYear).addIntParam(EtparBenftYear).addStringParam(statusCode).addStringParam(typeCode);
            Cursor rs = ps.executeQueryCursor();
            HashSet appNumSet = new HashSet();
            String appNum = null;

            try {
               while(rs.next()) {
                  appNum = rs.getString(1);
                  if(appNum != null && appNum.trim().length() >= 2) {
                     appNumSet.add(appNum.trim());
                  }
               }

               rs.close();
               return appNumSet;
            } catch (Exception var15) {
               throw new RuntimeException(" dataService appNumberNoEndYearSet failure : " + appNum);
            }
         }
      }
   }

   public void insertSoftDelete(String oldAppNum, int categoryCode, int activityCode, String note, String recCreateModuleID, String userIdAlias) {
      PreparedStatement ps = this.template.newPreparedStatement(this.sqlStatement.getSelectAppnumberSql()).addStringParam(this.setBlank(oldAppNum));
      ps.setQueryTimeout(this.queryTimeOut);
      RowSet rs = ps.executeQuery();
      String newAppNum = "";
      boolean bnftyr = false;
      if(!rs.hasNext()) {
         throw new DataServiceException("dataservice error/old appnumber not found - Inserting Termination record based on application number: " + newAppNum + ". oldAppNumber: " + oldAppNum + " old appnumber not found");
      } else {
         Row row = rs.next();
         int var21 = row.getColumnAsInt("BENEFIT_YEAR");
         ++var21;
         String syear = Integer.toString(var21);
         newAppNum = oldAppNum.substring(0, 9) + syear.substring(2);
         String middleName = "";
         if(row.getColumnAsString("CONTACT_MIDDLE_NAME") != null && row.getColumnAsString("CONTACT_MIDDLE_NAME").trim().length() > 0) {
            middleName = row.getColumnAsString("CONTACT_MIDDLE_NAME");
         } else {
            middleName = null;
         }

         String contactAddr2 = "";
         if(row.getColumnAsString("CONTACT_ADDR_2") != null && row.getColumnAsString("CONTACT_ADDR_2").trim().length() > 0) {
            contactAddr2 = row.getColumnAsString("CONTACT_ADDR_2");
         } else {
            contactAddr2 = null;
         }

         Object date = null;
         PreparedStatement psInsertAppnumber = this.template.newPreparedStatement(this.sqlStatement.getInsertSoftDeleteSql()).addStringParam(newAppNum).addIntParam(var21).addStringParam(row.getColumnAsString("CONTACT_FIRST_NAME")).addStringParam(middleName).addStringParam(row.getColumnAsString("CONTACT_LAST_NAME")).addLongParam(row.getColumnAsLong("CONTACT_ODB_ELIG_NO")).addStringParam(row.getColumnAsString("CONTACT_ADDR_1")).addStringParam(contactAddr2).addStringParam(row.getColumnAsString("CONTACT_CITY")).addStringParam(row.getColumnAsString("CONTACT_PROV_CD")).addStringParam(row.getColumnAsString("CONTACT_POSTAL_CD")).addStringParam(row.getColumnAsString("CONTACT_LANGUAGE_PREF")).addStringParam(row.getColumnAsString("CONTACT_HOME_PHONE")).addStringParam(row.getColumnAsString("CONTACT_BUSINESS_PHONE")).addStringParam(row.getColumnAsString("CONTACT_BUSINESS_EXTENSION")).addStringParam(recCreateModuleID).addStringParam(recCreateModuleID).addStringParam(row.getColumnAsString("PROGRAM_GRP")).addStringParam(row.getColumnAsString("COUNTRY_CD")).addStringParam(row.getColumnAsString("USE_DELEGATE_IND")).addStringParam(recCreateModuleID).addStringParam(recCreateModuleID);
         int rtnCode = psInsertAppnumber.executeUpdate();
         if(rtnCode == 0) {
            throw new DataserviceUniqueConstraintException("Unique constraint violation doing Soft Delete based on application number: " + oldAppNum + ". return Code: " + rtnCode + " application number: " + newAppNum + ". oldAppNumt: " + oldAppNum);
         } else {
            try {
               this.renewAppAlertsTermAppnumber(oldAppNum, newAppNum, recCreateModuleID, 25, 458);
            } catch (Exception var20) {
               logger.log(Level.SEVERE, var20.toString(), var20);
               throw new DataServiceException("dataservice insertSoftDelete Renew Appalerts - application number: " + newAppNum + oldAppNum, var20);
            }

            try {
               this.writeInsertNote(var21, newAppNum, note, categoryCode, activityCode, recCreateModuleID, userIdAlias);
            } catch (Exception var19) {
               logger.log(Level.SEVERE, var19.toString(), var19);
               throw new DataServiceException("dataservice insertSoftDelete note - application number: " + newAppNum + " oldAppNumber" + oldAppNum, var19);
            }
         }
      }
   }

   public void insertRenewAppNumber(String oldAppNum, String modulID, String userIdAlias, int alertsActivityCode, int alertsCategoryCode, String renewAlerts) {
      try {
         PreparedStatement e = this.template.newPreparedStatement(this.sqlStatement.getSelectAppnumberSql()).addStringParam(this.setBlank(oldAppNum));
         e.setQueryTimeout(this.queryTimeOut);
         RowSet rs = e.executeQuery();
         String newAppNum = "";
         boolean bnftyr = false;
         if(rs.hasNext()) {
            Row row = rs.next();
            int var18 = row.getColumnAsInt("BENEFIT_YEAR");
            ++var18;
            String syear = Integer.toString(var18);
            newAppNum = oldAppNum.substring(0, 9) + syear.substring(2);
            String middleName = "";
            if(row.getColumnAsString("CONTACT_MIDDLE_NAME") != null && row.getColumnAsString("CONTACT_MIDDLE_NAME").trim().length() > 0) {
               middleName = row.getColumnAsString("CONTACT_MIDDLE_NAME");
            } else {
               middleName = null;
            }

            String contactAddr2 = "";
            if(row.getColumnAsString("CONTACT_ADDR_2") != null && row.getColumnAsString("CONTACT_ADDR_2").trim().length() > 0) {
               contactAddr2 = row.getColumnAsString("CONTACT_ADDR_2");
            } else {
               contactAddr2 = null;
            }

            PreparedStatement InsertRenewAppNum = this.template.newPreparedStatement(this.sqlStatement.getInsertRenewAppNumberSql()).addStringParam(newAppNum).addIntParam(var18).addStringParam(row.getColumnAsString("CONTACT_FIRST_NAME")).addStringParam(middleName).addStringParam(row.getColumnAsString("CONTACT_LAST_NAME")).addLongParam(row.getColumnAsLong("CONTACT_ODB_ELIG_NO")).addStringParam(row.getColumnAsString("CONTACT_ADDR_1")).addStringParam(contactAddr2).addStringParam(row.getColumnAsString("CONTACT_CITY")).addStringParam(row.getColumnAsString("CONTACT_PROV_CD")).addStringParam(row.getColumnAsString("CONTACT_POSTAL_CD")).addStringParam(row.getColumnAsString("CONTACT_LANGUAGE_PREF")).addStringParam(row.getColumnAsString("CONTACT_HOME_PHONE")).addStringParam(row.getColumnAsString("CONTACT_BUSINESS_PHONE")).addStringParam(row.getColumnAsString("CONTACT_BUSINESS_EXTENSION")).addStringParam(modulID).addStringParam(modulID).addStringParam(row.getColumnAsString("PROGRAM_GRP")).addStringParam(row.getColumnAsString("COUNTRY_CD")).addStringParam(row.getColumnAsString("USE_DELEGATE_IND")).addStringParam(modulID).addStringParam(modulID);
            int rtnCode = InsertRenewAppNum.executeUpdate();
            if(rtnCode == 0) {
               throw new DataserviceUniqueConstraintException("Unique constraint violation doing renew application based on application number: " + oldAppNum + ". return Code: " + rtnCode + ". oldAppNumt: " + oldAppNum);
            }
         }

         if(renewAlerts.equals("Y")) {
            this.renewAppAlerts(oldAppNum, newAppNum, modulID);
         }

      } catch (Exception var17) {
         throw new DataServiceException("dataservice error doing renewxx application based on application number: " + oldAppNum + ". oldAppNumt: " + oldAppNum);
      }
   }

   public List getPendingRenewalListTimestamp(int benefitYr, String startDate, String endDate, String appNumber) {
      PreparedStatement ps = this.template.newPreparedStatement(this.sqlStatement.getPendingRenewalListTimestampSql()).addIntParam(benefitYr).addStringParam("P").addStringParam("R").addTimestampParam(Timestamp.valueOf(endDate)).addTimestampParam(Timestamp.valueOf(startDate)).addStringParam(appNumber);
      Cursor rs = ps.executeQueryCursor();
      ArrayList list = new ArrayList();

      try {
         while(rs.next()) {
            list.add(rs.getString(2) + ";" + rs.getString(1));
         }

         rs.close();
         return list;
      } catch (Exception var9) {
         throw new RuntimeException(" dataService getPendingRenewalListTimestamp failure : " + appNumber + " startDate: " + startDate + "endDate: " + endDate + " benefitYr: " + benefitYr + " msg:" + var9);
      }
   }

   public void renewAppAlerts(String oldAppNum, String newAppNum, String modulID) {
      PreparedStatement spSelectAlerts = this.template.newPreparedStatement(this.sqlStatement.getRenewAlertsSql()).addStringParam(oldAppNum);
      spSelectAlerts.setQueryTimeout(this.queryTimeOut);
      Cursor rsSelectAlerts = spSelectAlerts.executeQueryCursor();

      try {
         int rtnCode;
         do {
            if(!rsSelectAlerts.next()) {
               rsSelectAlerts.close();
               return;
            }

            String e = "";
            Timestamp ts = null;
            if(rsSelectAlerts.getString(12) != null) {
               e = rsSelectAlerts.getString(12);
               ts = Timestamp.valueOf(e);
            }

            PreparedStatement spRenewAlerts = this.template.newPreparedStatement(this.sqlStatement.getInsertRenewAlertsSql()).addStringParam(newAppNum).addIntParam(Integer.parseInt(rsSelectAlerts.getString(3))).addIntParam(Integer.parseInt(rsSelectAlerts.getString(4))).addStringParam(modulID).addStringParam(modulID).addStringParam(modulID).addStringParam(modulID).addStringParam(rsSelectAlerts.getString(10)).addStringParam(rsSelectAlerts.getString(11)).addTimestampParam(ts);
            spSelectAlerts.setQueryTimeout(this.queryTimeOut);
            rtnCode = spRenewAlerts.executeUpdate();
         } while(rtnCode != 0);

         throw new DataserviceUniqueConstraintException("Unique constraint appalerts violation doing renew application based on application number: " + newAppNum + ". return Code: " + rtnCode + ". oldAppNumt: " + oldAppNum);
      } catch (Exception var10) {
         throw new DataServiceException("dataservice error doing renew Alertrs application based on application number: " + newAppNum + ". oldAppNumt: " + oldAppNum);
      }
   }

   public void renewAppAlertsTermAppnumber(String oldAppNum, String newAppNum, String modulID, int categoryCode, int activityCode) {
      PreparedStatement spSelectAlerts = this.template.newPreparedStatement(this.sqlStatement.getAlertsSql()).addStringParam(oldAppNum).addIntParam(categoryCode).addIntParam(activityCode);
      spSelectAlerts.setQueryTimeout(this.queryTimeOut);
      Cursor rsSelectAlerts = spSelectAlerts.executeQueryCursor();

      try {
         int rtnCode;
         do {
            if(!rsSelectAlerts.next()) {
               rsSelectAlerts.close();
               return;
            }

            String e = "";
            Timestamp ts = null;
            if(rsSelectAlerts.getString(12) != null) {
               e = rsSelectAlerts.getString(12);
               ts = Timestamp.valueOf(e);
            }

            PreparedStatement spRenewAlerts = this.template.newPreparedStatement(this.sqlStatement.getInsertRenewAlertsSql()).addStringParam(newAppNum).addIntParam(Integer.parseInt(rsSelectAlerts.getString(3))).addIntParam(Integer.parseInt(rsSelectAlerts.getString(4))).addStringParam(modulID).addStringParam(modulID).addStringParam(modulID).addStringParam(modulID).addStringParam(rsSelectAlerts.getString(10)).addStringParam(rsSelectAlerts.getString(11)).addTimestampParam(ts);
            spSelectAlerts.setQueryTimeout(this.queryTimeOut);
            rtnCode = spRenewAlerts.executeUpdate();
         } while(rtnCode != 0);

         throw new DataserviceUniqueConstraintException("Unique constraint appalerts violation doing renew application based on application number: " + newAppNum + ". return Code: " + rtnCode + ". oldAppNumt: " + oldAppNum);
      } catch (Exception var12) {
         throw new DataServiceException("dataservice error doing renew Alerts application based on application number: " + newAppNum + ". oldAppNumt: " + oldAppNum);
      }
   }

   public List getTDP004ApplicationNumbers(int benefitYr, String startDate, String endDate, String appNumber) {
      ArrayList appls = new ArrayList();
      PreparedStatement spTDP004ApplNumbers = this.template.newPreparedStatement(this.sqlStatement.getTDP004ApplNumbersSql()).addIntParam(benefitYr).addIntParam(benefitYr);
      spTDP004ApplNumbers.setQueryTimeout(this.queryTimeOut);
      RowSet rs = spTDP004ApplNumbers.executeQuery();
      Row row = null;

      while(rs.hasNext()) {
         AppNumberComposite appNumberComposite = new AppNumberComposite();
         row = rs.next();
         appNumberComposite.setApplicationNumber(row.getColumnAsTrimmedString("APPLICATION_NUMBER"));
         appNumberComposite.setRecCreateTimeStamp(row.getColumnAsTrimmedString("REC_CREATE_TIMESTAMP"));
         appls.add(appNumberComposite);
      }

      return appls;
   }

   public void insertExceptionReport(String batchJobID, int exceptionNum, String description, String appNumber, String odbEligNum, String lastUpdateModuleId, String lastUpdateUserId) {
      PreparedStatement psInsertExceptionReport = this.template.newPreparedStatement(this.sqlStatement.getExcptionReportSql()).addTimestampParam(new Timestamp((new Date()).getTime())).addStringParam(batchJobID).addIntParam(exceptionNum).addStringParam(description).addStringParam(appNumber).addStringParam(odbEligNum).addStringParam(lastUpdateModuleId).addStringParam(lastUpdateUserId).addStringParam(lastUpdateModuleId).addStringParam(lastUpdateUserId);
      int rc = psInsertExceptionReport.executeUpdate();
      if(rc == 0) {
         throw new DataserviceUniqueConstraintException(" Exception happend in insertExceptionReport for Application " + appNumber);
      }
   }

   public int WriteInsertNoteActivityDate(int benftyr, String applicationNumber, String note, int categoryCode, int activityCode, String createModuleId, String recordCreateUserId, String activityDate) {
      int rtnCode = 0;

      try {
         PreparedStatement e = this.template.newPreparedStatement(this.sqlStatement.getInsertNoteSql()).addStringParam(this.setBlank(applicationNumber)).addIntParam(categoryCode).addIntParam(activityCode).addStringParam(this.setBlank(activityDate)).addStringParam(this.setBlank(note)).addStringParam(this.setBlank(createModuleId)).addIntParam(benftyr).addStringParam(this.setBlank(createModuleId)).addStringParam(this.setBlank(createModuleId)).addStringParam(this.setBlank(createModuleId));
         e.setQueryTimeout(this.queryTimeOut);
         rtnCode = e.executeUpdate();
      } catch (Exception var11) {
         logger.log(Level.SEVERE, var11.toString(), var11);
         logger.log(Level.INFO, "dataservice APPNOTE table, Insert Note Error: " + rtnCode + " msg: ", var11);
      }

      return rtnCode;
   }

}
