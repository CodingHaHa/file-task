package com.task.job.support;

import com.task.constant.FileConstants;
import com.task.entities.FtpBill;
import com.task.entities.JobExecuteResult;
import com.task.enums.BillStatusEnum;
import com.task.exception.JobExecuteException;
import com.task.service.FtpService;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author fengL
 * @create 2019-07-11 22:18
 * @desc
 **/
@Component
@Slf4j
public class FileJobSupport {

    @Autowired
    private FtpService ftpService;

    /**
     * 私有方法：解析账单文件
     * @param fileName
     * @return
     */
    public List<FtpBill> parseFile(String fileName, BillStatusEnum billStatusEnum){
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(defaultResourceLoader.getResource("classpath:old-data/"+fileName).getInputStream()))) {
            return reader.lines().filter(line->{
                String[] strings = line.split(",");
                return strings[2].equals(billStatusEnum.code);
            }).map(line->{
                String[] strings = line.split(",");
                FtpBill bill = new FtpBill();
                bill.setDatetime(strings[0]);
                bill.setMoney(BigDecimal.valueOf(Double.parseDouble(strings[1])));
                bill.setStatus(Integer.valueOf(strings[2]));
                return bill;
            }).collect(Collectors.toList());
        }catch (Exception e){
            log.error("解析账单数据异常:{}",e.getMessage());
            throw new JobExecuteException("解析账单数据异常:"+e.getMessage());
        }
    }


    /**
     * 私有方法：获取待解析的文件名
     * @return
     */
    public synchronized String getTargetFileName(){
        List<String> oldFileNameList = ftpService.listOldFileName();
        Set<String> handleSuccessFileNameSet = ftpService.listRecoredHandledFileName(FileConstants.TASK_DATA_HANDLE_SUCCESS_FILE).stream().filter(item-> !StringUtils.isEmpty(item.trim())).collect(Collectors.toSet());
        Set<String> handleErrFileNameSet = ftpService.listRecoredHandledFileName(FileConstants.TASK_DATA_HANDLE_ERR_FILE).stream().filter(item-> !StringUtils.isEmpty(item.trim())).collect(Collectors.toSet());

        List<String> targetFileNameList = oldFileNameList.stream().filter(
                old -> !handleSuccessFileNameSet.contains(old))
                .collect(Collectors.toList());
        if(CollectionUtils.isEmpty(targetFileNameList)){
            return null;
        }
        return targetFileNameList.get(0);
    }


    /**
     * 私有方法：记录解析成功的文件
     * @param fileName
     */
    public synchronized void recordHandleSuccessFileName(String fileName){
        try(FileWriter fileWriter = new FileWriter(FileConstants.BASE_PATH + FileConstants.PATH_SEPARATOR + FileConstants.DIR_task_DATA+FileConstants.PATH_SEPARATOR + FileConstants.TASK_DATA_HANDLE_SUCCESS_FILE,true)) {
            fileWriter.write(fileName+'\n');
        } catch (IOException e) {
            log.error("添加处理成功文件记录失败：{}",e.getMessage());
            throw new JobExecuteException("添加文件记录失败："+e.getMessage());
        }
    }


    public synchronized void recordHandleErrFileName(String fileName){
        try(FileWriter fileWriter = new FileWriter(FileConstants.BASE_PATH + FileConstants.PATH_SEPARATOR + FileConstants.DIR_task_DATA + FileConstants.PATH_SEPARATOR + FileConstants.TASK_DATA_HANDLE_ERR_FILE,true)) {
            fileWriter.write(fileName+'\n');
        } catch (IOException e) {
            log.error("添加处理失败文件记录失败：{}",e.getMessage());
            throw new JobExecuteException("添加文件记录失败："+e.getMessage());
        }
    }


    public synchronized void recordExecuteResult(JobExecuteResult jobExecuteResult){
        try(FileWriter fileWriter = new FileWriter(FileConstants.BASE_PATH + FileConstants.PATH_SEPARATOR + FileConstants.DIR_task_DATA+ FileConstants.PATH_SEPARATOR + FileConstants.TASK_DATA_JOB_EXECUTE_RESULT,true)) {
            fileWriter.write(jobExecuteResult.getStrInfo()+'\n');
        } catch (IOException e) {
            log.error("添加定时器执行结果文件记录失败：{}",e.getMessage());
            throw new JobExecuteException("添加定时器执行结果文件记录失败："+e.getMessage());
        }
    }

}
