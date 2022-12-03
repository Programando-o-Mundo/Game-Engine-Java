package com.gustavolr.os;
import java.nio.file.Paths;

public class OsUtils 
{
    
    public static String join(String... s) {
        return Paths.get("",s).toString();
    }

    public static java.net.URL getResource(String path) {
        return OsUtils.class.getClassLoader().getResource(path);
    }
}
