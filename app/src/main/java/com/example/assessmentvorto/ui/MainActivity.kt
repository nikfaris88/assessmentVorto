package com.example.assessmentvorto.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import javax.inject.Inject
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import com.example.assessmentvorto.R
import com.example.assessmentvorto.base.ViewModelFactory
import com.example.assessmentvorto.databinding.ActivityMainBinding
import com.example.assessmentvorto.model.MessageRequest


class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var factory: ViewModelFactory
    var adapter: MainAdapter? = null
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        setRecyclerView()

        binding.flSend.setOnClickListener {
            onSendLocation()
        }

    }

    fun setRecyclerView() {
        viewModel.data.observe(this) {
            if (adapter == null) {
                adapter = MainAdapter()
                Log.d("TAG", "it: $it")
            }
            adapter!!.updateItems(viewModel.data.value)
            binding.rvListMessage.adapter = adapter
            binding.rvListMessage.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun onSendLocation() {
        viewModel.requestTransaction("37.786882", "-122.399972")
    }
}