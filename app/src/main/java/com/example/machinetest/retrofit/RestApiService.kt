package com.example.machinetest.retrofit

//class RestApiService {
//    fun addUser(userData: DataModelGet, onResult: (UserInfo?) -> Unit){
//        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
//        retrofit.addUser(userData).enqueue(
//            object : Callback<UserInfo> {
//                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
//                    onResult(null)
//                }
//                override fun onResponse( call: Call<UserInfo>, response: Response<UserInfo>) {
//                    val addedUser = response.body()
//                    onResult(addedUser)
//                }
//            }
//        )
//    }
//}