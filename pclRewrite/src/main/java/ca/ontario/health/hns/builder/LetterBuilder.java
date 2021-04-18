package ca.ontario.health.hns.builder;

import ca.ontario.health.hns.common.ByteWrapper;
import ca.ontario.health.hns.common.DocumentServiceCodes;
import ca.ontario.health.hns.domain.SCPRejection;
import ca.ontario.health.hns.util.csv.StringUtils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRRtfExporter;

import java.io.ByteArrayOutputStream;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;

//public class LetterBuilder extends DocumentBuilder {
public class LetterBuilder {
    /**
     * generate SCP Rejection Letter
     *
     * @param SCPRejection
     * @return
     * @throws Exception
     */
    public static ByteWrapper generateSCPRejectionLetter(SCPRejection SCPRejection) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appNumber", StringUtils.formatAppNumber(SCPRejection.getAppNum()));
        params.put("documentFormat", DocumentServiceCodes.DOC_FORMAT_PDF);
        //logger.info("SCPRejectionLetter :" + params);
        //ApplicationManagementDao dao = new ApplicationManagementDaoImpl();
        //ApplicationCore app = dao.findApplication(appNumber);
        String str = "Contact Information is not found";
        params.put("program", "scp");
        String strName = SCPRejection.getContactName();
        strName = strName.replace((char)65533 , ' ');
        str = strName + "\n"
                + (SCPRejection.getAdd2() == null ? "" : SCPRejection.getAdd2() + "\n") + SCPRejection.getCity()
                + " " + SCPRejection.getProv() + " " + StringUtils.formatPostalCode(SCPRejection.getPostCode());
        String country = SCPRejection.getCountry();
        if(country!=null&&!"CAN".equalsIgnoreCase(country)){
            str=str+ "\n" + country;
        }
        params.put("address",str);
        params.put("benefitYear", Integer.valueOf(SCPRejection.getBenefitYear()));
        params.put("appNumber", StringUtils.formatAppNumber(SCPRejection.getAppNum()));

        params.put("lang", SCPRejection.getLang());

        // Handle new and renewal application differently.
        // check this condition
        //  if (DBConstants.TYPE_NEW.equals(app.getTypeCode())) {
        params.put("paragraph00a", "SHOW");
        //} else {
        //  String dateStr = app.getBenefitYear() + DBConstants.BENEFIT_YEAR_END_DATE;
        //  DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        //   Date theDate = StringUtils.getDateFromString(dateStr, dateFormat);
        //  String endDateStr = BPCSReportUtil.LetterDateFormatter(theDate, lang);
        params.put("endDate", SCPRejection.getCovEndDate());

        params.put("paragraph00b", "SHOW");


       /* List<Recipient> recipList = null;
        recipList = dao.findRecipients(appNumber);
        boolean isCouple = false;
        boolean areAll0028 = true;
        for (Iterator iterator = recipList.iterator(); iterator.hasNext();) {
            Recipient rec = (Recipient) iterator.next();
            if (rec.getMbrshpEndDate() != null && rec.getMbrshpEndDate().before(rec.getMbrshpStartDate()))
                continue; // skip inactive members

            loadMembersPerParagraph(rec, "01", "0021", params,-1);
            //loadMembersPerParagraph(rec, "02", "0028", params,-1);
            boolean isSingle0028 = false;
            for (String pi : rec.getProblemInds()) {
                if ("0028".equals(pi)) {
                    isSingle0028 = true;
                    break;
                }
            }*/

        String probCodes = SCPRejection.getProbCodes();


        // Print paragraph 2 when all members are under 65.
        if (probCodes.contains("0028")) {
            params.put("paragraph02", "SHOW");
        }

        /*Household household = null;
        household = dao.findHousehold(appNumber);
        if(household!=null&&"Y".equalsIgnoreCase(household.getHouseholdFlags().getCoupleIndicator().trim())){
            isCouple=true;
        }*/

        if (probCodes.contains("0029")) {
          //  appendValue("paragraph" + (isCouple ? "04" : "03"), "Show", params, false, 0);
            params.put("paragraph03", "SHOW");
        }
/*

        List<String> problemInds = household.getProblemInds();
        for (String pi : problemInds) {
            if ("0029".equals(pi)) {
                appendValue("paragraph" + (isCouple ? "04" : "03"), "Show", params, false, 0);
                break;
            }
        }
*/

        // SCP income verification.
        if (SCPRejection.getMemCount().equals("2") )
           params.put("coupleThreshold", SCPRejection.getThersholdAmt());
        else
          params.put("singleThreshold", SCPRejection.getThersholdAmt());
        


       /* int yearInt = app.getBenefitYear().intValue();
        InformationManagementDao imDao = new InformationManagementDaoImpl();*/

