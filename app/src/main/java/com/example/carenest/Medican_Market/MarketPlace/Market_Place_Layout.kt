package com.example.carenest.Medican_Market.MarketPlace

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carenest.Medican_Market.MarketDiscription.Product_Details
import com.example.carenest.Medican_Market.Medican_Product
import com.example.carenest.Medican_Market.Medican_Shop
import com.example.carenest.Medican_Market.Medicine_Shop_ViewModel
import com.example.carenest.R
import com.example.carenest.databinding.FragmentMarketPlaceLayoutBinding
import com.example.carenest.databinding.FragmentMedicanShopBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlin.toString


class Market_Place_Layout : Fragment() {

    private var _binding: FragmentMarketPlaceLayoutBinding? = null
    private val binding get() = _binding!!

    private lateinit var Farmer_Market_Place_ViewModel : Medicine_Shop_ViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter_Market_Place
    private lateinit var dataList_Products: ArrayList<Medican_Product>

    lateinit var product_Name: Array<String>
    lateinit var Product_Price: Array<Int>
    lateinit var Product_Image: Array<String>
    lateinit var Product_Discription: Array<String>

    var db = Firebase.firestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_market__place__layout, container, false)

        _binding = FragmentMarketPlaceLayoutBinding.inflate(inflater,container,false)
        Farmer_Market_Place_ViewModel = ViewModelProvider(requireActivity()).get(Medicine_Shop_ViewModel::class.java)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataInitialize()


        Farmer_Market_Place_ViewModel.getData().observe(viewLifecycleOwner){
            binding.MarketId.setText(it.toString())
            count(it.toString())
        }





        val layoutManager = GridLayoutManager(this@Market_Place_Layout.requireActivity(), 2)
        recyclerView = view.findViewById(R.id.recyclerView_Farmer_Product)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        // dataList = arrayListOf<GovernmentSchemes>()
