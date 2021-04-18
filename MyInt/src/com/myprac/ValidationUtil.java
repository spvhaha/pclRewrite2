package com.myprac;


import java.util.Collection;

/**
 * Utility class that has validation tools
 */
public class ValidationUtil {

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
