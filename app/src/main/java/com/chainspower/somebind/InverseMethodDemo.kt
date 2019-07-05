package com.chainspower.somebind

import androidx.databinding.InverseMethod

/**
 * Description:
 * 进行数据转换的注解 @InverseMethod 将参数转换为返回值，
 * 和将返回值转换为参数类型
 *
 * Date：2019/7/4-18:08
 * Author: cwh
 */
class InverseMethodDemo {

    @InverseMethod("orderCodeToString")
    fun stringToOrderCode(value: String): String {
        return when (value) {
            "order 1" -> {
                "001"
            }

            "order 2" -> {
                "002"
            }
            else -> {
                "000"
            }
        }
    }

    fun orderCodeToString(code: String): String {
        return when (code) {
            "001" -> {
                "order 1"
            }

            "002" -> {
                "order 2"
            }
            else -> "order null"
        }
    }
}