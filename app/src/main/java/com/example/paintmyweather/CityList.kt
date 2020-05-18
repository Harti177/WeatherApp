package com.example.paintmyweather

import android.content.Context
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.lang.Exception

public class CityList{
    companion object cityList {

        var cityList: MutableList<String> = mutableListOf()

        fun checkCityListFile(context: Context): MutableList<String>{
            val cityListFromFile = mutableListOf<String>()
            val cityListFileName = "cityListFileName"
            var fileInputStream: FileInputStream? = null

            try{

                fileInputStream = context.openFileInput(cityListFileName)
                var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                var text: String? = ""
                while ({ text = bufferedReader.readLine(); text }() != null) {
                    val city  = text
                    if(city != null){
                        cityListFromFile.add(city);
                    }
                }

                return cityListFromFile

            }catch(e: Exception){
                e.printStackTrace()
            }

            return mutableListOf()
        }

        fun addToCityList(newCity:String, context: Context){
            val cityListFileName = "cityListFileName"
            context.openFileOutput(cityListFileName, Context.MODE_PRIVATE).use { cityFile ->

                var cityPresentAlready = false;

                if(cityList.size != 0){
                    cityList.forEach {city ->
                        if(city == newCity) {
                            cityPresentAlready = true
                        }
                        cityFile.write(city.toByteArray())
                        cityFile.write(("\n").toByteArray())
                    }
                }
                
                if(!cityPresentAlready) {
                    Log.d("hari", "hari"+ newCity)
                    cityFile.write(("\n").toByteArray())
                    cityList.add(newCity)
                }
            }
        }
    }
}