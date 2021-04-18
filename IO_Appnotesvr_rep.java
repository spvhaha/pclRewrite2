package appnote.ddl;
import java.util.Vector;
import java.util.Hashtable;
import java.io.CharConversionException;
import java.util.HashMap;
import java.util.MissingResourceException;
import com.tandem.ext.guardian.*;
import com.tandem.ext.util.*;
/**
IO_Appnotesvr_rep class was generated for DDL Rec/Def: appnotesvr_rep on: 1/14/16 at: 14:3:39.
This is an INPUT/OUTPUT class which can be used to read and write data.*/

  public class IO_Appnotesvr_rep implements com.tandem.ext.guardian.GuardianInput, 
            com.tandem.ext.guardian.GuardianOutput {
    protected int _numConversionErrors;
    protected boolean _allowErrors = false;
    protected HashMap _errorDetail;

    protected int _bufferLen = 419;
    public String errorid;
    public String errorcode;
    public String errordesc;
    public String applicationnumber;

/**
IO_Appnotesvr_rep Constructor
*/
   public IO_Appnotesvr_rep() {
        _errorDetail = new HashMap();
   }

/**
Returns the expected length of the byte array used by this class.
@return int expected length of byte array.
*/
   public int getLength() {
      return _bufferLen;
   }
/**
  Indicates whether DataConversion Exceptions are thrown during execution
  of the unmarshal method. Specifying false indicates that the first data
  conversion error encountered during execution of the unmarshal method, 
  causes a DataConversionException to be thrown.  All subsequent data in 
  the byte array supplied to the unmarshal method is NOT converted

  Specifying true indicates that DataConversionExceptions are ignored 
  during execution of the unmarshal method.  umarshal will attempt to 
  convert all data in the byte array.  To determine if errors have 
  occurred during execution of unmarshal, the user can call 
  getNumConversionFailures().  This method returns the number of fields 
  that failed to convert.  If the call to getNumConversionFailures() 
  returns a number greater than zero, a call to 
  getConversionFailureDetails() returns a HashMap whose keys are the fields
  that failed to convert and whose value is the associated 
  DataConversionException.
  @param boolean true to indicate that conversion errors are to be 
  allowed; false otherwise.
  @see #getNumConversionFailures
  @see #getConversionFailureDetails
*/
    public void allowErrors(boolean value) {
       _allowErrors = value;
    }
/**
   Returns the number of fields that failed to convert during execution
   of the unmarshal method.
   @return the number of fields that failed to convert during the 
   execution of unmarshal.  A returned value of zero indicates that
   all fields converted.
   @see #allowErrors
   @see #getConversionFailureDetails
*/
    public int getNumConversionFailures() {
       return _numConversionErrors;
    }
/**
    Returns a HashMap with information about fields that failed to 
    convert during execution of the unmarshal method.  Keys are 
    the fields that failed to convert and values are the associated
    DataConversionExceptions.

    @return HashMap containing details of data conversion failures.
    If no failures occurred the HashMap.isEmpty() method returns true.
    @see #getNumConversionFailures
    @see #allowErrors
*/
     public HashMap getConversionFailureDetails() {
        return _errorDetail;
     }

/**
Used to set a value for errorid.
@param value  the value to be set.
*/
    public void setErrorid(String value) {
      errorid = value;
    }
/**
Used to set a value for errorcode.
@param value  the value to be set.
*/
    public void setErrorcode(String value) {
      errorcode = value;
    }
/**
Used to set a value for errordesc.
@param value  the value to be set.
*/
    public void setErrordesc(String value) {
      errordesc = value;
    }
/**
Used to set a value for applicationnumber.
@param value  the value to be set.
*/
    public void setApplicationnumber(String value) {
      applicationnumber = value;
    }

/**
Used to clear any existing values for errorid.
*/
    public void clearErrorid() {
        errorid = null;
    }
/**
Used to clear any existing values for errorcode.
*/
    public void clearErrorcode() {
        errorcode = null;
    }
/**
Used to clear any existing values for errordesc.
*/
    public void clearErrordesc() {
        errordesc = null;
    }
/**
Used to clear any existing values for applicationnumber.
*/
    public void clearApplicationnumber() {
        applicationnumber = null;
    }
/** Used to clear the values of all instance variables in this class.
*/
  public void clear_All() {
      errorid = null;
      errorcode = null;
      errordesc = null;
      applicationnumber = null;
  }

/**
Used to obtain the value of errorid.
@return the value for errorid.
*/
    public String getErrorid() {
      return(errorid);
    }
/**
Used to obtain the value of errorcode.
@return the value for errorcode.
*/
    public String getErrorcode() {
      return(errorcode);
    }
/**
Used to obtain the value of errordesc.
@return the value for errordesc.
*/
    public String getErrordesc() {
      return(errordesc);
    }
/**
Used to obtain the value of applicationnumber.
@return the value for applicationnumber.
*/
    public String getApplicationnumber() {
      return(applicationnumber);
    }
/**
Moves the data from the supplied byte array to the instance variables.
@param _buffer a byte array which contains the data to be moved. 
@param _count the number of bytes to be converted.
@exception com.tandem.ext.util.DataConversionException if 
a field fails to convert correctly and allowErrors has been set to false; or
allowErrors is set to true and _count < getLength().
@see #allowErrors
**/
    public void unmarshal(byte [] _buffer, int _count) 
      throws com.tandem.ext.util.DataConversionException {

      int _offset;
      int _fieldSize;
      _errorDetail.clear();
      _numConversionErrors = 0;
      if(!_allowErrors && _count < _bufferLen) {
          _numConversionErrors++;
          throw new DataConversionException(DataConversionException.ConvError.PARAM,
            "The value supplied for the _count variable is less than expected (see getLength())");
      }
      if(_allowErrors && _count < _bufferLen) {
          DataConversionException _bfe = new DataConversionException(DataConversionException.ConvError.PARAM,
          "The value supplied for the _count variable is less than expected (see  getLength())");
          _numConversionErrors++;
          _errorDetail.put(_buffer,_bfe);
      }


      _offset = 0;
      if(_offset >= _count) return;
      _fieldSize = Math.min(5,_count - _offset);
        try {
           errorid = DataConversion.CobolStrToJavaStr(_buffer,_offset,_fieldSize);
        }catch (DataConversionException dce) {
           _numConversionErrors++;
           if(_allowErrors) {
              _errorDetail.put("errorid",dce);
           } else { 
              throw dce;
           }
        }

      _offset = 5;
      if(_offset >= _count) return;
      _fieldSize = Math.min(3,_count - _offset);
        try {
           errorcode = DataConversion.CobolStrToJavaStr(_buffer,_offset,_fieldSize);
        }catch (DataConversionException dce) {
           _numConversionErrors++;
           if(_allowErrors) {
              _errorDetail.put("errorcode",dce);
           } else { 
              throw dce;
           }
        }

      _offset = 8;
      if(_offset >= _count) return;
      _fieldSize = Math.min(400,_count - _offset);
        try {
           errordesc = DataConversion.CobolStrToJavaStr(_buffer,_offset,_fieldSize);
        }catch (DataConversionException dce) {
           _numConversionErrors++;
           if(_allowErrors) {
              _errorDetail.put("errordesc",dce);
           } else { 
              throw dce;
           }
        }

      _offset = 408;
      if(_offset >= _count) return;
      _fieldSize = Math.min(11,_count - _offset);
        try {
           applicationnumber = DataConversion.CobolStrToJavaStr(_buffer,_offset,_fieldSize);
        }catch (DataConversionException dce) {
           _numConversionErrors++;
           if(_allowErrors) {
              _errorDetail.put("applicationnumber",dce);
           } else { 
              throw dce;
           }
        }
    }
/**
Moves and converts the data from the instance variables to a byte array
@return a byte array containing the converted data.
@exception com.tandem.ext.util.DataConversionException if an instance
variable fails to convert successfully.
**/

    public byte[] marshal()
        throws com.tandem.ext.util.DataConversionException, NumberFormatException {

        byte [] _buffer = new byte[_bufferLen];
        marshal(_buffer);
        return _buffer;
    }

/**
Moves and converts the data from the instance variables to a byte array
@param _buffer the byte array to which the data is to be moved.
@return a byte array containing the converted data.
@exception com.tandem.guardian.DataConversionException if the length of the
supplied byte array is not greater than or equal to the minimum required
length or if an instance variable fails to convert successfully.
**/

    public byte[] marshal(byte [] _buffer)
        throws com.tandem.ext.util.DataConversionException {
      if(_buffer.length < _bufferLen) 
         throw new DataConversionException(DataConversionException.ConvError.RANGE,
          "Buffer length is less than minimum required buffer length");

      int _offset;


      _offset = 0;
        if(errorid != null) {
        DataConversion.JavaStrToCobolStr(_buffer,errorid,_offset,5);
        }

      _offset = 5;
        if(errorcode != null) {
        DataConversion.JavaStrToCobolStr(_buffer,errorcode,_offset,3);
        }

      _offset = 8;
        if(errordesc != null) {
        DataConversion.JavaStrToCobolStr(_buffer,errordesc,_offset,400);
        }

      _offset = 408;
        if(applicationnumber != null) {
        DataConversion.JavaStrToCobolStr(_buffer,applicationnumber,_offset,11);
        }
      return _buffer;
    }
  }
