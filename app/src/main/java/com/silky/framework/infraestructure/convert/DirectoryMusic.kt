package com.silky.framework.infraestructure.convert

import android.os.Environment
import java.io.File

class DirectoryMusic {
    companion object {
        fun getDirectoryPath(): String {
            val publicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)

            if (publicDirectory.exists()) {
                //val silkyFolder = File(publicDirectory, "silky")
                //silkyFolder.mkdirs()
                return publicDirectory.absolutePath
            } else {
                publicDirectory.mkdirs()
                return getDirectoryPath()
            }
        }
    }
}