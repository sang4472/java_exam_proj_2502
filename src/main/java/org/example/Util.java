package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
  public static String getNotDateStr() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date now = new Date();
    return dateFormat.format(now);

    }
}
