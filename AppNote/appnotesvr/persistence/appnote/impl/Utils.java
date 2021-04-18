package persistence.appnote.impl;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {

   private static final int NANOS_LENGTH = 9;
   private static final Logger logger = Logger.getLogger(Utils.class.getName());


   public static final String timestampToStringFormatted(Timestamp ts, String pattern, int appendNanos) {
      if(ts == null) {
         return " ";
      } else if(pattern == null) {
         throw new RuntimeException("Utils.timestampToStringFormatted(): pattern is null");
      } else {
         if(appendNanos > 9) {
            appendNanos = 9;
         }

         return (new SimpleDateFormat(pattern)).format(ts) + "." + getNanosAsZeroAppendedString(ts.getNanos(), appendNanos);
      }
   }

   private static final String getNanosAsZeroAppendedString(int nanos, int appendNanos) {
      String strNanos = Integer.toString(nanos);
      int lengthDiff = 9 - strNanos.length();

      StringBuilder sb;
      for(sb = new StringBuilder(); lengthDiff > 0; --lengthDiff) {
         sb.append("0");
      }

      return (sb.toString() + strNanos).substring(0, appendNanos);
   }

   public static final String dateToStringFormatted(Date dt, String pattern) {
      if(dt == null) {
         return " ";
      } else if(pattern == null) {
         throw new RuntimeException("Utils.dateToStringFormatted(): pattern is null");
      } else {
         return (new SimpleDateFormat(pattern)).format(dt);
      }
   }

   public static final Object setNullStringsToDefault(Object obj, String defaultValue) {
      Field[] fields = obj.getClass().getDeclaredFields();

      for(int index = 0; index < fields.length; ++index) {
         if(fields[index].getType().isInstance(defaultValue)) {
            try {
               if((String)fields[index].get(obj) == null) {
                  fields[index].set(obj, defaultValue);
               }
            } catch (Exception var5) {
               logger.log(Level.SEVERE, "Exception in Utils.setNullStringsToDefault(): ", var5);
               throw new RuntimeException(var5);
            }
         }
      }

      return obj;
   }

}
