package business.services.impl;

import business.objects.trillium.TrilliumHousehold;
import business.objects.trillium.TrilliumMember;
import business.services.ds.IDataService;
import exception.ds.DataServiceException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.ds.IDataServiceDao;

public class DataService implements IDataService {

   private static final String TTS_STATUS_CODE_COMMITTED = "C";
   private static final String ODB_ELIG_NO_9999999998 = "9999999998";
   private static final String TTS_STATUS_CODE_PEND = "P";
   private static final String TTS_TYPE_CODE_RENEWAL = "R";
   private IDataServiceDao dataServiceDao;
   private final Logger logger = Logger.getLogger(DataService.class.getName());


   public int updateAppnumberForAppnum(TrilliumHousehold trilliumHousehold) {
      if(trilliumHousehold == null) {
         this.logger.log(Level.SEVERE, "Appnumber update - TrilliumHousehold is NULL");
         throw new DataServiceException("Appnumber update - TrilliumHousehold is NULL");
      } else if((trilliumHousehold.isHeld() || trilliumHousehold.isTam()) && trilliumHousehold.isTtsSummary()) {
         if(trilliumHousehold.getApplicationNumber() == null) {
            this.logger.log(Level.SEVERE, "Application Number is NULL. Can not update Appnumber table for the TrilliumHousehold: " + trilliumHousehold.toString());
            throw new DataServiceException("Application Number is NULL. Can not update Appnumber table for the TrilliumHousehold: " + trilliumHousehold.toString());
         } else if(trilliumHousehold.getMembers() == null) {
            this.logger.log(Level.SEVERE, "There is no Family Members information provided for the household: " + trilliumHousehold.getApplicationNumber());
            throw new DataServiceException("There is no Family Members information provided for the household: " + trilliumHousehold.getApplicationNumber());
         } else if(trilliumHousehold.findContactPersonNo() < 0) {
            this.logger.log(Level.SEVERE, "There is no Contact information provided for the household: " + trilliumHousehold.getApplicationNumber());
            throw new DataServiceException("There is no Contact information provided for the household: " + trilliumHousehold.getApplicationNumber());
         } else {
            return this.dataServiceDao.updateAppnumberForAppnum(trilliumHousehold);
         }
      } else {
         this.logger.log(Level.SEVERE, "Can not update Appnumber table for the TrilliumHousehold: " + trilliumHousehold.getApplicationNumber());
         throw new DataServiceException("Can not update Appnumber table for the TrilliumHousehold: " + trilliumHousehold.getApplicationNumber());
      }
   }

   public int updateAppnumberAddress(TrilliumHousehold trilliumHousehold) {
      if(trilliumHousehold == null) {
         this.logger.log(Level.SEVERE, "Appnumber update - TrilliumHousehold is NULL");
         throw new DataServiceException("Appnumber update - TrilliumHousehold is NULL");
      } else if((trilliumHousehold.isHeld() || trilliumHousehold.isTam()) && trilliumHousehold.isTtsSummary()) {
         if(trilliumHousehold.getApplicationNumber() == null) {
            this.logger.log(Level.SEVERE, "Application Number is NULL. Can not update Appnumber table for the TrilliumHousehold: " + trilliumHousehold.toString());
            throw new DataServiceException("Application Number is NULL. Can not update Appnumber table for the TrilliumHousehold: " + trilliumHousehold.toString());
         } else if(trilliumHousehold.getMembers() == null) {
            this.logger.log(Level.SEVERE, "There is no Family Members information provided for the household: " + trilliumHousehold.getApplicationNumber());
            throw new DataServiceException("There is no Family Members information provided for the household: " + trilliumHousehold.getApplicationNumber());
         } else if(trilliumHousehold.findContactPersonNo() < 0) {
            this.logger.log(Level.SEVERE, "There is no Contact information provided for the household: " + trilliumHousehold.getApplicationNumber());
            throw new DataServiceException("There is no Contact information provided for the household: " + trilliumHousehold.getApplicationNumber());
         } else {
            return this.dataServiceDao.updateAppnumberAddress(trilliumHousehold);
         }
      } else {
         this.logger.log(Level.SEVERE, "Can not update Appnumber table for the TrilliumHousehold: " + trilliumHousehold.getApplicationNumber());
         throw new DataServiceException("Can not update Appnumber table for the TrilliumHousehold: " + trilliumHousehold.getApplicationNumber());
      }
   }

