package net.bi4vmr.study;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 本地测试示例。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0
 */
public class TestBase {

    @Test
    public void test_Environment() {
        System.out.println("----- Test Environment start -----");

        assertEquals(4, 2 + 2);

        System.out.println("----- Test Environment end -----");
    }
}
