package com.task.threads;

import com.task.constant.FileConstants;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Arrays;

/**
 * @author fengL
 * @create 2019-07-11 23:56
 * @desc jvm关闭时清理垃圾数据
 **/
@Slf4j
public class ClearThread extends Thread {

    @Override
    public void run() {
        log.info("程序即将终止，开始清理垃圾数据");
        File file = new File(FileConstants.BASE_PATH + FileConstants.PATH_SEPARATOR + "data");
        if (file.exists()) {
            deleteFile(file);
            file.delete();
        }
        log.info("清理垃圾数据清理完毕，感谢您的使用!");
    }

    public void deleteFile(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                Arrays.stream(file.listFiles()).forEach(f -> {
                    if (f.isDirectory()) {
                        deleteFile(f);
                    } else {
                        f.delete();
                    }
                });
            }
            file.delete();
        }
    }
}
