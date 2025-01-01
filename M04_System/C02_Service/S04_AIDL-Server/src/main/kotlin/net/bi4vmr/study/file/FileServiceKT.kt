package net.bi4vmr.study.file

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.ParcelFileDescriptor
import android.util.Log
import net.bi4vmr.aidl.IFileService
import net.bi4vmr.study.util.IOUtil
import java.io.BufferedWriter
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.OutputStreamWriter

/**
 * 示例服务：异常传递测试服务。
 *
 * @author bi4vmr@outlook.com
 * @version 1.0.0
 */
class FileServiceKT : Service() {

    companion object {
        private val TAG: String = "TestApp-Server-${FileServiceKT::class.simpleName}"
    }

    override fun onBind(intent: Intent): IBinder {
        return ServiceImpl()
    }

    /**
     * AIDL接口的实现类。
     */
    private inner class ServiceImpl : IFileService.Stub() {

        override fun upload(pfd: ParcelFileDescriptor) {
            // 从PFD获取文件描述符
            val fd = pfd.fileDescriptor
            runCatching {
                // 从输入流读取数据
                FileInputStream(fd).use {
                    val content = IOUtil.readFile(it)
                    Log.d(TAG, "获取到内容：$content")
                }
            }.onFailure { e ->
                e.printStackTrace()
            }
        }

        override fun download(pfd: ParcelFileDescriptor) {
            // 将目标文件数据写入客户端提供的文件描述符所指代的文件
            val fd = pfd.fileDescriptor
            runCatching {
                // 向输出流写入数据
                val fos = FileOutputStream(fd)
                BufferedWriter(OutputStreamWriter(fos)).use {
                    it.write("File on server")
                }
            }.onFailure { e ->
                e.printStackTrace()
            }
        }
    }
}
