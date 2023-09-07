package com.xxx.service;

import com.xxx.bean.qrCode;
import com.xxx.dao.QrCodeData;

import java.util.List;

/**
 * @author xzy
 * @create 2022/3/20 17:01
 */
public class serviceQrCode {
    public void setData(qrCode q){
        new QrCodeData().add(q);
    }
    public List getData(){
        return new QrCodeData().select();
    }
}
