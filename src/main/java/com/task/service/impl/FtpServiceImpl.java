package com.task.service.impl;

import com.task.constant.FileConstants;
import com.task.entities.FtpBill;
import com.task.exception.JobExecuteException;
import com.task.service.FtpService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author fengL
 * @create 2019-07-09 23:56
 * @desc
 **/
@Service
@Slf4j
public class FtpServiceImpl implements FtpService {


  @Override
  public List<String> listOldFileName() {
    ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    Resource[] resources;
    try {
      resources = resourcePatternResolver.getResources("classpath*:old-data/*.txt");
    } catch (IOException e) {
      log.error("加载旧数据失败：{}", e.getMessage());
      throw new JobExecuteException("加载旧数据失败: " + e.getMessage());
    }

    List<String> oldFileNameList = new ArrayList<>();
    for (Resource resource : resources) {
      oldFileNameList.add(resource.getFilename());
    }
    return oldFileNameList;
  }

  @Override
  public List<String> listRecoredHandledFileName(String recordFileName) {
    try (BufferedReader fileReader = new BufferedReader(new FileReader(
        FileConstants.BASE_PATH + FileConstants.PATH_SEPARATOR + FileConstants.DIR_task_DATA
            + FileConstants.PATH_SEPARATOR + recordFileName))) {
      return fileReader.lines().filter(line -> !StringUtils.isEmpty(line.trim()))
          .collect(Collectors.toList());
    } catch (IOException e) {
      log.error("解析数据失败：{}", e.getMessage());
      throw new JobExecuteException("解析数据失败: " + e.getMessage());
    }
  }


  @Override
  public void generateNewFile(String fileName, List<FtpBill> ftpBillList) {
    try (FileWriter fileWriter = new FileWriter(
        FileConstants.BASE_PATH + FileConstants.PATH_SEPARATOR + FileConstants.DIR_NEW_DATA
            + FileConstants.PATH_SEPARATOR + fileName, true)) {
      ftpBillList.stream().forEach(e -> {
        try {
          fileWriter.write(e.getStr() + '\n');
        } catch (IOException e1) {
          log.error("文件写入失败：{}", e1.getMessage());
          throw new JobExecuteException("文件写入失败: " + e1.getMessage());
        }
      });
    } catch (Exception e) {
      log.error("生成新文件失败：{}", e.getMessage());
      throw new JobExecuteException("生成新文件失败: " + e.getMessage());
    }
  }
}
