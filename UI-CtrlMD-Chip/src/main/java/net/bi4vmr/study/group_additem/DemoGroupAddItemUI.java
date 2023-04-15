package net.bi4vmr.study.group_additem;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import net.bi4vmr.study.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DemoGroupAddItemUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_group_additem);

        ChipGroup chipgroup = findViewById(R.id.chipgroup);
        Button btnGet = findViewById(R.id.btnGet);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnDel = findViewById(R.id.btnDel);

        // 获取当前状态按钮
        btnGet.setOnClickListener(v -> {
            // 获取所有表项
            List<View> viewList = new ArrayList<>();
            int childCount = chipgroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                viewList.add(chipgroup.getChildAt(i));
            }
            Log.i("myapp", "当前Group中已添加的Item数量：" + viewList.size());

            // 获取选中表项
            List<Integer> idList = chipgroup.getCheckedChipIds();
            Log.i("myapp", "当前Group中被选中的ItemID：" + idList);
            for (int id : idList) {
                Chip item = (Chip) chipgroup.findViewById(id);
                if (item != null) {
                    Log.i("myapp", "Text:" + item);
                }
            }
        });

        // 新增Item按钮
        btnAdd.setOnClickListener(v -> {
            Chip chip = new Chip(this);
            chip.setCheckedIconVisible(true);
            chip.setCheckable(true);
            chip.setText(genRandomID());
            chipgroup.addView(chip);
        });

        // 删除Item按钮
        btnDel.setOnClickListener(v -> {
            int itemCount = chipgroup.getChildCount();
            if (itemCount == 0) {
                return;
            }

            // 移除最后一个子View
            chipgroup.removeViewAt(itemCount - 1);
        });
    }

    // 获取随机ID
    private String genRandomID() {
        return UUID.randomUUID()
                .toString()
                .toUpperCase()
                .substring(0, 6);
    }
}
