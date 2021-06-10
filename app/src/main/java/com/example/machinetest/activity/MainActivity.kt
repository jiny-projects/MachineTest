package com.example.machinetest

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetest.adapters.DataAdpter
import com.example.machinetest.model.DataModel
import com.example.machinetest.model.DataModelGet
import com.example.machinetest.retrofit.ApiClient
import com.example.machinetest.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var progerssProgressDialog: ProgressDialog
    var dataList = ArrayList<DataModelGet>()
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)

        //setting up the adapter
        recyclerView.adapter= DataAdpter(dataList,this)
        recyclerView.layoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        progerssProgressDialog= ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()
        getData()

    }
    fun  PostRecord(view: View){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setView(dialogView)


        val edtTitle = dialogView.findViewById(R.id.edtTitle) as EditText
        val edtDesc = dialogView.findViewById(R.id.edtDesc) as EditText

        dialogBuilder.setTitle("Post Record")
        dialogBuilder.setMessage("Enter data below")
        dialogBuilder.setPositiveButton("Post", DialogInterface.OnClickListener { _, _ ->


            val edtTitle = edtTitle.text.toString()
            val edtDesc = edtDesc.text.toString()
            if(edtTitle.trim()!="" && edtDesc.trim()!=""){

                val retrofit = Retrofit.Builder()
                    .baseUrl("http://fastingconsole.us-east-1.elasticbeanstalk.com/")

                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val retrofitAPI: ApiInterface = retrofit.create(ApiInterface::class.java)



                val modal = DataModel(
                    title =edtTitle.toString(),
                    description = edtDesc.toString())


                val call: Call<DataModel> = retrofitAPI.CreatePost(modal)

                // on below line we are executing our method.
                call.enqueue(object : Callback<DataModel?> {
                    override fun onResponse(
                        call: Call<DataModel?>,
                        response: Response<DataModel?>
                    ) {
                        // this method is called when we get response from our api.
                        Toast.makeText(this@MainActivity, "Data added to API", Toast.LENGTH_SHORT).show()

                        // below line is for hiding our progress bar.


                        // on below line we are setting empty text
                        // to our both edit text.


                        // we are getting response from our body
                        // and passing it to our modal class.
                        val responseFromAPI: DataModel? = response.body()

                        // on below line we are getting our data from modal class and adding it to our string.
                        val responseString =
                            """
                    Response Code : ${response.code()}
                    Name : ${responseFromAPI?.title}
                    Job : ${responseFromAPI?.description}
                    """.trimIndent()
                        Toast.makeText(applicationContext,responseString, Toast.LENGTH_LONG).show()

                        // below line we are setting our
                        // string to our text view.
                        //responseTV.setText(responseString)
                    }

                    override fun onFailure(call: Call<DataModel?>, t: Throwable) {
                        // setting text to our text view when
                        // we get error response from API.
                        var error=t.message.toString()
                        // responseTV.setText("Error found is : " + t.message)
                    }
                })
            }else{
                Toast.makeText(applicationContext,"Title or name or Description cannot be blank", Toast.LENGTH_LONG).show()
            }

        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()



    }
    private fun getData() {
        val call: Call<List<DataModelGet>> = ApiClient.getClient.getDatas()
        call.enqueue(object : Callback<List<DataModelGet>> {

            override fun onResponse(call: Call<List<DataModelGet>>?, response: Response<List<DataModelGet>>?) {
                progerssProgressDialog.dismiss()
                dataList.addAll(response!!.body()!!)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<DataModelGet>>?, t: Throwable?) {
                progerssProgressDialog.dismiss()
            }

        })
    }


}