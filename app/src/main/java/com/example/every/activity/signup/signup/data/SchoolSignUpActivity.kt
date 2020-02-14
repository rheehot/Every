package com.example.every.activity.signup.signup.data

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.base.BaseSignUpActivity
import com.example.every.activity.signup.SignUpData
import com.example.every.databinding.ActivitySchoolBinding
import com.example.every.viewmodel.signup.signup.data.SchoolSignUpViewModel

class SchoolSignUpActivity : BaseSignUpActivity() {

    lateinit var binding : ActivitySchoolBinding
    lateinit var viewModel : SchoolSignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school)

        binding = DataBindingUtil.setContentView(this@SchoolSignUpActivity, R.layout.activity_school)
        viewModel = ViewModelProviders.of(this@SchoolSignUpActivity).get(SchoolSignUpViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SchoolSignUpActivity

        toolbarInit(binding.toolbar)
        observerViewModel()
    }

    // Activity 새로 보이는 생명 주기
    override fun onResume() {
        super.onResume()
        checkSchoolData()
    }

    fun checkSchoolData(){
        val checkSchool = getSharedPreferences("checkSchool", Context.MODE_PRIVATE)
        viewModel.schoolNameSetting(checkSchool)
    }

    fun observerViewModel(){
        with(viewModel){
            onSearchEvent.observe(this@SchoolSignUpActivity, Observer {
                startActivity(Intent(this@SchoolSignUpActivity, SchoolListSignUpActivity::class.java))
            })
            onEnableTrueEVent.observe(this@SchoolSignUpActivity, Observer {
                binding.schoolName.setSingleLine(true)
                binding.schoolName.ellipsize = TextUtils.TruncateAt.MARQUEE
                binding.schoolName.isSelected = true

                binding.nextButton.isEnabled = true
                binding.nextButton.setBackgroundResource(R.drawable.background_corners_gradient)
            })
            onEnableFalseEvent.observe(this@SchoolSignUpActivity, Observer {
                binding.schoolName.setSingleLine(true)
                binding.schoolName.ellipsize = TextUtils.TruncateAt.MARQUEE
                binding.schoolName.isSelected = true

                binding.nextButton.isEnabled = false
                binding.nextButton.setBackgroundResource(R.color.gray)
            })
            onSuccessEvent.observe(this@SchoolSignUpActivity, Observer {
                SignUpData.signUpDataStudent.school_id = viewModel.schoolId.value.toString()
                startActivity(Intent(applicationContext, SignUpFinishActivity::class.java))
            })
        }
    }
}