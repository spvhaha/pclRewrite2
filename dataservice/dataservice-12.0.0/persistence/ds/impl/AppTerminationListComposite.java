package persistence.ds.impl;


public class AppTerminationListComposite {

   private String appl_number = null;
   private int activity_code;
   private String rec_create_timestamp = null;
   private String use_delegate_ind = null;


   public String getAppl_number() {
      return this.appl_number;
   }

   public void setAppl_number(String appl_number) {
      this.appl_number = appl_number;
   }

   public int getActivity_code() {
      return this.activity_code;
   }

   public void setActivity_code(int activity_code) {
      this.activity_code = activity_code;
   }

   public String getRec_create_timestamp() {
      return this.rec_create_timestamp;
   }

   public void setRec_create_timestamp(String rec_create_timestamp) {
      this.rec_create_timestamp = rec_create_timestamp;
   }

   public String getUse_delegate_ind() {
      return this.use_delegate_ind;
   }

   public void setUse_delegate_ind(String use_delegate_ind) {
      this.use_delegate_ind = use_delegate_ind;
   }
}
