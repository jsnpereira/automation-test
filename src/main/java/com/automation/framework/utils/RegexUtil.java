package com.automation.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    public static int matchNumber(String regex, String text){
        int number = 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()){
            number = Integer.parseInt(matcher.group(1).replace(",",""));
        }
        return number;
    }

}
