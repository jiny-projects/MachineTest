package com.example.machinetest.retrofit

import com.example.machinetest.model.DataModel
import com.example.machinetest.model.DataModelGet
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {

    @GET("kotlintest")
    fun getDatas(): Call<List<DataModelGet>>

  ///  @Headers("Content-Type: application/json")
//    @POST("kotlintest")
//    fun addUser(@Body userData: DataModel): Call<DataModel>

//    @POST("kotlintest")
//    fun  //on below line we are creating a method to post our data.
//            createPost(@Body dataModal: DataModel?): Call<DataModel?>?

    @Headers("Content-Type: application/json")
    @POST("kotlintest")
    fun addUser(@Body userData: DataModel): Call<DataModel>
    @Headers("Content-Type: application/json")
    @POST("kotlintest")

    //on below line we are creating a method to post our data.
    fun CreatePost(@Body userData: DataModel): Call<DataModel>
}