/**
 *
 */
package ca.ontario.health.hns.domain;


 /*   'Family Array Layout (0 to 16)
			 '   0  : Record Type
			 '   1  : SB Number
			 '   2  : Contact Name (first middle last)
			 '   3  : Language
			 '   4  : Address 1
			 '   5  : Address 2
			 '   6  : City
			 '   7  : Province
			 '   8  : Postal Code
			 '   9  : Country
			 '   10 : Benefit Year
			 '   11 : Member Count
			 '   12 : SCP Coverage End Date
			 '   13 : Threshold Amount
			 '   14 : Senior Annual Deductible
			 '   15 : Senior Ceiling Amount
			 '   16 : Problem Codes*/


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class SCPRejection  {
    private static final long serialVersionUID = 1L;
    private String recType;
    @Id
    private String appNum;
    private String contactName;
    private String lang;
    private String add1;
    private String add2;
    private String city;
    private String prov;
    private String postCode;
    private String country;
    private String benefitYear;
    private String memCount;
    private String covEndDate;
    private String thersholdAmt;

    private String ceilingAmt;
    private String deductAmt;
    private String probCodes;


    public SCPRejection(){

    }

    /*public SCPRejection(String recType, String appNum, String contactName, String lang, String add1, String add2, String city, String prov, String postCode, String country, String benefitYear, String memCount, String covEndDate, String thersholdAmt, String ceilingAmt, String deductAmt, String probCodes) {
        this.recType = recType;
        this.appNum = appNum;
        this.contactName = contactName;
        this.lang = lang;
        this.add1 = add1;
        this.add2 = add2;
        this.city = city;
        this.prov = prov;
        this.postCode = postCode;
        this.country = country;
        this.benefitYear = benefitYear;
        this.memCount = memCount;
        this.covEndDate = covEndDate;
        this.thersholdAmt = thersholdAmt;
        this.ceilingAmt = ceilingAmt;
        this.deductAmt = deductAmt;
        this.probCodes = probCodes;
    }*/

    @Override
    public String toString() {
        return "PclData{" +
                "recType='" + recType + '\'' +
                ", appNum='" + appNum + '\'' +
                ", contactName='" + contactName + '\'' +
                ", lang='" + lang + '\'' +
                ", add1='" + add1 + '\'' +
                ", add2='" + add2 + '\'' +
                ", city='" + city + '\'' +
                ", prov='" + prov + '\'' +
                ", postCode='" + postCode + '\'' +
                ", country='" + country + '\'' +
                ", benefitYear='" + benefitYear + '\'' +
                ", memCount='" + memCount + '\'' +
                ", covEndDate='" + covEndDate + '\'' +
                ", thersholdAmt='" + thersholdAmt + '\'' +
                ", ceilingAmt='" + ceilingAmt + '\'' +
                ", deductAmt='" + deductAmt + '\'' +
                ", probCodes='" + probCodes + '\'' +
                '}';
    }


    public String getRec_Type() {
        return recType;
    }

    public void setRec_Type(String rec_Type) {
        this.recType = recType;
    }

    public String getAppNum() {
        return appNum;
    }

    public void setAppNum(String appNum) {
        this.appNum = appNum;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getAdd1() {
        return add1;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getAdd2() {
        return add2;
    }

    public void setAdd2(String add2) {
        this.add2 = add2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBenefitYear() {
        return benefitYear;
    }

    public void setBenefitYear(String benefitYear) {
        this.benefitYear = benefitYear;
    }

    public String getMemCount() {
        return memCount;
    }

    public void setMemCount(String memCount) {
        this.memCount = memCount;
    }

    public String getCovEndDate() {
        return covEndDate;
    }

    public void setCovEndDate(String covEndDate) {
        this.covEndDate = covEndDate;
    }

    public String getThersholdAmt() {
        return thersholdAmt;
    }

    public void setThersholdAmt(String thersholdAmt) {
        this.thersholdAmt = thersholdAmt;
    }

    public String getDeductAmt() {
        return deductAmt;
    }

    public void setDeductAmt(String deductAmt) {
        this.deductAmt = deductAmt;
    }

    public String getProbCodes() {
        return probCodes;
    }

    public void setProbCodes(String probCodes) {
        this.probCodes = probCodes;
    }

    public String getCeilingAmt() {
        return ceilingAmt;
    }

    public void setCeilingAmt(String ceilingAmt) {
        this.ceilingAmt = ceilingAmt;
    }


}
