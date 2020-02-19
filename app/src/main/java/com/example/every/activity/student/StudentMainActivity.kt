package com.example.every.activity.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.databinding.ActivityStudentMainActivitiyBinding
import com.example.every.fragment.StudentBambooFragment
import com.example.every.fragment.StudentMainFragment
import com.example.every.fragment.StudentMoreFragment
import com.example.every.fragment.StudentScheduleFragment
import com.example.every.viewmodel.student.StudentMainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class StudentMainActivity : AppCompatActivity() {

    lateinit var binding : ActivityStudentMainActivitiyBinding
    lateinit var viewModel : StudentMainViewModel

    val studentMainFragment = StudentMainFragment()
    val studentBambooFragment = StudentBambooFragment()
    val studentScheduleFragment = StudentScheduleFragment()
    val studentMoreFragment = StudentMoreFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_main_activitiy)

        binding = DataBindingUtil.setContentView(this@StudentMainActivity, R.layout.activity_student_main_activitiy)
        viewModel = ViewModelProviders.of(this@StudentMainActivity).get(StudentMainViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@StudentMainActivity

        init()
        selectedEvent()
    }

    private fun init(){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, studentMainFragment).commitAllowingStateLoss()
    }

    private fun selectedEvent(){
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                when(item.itemId){
                    R.id.home -> {
                        fragmentTransaction.replace(R.id.frameLayout, studentMainFragment).commitAllowingStateLoss()
                        return true
                    }
                    R.id.bamboo -> {
                        fragmentTransaction.replace(R.id.frameLayout, studentBambooFragment).commitAllowingStateLoss()
                        return true
                    }
                    R.id.schedule -> {
                        fragmentTransaction.replace(R.id.frameLayout, studentScheduleFragment).commitAllowingStateLoss()
                        return true
                    }
                    R.id.more -> {
                        fragmentTransaction.replace(R.id.frameLayout, studentMoreFragment).commitAllowingStateLoss()
                        return true
                    }
                }
                return false
            }
        })
    }
}