       /* if (isCouple) {
            // Couple income threshold.
            String str1 = imDao.getBPCSParaValue(DBConstants.PROGRAM_GRP_SRCOPAY,
                    DBConstants.PARA_COUPLE_INCOME_THRESHOLD, yearInt);
            if (str1.indexOf(".") > -1) {
                str1 = str1.substring(0, str1.indexOf("."));
            }
            String coupleThreshold = StringUtils.formatNumber(str1, lang);

            params.put("coupleThreshold", coupleThreshold);
        } else {
            // Single income threshold.
            String str1 = imDao.getBPCSParaValue(DBConstants.PROGRAM_GRP_SRCOPAY,
                    DBConstants.PARA_SINGLE_INCOME_THRESHOLD, yearInt);
            if (str1.indexOf(".") > -1) {
                str1 = str1.substring(0, str1.indexOf("."));
            }
            String singleThreshold = StringUtils.formatNumber(str1, lang);

            params.put("singleThreshold", singleThreshold);
        }*/

       /* // Annual deductible.
        String str1 = imDao.getBPCSParaValue(DBConstants.PROGRAM_GRP_SENIORS,
                DBConstants.PARA_SN_DEFAULT_DEDUCTIBLE_AMOUNT, yearInt);
        String annualDeductible = StringUtils.formatNumber(str1, lang);*/
        params.put("annualDeductible",  SCPRejection.getDeductAmt());

        // Ceiling amount.
        /*str1 = imDao.getBPCSParaValue(DBConstants.PROGRAM_GRP_SENIORS,
                DBConstants.PARA_SN_DEFAULT_COPAY_CEILING, yearInt);
        String ceilingAmount = StringUtils.formatNumber(str1, lang);*/
        params.put("ceilingAmount", SCPRejection.getCeilingAmt());

        //logger.info("generateSCPRejectionLetter :" + params);


        return generateSCPRejectionLetter(params);

    }

    /**
     * generateSCPRejectionLetter
     * @param params
     * @return
     * @throws Exception
     */
    public static ByteWrapper generateSCPRejectionLetter(Map params) throws Exception {
        return executeJasper(getTemplateName("SCP_Rejection_Letter", params), params, null);
    }

    /**
     *
     *
     *
     * @param key
     * @param value
     * @param params
     */
    static void appendValue(String key, String value, Map params,
                            boolean addMember, int memberIndex) {
        String membersStr = (String) params.get(key);
        if (addMember){
            if(membersStr == null)
                membersStr = "\t";

            if (memberIndex < 0) {
                membersStr += "\n";
            } else {
                membersStr += "\n\t";
            }
        }
        membersStr += value;
        params.put(key, membersStr);

    }


    /**
     * getTemplateName
     * @param templateName
     * @param params
     * @return
     */
    private static String getTemplateName(String templateName, Map params) {
        String lang = ((String) params.get("lang"));
        if(lang==null||lang.trim().equals("")){
            lang="en";
            params.put("lang", "EN");
        }
        templateName += (lang != null && lang.equalsIgnoreCase("fr")) ? "_Fr" : "_En";
        return templateName;
    }


    /**
     * executeJasper
     * @param templateName
     * @param params
     * @param ds
     * @return
     * @throws Exception
     */
    private static ByteWrapper executeJasper(String templateName, Map params, JRDataSource ds)
            throws Exception {
        try {
           /* if (debug){
                logger.info("templateName : "+templateName +"   start ************");
                printoutMap(params);
                logger.info("templateName : "+templateName+ "   ended***********");
            }*/

            //String absolutePath = FileUtil.absolutePath(reportDir + templateName + ".jasper");
            String reportDir = "C:\\BPCS\\reports\\batchReport\\";
            String reportDestDir = "C:\\BPCS\\reports\\batchReport\\Dest\\" ;
            String absolutePath = reportDir + templateName + ".jasper";

            if (ds == null)
                ds = new JREmptyDataSource();

            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(absolutePath, params, ds);

            boolean debug;
            debug = true;
            if (DocumentServiceCodes.DOC_FORMAT_PDF.equals(params.get("documentFormat"))) {
                if (debug)
                    JasperExportManager.exportReportToPdfFile(jasperPrint, reportDestDir + templateName
                            + System.currentTimeMillis() + ".pdf");

                return new ByteWrapper(JasperExportManager.exportReportToPdf(jasperPrint));

            } else if (DocumentServiceCodes.DOC_FORMAT_RTF.equals(params.get("documentFormat"))) {

                if (debug) {
                    JRRtfExporter exporter = new JRRtfExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    String outputfilename=reportDestDir + templateName
                            + System.currentTimeMillis() + ".rtf";
                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outputfilename);
                    exporter.exportReport();
                   // logger.info("the letter file is at : "+outputfilename);
                }
                JRRtfExporter exporter = new JRRtfExporter();
                final ByteArrayOutputStream rtfStream = new ByteArrayOutputStream();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, rtfStream);
                exporter.exportReport();
                ByteWrapper byteArray = new ByteWrapper(rtfStream.toByteArray());

                return byteArray;
            } else
                throw new EmptyStackException();

        } catch (Throwable t) {
            Exception exp = (Exception) t.getCause();
            throw exp;
        }
    }
}
