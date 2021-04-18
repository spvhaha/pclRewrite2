package persistence.ds;

import business.objects.trillium.TrilliumHousehold;
import business.objects.trillium.TrilliumMember;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;

public interface IDataServiceDao {

   int updateAppnumberForAppnum(TrilliumHousehold var1);

   int updateAppnumberAddress(TrilliumHousehold var1);

   TrilliumHousehold getTtsHousehold(String var1, TrilliumHousehold var2);

   int updateAppnumberStatusCode(String var1, String var2);

   TrilliumMember getApplicationContact(String var1);

   void writeInsertNote(int var1, String var2, String var3, int var4, int var5, String var6, String var7);

   void updateHouseholdTdp003(TrilliumHousehold var1, String var2, String var3);

   TrilliumMember getDelegateContactAddress(String var1);

   int getApplicationAlerts(String var1, int var2, int var3);

   SortedMap getApplicationRenewalPendinList(String var1, String var2, int var3);

   List applTerminationList(int var1, String var2, Timestamp var3, Timestamp var4);

   List applCommittedList(int var1, Timestamp var2);

   Set appNumberNoEndYearSet(int var1, int var2, String var3, String var4);

   void insertSoftDelete(String var1, int var2, int var3, String var4, String var5, String var6);

   void insertRenewAppNumber(String var1, String var2, String var3, int var4, int var5, String var6);

   List getPendingRenewalListTimestamp(int var1, String var2, String var3, String var4);

   List getTDP004ApplicationNumbers(int var1, String var2, String var3, String var4);

   void insertExceptionReport(String var1, int var2, String var3, String var4, String var5, String var6, String var7);

   int WriteInsertNoteActivityDate(int var1, String var2, String var3, int var4, int var5, String var6, String var7, String var8);
}
