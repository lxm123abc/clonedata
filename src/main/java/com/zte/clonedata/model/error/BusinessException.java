package com.zte.clonedata.model.error;

import org.springframework.util.StringUtils;

/**
 * ProjectName: dianping-com.zte.dianping.common
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 11:37 2020/2/20 0020
 * @Description:
 */
public class BusinessException extends Exception {

    private CommonError commonError;

    public BusinessException(EmBusinessError emBusinessError) {
        super();
        this.commonError = new CommonError(emBusinessError);
    }

    public CommonError getCommonError() {
        return commonError;
    }

    public void setCommonError(CommonError commonError) {
        this.commonError = commonError;
    }

    public BusinessException(EmBusinessError emBusinessError, String errMsg) {
        super();
        this.commonError = new CommonError(emBusinessError);
        if (!StringUtils.isEmpty(errMsg)){
            this.commonError.setErrorMsg(errMsg);
        }
    }
}