//        recyclerView.adapter = AdapterforGovernmentSchemes(dataList)
        adapter = Adapter_Market_Place(dataList_Products)
        recyclerView.adapter = adapter


        adapter.setOnItemClickListener(object : Adapter_Market_Place.onItemClickListener_Market_Place {

            override fun onItemClick(position: Int) {

                Log.d("TAG", "onItemClick: $position")
                Toast.makeText(this@Market_Place_Layout.requireActivity(), "You clicked on item no. $position", Toast.LENGTH_SHORT).show()
                //val transition: FragmentTransaction = childFragmentManager.beginTransaction()
                //.replace(R.id.fragment_container, FragmentHomeBinding)
                //  transition.commit()
//                val homeFragment = Home()
//                transition.replace(R.id.fragment_container, homeFragment)
//                transition.commit()

//                FarmerMainActivity.replaceFragment(Home())Home

//                Farmer_Market_Place_ViewModel.setData3(datalist1[position].product_img_Id_Url)
//                Farmer_Market_Place_ViewModel.setData1(datalist1[position].product_name)
//                Farmer_Market_Place_ViewModel.setData2(datalist1[position].product_Discreeption)
//                Farmer_Market_Place_ViewModel.setData4(datalist1[position].product_price)

//                parentFragmentManager.commit {
//                    setReorderingAllowed(true)
//                    replace(
//                        R.id.fragment_container,
//                        New_Crops_Discriptions::class.java,
//                        null
//                    ) // Replace with your FragmentContainerView's ID and the new Fragment class
//                    addToBackStack(null)
//
//                }
            }


        })
    }

    fun add3(count: Int, n : String){
        var a :Int= 1;

        Log.d("TAG", "add3: $n")
        Log.d("TAG", "add3: $n"+"Farmer_Market")
        Log.d("TAG", "add3: Seed MarketFarmer_Market")
        Log.d("TAG", "add3: $count")

        while(a < count+1) {

            val docRef = db.collection("${n}" +"Farmer_Market").document("${a}")
            docRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val data =
                        documentSnapshot.toObject<Medican_Product>(
                            Medican_Product::class.java
                        )
                    // Access data fields (e.g., val item = data["item"])

                    if (data != null) {
                        Log.d("TAG", "DocumentSnapshot data: ${data.product_name}")

                        product_Name = product_Name + data.product_name.toString()
                        Product_Discription = Product_Discription + data.product_Discreeption.toString()
                        Product_Image = Product_Image + data.product_img_Id_Url.toString()


                        Log.d("TAG", "DocumentSnapshot data uri: ${data.product_img_Id_Url}")

                        Log.d("TAG", "DocumentSnapshot data name: ${data.product_name}")
                        Log.d(
                            "TAG",
                            "DocumentSnapshot data discri: ${data.product_Discreeption}"
                        )
                        Log.d("TAG", "DocumentSnapshot data deta: ${data.product_price}")

                        val dataclass = Medican_Product(
                            data.product_img_Id_Url,
                            data.product_name,
                            data.product_price,
                            data.product_quantity,
                            data.product_Discreeption,
                            data.product_id,
                        )
                        dataList_Products.add(dataclass)

                        // add1(data.SchemesName,data.SchemesDiscription)
                        add1(dataList_Products)

                    } else {
                        // Document does not exist
                        Log.d("TAG", "DocumentSnapshot does not exist")
                    }
                } else {
                    Log.d("Tag", "Failed")
                }
            }.addOnFailureListener {
                Log.d("Tag", "Failed")
            }
            a = a + 1
            Log.d("TAG", "add3: $a")

            if (a == 7) {
                //add1(dataList)
            }
        }

    }

    private fun dataInitialize(){
        dataList_Products = arrayListOf<Medican_Product>()




        product_Name = arrayOf(


        )
        Product_Image = arrayOf(


        )
        Product_Discription = arrayOf(





        )
        Product_Price = arrayOf(



        )
        for(i in product_Name.indices){

            val dataclass = Medican_Product(Product_Image[i],product_Name[i]
                , Product_Price[i],null,Product_Discription[i],null)
            dataList_Products.add(dataclass)

        }
    }

    fun add1(datalist2: ArrayList<Medican_Product> ) {

        Log.d("TAG", "add1: $datalist2")
        adapter = Adapter_Market_Place(datalist2)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : Adapter_Market_Place.onItemClickListener_Market_Place {

            override fun onItemClick(position: Int) {

                Log.d("TAG", "onItemClick: $position")
                //Toast.makeText(this@Market_Place_Layout.requireActivity(), "You clicked on item no. $position", Toast.LENGTH_SHORT).show()
                //val transition: FragmentTransaction = childFragmentManager.beginTransaction()
                //.replace(R.id.fragment_container, FragmentHomeBinding)
                //  transition.commit()
//                val homeFragment = Home()
//                transition.replace(R.id.fragment_container, homeFragment)
//                transition.commit()

//                FarmerMainActivity.replaceFragment(Home())Home

                Farmer_Market_Place_ViewModel.setImg(datalist2[position].product_img_Id_Url.toString())
                Farmer_Market_Place_ViewModel.setName(datalist2[position].product_name.toString())
                Farmer_Market_Place_ViewModel.setDis(datalist2[position].product_Discreeption.toString())
                datalist2[position].product_price?.let { Farmer_Market_Place_ViewModel.setPrice(it.toInt()) }


                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace(
                        R.id.fragment_container,
                        Product_Details::class.java,
                        null
                    ) // Replace with your FragmentContainerView's ID and the new Fragment class
                    addToBackStack(null)

                }
            }


        })

    }

    fun count (n: String){

        var count : Int = 0
        Log.d("Tag idk ", "$n")

        val collectionRef = db.collection("${n}Farmer_Market")

        collectionRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                count = task.result?.size() ?: 0
                println("Total documents: $count")
                Log.d("Tag", "$count")
//                dataInitialize(count)
                add3(count, n)

            } else {
                println("Error fetching documents: ${task.exception}")
            }
        }

        dataInitialize()


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this,object : OnBackPressedCallback(true){

            override fun handleOnBackPressed() {

//                if(parentFragmentManager.findFragmentById(R.id.fragment_container) is Home) {
                Log.d("Tag","${parentFragmentManager.findFragmentById(R.id.fragment_container)}")
//
//
//                }

                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace(
                        R.id.fragment_container,
                        Medican_Shop::class.java,
                        null
                    ) // Replace with your FragmentContainerView's ID and the new Fragment class
                    addToBackStack(null)

                }





            }
        })
    }


}