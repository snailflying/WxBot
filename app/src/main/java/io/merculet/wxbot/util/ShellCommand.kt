package io.merculet.wxbot.util

import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.util.regex.Pattern

/**
 * Created by WZ on 2018-01-26.
 */
object ShellCommand {

    /**
     * Shell命令
     * 每次打开APP准备读取数据库之前都需要执行一次该命令。先通过这个命令，使得当前app获取到root权限，然后再通过chmod命令来修改微信的data目录的读写权限，因为我们需要操作读取微信的数据库文件，所以必须要有微信文件的操作权限。
     *
     * @param command 命令语句
     */
    fun shellCommand(command: String): Boolean {
        var process: Process? = null
        var os: DataOutputStream? = null
        try {
            process = Runtime.getRuntime().exec("su")
            os = DataOutputStream(process?.outputStream)
            os.writeBytes(command + "\n")
            os.writeBytes("exit\n")
            os.flush()
            process.waitFor()
        } catch (e: Exception) {
            throw Exception(e.message)
        } finally {
            try {
                os?.close()
                process!!.destroy()
            } catch (e: Exception) {
                throw Exception(e.message)
            }
        }
        return true
    }

    /** * 执行linux指令 * * @param paramString  */
    fun execRootCmd(paramString: String) {
        try {
            val localProcess = Runtime.getRuntime().exec("su")
            var localObject: Any = localProcess.outputStream
            val localDataOutputStream = DataOutputStream(localObject as OutputStream)
            localObject = paramString + "\n"
            localDataOutputStream.writeBytes(localObject)
            localDataOutputStream.flush()
            localDataOutputStream.writeBytes("exit\n")
            localDataOutputStream.flush()
            localProcess.waitFor()
        } catch (localException: Exception) {
            localException.printStackTrace()
        }

    }

    /**
     * 判断APP是否拥有Root权限
     *
     * @param pkgCodePath
     * @return
     */
    fun checkRoot(pkgCodePath: String): Boolean {
        var process: Process? = null
        try {
            val cmd = "ls -l " + pkgCodePath
            process = Runtime.getRuntime().exec(cmd)

            val mReader = BufferedReader(InputStreamReader(process!!.inputStream))
            val mRespBuff = StringBuffer()
            val buff = CharArray(1024 * 10)
            var ch = mReader.read(buff)
            while (ch != -1) {
                mRespBuff.append(buff, 0, ch)
                ch = mReader.read(buff)
            }
            mReader.close()

            val regx = "-rwxr[\\s\\S]+"//root后权限的表达式
            return Pattern.matches(regx, mRespBuff.toString())
        } catch (e: Exception) {
            throw Exception(e.message)
        } finally {
            try {
                process!!.destroy()
            } catch (e: Exception) {
                throw Exception(e.message)
            }
        }
    }
}