   public void updateHousehold(TrilliumHousehold trilliumHousehold) {
      boolean recUpdated = false;

      try {
         this.updateAppnumberForAppnum(trilliumHousehold);
         recUpdated = false;
         if(!((TrilliumMember)trilliumHousehold.getMembers().get(trilliumHousehold.findContactPersonNo())).getFmbrOdbEligNo().equals("9999999998")) {
            this.updateAppnumberAddress(trilliumHousehold);
         }

      } catch (Exception var4) {
         this.logger.log(Level.SEVERE, "updateHousehold(): " + var4.getMessage(), var4);
         throw new DataServiceException(var4);
      }
   }

   public TrilliumHousehold getTtsHousehold(String applicationNumber, TrilliumHousehold trilliumHousehold) {
      if(applicationNumber == null) {
         this.logger.log(Level.SEVERE, "Application Number is NULL");
         throw new DataServiceException("Application Number is NULL");
      } else if(trilliumHousehold == null) {
         this.logger.log(Level.SEVERE, "TrilliumHousehold is null for Application Number " + applicationNumber);
         throw new DataServiceException("TrilliumHousehold is null for Application Number " + applicationNumber);
      } else if(trilliumHousehold.isTtsSummary()) {
         this.logger.log(Level.SEVERE, "Operation is not supported. The TrilliumHousehold object is already ttsSummary type");
         throw new DataServiceException("Operation is not supported. The TrilliumHousehold object is already ttsSummary type");
      } else if((trilliumHousehold.isHeld() || trilliumHousehold.isTam()) && !applicationNumber.equals(trilliumHousehold.getApplicationNumber())) {
         this.logger.log(Level.SEVERE, "Argument Application Number and Application Number in TrilliumHousehold object are NOT the same: " + applicationNumber + " ," + trilliumHousehold.getApplicationNumber());
         throw new DataServiceException("Argument Application Number and Application Number in TrilliumHousehold object are NOT the same: " + applicationNumber + " ," + trilliumHousehold.getApplicationNumber());
      } else {
         this.dataServiceDao.getTtsHousehold(applicationNumber, trilliumHousehold);
         if(trilliumHousehold.isHeld() && trilliumHousehold.getStatusCode().equals("C")) {
            this.logger.log(Level.SEVERE, "Data discrepancy problem between Held and TTS data for Application Number " + trilliumHousehold.getApplicationNumber());
            throw new DataServiceException("Data discrepancy problem between Held and TTS data for Application Number " + trilliumHousehold.getApplicationNumber());
         } else {
            return trilliumHousehold;
         }
      }
   }

   public int updateAppnumberStatusCode(String applicationNumber, String statusCode) {
      boolean recUpdated = false;
      if(applicationNumber == null) {
         this.logger.log(Level.SEVERE, "Application Number is NULL");
         throw new DataServiceException("Application Number is NULL");
      } else {
         try {
            int recUpdated1 = this.dataServiceDao.updateAppnumberStatusCode(applicationNumber, statusCode);
            if(this.logger.isLoggable(Level.FINE)) {
               this.logger.log(Level.FINE, "Application number: " + applicationNumber + " updateAppnumberStatusCode() - records updated: " + recUpdated1);
            }

            return recUpdated1;
         } catch (Exception var5) {
            this.logger.log(Level.SEVERE, var5.toString(), var5);
            throw new DataServiceException("Update Application Number status_code failed for application number: " + applicationNumber, var5);
         }
      }
   }

   public TrilliumMember findApplicationContact(String applicationNumber) {
      try {
         return this.dataServiceDao.getApplicationContact(applicationNumber);
      } catch (Exception var3) {
         this.logger.log(Level.SEVERE, var3.toString(), var3);
         throw new DataServiceException("dataservice findApplicationContact failed for application number: " + applicationNumber, var3);
      }
   }

   public void insertNote(int benftyr, String applicationNumber, String note, int categoryCode, int activityCode, String createModuleId, String recordCreateUserId) {
      this.dataServiceDao.writeInsertNote(benftyr, applicationNumber, note, categoryCode, activityCode, createModuleId, recordCreateUserId);
   }

   public void householdUpdate(TrilliumHousehold trilliumHousehold, String updateModuleId, String userIdAlias) {
      this.dataServiceDao.updateHouseholdTdp003(trilliumHousehold, updateModuleId, userIdAlias);
   }

   public void setDataServiceDao(IDataServiceDao dataServiceDao) {
      this.dataServiceDao = dataServiceDao;
   }

   public TrilliumMember getAppNumberDelegateContactAddress(String applicationNumber) {
      try {
         return this.dataServiceDao.getDelegateContactAddress(applicationNumber);
      } catch (Exception var3) {
         this.logger.log(Level.SEVERE, var3.toString(), var3);
         throw new DataServiceException("dataservice getAppNumberDelegateContactAddress failed for application number: " + applicationNumber, var3);
      }
   }

