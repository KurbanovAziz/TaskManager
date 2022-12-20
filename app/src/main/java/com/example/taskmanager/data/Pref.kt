package com.example.taskmanager.data

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref(private val context: Context) {

    private val  pref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)



    fun setOnBoardingSeen(isSeen:Boolean){
        pref.edit().putBoolean(ON_BOARDING_SING, isSeen).apply()
    }

    fun isOnBoardingSeen():Boolean{
        return pref.getBoolean(ON_BOARDING_SING,false)
    }
    fun saveName(name:String){
        pref.edit().putString(NAME_KEY,name).apply()
    }
    fun getName():String{
        return pref.getString(NAME_KEY,"").toString()
    }
    fun saveImage(image:String){
        pref.edit().putString(IMAGE_KEY,image).apply()
    }
    fun getImage():String{
        return pref.getString(IMAGE_KEY,"").toString()
    }
    fun saveAge(age:String){
        pref.edit().putString(AGE_KEY,age).apply()
    }
    fun getAge():String{
        return pref.getString(AGE_KEY,"").toString()
    }
    fun saveFloor(floor: String){
        pref.edit().putString(FLOOR_KEY,floor).apply()
    }
    fun getFloor():String{
        return pref.getString(FLOOR_KEY,"").toString()
    }

    companion object{

        private const val PREF_NAME = "pref_task_manager"
        private const val NAME_KEY = "name.pref"
        private const val FLOOR_KEY = "floor.pref"
        private const val AGE_KEY = "ag.pref"
        private const val IMAGE_KEY = "image.pref"
        private const val ON_BOARDING_SING = "is_seen"
    }
}