package com.example.surfit.utils

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns

object FileUtil {
    fun getFileName(uri: Uri?, context: Context): String {
        if (uri?.scheme.equals("content")) {
            context.contentResolver.query(
                uri ?: Uri.EMPTY, null, null, null, null
            )?.use { cursor ->
                if (cursor.moveToFirst()) {
                    return cursor.getString(
                        cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME)
                    )
                }
            }
        }
        return uri?.lastPathSegment?.substringAfter("/") ?: ""
    }
}