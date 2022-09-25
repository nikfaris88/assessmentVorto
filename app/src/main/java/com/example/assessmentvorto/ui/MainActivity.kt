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
            onSendMessage()
        }

    }

    fun setRecyclerView() {
        viewModel.adapterModels.observe(this) {
            Log.d("TAG", "it: $it")
            if (adapter == null) {
                adapter = MainAdapter(
                    viewModel.businessList
                )
                binding.rvListMessage.adapter = adapter
                binding.rvListMessage.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.root.viewTreeObserver.addOnGlobalLayoutListener {
                    val heightDiff: Int = binding.root.rootView.height - binding.root.height
                    if (heightDiff > 100) {
                        if (adapter!!.itemCount > 0) binding.rvListMessage.smoothScrollToPosition(
                            adapter!!.itemCount - 1
                        )
                    }
                }


            } else {
                adapter!!.notifyDataSetChanged()
            }
        }
    }

    fun onSendMessage() {

        if (binding.tieInput.text?.trim() == null) {
            Toast.makeText(this, "Please enter message to send.", Toast.LENGTH_SHORT).show()
        } else {
            var messageRequest = MessageRequest(
                binding.tieInput.text.toString()
            )

            viewModel.requestTransaction("37.786882", "-122.399972")
        }
    }
}