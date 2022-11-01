 package com.chaseway.retrofitapp

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.gson.Gson
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

 const val BASE_URL = "https://jsonplaceholder.typicode.com/"
 class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getDataForUse()

    }

    private fun getDataForUse() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<DataForUseItem>?> {
            override fun onResponse(call: Call<List<DataForUseItem>?>,
                response: Response<List<DataForUseItem>?>
            ) {
                val responseBody = response.body()!!

                val myStringBuilder = StringBuilder()
                for (myData in responseBody){
                    myStringBuilder.append(myData.id)
                    myStringBuilder.append("\n")
                }
                lateinit var textView: TextView
                textView = findViewById(R.id.text_id)
                textView.text = myStringBuilder
            }

            override fun onFailure(call: Call<List<DataForUseItem>?>, t: Throwable) {
               Log.d("MainActivity", "onFailure:" +t.message)
            }
        })
    }
}