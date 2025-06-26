package net.bi4vmr.study;

import android.app.Application;
import android.content.Context;

import org.junit.Before;
import org.junit.Test;

/**
 * 示例一：构建环境。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0
 */
public class TestBase {

    private Application mockApplication;
    private Context mockContext;

    @Test
    public void test_Env() {
        System.out.println("----- TestEnv start -----");

        System.out.println("----- TestEnv end -----");
    }
}
