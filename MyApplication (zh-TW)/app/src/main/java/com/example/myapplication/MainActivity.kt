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
import android.app.Activity
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.math.abs
import android.widget.Toast.makeText as makeText



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //設置初始介面
        val rollButton: Button = findViewById(R.id.roll)  //找出按鈕元件
        var startn: EditText = findViewById(R.id.startnum) //找出開始數字元件
        var endn: EditText = findViewById(R.id.endnum)     //找出結束數字元件
        var nav_view: BottomNavigationView = findViewById(R.id.nav_view)//底部選單元件
        nav_view.selectedItemId = R.id.item1  //設置底欄的選中項目
        setTitle("抽籤")        //預設標題
        var noticount:Int=0


        //點擊底欄會發生的事情
        nav_view.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { //選取底欄項目發生的事情
                when (it.itemId) {                          //  當(東西的ID)
                    R.id.item1 -> {
                        var toast = makeText(this, "抽籤", Toast.LENGTH_SHORT) //產生訊息
                        toast.show() //顯示訊息
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.item2 -> {
                        setTitle("哈囉 World!")
                        startActivity(Intent(this, MainActivity2::class.java)) //切換介面配置
                        var toast = makeText(this, "哈囉", Toast.LENGTH_SHORT) //產生訊息
                        toast.show() //顯示訊息
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
                        var toast = makeText(this, "抽籤", Toast.LENGTH_SHORT) //產生訊息
                        toast.show() //顯示訊息
                        return@OnNavigationItemReselectedListener
                    }
                    R.id.item2 -> {
                        setTitle("哈囉 World!")
                        startActivity(Intent(this, MainActivity2::class.java)) //切換介面配置
                        var toast = makeText(this, "哈囉", Toast.LENGTH_SHORT) //產生訊息
                        toast.show() //顯示訊息
                        return@OnNavigationItemReselectedListener
                    }
                }
            })


        //監聽如果按鈕被按下
        rollButton.setOnClickListener {
            var start: Long = startn.text.toString().toLong()    //開始數字轉換為整數 (要先變字串才能變整數，不然會出錯)
            var end: Long = endn.text.toString().toLong()      //結束數字轉換為整數 (要先變字串才能變整數，不然會出錯)
            //篩選確認不會出錯才抽籤
            if (start >= end) {  //如果開始數字大於等於結束數字會出錯
                var toastErr =
                    makeText(this, "開始數字不可大於等於結束數字", Toast.LENGTH_SHORT)    //設定錯誤的訊息
                toastErr.show()       //顯示錯誤的訊息
            } else if (start < 0) {
                var toastErr =
                    makeText(this, "開始數字不可小於0", Toast.LENGTH_SHORT)    //設定錯誤的訊息
                toastErr.show()
            } else {

                //數字正確就進入抽籤程序
                var Draw = (start..end).random()     //抽籤
                var DrawRes = "抽籤結果是： $Draw" //抽籤結果模板供各種印出方式使用

                //訊息顯示抽籤結果
                var toastCorrect =makeText(this, "$DrawRes", Toast.LENGTH_SHORT)    //設定訊息
                toastCorrect.show()       //顯示訊息

                //文字元件顯示抽籤結果
                var hellotext: TextView = findViewById(R.id.hello)   //找出hello文字元件
                hellotext.text = DrawRes    //把hello文字改成骰子結果

                //對話框區
                val diag = AlertDialog.Builder(this)  //產生Builder物件
                diag.setTitle("抽籤結果")  //呼叫setMessage方法設定顯示文字
                diag.setMessage("$DrawRes")  //抽籤結果寫出來
                diag.setPositiveButton("好",null)  //顯示對話框按鈕
                diag.show()  //顯示對話框

                //底欄數字顯示
                if (Draw<1000)
                {nav_view.getOrCreateBadge(R.id.item1).number = Draw.toString().toInt()}
                else{nav_view.getOrCreateBadge(R.id.item1).number = 1000}

                //建立通知的頻道
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val mChannel = NotificationChannel("result", "抽籤結果", NotificationManager.IMPORTANCE_HIGH)
                    mChannel.description = "抽籤結果提示用"
                    val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.createNotificationChannel(mChannel)
                }

                //設定通知內容
                var builder = NotificationCompat.Builder(this, "result")
                    .setSmallIcon(R.drawable.ic_baseline_casino_24)  //通知的圖示
                    .setContentTitle("抽籤結果")  //通知標題
                    .setContentText("$DrawRes")  //通知訊息內文
                    .setPriority(NotificationCompat.PRIORITY_HIGH)  //通知優先權

                //建立訊息通知
                with(NotificationManagerCompat.from(this)) {
                    noticount++  //建立新訊息ID
                    notify(noticount+1, builder.build()) //決定訊息要出現在哪個通知ID中

                }

            }

        }


    }
}



