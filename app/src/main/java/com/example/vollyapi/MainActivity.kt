package com.example.vollyapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {
    val url = "https://api.github.com/users"
    var userinfoItem =arrayOf<userinfoItem>()
    var userinfo = userinfo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val stringRequest = StringRequest(
            url,
            Response.Listener{
                val gsonBuilder = GsonBuilder()
                val gson = gsonBuilder.create()

                userinfoItem = gson.fromJson(it,Array<userinfoItem>::class.java)
                userinfoItem.forEach {
                    userinfo.add(it)
                }
                val adapter = Adapter(this, userinfo)
                recyclerView.layoutManager  = LinearLayoutManager(this)
                recyclerView.adapter = adapter
            },
            Response.ErrorListener {
                Toast.makeText(this,"Something went wrong" +it.toString(),Toast.LENGTH_SHORT).show()
            }

        )
        val volley = Volley.newRequestQueue(this)
        volley.add(stringRequest)
    }
}