package pdg.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    public static Date fromSql(String date) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:dd").parse(date);
    }

    public static String toSqlFormat(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:dd").format(date);
    }
}

