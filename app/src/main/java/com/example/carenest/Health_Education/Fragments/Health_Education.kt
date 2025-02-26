package com.example.carenest.Health_Education.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carenest.Health_Education.Adapter_Health_Education
import com.example.carenest.Health_Education.ViewModel.DataClass_Health_Education
import com.example.carenest.Health_Education.ViewModel.Health_Education_ViewModel
import com.example.carenest.R
import com.example.carenest.databinding.FragmentHealthEducationBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class Health_Education : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter_Health_Education
    private lateinit var dataList: ArrayList<DataClass_Health_Education>

    private lateinit var Health_Education_View_Model1: Health_Education_ViewModel

    lateinit var Blogname: Array<String>
    lateinit var BlogDiscription: Array<String>
    lateinit var BlogBenefits: Array<String>
    lateinit var BlogEligibility: Array<String>
    lateinit var BlogImagelink: Array<String>
    lateinit var BlogvideoLink: Array<String>
    //lateinit var Img: Array<Int>


    val db = Firebase.firestore
    private  var _binding: FragmentHealthEducationBinding? = null
    private val binding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_health__education, container, false)
        Health_Education_View_Model1 = ViewModelProvider(requireActivity()).get(
            Health_Education_ViewModel::class.java)

        _binding = FragmentHealthEducationBinding.inflate(inflater, container, false)

//        Government_Schemes_View_Model = ViewModelProvider(requireActivity()).get(Government_Schemes_View_Model::class.java)



        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recyclerView_government_Scheme)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        // dataList = arrayListOf<GovernmentSchemes>()
//        recyclerView.adapter = AdapterforGovernmentSchemes(dataList)
        adapter = Adapter_Health_Education(dataList)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : Adapter_Health_Education.onItemClickListener {
            override fun onItemClick(position: Int) {

                Log.d("TAG", "onItemClick: $position")
                Toast.makeText(this@Health_Education.requireActivity(), "You clicked on item no. $position", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun dataInitialize(){

        dataList = arrayListOf<DataClass_Health_Education>()

//        Img = arrayOf(
//            R.drawable.icons_name,
//            R.drawable.baseline_account_circle_24,
//            R.drawable.baseline_home_24,
//        )

//        val docRef = db.collection("cities").document("BJ")
//        docRef.get().addOnSuccessListener { documentSnapshot ->
//            val city = documentSnapshot.toObject<City>()

        var count : Int = 0

        val collectionRef = db.collection("HealthEducation")

        collectionRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                count = task.result?.size() ?: 0
                println("Total documents: $count")
                Log.d("Tag","$count")
                adds(count)
            } else {
                println("Error fetching documents: ${task.exception}")
            }
        }

        var b = "hi i a,"
        Blogname = arrayOf(


        )
        BlogDiscription = arrayOf(

        )
        BlogBenefits = arrayOf(


        )
        BlogEligibility = arrayOf(


        )
        BlogImagelink = arrayOf(


        )
        BlogvideoLink = arrayOf(

        )
//
//
//
        for (i in Blogname.indices){

            val dataClass = DataClass_Health_Education(Blogname[i],BlogBenefits[i])
            dataList.add(dataClass)
        }

    }

    private fun adds(count: Int) {

        var a :Int= 1;

        var n = ""
        while(a < count+1) {
            val docRef = db.collection("HealthEducation").document("${a}")
            docRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val data =
                        documentSnapshot.toObject<DataClass_Health_Education>(
                            DataClass_Health_Education::class.java)
                    // Access data fields (e.g., val item = data["item"])

                    if (data != null) {
                        Log.d("TAG", "DocumentSnapshot data: ${data.BlogName}")

                        Blogname = Blogname + data.BlogName.toString()
                        BlogDiscription = BlogDiscription + data.BlogDiscription.toString()
                        BlogBenefits = BlogBenefits + data.BlogDetails
//                        BlogBenefits = BlogBenefits + data.BlogBenefits.toString()
//                        BlogEligibility = BlogEligibility + data.BlogEligibility.toString()
                        BlogvideoLink = BlogvideoLink + data.BlogvideoLink.toString()
                        BlogImagelink = BlogImagelink + data.BlogImageLink.toString()

                        val dataclass = DataClass_Health_Education(data.BlogName,data.BlogDiscription,data.BlogDetails,data.BlogImageLink,data.BlogvideoLink)
                        dataList.add(dataclass)

                        // add1(data.SchemesName,data.SchemesDiscription)
                        add1(dataList)

                        n = "Done"
                        a = a + 1
                    } else {
                        // Document does not exist
                        Log.d("TAG", "DocumentSnapshot does not exist")
                        a = 0
                    }


                }
                else{
                    Log.d("Tag" , "Failed")
                    a = 0
                }




            }.addOnFailureListener{
                a = 0
            }

            a = a+1



            Log.d("TAG", "dataInitialize: $a")

            if(a == 7){
                //add1(dataList)
            }
        }

    }

    fun add1(datalist1: ArrayList<DataClass_Health_Education> ) {

        Log.d("TAG", "add1: $datalist1")
        adapter = Adapter_Health_Education(datalist1)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : Adapter_Health_Education.onItemClickListener {

            override fun onItemClick(position: Int) {

                Log.d("TAG", "onItemClick: $position")
                //Toast.makeText(this@Government_Schemes.requireActivity(), "You clicked on item no. $position", Toast.LENGTH_SHORT).show()
                //val transition: FragmentTransaction = childFragmentManager.beginTransaction()
                //.replace(R.id.fragment_container, FragmentHomeBinding)
                //  transition.commit()
//                val homeFragment = Home()
//                transition.replace(R.id.fragment_container, homeFragment)
//                transition.commit()

//                FarmerMainActivity.replaceFragment(Home())Home

                Health_Education_View_Model1.setData1(dataList[position].BlogName)
                Health_Education_View_Model1.setData2(dataList[position].BlogDiscription)
                Health_Education_View_Model1.setData3(dataList[position].BlogDetails)
                Health_Education_View_Model1.setData5(dataList[position].BlogvideoLink)
                Health_Education_View_Model1.setData6(dataList[position].BlogImageLink)

                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace(
                        R.id.fragment_container,
                        Health_Education_Discription::class.java,
                        null
                    ) // Replace with your FragmentContainerView's ID and the new Fragment class
                    addToBackStack(null)

                }
            }


        })


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

//                parentFragmentManager.commit {
//                    setReorderingAllowed(true)
//                    replace(
//                        R.id.fragment_container,
//                        Home::class.java,
//                        null
//                    ) // Replace with your FragmentContainerView's ID and the new Fragment class
//                    addToBackStack(null)
//
//                }

            }
        })
    }


}