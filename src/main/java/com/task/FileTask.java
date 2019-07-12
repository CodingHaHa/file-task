package com.task;

import com.task.threads.ClearThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author fengL
 * @create 2019-07-09 13:13
 * @desc
 **/
@EnableScheduling
@SpringBootApplication
public class FileTask {

  public static void main(String[] args) {
    SpringApplication.run(FileTask.class,args);

    Runtime.getRuntime().addShutdownHook(new ClearThread());
  }
}
