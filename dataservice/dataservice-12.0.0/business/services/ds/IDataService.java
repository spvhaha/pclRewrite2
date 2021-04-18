package business.services.ds;

import business.objects.trillium.TrilliumHousehold;
import business.objects.trillium.TrilliumMember;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import persistence.ds.IDataServiceDao;

public interface IDataService {

   void setDataServiceDao(IDataServiceDao var1);

   int updateAppnumberForAppnum(TrilliumHousehold var1);

   int updateAppnumberAddress(TrilliumHousehold var1);

   void updateHousehold(TrilliumHousehold var1);

   TrilliumHousehold getTtsHousehold(String var1, TrilliumHousehold var2);

   int updateAppnumberStatusCode(String var1, String var2);

   TrilliumMember findApplicationContact(String var1);

   void insertNote(int var1, String var2, String var3, int var4, int var5, String var6, String var7);

   void householdUpdate(TrilliumHousehold var1, String var2, String var3);

   TrilliumMember getAppNumberDelegateContactAddress(String var1);

   int findApllicationAlerts(String var1, int var2, int var3);

   SortedMap getPendingRenewalList(int var1);

   List getPendingRenewalListTimestamp(int var1, String var2, String var3, String var4);

   List getApplTerminationList(int var1, String var2, Timestamp var3, Timestamp var4);

   List getApplCommittedList(int var1, Timestamp var2);

   Set getAppNumberNoEndYearSet(int var1, int var2, String var3, String var4);

   void insertSoftDelete(String var1, int var2, int var3, String var4, String var5, String var6);

   void insertRenewalApplicationNumber(String var1, String var2, String var3, int var4, int var5, String var6);

   List getTDP004ApplicationNumbers(int var1, String var2, String var3, String var4);

   void insertExceptionReport(String var1, int var2, String var3, String var4, String var5, String var6, String var7);

   int insertNoteActivityDate(int var1, String var2, String var3, int var4, int var5, String var6, String var7, String var8);
}
