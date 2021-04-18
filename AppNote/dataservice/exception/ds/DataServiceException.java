package exception.ds;


public class DataServiceException extends RuntimeException {

   public DataServiceException() {}

   public DataServiceException(String msg) {
      super(msg);
   }

   public DataServiceException(Exception e) {
      super(e);
   }

   public DataServiceException(String msg, Exception e) {
      super(msg, e);
   }
}
