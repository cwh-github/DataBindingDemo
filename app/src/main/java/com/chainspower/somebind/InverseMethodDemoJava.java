package com.chainspower.somebind;

import androidx.databinding.InverseMethod;

/**
 * Description:
 * Date：2019/7/4-18:21
 * Author: cwh
 */
public class InverseMethodDemoJava {

    @InverseMethod("orderCodeToString")
    public static String stringToOrderCode(String value){
        if(value==null){
            return null;
        }
       switch (value) {
           case "order 1" :{
                return "001";
            }

           case "order 2" :{
                return "002";
            }
            default: {
                return "000";
            }
        }
    }


    public static String orderCodeToString(String code){
        if(code==null){
            return null;
        }
        switch (code) {
            case "001" :{
               return "order 1";
            }

            case "002":{
               return  "order 2";
            }
            default: return "order null";
        }
    }
}
