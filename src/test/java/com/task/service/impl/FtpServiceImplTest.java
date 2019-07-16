package com.task.service.impl;

import com.task.constant.FileConstants;
import com.task.entities.FtpBill;
import com.task.service.FtpService;
import com.task.util.DateTimeUtil;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FtpServiceImplTest {


  @Autowired
  private FtpService ftpService;


  @org.junit.Test
  public void listOldFileName() {
    List<String> list = ftpService.listOldFileName();
    org.junit.Assert.assertEquals(5,list.size());
  }

  @org.junit.Test
  public void listRecoredHandledFileName() {
    List<String> list = ftpService
        .listRecoredHandledFileName(FileConstants.TASK_DATA_HANDLE_SUCCESS_FILE);
    Assert.assertNotNull(list);
  }

  @org.junit.Test
  public void generateNewFile() {

    String fileName = "abc.txt";
    List<FtpBill> list = new ArrayList<>();
    list.add(FtpBill.builder().datetime(DateTimeUtil.getDateTimeStrOfNow()).money(BigDecimal.valueOf(12.56)).status(1).build());
    list.add(FtpBill.builder().datetime(DateTimeUtil.getDateTimeStrOfNow()).money(BigDecimal.valueOf(89.56)).status(2).build());
    list.add(FtpBill.builder().datetime(DateTimeUtil.getDateTimeStrOfNow()).money(BigDecimal.valueOf(12.56)).status(1).build());
    list.add(FtpBill.builder().datetime(DateTimeUtil.getDateTimeStrOfNow()).money(BigDecimal.valueOf(12.56)).status(1).build());
    ftpService.generateNewFile(fileName,list);

    File file = new File(FileConstants.BASE_PATH+FileConstants.PATH_SEPARATOR+FileConstants.DIR_NEW_DATA+FileConstants.PATH_SEPARATOR+fileName);
    Assert.assertTrue(file.exists());
  }
}
