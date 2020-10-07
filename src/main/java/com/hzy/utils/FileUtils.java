package com.hzy.utils;

public class FileUtils {
//    public static String HOST_PORT = "http://127.0.0.1:8081/";
    public static String HOST_PORT = "http://10.101.76.66:8081/";
    public static String IMAGE_DIR = "C:/upload/";
    public static String[] IMAGE_EXT = new String[] {"png","bmp","jpg","jpeg","gif"};

    public static boolean isImage(String imageExt) {
        for (String ext : IMAGE_EXT) {
            if (ext.equals(imageExt)) {
                return true;
            }
        }
        return false;
    }
}
