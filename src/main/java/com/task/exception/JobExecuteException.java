package com.task.exception;

/**
 * @author fengL
 * @create 2019-07-11 17:47
 * @desc 自定义运行时异常
 **/
public class JobExecuteException extends RuntimeException {

  public JobExecuteException(String msg){
    super(msg);
  }

}
