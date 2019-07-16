package com.task.job.support;

import com.task.constant.FileConstants;
import com.task.entities.FtpBill;
import com.task.entities.JobExecuteResult;
import com.task.enums.BillStatusEnum;
import com.task.enums.JobExecuteStatusEnum;
import com.task.service.FtpService;
import com.task.util.DateTimeUtil;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FileJobSupportTest {

  @Autowired
  private FileJobSupport fileJobSupport;
  @Autowired
  private FtpService ftpService;

  @Test
  public void parseFile() {
    List<String> list = ftpService.listOldFileName();
    if(CollectionUtils.isEmpty(list)){
      log.info("暂无数据");
      return;
    }
    list.sort(Comparator.comparingInt(e->e.hashCode()));
    String fileName = list.get(0);
    List<FtpBill> ftpBillList = fileJobSupport.parseFile(fileName, BillStatusEnum.Bill_STATUS_PAY_SUCCESS);
    Assert.assertEquals(1,ftpBillList.size());
  }

  @Test
  public void getTargetFileName() {
    Assert.assertNotNull(fileJobSupport.getTargetFileName());
  }

  @Test
  public void recordHandleSuccessFileName() {
    String fileName = "62829c6542fc4b32809339189f072c1e-2019-07-09-13-39-20-bill.txt";
    fileJobSupport.recordHandleSuccessFileName(fileName);
    Set<String> handleSuccessFileNameSet = ftpService.listRecoredHandledFileName(FileConstants.TASK_DATA_HANDLE_SUCCESS_FILE).stream().filter(item-> !StringUtils
        .isEmpty(item.trim())).collect(Collectors.toSet());
    Assert.assertTrue(handleSuccessFileNameSet.contains(fileName));
  }

  @Test
  public void recordHandleErrFileName() {
    String fileName = "62829c6542fc4b32809339189f072c1e-2019-07-09-13-39-20-bill.txt";
    fileJobSupport.recordHandleErrFileName(fileName);
    Set<String> handleSuccessFileNameSet = ftpService.listRecoredHandledFileName(FileConstants.TASK_DATA_HANDLE_ERR_FILE).stream().filter(item-> !StringUtils
        .isEmpty(item.trim())).collect(Collectors.toSet());
    Assert.assertTrue(handleSuccessFileNameSet.contains(fileName));
  }

  @Test
  public void recordExecuteResult() {
    String fileName = "62829c6542fc4b32809339189f072c1e-2019-07-09-13-39-20-bill.txt";
    fileJobSupport.recordExecuteResult(JobExecuteResult.builder().executeTime(DateTimeUtil.getDateTimeStrOfNow()).fileName(fileName).status(
        JobExecuteStatusEnum.EXECUTE_STATUS_ERROR.code).build());
  }
}
