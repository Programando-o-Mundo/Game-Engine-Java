package com.gustavolr.utils;

public final class OsUtils 
{
    public static String join(String... s) {
        return java.nio.file.Paths.get("",s).toString();
    }

    public static java.net.URL getResource(String path) {
        return OsUtils.class.getClassLoader().getResource(path);
    }

    public static java.io.InputStream getResourceAsInputStream(String path) {
        return OsUtils.class.getClassLoader().getResourceAsStream(path);
    }
}
