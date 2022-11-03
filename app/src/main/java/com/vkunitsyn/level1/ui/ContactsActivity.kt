package com.vkunitsyn.level1.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.vkunitsyn.level1.adapter.ContactsAdapter
import com.example.level1.databinding.ActivityContactsBinding

class ContactsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityContactsBinding
    private lateinit var adapter: ContactsAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        processBackArrowClick()
        processAddContactClick()
    }

    private fun processAddContactClick() {
        binding.tvAddContact.setOnClickListener {
            val intent = Intent(this@ContactsActivity, AddContactActivity::class.java)
            startActivity(intent)
        }
    }

    private fun processBackArrowClick() {
        binding.ibArrowBack.setOnClickListener {
            finish()
        }
    }

    private fun initAdapter() {
        recyclerView = binding.rvContacts
        adapter = ContactsAdapter()
        recyclerView.adapter = adapter
    }
}