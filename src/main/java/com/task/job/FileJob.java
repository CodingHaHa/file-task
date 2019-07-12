package com.task.job;

import com.task.entities.FtpBill;
import com.task.entities.JobExecuteResult;
import com.task.enums.BillStatusEnum;
import com.task.enums.JobExecuteStatusEnum;
import com.task.job.support.FileJobSupport;
import com.task.service.FtpService;
import com.task.util.DateTimeUtil;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author fengL
 * @create 2019-07-09 22:18
 * @desc
 **/
@Component
@Lazy(value=false)
@Slf4j
public class FileJob {


  @Autowired
  private FtpService ftpService;
  @Autowired
  private FileJobSupport fileJobSupport;

  @Scheduled(cron="${schedule.cron}")
  public void run(){
    String targetFileName = null;
    try {
      log.info("开始执行定时任务");
      //1.获取要被处理的文件
      targetFileName = fileJobSupport.getTargetFileName();
      if(null == targetFileName){
        log.info("暂无数据，定时任务执行完毕");
        return;
      }
      log.info("待处理文件:{}",targetFileName);

      //2.解析文件
      List<FtpBill> ftpBillList = fileJobSupport.parseFile(targetFileName, BillStatusEnum.Bill_STATUS_PAY_SUCCESS);

      //3.生成新文件
      ftpService.generateNewFile(targetFileName,ftpBillList);

      //4.添加记录
      fileJobSupport.recordHandleSuccessFileName(targetFileName);
      fileJobSupport.recordExecuteResult(JobExecuteResult.newInstance(targetFileName, DateTimeUtil.getDateTimeStrOfNow(), JobExecuteStatusEnum.EXECUTE_STATUS_SUCCESS.code));
      log.info("定时任务执行完毕");
    }catch (Exception e){
      log.error("本次任务执行失败：{}",e.getMessage());
      fileJobSupport.recordHandleErrFileName(targetFileName);
      fileJobSupport.recordExecuteResult(JobExecuteResult.newInstance(targetFileName, DateTimeUtil.getDateTimeStrOfNow(), JobExecuteStatusEnum.EXECUTE_STATUS_ERROR.code));
    }
  }
}
