package exception.ds;


public class DataserviceUniqueConstraintException extends RuntimeException {

   public DataserviceUniqueConstraintException() {}

   public DataserviceUniqueConstraintException(String msg) {
      super(msg);
   }

   public DataserviceUniqueConstraintException(Throwable t) {
      super(t);
   }

   public DataserviceUniqueConstraintException(String msg, Throwable t) {
      super(msg, t);
   }
}
