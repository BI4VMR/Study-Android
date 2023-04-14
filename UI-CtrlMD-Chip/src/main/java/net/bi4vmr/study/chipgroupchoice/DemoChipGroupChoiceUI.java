package net.bi4vmr.study.chipgroupchoice;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.ChipGroup;

import net.bi4vmr.study.R;

public class DemoChipGroupChoiceUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_chipgroup_choice);

        ChipGroup chipgroupSingle = findViewById(R.id.chipgroupSingle);
        ChipGroup chipgroupMulti = findViewById(R.id.chipgroupMulti);

        /* 单选模式 */
        // 设置单选模式
        chipgroupSingle.setSingleSelection(true);
        // 设置Item选中状态改变监听器
        chipgroupSingle.setOnCheckedStateChangeListener((group, checkedIds) -> {
            // 打印日志
            Log.i("myapp", "单选列表选中状态改变。 CheckedList:" + checkedIds);
            // 单选列表默认允许全都不选中，如果我们希望永远有选中的Item，可以
            // if (checkedIds.size()==0){
            //     chipgroupSingle.set
            // }
            //TODO
        });

        /* 单选模式 */
        // 设置Item选中状态改变监听器
        chipgroupMulti.setOnCheckedStateChangeListener((group, checkedIds) -> {
            // 打印日志
            Log.i("myapp", "多选列表选中状态改变。 CheckedList:" + checkedIds);
        });
    }
}
