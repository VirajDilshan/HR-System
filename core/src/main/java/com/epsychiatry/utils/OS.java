package com.epsychiatry.utils;

import com.epsychiatry.model.enums.Os;

public class OS {

    public static boolean isLinux(){
        return (System.getProperty("os.name").compareTo(Os.LINUX.getDisplayName()) == 0);
    }

    public static boolean isWindows10() {
        return (System.getProperty("os.name").compareTo(Os.WINDOWS10.getDisplayName()) == 0);
    }
}
