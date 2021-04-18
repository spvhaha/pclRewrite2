package appnote.ddl;

import com.tandem.ext.guardian.GuardianInput;
import com.tandem.ext.guardian.GuardianOutput;
import com.tandem.ext.util.DataConversion;
import com.tandem.ext.util.DataConversionException;
import com.tandem.ext.util.DataConversionException.ConvError;
import java.util.HashMap;

public class IO_Appnotesrv_req implements GuardianInput, GuardianOutput {

   protected int _numConversionErrors;
   protected boolean _allowErrors = false;
   protected HashMap _errorDetail = new HashMap();
   protected int _bufferLen = 437;
   public String applicationnumber;
   public String benftyr;
   public String categorycode;
   public String activitycode;
   public String activitydate;
   public String msgtext;
   public String createmoduleid;


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

   public void setApplicationnumber(String value) {
      this.applicationnumber = value;
   }

   public void setBenftyr(String value) {
      this.benftyr = value;
   }

   public void setCategorycode(String value) {
      this.categorycode = value;
   }

   public void setActivitycode(String value) {
      this.activitycode = value;
   }

   public void setActivitydate(String value) {
      this.activitydate = value;
   }

   public void setMsgtext(String value) {
      this.msgtext = value;
   }

   public void setCreatemoduleid(String value) {
      this.createmoduleid = value;
   }

   public void clearApplicationnumber() {
      this.applicationnumber = null;
   }

   public void clearBenftyr() {
      this.benftyr = null;
   }

   public void clearCategorycode() {
      this.categorycode = null;
   }

   public void clearActivitycode() {
      this.activitycode = null;
   }

   public void clearActivitydate() {
      this.activitydate = null;
   }

   public void clearMsgtext() {
      this.msgtext = null;
   }

   public void clearCreatemoduleid() {
      this.createmoduleid = null;
   }

   public void clear_All() {
      this.applicationnumber = null;
      this.benftyr = null;
      this.categorycode = null;
      this.activitycode = null;
      this.activitydate = null;
      this.msgtext = null;
      this.createmoduleid = null;
   }

   public String getApplicationnumber() {
      return this.applicationnumber;
   }

   public String getBenftyr() {
      return this.benftyr;
   }

   public String getCategorycode() {
      return this.categorycode;
   }

   public String getActivitycode() {
      return this.activitycode;
   }

   public String getActivitydate() {
      return this.activitydate;
   }

   public String getMsgtext() {
      return this.msgtext;
   }

   public String getCreatemoduleid() {
      return this.createmoduleid;
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
            int _fieldSize = Math.min(11, _count - _offset);

            try {
               this.applicationnumber = DataConversion.CobolStrToJavaStr(_buffer, _offset, _fieldSize);
            } catch (DataConversionException var12) {
               ++this._numConversionErrors;
               if(!this._allowErrors) {
                  throw var12;
               }

               this._errorDetail.put("applicationnumber", var12);
            }

            _offset = 11;
            if(_offset + 4 <= _count) {
               try {
                  this.benftyr = DataConversion.NumStrToJavaStr(_buffer, _offset, 4, 0, 16);
               } catch (DataConversionException var11) {
                  ++this._numConversionErrors;
                  if(!this._allowErrors) {
                     throw var11;
                  }

                  this._errorDetail.put("benftyr", var11);
               }

               _offset = 15;
               if(_offset + 3 <= _count) {
                  try {
                     this.categorycode = DataConversion.NumStrToJavaStr(_buffer, _offset, 3, 0, 16);
                  } catch (DataConversionException var10) {
                     ++this._numConversionErrors;
                     if(!this._allowErrors) {
                        throw var10;
                     }

                     this._errorDetail.put("categorycode", var10);
                  }

                  _offset = 18;
                  if(_offset + 3 <= _count) {
                     try {
                        this.activitycode = DataConversion.NumStrToJavaStr(_buffer, _offset, 3, 0, 16);
                     } catch (DataConversionException var9) {
                        ++this._numConversionErrors;
                        if(!this._allowErrors) {
                           throw var9;
                        }

                        this._errorDetail.put("activitycode", var9);
                     }

                     _offset = 21;
                     if(_offset < _count) {
                        _fieldSize = Math.min(10, _count - _offset);

                        try {
                           this.activitydate = DataConversion.CobolStrToJavaStr(_buffer, _offset, _fieldSize);
                        } catch (DataConversionException var8) {
                           ++this._numConversionErrors;
                           if(!this._allowErrors) {
                              throw var8;
                           }

                           this._errorDetail.put("activitydate", var8);
                        }

                        _offset = 31;
                        if(_offset < _count) {
                           _fieldSize = Math.min(400, _count - _offset);

                           try {
                              this.msgtext = DataConversion.CobolStrToJavaStr(_buffer, _offset, _fieldSize);
                           } catch (DataConversionException var7) {
                              ++this._numConversionErrors;
                              if(!this._allowErrors) {
                                 throw var7;
                              }

                              this._errorDetail.put("msgtext", var7);
                           }

                           short var13 = 431;
                           if(var13 < _count) {
                              _fieldSize = Math.min(6, _count - var13);

                              try {
                                 this.createmoduleid = DataConversion.CobolStrToJavaStr(_buffer, var13, _fieldSize);
                              } catch (DataConversionException var6) {
                                 ++this._numConversionErrors;
                                 if(!this._allowErrors) {
                                    throw var6;
                                 }

                                 this._errorDetail.put("createmoduleid", var6);
                              }

                           }
                        }
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
         if(this.applicationnumber != null) {
            DataConversion.JavaStrToCobolStr(_buffer, this.applicationnumber, _offset, 11);
         }

         _offset = 11;
         if(this.benftyr != null) {
            DataConversion.JavaStrToNumStr(_buffer, this.benftyr, _offset, 4, 0, 16);
         }

         _offset = 15;
         if(this.categorycode != null) {
            DataConversion.JavaStrToNumStr(_buffer, this.categorycode, _offset, 3, 0, 16);
         }

         _offset = 18;
         if(this.activitycode != null) {
            DataConversion.JavaStrToNumStr(_buffer, this.activitycode, _offset, 3, 0, 16);
         }

         _offset = 21;
         if(this.activitydate != null) {
            DataConversion.JavaStrToCobolStr(_buffer, this.activitydate, _offset, 10);
         }

         _offset = 31;
         if(this.msgtext != null) {
            DataConversion.JavaStrToCobolStr(_buffer, this.msgtext, _offset, 400);
         }

         short _offset1 = 431;
         if(this.createmoduleid != null) {
            DataConversion.JavaStrToCobolStr(_buffer, this.createmoduleid, _offset1, 6);
         }

         return _buffer;
      }
   }
}
