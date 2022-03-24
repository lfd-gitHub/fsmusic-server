package com.lfd.fsmusic.utils;

import com.lfd.fsmusic.repository.entity.File;

import java.util.Arrays;

public class Common {

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

    public static File.Type getFileTypeFromExt(String ext) {
        if (isAudio(ext)) {
            return File.Type.AUDIO;
        }

        if (isImage(ext)) {
            return File.Type.IMAGE;
        }

        if (isVideo(ext)) {
            return File.Type.VIDEO;
        }

        return File.Type.OTHER;
    }

    public static final String[] sExtVideo = { "vob", "mp4", "avi",
            "flv", "f4v", "wmv", "mov", "rmvb",
            "mkv", "mpg", "m4v", "webm", "rm",
            "mpeg", "asf", "ts", "mts" };
    public static final String[] sExtAudio = { "mp3", "wav" };
    public static String[] sExtImage = { "png", "jpg", "jpeg" };

    private static Boolean isVideo(String ext) {
        return Arrays.binarySearch(sExtVideo,ext.toLowerCase()) >= 0;
    }

    private static Boolean isAudio(String ext) {
        return Arrays.binarySearch(sExtAudio,ext.toLowerCase()) >= 0;
    }

    private static Boolean isImage(String ext) {
        return Arrays.binarySearch(sExtImage,ext.toLowerCase()) >= 0;
    }

}
