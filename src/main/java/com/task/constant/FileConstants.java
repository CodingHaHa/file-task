package com.task.constant;

import java.io.File;

/**
 * @author fengL
 * @create 2019-07-11 18:24
 * @desc 文件相关常量
 **/
public class FileConstants {

  private FileConstants(){}

  public static final String DIR_NEW_DATA = "data/new-data";
  public static final String DIR_task_DATA = "data/task-data";
  public static final String FILE_TASK_DATA_HANDLE_ERR_FILE = "data/task-data/handle-err-file.txt";
  public static final String FILE_TASK_DATA_HANDLE_SUCCESS_FILE = "data/task-data/handle-success-file.txt";
  public static final String FILE_TASK_DATA_JOB_EXECUTE_RESULT = "data/task-data/job-execute-result.txt";


  /**
   * 任务处理成功记录文件
   */
  public static final String TASK_DATA_HANDLE_SUCCESS_FILE = "handle-success-file.txt";
  /**
   * 任务处理失败记录文件
   */
  public static final String TASK_DATA_HANDLE_ERR_FILE = "handle-err-file.txt";
  /**
   * 任务执行结果记录文件
   */
  public static final String TASK_DATA_JOB_EXECUTE_RESULT = "job-execute-result.txt";


  /**
   * 路径分割符
   */
  public static final String PATH_SEPARATOR = File.separator;

  /**
   * 当前路径
   */
  public static final String BASE_PATH = System.getProperty("user.dir");
}
