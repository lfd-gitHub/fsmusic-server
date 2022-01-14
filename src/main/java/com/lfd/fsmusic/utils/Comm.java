package com.lfd.fsmusic.utils;

public class Comm {

    /**
     * if error return -1
     * 
     * @param value
     * @return
     */
    public static Integer tryParse(String value) {
        return tryParse(value, -1);
    }

    public static Integer tryParse(String value, int errValue) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            e.printStackTrace();
            return errValue;
        }
    }
}
