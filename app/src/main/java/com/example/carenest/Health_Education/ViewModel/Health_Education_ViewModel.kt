package com.example.carenest.Health_Education.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Health_Education_ViewModel : ViewModel() {

    private var Blog_Name = MutableLiveData<String>()
    private var Blog_Details = MutableLiveData<String>()
    private var Blog_Discription = MutableLiveData<String>()
    private var BlogImage_link = MutableLiveData<String>()
    private var BlogVideo_Link = MutableLiveData<String>()

    fun setData1(postion:String){

        Blog_Name.value = postion
    }
    fun setData2(postion:String){

        Blog_Discription.value = postion
    }
    fun setData3(postion:String){
        Blog_Details.value = postion
    }

    fun setData5(postion:String){
        BlogImage_link.value = postion

    }
    fun setData6(postion: String){
        BlogVideo_Link.value = postion
    }
    fun getData1(): LiveData<String> {

        return Blog_Name
    }
    fun getData2(): LiveData<String> {

        return Blog_Discription
    }
    fun getData3(): LiveData<String> {
        return Blog_Details
    }
    fun getData5(): LiveData<String> {
        return BlogImage_link
    }
    fun getData6(): LiveData<String>{
        return BlogVideo_Link
    }
}