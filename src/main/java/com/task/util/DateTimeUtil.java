package com.task.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author fengL
 * @create 2019-07-11 18:33
 * @desc 时间日期工具类
 **/
public class DateTimeUtil {

  private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");

  private DateTimeUtil(){}

  /**
   * 获取当前时间的字符串
   * @return
   */
  public static String getDateTimeStrOfNow(){
    return LocalDateTime.now().format(formatter);
  }

}
