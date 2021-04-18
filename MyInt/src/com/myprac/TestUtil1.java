package com.myprac;

import java.util.Collection;

public class TestUtil1 {

    public static void main(String[] args) {
       String str = unmask("1234 567 8",MaskTextType.MASK_ODB_NUMBER);
       if (str.contains("/s")) System.out.println("Space detected");
        System.out.println(str); // Display the string.
    }
    public static final char MASK_CHAR_DIGIT = '#';
    public static final char MASK_CHAR_LETTER = '!';

    /**
     * This is the enum to define all types of mask text in system.
     */
    public enum MaskTextType {
        // tdp app number
        MASK_TDP_APPLICATION_NUMBER("R!-###-####-##", "R!#########", "-"),
        // sc app number
        MASK_SC_APPLICATION_NUMBER("S!-###-####-##", "S!#########", "-"),
        // phone
        MASK_PHONE_NUMBER("(###)###-####", "##########", "[\\(\\)-]"),
        // sin
        MASK_SIN_NUMBER("### ### ###", "#########", " "),
        // odb number
        MASK_ODB_NUMBER("#### ### ###", "##########", " "),
        // odb/mcss number
        MASK_ODB_MCSS_NUMBER("#### ### ###", "##########", " "),
        // integer
        MASK_INTEGER("##########", "##########", ""),
        // Phone extension
        MASK_PHONE_EXTENSION("######", "######", "");

        private String formattedMask;
        private String plainTextMask;
        private String delimiter;

        MaskTextType(String formattedMask, String plainTextMask, String delimiter) {
            this.setFormattedMask(formattedMask);
            this.setPlainTextMask(plainTextMask);
            this.setDelimiter(delimiter);
        }

        public void setFormattedMask(String formattedMask) {
            this.formattedMask = formattedMask;
        }

        public String getFormattedMask() {
            return formattedMask;
        }

        public void setPlainTextMask(String plainTextMask) {
            this.plainTextMask = plainTextMask;
        }

        public String getPlainTextMask() {
            return plainTextMask;
        }

        /**
         * @param delimiter the delimiter to set
         */
        public void setDelimiter(String delimiter) {
            this.delimiter = delimiter;
        }

        /**
         * @return the delimiter
         */
        public String getDelimiter() {
            return delimiter;
        }
    }


    public static String unmask(String maskedText, MaskTextType maskTextType) {
        System.out.println( maskedText.length() );
        System.out.println( maskTextType.getFormattedMask().length() );
        if (ValidationUtil.isBlank(maskedText)
                || maskedText.length() != maskTextType.getFormattedMask().length()) {
           return maskedText;
    }
        char[] maskPlain = maskTextType.getPlainTextMask().toCharArray();
        StringBuilder ret = new StringBuilder();
        int sharpIdx = 0;
        for (int i = 0; i < maskPlain.length; i++) {
            char c = maskPlain[i];
            if (c == MASK_CHAR_DIGIT || c == MASK_CHAR_LETTER) {
                sharpIdx = maskTextType.getFormattedMask().indexOf(c, sharpIdx);
                ret.append(maskedText.substring(sharpIdx, sharpIdx + 1));
                sharpIdx++;
            } else {
                ret.append(c);
            }
        }
        return ret.toString();
    }


    /**
     * Utility class that has validation tools
     */
    public static class ValidationUtil {

        /**
         * check if the obj is blank
         *
         * @param obj
         * @return
         */
        public static boolean isBlank(Object obj) {
            return obj == null || obj.toString().length() == 0;
        }

        /**
         * check if a list is null or empty
         *
         * @param <H>
         *
         * @param collection
         * @return
         */
        public static <H> boolean isEmpty(Collection<H> collection) {
            return collection == null || collection.isEmpty();
        }


    }
}

