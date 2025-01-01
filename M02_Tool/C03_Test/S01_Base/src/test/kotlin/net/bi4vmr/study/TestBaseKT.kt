package net.bi4vmr.study

import org.junit.Assert
import org.junit.Test

/**
 * 本地测试示例。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0
 */
class TestBaseKT {

    @Test
    fun test_Environment() {
        println("----- Test Environment start -----")

        Assert.assertEquals(4, 2 + 2)

        println("----- Test Environment end -----")
    }
}
