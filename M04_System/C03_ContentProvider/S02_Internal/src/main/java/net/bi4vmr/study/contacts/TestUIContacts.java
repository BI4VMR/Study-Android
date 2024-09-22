package net.bi4vmr.study.contacts;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiContactsBinding;

public class TestUIContacts extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIContacts.class.getSimpleName();

    private TestuiContactsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiContactsBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnQuery.setOnClickListener(v -> testQuery());
    }

    // 查询联系人信息
    private void testQuery() {
        binding.tvLog.append("\n--- 查询联系人信息 ---\n");
        Log.i(TAG, "--- 查询联系人信息 ---");

        // 查询联系人需要权限，此处省略动态申请逻辑，请在应用设置中授权。
        boolean canQueryContacts = (checkSelfPermission(Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED);
        if (!canQueryContacts) {
            binding.tvLog.append("请授予“读取联系人”权限！\n");
            Log.i(TAG, "请授予“读取联系人”权限！");
            return;
        }

        // 获取ContentResolver对象
        ContentResolver contentResolver = getContentResolver();
        // 查询所有联系人
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        // 遍历游标，读取联系人信息。
        if (cursor != null && cursor.moveToFirst()) {
            // 获取列索引
            int indexID = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            int indexName = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            // 部分系统版本可能缺少某些字段，导致索引数值为"-1"，需要注意这种情况。
            if (indexID < 0 || indexName < 0) {
                // 此处不做容错处理，缺少字段则结束操作。
                cursor.close();
                return;
            }

            // 循环读取数据
            do {
                int id = cursor.getInt(indexID);
                String name = cursor.getString(indexName);

                binding.tvLog.append("Contact ID:" + id + ", Name:" + name + "\n");
                Log.i(TAG, "Contact ID:" + id + ", Name:" + name);
            } while (cursor.moveToNext());
            cursor.close();
        }
    }
}
