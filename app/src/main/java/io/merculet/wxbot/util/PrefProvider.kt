package io.merculet.wxbot.util

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import io.merculet.core.config.Config
import io.merculet.core.config.Config.PREFERENCE_NAME_SETTINGS
import io.merculet.wxbot.util.ext.getProtectedSharedPreferences

// PrefProvider shares the preferences using content provider model.
class PrefProvider : ContentProvider() {

    private val preferences: MutableMap<String, SharedPreferences> = mutableMapOf()

    private fun getPreferenceType(value: Any?): String {
        return when (value) {
            null -> "Null"
            is Int -> "Int"
            is Long -> "Long"
            is Float -> "Float"
            is Boolean -> "Boolean"
            is String -> "String"
            is Set<*> -> "StringSet"
            else -> "${value::class.java}"
        }
    }

    override fun onCreate(): Boolean {
        preferences[PREFERENCE_NAME_SETTINGS] = context.getProtectedSharedPreferences(PREFERENCE_NAME_SETTINGS, MODE_PRIVATE)

        return true
    }

    override fun getType(uri: Uri?): String? = null

    override fun insert(uri: Uri?, values: ContentValues?): Uri {
        throw UnsupportedOperationException("Wechat Magician PrefProvider: Cannot modify read-only preferences!")
    }

    override fun query(uri: Uri?, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        if (uri == null) {
            return null
        }
        val segments = uri.pathSegments
        if (segments.size != 1) {
            return null
        }
        val preference = preferences[segments[0]] ?: return null
        return MatrixCursor(arrayOf(Config.PROVIDER_PREF_KEY, Config.PROVIDER_PREF_VALUE, Config.PROVIDER_PREF_TYPE)).apply {
            preference.all.forEach { entry ->
                val type = getPreferenceType(entry.value)
                addRow(arrayOf(entry.key, entry.value, type))
            }
        }
    }

    override fun update(uri: Uri?, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        throw UnsupportedOperationException("Wechat Magician PrefProvider: Cannot modify read-only preferences!")
    }

    override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<out String>?): Int {
        throw UnsupportedOperationException("Wechat Magician PrefProvider: Cannot modify read-only preferences!")
    }
}