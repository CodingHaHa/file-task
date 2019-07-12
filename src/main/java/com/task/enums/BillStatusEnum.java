package com.task.enums;

/**
 * @author fengL
 * @create 2019-07-11 13:17
 * @desc 账单状态枚举
 **/
public enum BillStatusEnum {

    BILL_STATUS_PAY_WAITING("1", "待支付"),
    Bill_STATUS_PAY_SUCCESS("2", "支付完成"),
    Bill_STATUS_PAY_ERROR("3", "支付失败");


    public String code;
    public String desc;

    BillStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
