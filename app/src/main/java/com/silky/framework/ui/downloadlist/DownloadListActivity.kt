package com.silky.framework.ui.downloadlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.silky.framework.R

class DownloadListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_list)
        setNameBar()
    }

    override fun onStart() {
        super.onStart()

        val xtras = intent.extras
        val ntent = intent
        val clipdata = ntent.clipData
        val item = clipdata?.getItemAt(0)
        val text = item?.text

        if (!text.isNullOrEmpty()) {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setNameBar() {
        supportActionBar?.title = getString(R.string.download_activity_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}