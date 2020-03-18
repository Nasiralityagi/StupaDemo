package com.android.stupa.store

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.android.stupa.Stupa

private const val PREFS_FILENAME = "stupaPref"


object PrefStoreManager {
    //public const val SIGN_UP = "signup"
    //public const val LOGIN = "login"
    //Shared Preference field used to save and retrieve JSON string
    lateinit var prefs: SharedPreferences

    /**
     * Call this first before retrieving or saving object.
     *
     * @param application Instance of application class
     */
    fun with(application: Stupa) {
        prefs = application.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
    }

    /**
     * Saves object into the Preferences.
     *
     * @param `object` Object of model class (of type [T]) to save
     * @param key Key with which Shared preferences to
     **/
    fun <T> put(`object`: T, key: String) {
        //Convert object to JSON String.
        val jsonString = GsonBuilder().create().toJson(`object`)
        //Save that String in SharedPreferences
        prefs.edit().putString(key, jsonString).apply()
    }

    fun clear(){
        prefs.edit().clear().apply()
    }

    fun readAll(): Map<String, *> {
        return prefs.all
    }

    /**
     * Used to retrieve object from the Preferences.
     *
     * @param key Shared Preference key with which object was saved.
     **/
    inline fun <reified T> get(key: String): T? {
        //We read JSON String which was saved.
        val value = prefs.getString(key, null)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type “T” is used to cast.
        return GsonBuilder().create().fromJson(value, T::class.java)
    }

}