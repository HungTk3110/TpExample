package com.example.tpexp1.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tpexp1.data.model.Language
import com.example.tpexp1.databinding.ActivityMainBinding
import com.example.tpexp1.ui.adapter.RcvAdapter

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val rcvAdapter = RcvAdapter(this@MainActivity , onItemClick)

        binding.rcv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            viewModel?.getListLanguage()?.observe(this@MainActivity) {
                rcvAdapter.setList(it)
            }
            adapter = rcvAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private val onItemClick: (Language) -> Unit = {
        Toast.makeText(this, it.name.toString(), Toast.LENGTH_SHORT).show()
    }

}