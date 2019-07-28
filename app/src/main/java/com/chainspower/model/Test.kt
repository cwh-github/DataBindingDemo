package com.chainspower.model

/**
 * Description:
 * Date：2019/7/9-18:55
 * Author: cwh
 */
abstract class Test {
    abstract fun say()

    abstract val i:Int
}

class TestImp:Test(){
    override fun say() {

    }

    override val i: Int
        get() = 10

}