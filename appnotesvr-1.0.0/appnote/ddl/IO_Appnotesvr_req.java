package appnote.ddl;
import java.util.Vector;
import java.util.Hashtable;
import java.io.CharConversionException;
import java.util.HashMap;
import java.util.MissingResourceException;
import com.tandem.ext.guardian.*;
import com.tandem.ext.util.*;
/**
IO_Appnotesvr_req class was generated for DDL Rec/Def: appnotesvr_req on: 1/14/16 at: 14:3:39.
This is an INPUT/OUTPUT class which can be used to read and write data.*/

  public class IO_Appnotesvr_req implements com.tandem.ext.guardian.GuardianInput, 
            com.tandem.ext.guardian.GuardianOutput {
    protected int _numConversionErrors;
    protected boolean _allowErrors = false;
    protected HashMap _errorDetail;

    protected int _bufferLen = 437;
    public String applicationnumber;
    public String benftyr;
    public String categorycode;
    public String activitycode;
    public String activitydate;
    public String msgtext;
    public String createmoduleid;

/**
IO_Appnotesvr_req Constructor
*/
   public IO_Appnotesvr_req() {
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
Used to set a value for applicationnumber.
@param value  the value to be set.
*/
    public void setApplicationnumber(String value) {
      applicationnumber = value;
    }
/**
Used to set a value for benftyr.
@param value  the value to be set.
*/
    public void setBenftyr(String value) {
      benftyr = value;
    }
/**
Used to set a value for categorycode.
@param value  the value to be set.
*/
    public void setCategorycode(String value) {
      categorycode = value;
    }
/**
Used to set a value for activitycode.
@param value  the value to be set.
*/
    public void setActivitycode(String value) {
      activitycode = value;
    }
/**
Used to set a value for activitydate.
@param value  the value to be set.
*/
    public void setActivitydate(String value) {
      activitydate = value;
    }
/**
Used to set a value for msgtext.
@param value  the value to be set.
*/
    public void setMsgtext(String value) {
      msgtext = value;
    }
/**
Used to set a value for createmoduleid.
@param value  the value to be set.
*/
    public void setCreatemoduleid(String value) {
      createmoduleid = value;
    }

/**
Used to clear any existing values for applicationnumber.
*/
    public void clearApplicationnumber() {
        applicationnumber = null;
    }
/**
Used to clear any existing values for benftyr.
*/
    public void clearBenftyr() {
        benftyr = null;
    }
/**
Used to clear any existing values for categorycode.
*/
    public void clearCategorycode() {
        categorycode = null;
    }
/**
Used to clear any existing values for activitycode.
*/
    public void clearActivitycode() {
        activitycode = null;
    }
/**
Used to clear any existing values for activitydate.
*/
    public void clearActivitydate() {
        activitydate = null;
    }
/**
Used to clear any existing values for msgtext.
*/
    public void clearMsgtext() {
        msgtext = null;
    }
/**
Used to clear any existing values for createmoduleid.
*/
    public void clearCreatemoduleid() {
        createmoduleid = null;
    }
/** Used to clear the values of all instance variables in this class.
*/
  public void clear_All() {
      applicationnumber = null;
      benftyr = null;
      categorycode = null;
      activitycode = null;
      activitydate = null;
      msgtext = null;
      createmoduleid = null;
  }

/**
Used to obtain the value of applicationnumber.
@return the value for applicationnumber.
*/
    public String getApplicationnumber() {
      return(applicationnumber);
    }
/**
Used to obtain the value of benftyr.
@return the value for benftyr.
*/
    public String getBenftyr() {
      return(benftyr);
    }
/**
Used to obtain the value of categorycode.
@return the value for categorycode.
*/
    public String getCategorycode() {
      return(categorycode);
    }
/**
Used to obtain the value of activitycode.
@return the value for activitycode.
*/
    public String getActivitycode() {
      return(activitycode);
    }
/**
Used to obtain the value of activitydate.
@return the value for activitydate.
*/
    public String getActivitydate() {
      return(activitydate);
    }
/**
Used to obtain the value of msgtext.
@return the value for msgtext.
*/
    public String getMsgtext() {
      return(msgtext);
    }
/**
Used to obtain the value of createmoduleid.
@return the value for createmoduleid.
*/
    public String getCreatemoduleid() {
      return(createmoduleid);
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

      _offset = 11;
      if(_offset + 4 > _count) return;
        try {
           benftyr = DataConversion.NumStrToJavaStr(_buffer,_offset,4, 0, 16);
        }catch (DataConversionException dce) {
           _numConversionErrors++;
           if(_allowErrors) {
              _errorDetail.put("benftyr",dce);
           } else { 
              throw dce;
           }
        }

      _offset = 15;
      if(_offset + 3 > _count) return;
        try {
           categorycode = DataConversion.NumStrToJavaStr(_buffer,_offset,3, 0, 16);
        }catch (DataConversionException dce) {
           _numConversionErrors++;
           if(_allowErrors) {
              _errorDetail.put("categorycode",dce);
           } else { 
              throw dce;
           }
        }

      _offset = 18;
      if(_offset + 3 > _count) return;
        try {
           activitycode = DataConversion.NumStrToJavaStr(_buffer,_offset,3, 0, 16);
        }catch (DataConversionException dce) {
           _numConversionErrors++;
           if(_allowErrors) {
              _errorDetail.put("activitycode",dce);
           } else { 
              throw dce;
           }
        }

      _offset = 21;
      if(_offset >= _count) return;
      _fieldSize = Math.min(10,_count - _offset);
        try {
           activitydate = DataConversion.CobolStrToJavaStr(_buffer,_offset,_fieldSize);
        }catch (DataConversionException dce) {
           _numConversionErrors++;
           if(_allowErrors) {
              _errorDetail.put("activitydate",dce);
           } else { 
              throw dce;
           }
        }

      _offset = 31;
      if(_offset >= _count) return;
      _fieldSize = Math.min(400,_count - _offset);
        try {
           msgtext = DataConversion.CobolStrToJavaStr(_buffer,_offset,_fieldSize);
        }catch (DataConversionException dce) {
           _numConversionErrors++;
           if(_allowErrors) {
              _errorDetail.put("msgtext",dce);
           } else { 
              throw dce;
           }
        }

      _offset = 431;
      if(_offset >= _count) return;
      _fieldSize = Math.min(6,_count - _offset);
        try {
           createmoduleid = DataConversion.CobolStrToJavaStr(_buffer,_offset,_fieldSize);
        }catch (DataConversionException dce) {
           _numConversionErrors++;
           if(_allowErrors) {
              _errorDetail.put("createmoduleid",dce);
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
        if(applicationnumber != null) {
        DataConversion.JavaStrToCobolStr(_buffer,applicationnumber,_offset,11);
        }

      _offset = 11;
        if(benftyr != null) 
          DataConversion.JavaStrToNumStr(_buffer,benftyr, _offset, 4, 0, 16);

      _offset = 15;
        if(categorycode != null) 
          DataConversion.JavaStrToNumStr(_buffer,categorycode, _offset, 3, 0, 16);

      _offset = 18;
        if(activitycode != null) 
          DataConversion.JavaStrToNumStr(_buffer,activitycode, _offset, 3, 0, 16);

      _offset = 21;
        if(activitydate != null) {
        DataConversion.JavaStrToCobolStr(_buffer,activitydate,_offset,10);
        }

      _offset = 31;
        if(msgtext != null) {
        DataConversion.JavaStrToCobolStr(_buffer,msgtext,_offset,400);
        }

      _offset = 431;
        if(createmoduleid != null) {
        DataConversion.JavaStrToCobolStr(_buffer,createmoduleid,_offset,6);
        }
      return _buffer;
    }
  }