   public int findApllicationAlerts(String applicationNumber, int alertsActivityCode, int alertsCategoryCode) {
      try {
         return this.dataServiceDao.getApplicationAlerts(applicationNumber, alertsActivityCode, alertsCategoryCode);
      } catch (Exception var5) {
         this.logger.log(Level.SEVERE, var5.toString(), var5);
         throw new DataServiceException("dataservice findApllicationAlerts failed for application number: " + applicationNumber, var5);
      }
   }

   public SortedMap getPendingRenewalList(int benYear) {
      try {
         return this.dataServiceDao.getApplicationRenewalPendinList("P", "R", benYear);
      } catch (Exception var3) {
         this.logger.log(Level.SEVERE, var3.toString(), var3);
         throw new DataServiceException("dataservice getPendingRenewalList failed getting renewal list: ", var3);
      }
   }

   public List getApplTerminationList(int benefitYr, String appNum, Timestamp startDate, Timestamp endDate) {
      try {
         return this.dataServiceDao.applTerminationList(benefitYr, appNum, startDate, endDate);
      } catch (Exception var6) {
         this.logger.log(Level.SEVERE, var6.toString(), var6);
         throw new DataServiceException("dataservice applTerminationList failed getting list: ", var6);
      }
   }

   public List getApplCommittedList(int benefitYr, Timestamp recCreateTimestamp) {
      try {
         return this.dataServiceDao.applCommittedList(benefitYr, recCreateTimestamp);
      } catch (Exception var4) {
         this.logger.log(Level.SEVERE, var4.toString(), var4);
         throw new DataServiceException("dataservice applTerminationList failed getting list: ", var4);
      }
   }

   public Set getAppNumberNoEndYearSet(int benefitYear, int etpar, String typeCode, String statusCode) {
      try {
         return this.dataServiceDao.appNumberNoEndYearSet(benefitYear, etpar, typeCode, statusCode);
      } catch (Exception var6) {
         this.logger.log(Level.SEVERE, var6.toString(), var6);
         throw new DataServiceException("dataservice applTerminationList failed getting list: ", var6);
      }
   }

   public void insertSoftDelete(String oldAppNum, int categoryCode, int activityCode, String note, String recCreateModuleID, String userIdAlias) {
      try {
         this.dataServiceDao.insertSoftDelete(oldAppNum, categoryCode, activityCode, note, recCreateModuleID, userIdAlias);
      } catch (Exception var8) {
         this.logger.log(Level.SEVERE, var8.toString(), var8);
         throw new DataServiceException("dataservice insertSoftDelete: ", var8);
      }
   }

   public void insertRenewalApplicationNumber(String oldAppNum, String modulID, String userIdAlias, int alertsActivityCode, int alertsCategoryCode, String renewAlerts) {
      try {
         this.dataServiceDao.insertRenewAppNumber(oldAppNum, modulID, userIdAlias, alertsActivityCode, alertsCategoryCode, renewAlerts);
      } catch (Exception var8) {
         this.logger.log(Level.SEVERE, var8.toString(), var8);
         throw new DataServiceException("dataservice insertRenewAppNumber: ", var8);
      }
   }

   public List getPendingRenewalListTimestamp(int benefitYr, String startDate, String endDate, String appNumber) {
      new ArrayList();

      try {
         List applist = this.dataServiceDao.getPendingRenewalListTimestamp(benefitYr, startDate, endDate, appNumber);
         return applist;
      } catch (Exception var7) {
         throw new DataServiceException("dataservice getPendingRenewalListTimestamp: ", var7);
      }
   }

   public List getTDP004ApplicationNumbers(int benefitYr, String startDate, String endDate, String appNumber) {
      return this.dataServiceDao.getTDP004ApplicationNumbers(benefitYr, startDate, endDate, appNumber);
   }

   public void insertExceptionReport(String batchJobID, int exceptionNum, String description, String appNumber, String odbEligNum, String lastUpdateModuleId, String lastUpdateUserId) {
      this.dataServiceDao.insertExceptionReport(batchJobID, exceptionNum, description, appNumber, odbEligNum, lastUpdateModuleId, lastUpdateUserId);
   }

   public int insertNoteActivityDate(int benftyr, String applicationNumber, String note, int categoryCode, int activityCode, String createModuleId, String recordCreateUserId, String activityDate) {
      boolean rtnCode = false;
      int rtnCode1 = this.dataServiceDao.WriteInsertNoteActivityDate(benftyr, applicationNumber, note, categoryCode, activityCode, createModuleId, recordCreateUserId, activityDate);
      return rtnCode1;
   }
}
