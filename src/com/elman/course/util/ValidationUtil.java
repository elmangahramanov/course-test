package com.elman.course.util;

public class ValidationUtil {
    public static boolean Validate(String... fields){
        for (String s : fields){
            if (s!=null && !s.trim().isEmpty()){
                return true;
            }
        }
        return false;
    }

}
