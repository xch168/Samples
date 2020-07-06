package com.github.xch168.samples.room

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.xch168.samples.R

class RoomDemoActivity : AppCompatActivity() {

    private lateinit var firstNameEditor: EditText
    private lateinit var lastNameEditor: EditText

    private val userAdapter = UserAdapter();

    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_demo)

        firstNameEditor = findViewById(R.id.et_first)
        lastNameEditor = findViewById(R.id.et_last)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = userAdapter

        userViewModel.getUserList().observe(this) {
            userAdapter.replace(it)
        }
    }

    fun addUser(view: View) {
        val firstName = firstNameEditor.text.toString()
        val lastName = lastNameEditor.text.toString()

        userViewModel.addUser(User(firstName = firstName, lastName = lastName));

        firstNameEditor.setText("")
        lastNameEditor.setText("")

        firstNameEditor.requestFocus()
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, RoomDemoActivity::class.java)
            context.startActivity(intent)
        }
    }

}