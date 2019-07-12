package com.task.entities;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fengL
 * @create 2019-07-09 13:17
 * @desc ftp中账单对应的实体类
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FtpBill {

  /**
   * 账单生成时间
   */
  private String datetime;
  /**
   * 该笔账单金额
   */
  private BigDecimal money;
  /**
   * 账单支付状态
   */
  private Integer status;


  /**
   * 获取文本格式的数据
   * @return
   */
  public String getStr(){
    StringBuilder sb = new StringBuilder();
    sb.append(this.getDatetime()).append(",");
    sb.append(this.getMoney()).append(",");
    sb.append(this.getStatus());
    return sb.toString();
  }
}
