package persistence.ds.impl;


public class AppNumberComposite implements Comparable {

   private String applicationNumber = null;
   private String recCreateTimeStamp = null;


   public String getApplicationNumber() {
      return this.applicationNumber;
   }

   public void setApplicationNumber(String applicationNumber) {
      this.applicationNumber = applicationNumber;
   }

   public String getRecCreateTimeStamp() {
      return this.recCreateTimeStamp;
   }

   public void setRecCreateTimeStamp(String recCreateTimeStamp) {
      this.recCreateTimeStamp = recCreateTimeStamp;
   }

   public int compareTo(Object o) {
      if(!(o instanceof AppNumberComposite)) {
         throw new ClassCastException("AppNumberComposite - Cannot compare object  with object " + o.toString());
      } else {
         AppNumberComposite target = (AppNumberComposite)o;
         return (this.recCreateTimeStamp + this.applicationNumber).compareTo(target.getRecCreateTimeStamp() + target.getApplicationNumber());
      }
   }

   public String toString() {
      StringBuffer result = new StringBuffer();
      result.append(this.getRecCreateTimeStamp().trim()).append(";").append(this.getApplicationNumber().trim());
      return result.toString();
   }

   public boolean equals(Object o) {
      if(!(o instanceof AppNumberComposite)) {
         throw new ClassCastException("AppNumberComposite - Cannot compare object  with object " + o.toString());
      } else {
         AppNumberComposite target = (AppNumberComposite)o;
         return (this.recCreateTimeStamp + this.applicationNumber).equals(target.getRecCreateTimeStamp() + target.getApplicationNumber());
      }
   }
}
