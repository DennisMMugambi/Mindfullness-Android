package com.ekenya.rnd.android.common.Utils

import android.content.Context
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

object Utils {

    private const val PREFS_NAME = "Deliverance app"

    /*****set/store shared preferences  */
    fun setPreference(con: Context, key: String?, value: String?) {
        val preferences = con.getSharedPreferences(PREFS_NAME, 0)
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun setBooleanPreference(con: Context, key: String?, value: Boolean) {
        val preferences = con.getSharedPreferences(PREFS_NAME, 0)
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }



    /*** get/retrieve shared preferences   */
    fun getPreferences(con: Context, key: String?): String? {
        val sharedPreferences = con.getSharedPreferences(PREFS_NAME, 0)
        return sharedPreferences.getString(key, "0")
    }

    /*** get/retrieve shared preferences   */
    fun getBooleanPreferences(con: Context, key: String?): Boolean? {
        val sharedPreferences = con.getSharedPreferences(PREFS_NAME, 0)
        return sharedPreferences.getBoolean(key, true)
    }

    fun clear(con: Context) {
        val sharedPrefs = con.getSharedPreferences(PREFS_NAME, 0)
        val editor = sharedPrefs.edit()
        editor.clear()
        editor.apply()
    }

    fun createMultipartRequestBody(file: File?, name: String, mediaType: String): MultipartBody.Part? {
        val requestFile = file?.asRequestBody(mediaType.toMediaTypeOrNull())
        if (file != null) {
            return requestFile?.let { MultipartBody.Part.createFormData(name, file.name, it) }
        }
        return null
    }

    fun createRequestBody(value: Any): RequestBody = value.toString().toRequestBody("text/plain".toMediaTypeOrNull())


}