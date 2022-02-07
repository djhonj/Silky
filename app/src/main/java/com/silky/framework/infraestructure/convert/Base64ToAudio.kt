package com.silky.framework.infraestructure.convert

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream

class Base64ToAudio(private val context: Context) {
    fun isBase64(string: String) : Boolean {
        return try {
            Base64.decode(string, Base64.DEFAULT)
            true
        } catch(ex:java.lang.Exception) {
            false
        }
    }

    fun convert(base64: String, filename: String) {
        try {
            val bytes: ByteArray = Base64.decode(base64, Base64.DEFAULT)
            Log.i("l", DirectoryMusic.getDirectoryPath())

            File(DirectoryMusic.getDirectoryPath(), filename)?.let {file ->
                FileOutputStream(file).use { itOutput ->
                    itOutput.write(bytes)
                    itOutput.close()
                }
            }

//            val ss = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC)
//            Log.i("files", ss!!.absolutePath)
//            Log.i("files", ss!!.name)


            //val file = File(r, "silky")
            //file.mkdirs()

//            context.openFileOutput(filename, Context.MODE_PRIVATE).use {
//                it.write(bytes)
//                it.close()
//            }

//            File("${context.filesDir.absolutePath}/silky", filename)?.let {file ->
//                if (file.exists()) {
//                    FileOutputStream(file).use { itOutput ->
//                        itOutput.write(bytes)
//                        itOutput.close()
//                    }
//                }
//            }

            // Add a specific media item.
            //val resolver = context.contentResolver

// Find all audio files on the primary external storage device.
// On API <= 28, use VOLUME_EXTERNAL instead.
            //val audioCollection = MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            //val audioCollection = MediaStore.Audio.Media.getContentUri(MediaStore.MediaColumns.RELATIVE_PATH.)

// Publish a new song.
//            val newSongDetails = ContentValues().apply {
//                put(MediaStore.Audio.Media.DISPLAY_NAME, "My Song.mp3")
//                put(MediaStore.Audio.Media.DATA, bytes)
//            }

// Keeps a handle to the new song's URI in case we need to modify it
// later.
            //val myFavoriteSongUri = resolver
                //.insert(audioCollection, newSongDetails)

            //val s = context.getExternalFilesDir("silky")

//            context.getExternalFilesDir(null)?.let {
//                if (it.exists()) {
//                    File(it.absolutePath, filename)?.let {file ->
//                        FileOutputStream(file).use { itOutput ->
//                            itOutput.write(bytes)
//                            itOutput.close()
//                        }
//                    }
//
//                    //file.writeBytes(bytes)
//                }
//            }



            //val file = File(ContextCompat.getExternalFilesDirs(), filename)
            //Save Binary file to phone
            //file.createNewFile()
        } catch (ex: Exception) {
            Log.i("exception", ex.message.toString())
        }
    }
}