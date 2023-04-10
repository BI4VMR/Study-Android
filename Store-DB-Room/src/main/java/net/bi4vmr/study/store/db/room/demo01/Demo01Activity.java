package net.bi4vmr.study.store.db.room.demo01;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.store.db.room.R;

import java.util.List;

public class Demo01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);

        TextView tvInfo = findViewById(R.id.tvInfo);
        EditText etID = findViewById(R.id.etID);
        Button btSelect = findViewById(R.id.btSelect);
        Button btAdd = findViewById(R.id.btAdd);
        Button btDel = findViewById(R.id.btDel);
        Button btUpdate = findViewById(R.id.btUpdate);

        // 获取数据库实例
        StudentDB studentDB = StudentDB.getInstance(this);
        // 获取DAO实例
        StudentDAO dao = studentDB.getStudentDAO();

        btSelect.setOnClickListener(v -> {
            // 查询记录
            List<Student> result = dao.getStudent();
            tvInfo.setText(result.toString());
        });

        btAdd.setOnClickListener(v -> {
            // 获取待操作的数据项ID
            int id = Integer.parseInt(etID.getText().toString());
            // 新增记录
            Student s = new Student(id, "田所浩二", 24);
            dao.addStudent(s);
        });

        btDel.setOnClickListener(v -> {
            // 获取待操作的数据项ID
            int id = Integer.parseInt(etID.getText().toString());
            // 删除记录
            Student s = new Student(id);
            dao.delStudent(s);
        });

        btUpdate.setOnClickListener(v -> {
            // 获取待操作的数据项ID
            int id = Integer.parseInt(etID.getText().toString());
            // 更新记录
            Student s = new Student(id, "远野", 25);
            dao.updateStudent(s);
        });
    }
}