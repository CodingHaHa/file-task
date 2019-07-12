package com.task.enums;

/**
 * @author fengL
 * @create 2019-07-11 13:17
 * @desc 任务执行状态枚举
 **/
public enum JobExecuteStatusEnum {

    EXECUTE_STATUS_SUCCESS("1","执行成功"),
    EXECUTE_STATUS_ERROR("2","执行失败");


    public String code;
    public String desc;

    JobExecuteStatusEnum(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

}
