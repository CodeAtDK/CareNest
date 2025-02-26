package com.example.carenest.Symptom_Checker

import android.os.Bundle
import android.os.Message
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.compose.ui.unit.Constraints
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.commit
import com.example.carenest.HomeFragment
import com.example.carenest.Medican_Market.Medican_Shop
import com.example.carenest.R
import com.example.carenest.databinding.FragmentHomeBinding
import com.example.carenest.databinding.FragmentSymtomCheckerBinding
import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class SymtomChecker : Fragment() {

    private  var _binding: FragmentSymtomCheckerBinding?= null
    private val binding get() = _binding!!
//    private val apiMedic: ApiMedic  // Replace with your actual API key
//        get() = ApiMedic("Es4p2_GMAIL_COM_AUT")

    lateinit var chat: Chat
    var stringBuilder: StringBuilder = java.lang.StringBuilder()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_symtom_checker, container, false)
        _binding = FragmentSymtomCheckerBinding.inflate(inflater, container, false)

        val generativeModel = GenerativeModel(
            // For text-only input, use the gemini-pro model
            modelName = "gemini-pro",
            // Access your API key as a Build Configuration variable (see "Set up your API key" above)
            apiKey = "AIzaSyBas2t-UYqcsdufqijvhh-RoB_NOCIGjEU"
        )
        chat = generativeModel.startChat(
            history = listOf(
                content(role = "user") { text("Hello, I have 2 dogs in my house.") },
                content(role = "model") { text("Great to meet you. What would you like to know?") }
            )
        )

