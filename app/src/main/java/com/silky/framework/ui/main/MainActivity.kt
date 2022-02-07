package com.silky.framework.ui.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.silky.framework.R
import com.silky.framework.databinding.ActivityMainBinding
import com.silky.framework.ui.common.VideoAdapter
import com.silky.framework.ui.downloadlist.DownloadListActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val _mainPresenter: MainPresenter by inject{ parametersOf(this) }
    private lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.etSearch.setOnEditorActionListener { v, actionId, event ->
            loading(true)
            _binding.etSearch.isEnabled = false

            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    lifecycleScope.launch (Dispatchers.Main) {
                        val videos = withContext(Dispatchers.IO) { _mainPresenter.search(_binding.etSearch.text.toString()) }

                        videos?.let {
                            _binding.recyclerVideo.adapter = VideoAdapter(it, _mainPresenter)
                        }

                        loading(false)
                        _binding.etSearch.isEnabled = true
                    }

                    true
                }
                else -> false
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        return true
    }

    //para las acciones de actionbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionDownList -> {
                startActivity(Intent(applicationContext, DownloadListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun messageToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

    fun loading(visible: Boolean) {
        if (_binding.progressBar != null) {
            when (visible) {
                true -> _binding.progressBar.visibility = View.VISIBLE
                false -> _binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }
}