package persistence.ds.impl;

import java.sql.Timestamp;

public class AppCommittedComposite {

   private String applicationNumber = null;
   private Timestamp recCreateTimeStamp = null;


   public String getApplicationNumber() {
      return this.applicationNumber;
   }

   public void setApplicationNumber(String applicationNumber) {
      this.applicationNumber = applicationNumber;
   }

   public Timestamp getRecCreateTimeStamp() {
      return this.recCreateTimeStamp;
   }

   public void setRecCreateTimeStamp(Timestamp recCreateTimeStamp) {
      this.recCreateTimeStamp = recCreateTimeStamp;
   }
}
