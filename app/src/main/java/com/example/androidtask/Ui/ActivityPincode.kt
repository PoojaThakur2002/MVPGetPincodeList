package com.example.androidtask.Ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtask.Adapter.PincodeAdapter
import com.example.androidtask.Models.Pincode
import com.example.androidtask.R
import com.example.androidtask.databinding.ActivityPincodeBinding
import com.example.androidtask.handlers.PincodeClick
import retrofit2.Call
import retrofit2.Response

class ActivityPincode:AppCompatActivity() ,PincodeClick{

    lateinit var binding: ActivityPincodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPincodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPincodeList.layoutManager = LinearLayoutManager(this)

        binding.btnSubmit.setOnClickListener {
            when{
                binding.etPincode.text.toString().isEmpty()->{
                    Toast.makeText(this@ActivityPincode,"Please Enter Pincode",Toast.LENGTH_SHORT).show()
                }
                binding.etPincode.text.toString().length<6 ->{
                    Toast.makeText(this@ActivityPincode,"Please Enter 6 Digits Pincode",Toast.LENGTH_SHORT).show()
                }
                else->{
                    getList()
                }
            }
        }
    }

    fun getList(){
        binding.progressBar.visibility=View.VISIBLE
        ApiClient.init("https://api.postalpincode.in/pincode/"+binding.etPincode.text.toString()+"/")
        ApiClient.getService().getData().enqueue(object : retrofit2.Callback<List<Pincode>> {
            override fun onResponse(call: Call<List<Pincode>>, response: Response<List<Pincode>>) {
                if (response.isSuccessful && response.body() !=null && response.body()!![0].Status !="Error") {
                    binding.rvPincodeList.visibility=View.VISIBLE
                    binding.progressBar.visibility=View.GONE
                    val adapter =  PincodeAdapter(response.body()!![0].PostOffice,this@ActivityPincode)
                    binding.rvPincodeList.adapter=adapter
                    Log.e("body",response.body().toString())
                }else{
                    binding.progressBar.visibility=View.GONE
                    binding.rvPincodeList.visibility=View.GONE
                    Toast.makeText(this@ActivityPincode,"Data is not available on this Pincode",Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Pincode>>, t: Throwable) {
                binding.progressBar.visibility=View.GONE
                Log.e("api fail", t.toString())
            }
        })
    }


    override fun onClickBrand(
        District: String,
        State: String,
        Country: String,
        Pincode: String,
        circle: String,
        Division: String,
        Region: String,
        Block: String
    ) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Details")
        builder.setMessage(
            "District :- "+District+
                    "\n"+"State :- "+
                    State+"\n"+"" +
                    "Country :- "+Country+
                    "\n"+"Pincode :- "
                    +Pincode+"\n"
                    +"Circle:- "+circle+"\n"
                    +"Division:- "+Division+"\n"
                    +"Region:- "+Region+"\n"
                    +"Block:- "+Block
        )
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("OK"){dialogInterface, which ->

        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}