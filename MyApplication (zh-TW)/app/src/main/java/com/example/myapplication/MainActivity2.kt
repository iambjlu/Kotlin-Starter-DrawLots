package com.example.myapplication


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.Toast.makeText as makeText



class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2) //設置初始介面
        var nav_view: BottomNavigationView = findViewById(R.id.nav_view)//底部選單元件
        nav_view.selectedItemId = R.id.item2  //設置底欄的選中項目
        setTitle("哈囉World!")        //預設標題


        //點擊底欄會發生的事情
        nav_view.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { //選取底欄項目發生的事情
                when (it.itemId) {                          //  當選取東西的ID
                    R.id.item1 -> {
                        setTitle("抽籤")
                        startActivity(Intent(this, MainActivity::class.java)) //切換介面配置
                        var toast = makeText(this, "抽籤", Toast.LENGTH_SHORT) //產生吐司訊息
                        toast.show() //顯示吐司
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.item2 -> {
                        var toast = makeText(this, "哈囉", Toast.LENGTH_SHORT) //產生吐司訊息
                        toast.show() //顯示吐司
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            })

        //重複點擊底欄會發生的事情
        nav_view.setOnNavigationItemReselectedListener(
            BottomNavigationView.OnNavigationItemReselectedListener {
                when (it.itemId) {
                    R.id.item1 -> {
                        setTitle("抽籤")
                        startActivity(Intent(this, MainActivity::class.java)) //切換介面配置
                        var toast = makeText(this, "抽籤", Toast.LENGTH_SHORT) //產生吐司訊息
                        toast.show() //顯示吐司
                        return@OnNavigationItemReselectedListener
                    }
                    R.id.item2 -> {
                        var toast = makeText(this, "哈囉", Toast.LENGTH_SHORT) //產生吐司訊息
                        toast.show() //顯示吐司
                        return@OnNavigationItemReselectedListener
                    }
                }
            })


    }
}



