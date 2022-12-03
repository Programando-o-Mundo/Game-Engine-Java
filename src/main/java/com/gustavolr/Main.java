package com.gustavolr;

import com.gustavolr.os.OsUtils;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) {
        System.out.println(OsUtils.getResource(OsUtils.join("fonts","Roboto-Regular.ttf")));
    }
}
