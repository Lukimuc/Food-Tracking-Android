package com.guttrack.app.util

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

object PhotoFiles {
    fun newCaptureUri(context: Context): Uri {
        val dir = File(context.cacheDir, "photos").apply { mkdirs() }
        val file = File(dir, "meal_${System.currentTimeMillis()}.jpg")
        return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
    }

    /** Copies a URI (possibly from Photo Picker) to app-internal storage for persistence. */
    fun persist(context: Context, uriString: String): String {
        val uri = Uri.parse(uriString)
        if (uri.scheme == "file") return uriString // Already a file

        return try {
            context.contentResolver.openInputStream(uri)?.use { input ->
                val dir = File(context.filesDir, "photos").apply { mkdirs() }
                val file = File(dir, "meal_${System.currentTimeMillis()}.jpg")
                FileOutputStream(file).use { output ->
                    input.copyTo(output)
                }
                Uri.fromFile(file).toString()
            } ?: uriString
        } catch (e: Exception) {
            e.printStackTrace()
            uriString
        }
    }
}
