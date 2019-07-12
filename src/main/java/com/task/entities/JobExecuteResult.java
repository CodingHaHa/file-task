package com.task.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fengL
 * @create 2019-07-11 18:06
 * @desc 任务执行结果
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobExecuteResult {

  /**
   * 处理账单文件名
   */
  private String fileName;
  /**
   * 定时任务执行时间
   */
  private String executeTime;
  /**
   * 定时任务执行状态
   */
  private String status;


  public static JobExecuteResult newInstance(String fileName,String executeTime,String status){
    return new JobExecuteResult(fileName,executeTime,status);
  }


  public String getStrInfo(){
    StringBuilder sb = new StringBuilder();
    sb.append(fileName).append(",").append(executeTime).append(",").append(status);
    return sb.toString();
  }
}
