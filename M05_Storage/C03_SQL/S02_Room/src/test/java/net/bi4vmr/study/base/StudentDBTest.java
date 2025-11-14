package net.bi4vmr.study.base;

import android.content.Context;
import android.os.Build;

import androidx.room.Room;

import org.json.JSONException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

/**
 * {@link StudentDB}功能测试。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.TIRAMISU})
public class StudentDBTest {

    private StudentDB memoryDB;

    @Before
    public void setUp() {
        Context mockContext = RuntimeEnvironment.getApplication();

        // 构建内存数据库
        memoryDB = Room.inMemoryDatabaseBuilder(mockContext, StudentDB.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void tearDown() {
        memoryDB.close();
    }

    @Test
    public void test_AddStudent() throws JSONException {
        System.out.println("----- Test AddStudent start -----");

        // 插入测试数据
        Student testData1 = new Student(1L, "Alice", 18);
        Student testData2 = new Student(2L, "Bob", 20);
        memoryDB.getStudentDAO().addStudent(testData1);
        memoryDB.getStudentDAO().addStudent(testData2);

        // 验证能否读取先前插入的数据
        List<Student> all = memoryDB.getStudentDAO().getStudents();
        for (Student student : all) {
            System.out.println(student);
        }
        Assert.assertEquals(2, all.size());

        System.out.println("----- Test AddStudent end -----");
    }

    @Test
    public void test_DelStudent() {
        System.out.println("----- Test DelStudent start -----");

        // 插入测试数据
        Student testData1 = new Student(1L, "Alice", 18);
        Student testData2 = new Student(2L, "Bob", 20);
        memoryDB.getStudentDAO().addStudent(testData1);
        memoryDB.getStudentDAO().addStudent(testData2);

        // 删除编号为1的记录
        memoryDB.getStudentDAO().delStudent(testData1);

        // 验证当前记录数量是否正确
        List<Student> all = memoryDB.getStudentDAO().getStudents();
        for (Student student : all) {
            System.out.println(student);
        }
        Assert.assertEquals(1, all.size());

        System.out.println("----- Test DelStudent end -----");
    }
}
