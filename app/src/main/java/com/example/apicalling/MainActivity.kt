package com.example.apicalling

import android.app.Dialog
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apicalling.ApiClint.Companion.getRetrofit
import com.example.apicalling.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import kotlin.math.log
import android.app.AlertDialog as AlertDialog1

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    var n=0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.usBtn.setOnClickListener {
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.choise_country)
            dialog.show()

            var Business_btn = dialog.findViewById<TextView>(R.id.Business_btn)
            var Sports_btn = dialog.findViewById<TextView>(R.id.Sports_btn)
            var Technology_btn = dialog.findViewById<TextView>(R.id.Technology_btn)
            var Health_btn = dialog.findViewById<TextView>(R.id.Health_btn)

            Business_btn.setOnClickListener {
                getNews("us","business")
                dialog.dismiss()
            }

            Sports_btn.setOnClickListener {
                getNews("us","sports")
                dialog.dismiss()
            }

            Technology_btn.setOnClickListener {
                getNews("us","technology")
                dialog.dismiss()
            }

            Health_btn.setOnClickListener {
                getNews("us","health")
                dialog.dismiss()
            }


        }

        binding.inBtn.setOnClickListener {
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.choise_country)
            dialog.show()

            var Business_btn = dialog.findViewById<TextView>(R.id.Business_btn)
            var Sports_btn = dialog.findViewById<TextView>(R.id.Sports_btn)
            var Technology_btn = dialog.findViewById<TextView>(R.id.Technology_btn)
            var Health_btn = dialog.findViewById<TextView>(R.id.Health_btn)

            Business_btn.setOnClickListener {
                getNews("in","business")
                dialog.dismiss()
            }

            Sports_btn.setOnClickListener {
                getNews("in","sports")
                dialog.dismiss()
            }

            Technology_btn.setOnClickListener {
                getNews("in","technology")
                dialog.dismiss()
            }

            Health_btn.setOnClickListener {
                getNews("in","health")
                dialog.dismiss()
            }

        }

        binding.frBtn.setOnClickListener {
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.choise_country)
            dialog.show()

            var Business_btn = dialog.findViewById<TextView>(R.id.Business_btn)
            var Sports_btn = dialog.findViewById<TextView>(R.id.Sports_btn)
            var Technology_btn = dialog.findViewById<TextView>(R.id.Technology_btn)
            var Health_btn = dialog.findViewById<TextView>(R.id.Health_btn)

            Business_btn.setOnClickListener {
                getNews("fr","business")
                dialog.dismiss()
            }

            Sports_btn.setOnClickListener {
                getNews("fr","sports")
                dialog.dismiss()
            }

            Technology_btn.setOnClickListener {
                getNews("fr","technology")
                dialog.dismiss()
            }

            Health_btn.setOnClickListener {
                getNews("fr","health")
                dialog.dismiss()
            }

        }

        binding.auBtn.setOnClickListener {
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.choise_country)
            dialog.show()

            var Business_btn = dialog.findViewById<TextView>(R.id.Business_btn)
            var Sports_btn = dialog.findViewById<TextView>(R.id.Sports_btn)
            var Technology_btn = dialog.findViewById<TextView>(R.id.Technology_btn)
            var Health_btn = dialog.findViewById<TextView>(R.id.Health_btn)

            Business_btn.setOnClickListener {
                getNews("au","business")
                dialog.dismiss()
            }

            Sports_btn.setOnClickListener {
                getNews("au","sports")
                dialog.dismiss()
            }

            Technology_btn.setOnClickListener {
                getNews("au","technology")
                dialog.dismiss()
            }

            Health_btn.setOnClickListener {
                getNews("au","health")
                dialog.dismiss()
            }

        }

        binding.internetBtn.setOnClickListener {
            var check  = checkInternate()

            showDialog(check)
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkInternate(): Boolean {
        var connectActivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectActivityManager.activeNetwork != null && connectActivityManager.activeNetworkInfo!!.isConnected)
        {
            Log.e("TAG", "checkInternate: TRUE", )
            return true
        }
        else {
            Log.e("TAG", "checkInternate: FALSE", )
            return false
        }
    }

    private fun internetDialog(): android.app.AlertDialog {
        var alertDialog =  android.app.AlertDialog.Builder(this).setCancelable(false).setTitle("Internet Off")
            .setMessage("Please On Internet").setPositiveButton("Retry",object:DialogInterface.OnClickListener{
                @RequiresApi(Build.VERSION_CODES.M)
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    var check  = checkInternate()
                    showDialog(check)
                }
            }).create()
        return alertDialog
    }

    fun showDialog(check : Boolean){
        var alertDialog :AlertDialog1 = internetDialog()
        if (check == false)
        {
            alertDialog.show()
        }
        else
        {
            alertDialog.dismiss()
        }
    }

    fun  getNews(country  :String,catagory:String){
        var  apiInterface = getRetrofit().create(ApiInterface::class.java)
//        apiInterface.getNews(country,catagory,"95212db04ccb4eeab6ed771b8b531a92")

        apiInterface.getNews(country,catagory,"95212db04ccb4eeab6ed771b8b531a92").enqueue(object:Callback<NewsModel>{
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                Log.e("TAG", "onResponse: ${response.body()}", )

                var list  = response.body()
                setUpRv(list?.articles as List<ArticlesItem>?)
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}", )
            }
        })
    }

    fun setUpRv(l1 : List<ArticlesItem>?) {

        var adapter = newsAdapter(this,l1)
        var layoutManager = LinearLayoutManager(this)
        binding.rvview.adapter = adapter
        binding.rvview.layoutManager = layoutManager
    }

    override fun onStart() {
        super.onStart()
        getNews("in","business")
    }

}