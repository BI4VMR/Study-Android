package net.bi4vmr.tool

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.tool.base.TestUIWorkspaceKT
import net.bi4vmr.tool.base.launcher.LauncherDatabase
import net.bi4vmr.tool.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 桌面图标拖拽排序(KT)
        binding.btnWorkspaceKT.setOnClickListener {
            startActivity(Intent(this, TestUIWorkspaceKT::class.java))
        }

        // 重置布局数据
        binding.btnResetKT.setOnClickListener {
            deleteDatabase(LauncherDatabase.DB_NAME)
            Toast.makeText(this, "已清空布局数据", Toast.LENGTH_SHORT).show()
        }
    }
}
