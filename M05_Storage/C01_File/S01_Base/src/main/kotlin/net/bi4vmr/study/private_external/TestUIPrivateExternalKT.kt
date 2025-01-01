package net.bi4vmr.study.private_external

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiPrivateExternalBinding
import net.bi4vmr.study.util.IOUtil
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class TestUIPrivateExternalKT : AppCompatActivity() {

    companion object {
        private val TAG = "TestApp-${TestUIPrivateExternal::class.simpleName}"
    }

    private val binding: TestuiPrivateExternalBinding by lazy {
        TestuiPrivateExternalBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btnGetPath.setOnClickListener { testGetPath() }
            btnGetAllPath.setOnClickListener { testGetAllPath() }
            btnWrite.setOnClickListener { testWrite() }
            btnRead.setOnClickListener { testRead() }
        }
    }

    // 获取虚拟存储卡上的私有数据目录路径
    private fun testGetPath() {
        binding.tvLog.append("\n--- 获取虚拟存储卡上的私有数据目录路径 ---\n")
        Log.i(TAG, "--- 获取虚拟存储卡上的私有数据目录路径 ---")

        // 获取所有外置存储器上的私有缓存目录路径
        externalCacheDir?.let {
            val cachePath: String = it.absolutePath
            binding.tvLog.append("\n缓存目录: $cachePath")
            Log.i(TAG, "缓存目录: $cachePath")
        }

        /*
         * 获取所有外置存储器上的私有数据目录路径。
         *
         * 此处的参数用于控制返回的目录类型，"null"表示返回顶层目录，其他值的含义见“访问共享数据”示例。
         */
        getExternalFilesDir(null)?.let {
            val dataPath: String = it.absolutePath
            binding.tvLog.append("\n数据目录: $dataPath")
            Log.i(TAG, "数据目录: $dataPath")
        }
    }

    // 获取所有外置存储器上的私有数据目录路径
    private fun testGetAllPath() {
        binding.tvLog.append("\n--- 获取所有外置存储器上的私有数据目录路径 ---\n")
        Log.i(TAG, "--- 获取所有外置存储器上的私有数据目录路径 ---")

        /*
         * 获取所有外部存储区域上的缓存目录。
         *
         * 包括虚拟存储卡与物理存储卡。
         */
        externalCacheDirs?.forEach {
            binding.tvLog.append("\n缓存目录: $it")
            Log.i(TAG, "缓存目录: $it")
        }

        /*
         * 获取所有外部存储区域上的数据目录。
         *
         * 包括虚拟存储卡与物理存储卡。
         * 此处的参数用于控制返回的目录类型，"null"表示返回顶层目录，其他值的含义见“访问共享数据”示例。
         */
        getExternalFilesDirs(null)?.forEach {
            binding.tvLog.append("\n数据目录: $it")
            Log.i(TAG, "数据目录: $it")
        }
    }

    // 写入文件
    private fun testWrite() {
        binding.tvLog.append("\n--- 写入文件---\n")
        Log.i(TAG, "--- 写入文件 ---")

        var stream: FileOutputStream? = null
        try {
            // 获取虚拟存储卡上的数据目录
            val externalFileDir: File? = applicationContext.getExternalFilesDir(null)
            // 创建目标文件路径
            val dstFile = File(externalFileDir, "test.txt")
            // 使用流的方式写入数据
            stream = FileOutputStream(dstFile)
            stream.write("我能吞下玻璃而不伤身体。".toByteArray(Charsets.UTF_8))
            binding.tvLog.append("\n写入test.txt成功")
            Log.i(TAG, "写入test.txt成功")
        } catch (e: Exception) {
            binding.tvLog.append("\n出现异常！")
            Log.e(TAG, "出现异常！", e)
        } finally {
            IOUtil.closeQuietly(stream)
        }
    }

    // 读取文件
    private fun testRead() {
        binding.tvLog.append("\n--- 读取文件---\n")
        Log.i(TAG, "--- 读取文件 ---")

        var stream: FileInputStream? = null
        try {
            // 获取虚拟存储卡上的数据目录
            val externalFileDir: File? = applicationContext.getExternalFilesDir(null)
            // 创建目标文件路径
            val dstFile = File(externalFileDir, "test.txt")
            // 使用流的方式读取数据
            stream = FileInputStream(dstFile)
            val content: String = IOUtil.readFile(stream)
            binding.tvLog.append("\n读取test.txt的内容：\n$content")
            Log.i(TAG, "读取test.txt的内容： $content")
        } catch (e: Exception) {
            binding.tvLog.append("\n出现异常，请检查文件是否存在！")
            Log.e(TAG, "出现异常，请检查文件是否存在！", e)
        } finally {
            IOUtil.closeQuietly(stream)
        }
    }
}
