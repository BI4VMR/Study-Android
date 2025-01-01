package net.bi4vmr.study.group_choice;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import net.bi4vmr.study.R;

public class DemoGroupChoiceUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_group_choice);

        ChipGroup chipgroupSingle = findViewById(R.id.chipgroupSingle);
        ChipGroup chipgroupMulti = findViewById(R.id.chipgroupMulti);

        /* 单选模式 */
        // 设置单选模式
        chipgroupSingle.setSingleSelection(true);
        // 设置Chip选中状态改变监听器
        chipgroupSingle.setOnCheckedStateChangeListener((group, checkedIds) -> {
            // 参数"checkedIds"是当前所有选中项ID的列表，并不是项的索引。
            Log.i("myapp", "单选列表选中状态改变。 CheckedList:" + checkedIds);
            // 单选列表默认允许全都不选中，如果我们希望永远有选中的Item，可以在List长度为0时选中某一项。
            if (checkedIds.size() == 0) {
                // 此处以选中第一项为例
                View view = group.getChildAt(0);
                if (view instanceof Chip) {
                    Chip chip = (Chip) view;
                    chip.setChecked(true);
                }
            }
        });

        /* 多选模式 */
        // 设置Chip选中状态改变监听器
        chipgroupMulti.setOnCheckedStateChangeListener((group, checkedIds) -> {
            // 参数"checkedIds"是当前所有选中项ID的列表，并不是项的索引。
            Log.i("myapp", "多选列表选中状态改变。 CheckedList:" + checkedIds);
        });
    }
}
