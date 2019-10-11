package org.fox.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class STD {
    public static Date StringTurnDate(String Stime){
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        Date Dtime = null;
        try{
            Dtime = dateFormat.parse(Stime);
            return Dtime;
        }catch(Exception e){
            e.getMessage();
        }
        return Dtime;
    }
}
