package ua.gwm.elite_dangerous.eddb_java_api.utils;

import java.util.HashMap;

public abstract class EDDJavaAPILanguage extends HashMap<String, String> {

    public static EDDJavaAPILanguage en_US = new EDDJavaAPILanguage(){};
    public static EDDJavaAPILanguage ru_RU = new EDDJavaAPILanguage(){};
}
