package com.example.every.viewmodel.student.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.every.base.BaseViewModel
import com.example.every.base.StudentData
import com.example.every.network.Data
import com.example.every.network.Response
import retrofit2.Call
import retrofit2.Callback

class BambooMoreViewModel : BaseViewModel(){

    val idx = MutableLiveData<Int>()
    val content = MutableLiveData<String>()

    /**
     * deleteReply 대나무숲 게시글 댓글 삭제 API Response
     * status[200] 댓글 삭제 성공
     */

    fun deleteReply(){
        val res : Call<Response<Data>> = netRetrofit.bamboo.deleteBambooReply(StudentData.token.value.toString(), idx.value!!)
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                if(response.code() == 200) onSuccessEvent.call()
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("deleteReply[Error]", "대나무숲 게시물 댓글 삭제 과정에서 서버와 통신이 되지 않습니다.")
           }
        })
    } fun editContent() = onNextEvent.call()
}