package com.task.service;

import com.task.entities.FtpBill;
import java.util.List;

/**
 * @author fengL
 * @create 2019-07-09 13:09
 * @desc 模拟ftp操作
 **/
public interface FtpService {


  /**
   * 获取历史文件名
   * @return
   */
  List<String> listOldFileName();


  /**
   * 获取已经被记录的文件名
   * @return
   */
  List<String> listRecoredHandledFileName(String recordFileName);


  /**
   * 生成满足条件的文件，并写入到指对应的存放路径下
   * @param fileName
   * @param ftpBillList
   */
  void generateNewFile(String fileName,List<FtpBill> ftpBillList);

}
