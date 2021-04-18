package appnote.ddl;

import com.tandem.ext.guardian.GuardianInput;
import com.tandem.ext.guardian.GuardianOutput;
import com.tandem.ext.util.DataConversion;
import com.tandem.ext.util.DataConversionException;
import com.tandem.ext.util.DataConversionException.ConvError;
import java.util.HashMap;

public class IO_Appnotesrv_rep implements GuardianInput, GuardianOutput {

   protected int _numConversionErrors;
   protected boolean _allowErrors = false;
   protected HashMap _errorDetail = new HashMap();
   protected int _bufferLen = 419;
   public String errorid;
   public String errorcode;
   public String errordesc;
   public String applicationnumber;


   public int getLength() {
      return this._bufferLen;
   }

   public void allowErrors(boolean value) {
      this._allowErrors = value;
   }

   public int getNumConversionFailures() {
      return this._numConversionErrors;
   }

   public HashMap getConversionFailureDetails() {
      return this._errorDetail;
   }

   public void setErrorid(String value) {
      this.errorid = value;
   }

   public void setErrorcode(String value) {
      this.errorcode = value;
   }

   public void setErrordesc(String value) {
      this.errordesc = value;
   }

   public void setApplicationnumber(String value) {
      this.applicationnumber = value;
   }

   public void clearErrorid() {
      this.errorid = null;
   }

   public void clearErrorcode() {
      this.errorcode = null;
   }

   public void clearErrordesc() {
      this.errordesc = null;
   }

   public void clearApplicationnumber() {
      this.applicationnumber = null;
   }

   public void clear_All() {
      this.errorid = null;
      this.errorcode = null;
      this.errordesc = null;
      this.applicationnumber = null;
   }

   public String getErrorid() {
      return this.errorid;
   }

   public String getErrorcode() {
      return this.errorcode;
   }

   public String getErrordesc() {
      return this.errordesc;
   }

   public String getApplicationnumber() {
      return this.applicationnumber;
   }

   public void unmarshal(byte[] _buffer, int _count) throws DataConversionException {
      this._errorDetail.clear();
      this._numConversionErrors = 0;
      if(!this._allowErrors && _count < this._bufferLen) {
         ++this._numConversionErrors;
         throw new DataConversionException(ConvError.PARAM, "The value supplied for the _count variable is less than expected (see getLength())");
      } else {
         if(this._allowErrors && _count < this._bufferLen) {
            DataConversionException dce = new DataConversionException(ConvError.PARAM, "The value supplied for the _count variable is less than expected (see  getLength())");
            ++this._numConversionErrors;
            this._errorDetail.put(_buffer, dce);
         }

         byte _offset = 0;
         if(_offset < _count) {
            int _fieldSize = Math.min(5, _count - _offset);

            try {
               this.errorid = DataConversion.CobolStrToJavaStr(_buffer, _offset, _fieldSize);
            } catch (DataConversionException var9) {
               ++this._numConversionErrors;
               if(!this._allowErrors) {
                  throw var9;
               }

               this._errorDetail.put("errorid", var9);
            }

            _offset = 5;
            if(_offset < _count) {
               _fieldSize = Math.min(3, _count - _offset);

               try {
                  this.errorcode = DataConversion.CobolStrToJavaStr(_buffer, _offset, _fieldSize);
               } catch (DataConversionException var8) {
                  ++this._numConversionErrors;
                  if(!this._allowErrors) {
                     throw var8;
                  }

                  this._errorDetail.put("errorcode", var8);
               }

               _offset = 8;
               if(_offset < _count) {
                  _fieldSize = Math.min(400, _count - _offset);

                  try {
                     this.errordesc = DataConversion.CobolStrToJavaStr(_buffer, _offset, _fieldSize);
                  } catch (DataConversionException var7) {
                     ++this._numConversionErrors;
                     if(!this._allowErrors) {
                        throw var7;
                     }

                     this._errorDetail.put("errordesc", var7);
                  }

                  short var10 = 408;
                  if(var10 < _count) {
                     _fieldSize = Math.min(11, _count - var10);

                     try {
                        this.applicationnumber = DataConversion.CobolStrToJavaStr(_buffer, var10, _fieldSize);
                     } catch (DataConversionException var6) {
                        ++this._numConversionErrors;
                        if(!this._allowErrors) {
                           throw var6;
                        }

                        this._errorDetail.put("applicationnumber", var6);
                     }

                  }
               }
            }
         }
      }
   }

   public byte[] marshal() throws DataConversionException, NumberFormatException {
      byte[] _buffer = new byte[this._bufferLen];
      this.marshal(_buffer);
      return _buffer;
   }

   public byte[] marshal(byte[] _buffer) throws DataConversionException {
      if(_buffer.length < this._bufferLen) {
         throw new DataConversionException(ConvError.RANGE, "Buffer length is less than minimum required buffer length");
      } else {
         byte _offset = 0;
         if(this.errorid != null) {
            DataConversion.JavaStrToCobolStr(_buffer, this.errorid, _offset, 5);
         }

         _offset = 5;
         if(this.errorcode != null) {
            DataConversion.JavaStrToCobolStr(_buffer, this.errorcode, _offset, 3);
         }

         _offset = 8;
         if(this.errordesc != null) {
            DataConversion.JavaStrToCobolStr(_buffer, this.errordesc, _offset, 400);
         }

         short _offset1 = 408;
         if(this.applicationnumber != null) {
            DataConversion.JavaStrToCobolStr(_buffer, this.applicationnumber, _offset1, 11);
         }

         return _buffer;
      }
   }
}
