/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1.stock.vo;

import java.io.Serializable;


public class MyProtocol implements Serializable{
    public static final String signMsg[] = {"INSERT_MEMBER","SELECT_MEMBER","UPDATE_MEMBER","DELETE_MEMBER"};
    String sign;
    Object parameterObj;
    Object resultObj;

    public MyProtocol(String sign, Object parameterObj, Object resultObj) {
        setSign(sign);
        setParameterObj(parameterObj);
        setResultObj(resultObj);
    }

    public Object getParameterObj() {
        return parameterObj;
    }

    public static String[] getSignMsg() {
        return signMsg;
    }

    public String getSign() {
        return sign;
    }

    public Object getResultObj() {
        return resultObj;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setParameterObj(Object parameterObj) {
        this.parameterObj = parameterObj;
    }

    public void setResultObj(Object resultObj) {
        this.resultObj = resultObj;
    }
    
}