//        stringBuilder.append("I Recommend seeing a pediatrician as soon as possible \n\n")
        stringBuilder.append("This is all your Symptoms list\n\n")


        binding.rbMedicalHistoryYes.setOnClickListener {

            binding.etMedicalHistory.visibility = View.VISIBLE
           // binding.etMedicalHistory.layoutParams as ConstraintLayout.LayoutParams= ViewGroup.LayoutParams.WRAP_CONTENT

            binding.etMedicalHistory.layoutParams = binding.etMedicalHistory.layoutParams.apply {
                height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
        }

        binding.rbMedicalHistoryNo.setOnClickListener {

            binding.etMedicalHistory.visibility = View.INVISIBLE
            // binding.etMedicalHistory.layoutParams as ConstraintLayout.LayoutParams= ViewGroup.LayoutParams.WRAP_CONTENT

            binding.etMedicalHistory.layoutParams = binding.etMedicalHistory.layoutParams.apply {
                height = 0
            }
        }

        binding.sumbmitBtn.setOnClickListener {
            val age = binding.etAge.text.toString()
            val gender = view?.findViewById<RadioButton>(binding.rgGender.checkedRadioButtonId)?.text.toString()
            val medicalHistory = binding.etMedicalHistory.text.toString()

            val fever = view?.findViewById<RadioButton>(binding.rgFever.checkedRadioButtonId)?.text.toString()
            val cough = view?.findViewById<RadioButton>(binding.rgCough.checkedRadioButtonId)?.text.toString()
            val pain = view?.findViewById<RadioButton>(binding.rgPain.checkedRadioButtonId)?.text.toString()
            val fatigue = view?.findViewById<RadioButton>(binding.rgFatigue.checkedRadioButtonId)?.text.toString()
            val shortnessOfBreath = view?.findViewById<RadioButton>(binding.rgShortnessOfBreath.checkedRadioButtonId)?.text.toString()
            val headache = view?.findViewById<RadioButton>(binding.rgHeadache.checkedRadioButtonId)?.text.toString()
            val nauseaVomiting = view?.findViewById<RadioButton>(binding.rgNauseaVomiting.checkedRadioButtonId)?.text.toString()
            val diarrheaConstipation = view?.findViewById<RadioButton>(binding.rgDiarrheaConstipation.checkedRadioButtonId)?.text.toString()
            val rash = view?.findViewById<RadioButton>(binding.rgRash.checkedRadioButtonId)?.text.toString()
            val dizziness = view?.findViewById<RadioButton>(binding.rgDizziness.checkedRadioButtonId)?.text.toString()
            val chest_Pain = view?.findViewById<RadioButton>(binding.rgChestPain.checkedRadioButtonId)?.text.toString()
            val abdominal_Pain = view?.findViewById<RadioButton>(binding.rgAbdominalPain.checkedRadioButtonId)?.text.toString()
            val joint_Pain = view?.findViewById<RadioButton>(binding.rgJointPain.checkedRadioButtonId)?.text.toString()
            val urinarySystoms = view?.findViewById<RadioButton>(binding.rgUrinarySystoms.checkedRadioButtonId)?.text.toString()
            val skin_Change = view?.findViewById<RadioButton>(binding.rgSkinChange.checkedRadioButtonId)?.text.toString()
            val vision_Pain = view?.findViewById<RadioButton>(binding.rgVisionPain.checkedRadioButtonId)?.text.toString()
            val hearingChanges = view?.findViewById<RadioButton>(binding.rgHearingChanges.checkedRadioButtonId)?.text.toString()

            var message = """
                Age: $age
                Gender: $gender
                Medical History: $medicalHistory
                Fever: $fever
                Cough: $cough
                Pain: $pain
                Fatigue: $fatigue
                Shortness of Breath: $shortnessOfBreath
                Headache: $headache
                Nausea/Vomiting: $nauseaVomiting
                Diarrhea/Constipation: $diarrheaConstipation
                Rash: $rash
                Dizziness:$dizziness
                Chest_Pain:$chest_Pain
                Abdominal_Pain:$abdominal_Pain
                Joint_Pain:$joint_Pain
                UrinarySystoms:$urinarySystoms
                Skin_Change:$skin_Change
                Vision_Pain:$vision_Pain
                HearingChanges:$hearingChanges
                
            """.trimIndent()
            var message1 = message + "I have all this symptoms can you tell is it sever"

            Toast.makeText(this@SymtomChecker.requireActivity(), message, Toast.LENGTH_LONG).show()
            Log.d("Tag", "$message")

            //checkSymptoms(message)

            buttonSendChat(message1,message)
        }

        binding.SymptomsResult.setOnClickListener {

            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(
                    R.id.fragment_container,
                    HomeFragment::class.java,
                    null
                ) // Replace with your FragmentContainerView's ID and the new Fragment class
                addToBackStack(null)

            }
        }




        return binding.root
    }

    public fun buttonSendChat(symptoms : String, message: String){
//        stringBuilder.append(symptoms + "\n\n")
        MainScope().launch {
            var result = chat.sendMessage(symptoms)
            stringBuilder.append(message + "\n\n" + result.text + "\n\n")

            Log.d("Tag","${stringBuilder.toString()}")

            binding.tvSymptomsResult.text = (stringBuilder.toString())
            binding.SymptomsResultOutput.visibility = View.VISIBLE
            binding.SymptomsInput.visibility = View.INVISIBLE


//            binding.editTextOutput.setText(stringBuilder.toString())
//            binding.editTextInput.setText("")
//            binding.button.isEnabled = true
        }
    }

//    private fun checkSymptoms(symptoms: String) {
//        val request = SymptomCheckRequest(symptoms)
//        apiMedic.symptomCheck(request).enqueue(object : Callback<SymptomCheckResponse> {
//            override fun onResponse(call: Call<SymptomCheckResponse>, response: Response<SymptomCheckResponse>) {
//                if (response.isSuccessful) {
//                    val result = response.body()
//                    // Process the response (e.g., display results in the TextView)
//                    resultTextView.text = result?.toString()
//                } else {
//                    Log.e("ApiMedic", "Error: ${response.code()}")
//                    resultTextView.text = "Error: ${response.message()}"
//                }
//            }
//
//            override fun onFailure(call: Call<SymptomCheckResponse>, t: Throwable) {
//                Log.e("ApiMedic", "Network Error: ${t.message}")
//                resultTextView.text = "Network Error"
//            }
//        })
//    }

}


