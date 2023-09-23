package com.example.getapikt

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class MainActivity : AppCompatActivity() {

    lateinit var btn_next:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_next.setOnClickListener {
            startActivity(Intent(this@MainActivity,MainActivity2::class.java))
        }

            getMyData()
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofit = retrofitBuilder.getData()
        retrofit.enqueue(object : Callback<myData?> {
            override fun onResponse(call: Call<myData?>, response: Response<myData?>) {
                if (response.isSuccessful){
//                    val snackbar = Snackbar.make(this@MainActivity,"Mission Success",Snackbar.LENGTH_SHORT)
//                    snackbar.show()
                    Toast.makeText(applicationContext, "Mission success", Toast.LENGTH_SHORT).show()
                    findViewById<ProgressBar>(R.id.progress_circular).isVisible = false
                val responseBody = response.body()
                val stringBuilder = StringBuilder()
                for (mydata in responseBody!!){
                    stringBuilder.append(mydata.id)
                    stringBuilder.append("\n")
                }
                findViewById<TextView>(R.id.txt).text = stringBuilder
            }

                else{
                    Toast.makeText(applicationContext, "Mission Failed", Toast.LENGTH_SHORT).show()
                }

//                val sharedPreferences:SharedPreferences = getSharedPreferences("sharedPref",
//                    MODE_PRIVATE)
//                val editor:Editor = sharedPreferences.edit()


            }

            override fun onFailure(call: Call<myData?>, t: Throwable) {
                Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}