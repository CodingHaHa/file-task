package com.task.service;

import com.task.constant.FileConstants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author fengL
 * @create 2019-07-12 10:10
 * @desc 程序启动执行初始化工作
 **/
@Component
public class MyStartupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        String basePath = System.getProperty("user.dir");

        //创建初始化目录
        File newDatadir = new File(basePath+File.separator+FileConstants.DIR_NEW_DATA);
        if(!newDatadir.exists()){
            newDatadir.mkdirs();
        }
        File taskDatadir = new File(basePath+File.separator+FileConstants.DIR_task_DATA);
        if(!taskDatadir.exists()){
            taskDatadir.mkdirs();
        }
        //创建初始化文件
        File handleErrFile = new File(basePath+File.separator+FileConstants.FILE_TASK_DATA_HANDLE_ERR_FILE);
        if(!handleErrFile.exists()){
            handleErrFile.createNewFile();
        }
        File handleSuccFile = new File(basePath+File.separator+FileConstants.FILE_TASK_DATA_HANDLE_SUCCESS_FILE);
        if(!handleSuccFile.exists()){
            handleSuccFile.createNewFile();
        }
        File execResultFile = new File(basePath+File.separator+FileConstants.FILE_TASK_DATA_JOB_EXECUTE_RESULT);
        if(!execResultFile.exists()){
            execResultFile.createNewFile();
        }
    }

}
