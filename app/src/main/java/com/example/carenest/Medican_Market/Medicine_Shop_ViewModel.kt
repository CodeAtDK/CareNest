package com.example.carenest.Medican_Market

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Medicine_Shop_ViewModel : ViewModel(){

    private var Market_Type = MutableLiveData<String>()

    private var product_name = MutableLiveData<String>()
    private var product_price = MutableLiveData<Int>()
    private var product_Image = MutableLiveData<String>()
    private var product_description = MutableLiveData<String>()



    fun setData(markettype: String){

        Market_Type.value = markettype
    }

    fun setName(name: String){

        product_name.value = name
    }
    fun setImg(Img: String){

        product_Image.value = Img
    }fun setPrice(price: Int){

        product_price.value = price
    }fun setDis(Discr: String){

        product_description.value = Discr
    }

    fun getData(): MutableLiveData<String>{

        return Market_Type
    }
    fun getName(): MutableLiveData<String>{

        return product_name
    }
    fun getPrice(): MutableLiveData<Int>{

        return product_price
    }
    fun getImg(): MutableLiveData<String>{

        return product_Image
    }
    fun getDisc(): MutableLiveData<String>{

        return product_description
    }
}