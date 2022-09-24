package com.room_db_nandhakumar.taskroomdb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.room_db_nandhakumar.taskroomdb.R
import com.room_db_nandhakumar.taskroomdb.adapter.ParentAdapter
import com.room_db_nandhakumar.taskroomdb.databinding.ActivityHomeBinding
import com.room_db_nandhakumar.taskroomdb.response.ProfileResponse

class HomeActivity : AppCompatActivity() {
    private lateinit var activityHomeBinding: ActivityHomeBinding

    private var profileArray = ArrayList<ProfileResponse>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_home)

        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        var mimiheadline = ArrayList<String>()
        mimiheadline.add("Popular")
        mimiheadline.add("Treading")
        mimiheadline.add("Today")

        var content = ArrayList<String>()
        content.add("Favourite")
        content.add("Download")

        var preferences = ArrayList<String>()
        preferences.add("Language")
        preferences.add("DarkMode")
        preferences.add("Only Download via wifi")


        profileArray.add(ProfileResponse("Mini Headline",mimiheadline))
        profileArray.add(ProfileResponse("Content",content))
        profileArray.add(ProfileResponse("Preferences",preferences))


        activityHomeBinding.parentRecyclerview.layoutManager = LinearLayoutManager(this,  RecyclerView.VERTICAL, false)
        activityHomeBinding.parentRecyclerview.setHasFixedSize(true)
        var parentAdapter = ParentAdapter(applicationContext,profileArray)
        activityHomeBinding.parentRecyclerview.adapter = parentAdapter


    }
}