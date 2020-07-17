package com.elkiplangat.galleryview.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager

import com.elkiplangat.galleryview.R
import com.elkiplangat.galleryview.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

private const val READ_EXTERNAL_STORAGE_REQUEST = 1
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val adapter = ImagesAdapterRecycler()

        binding.recyclerView.also { view ->
            view.layoutManager = GridLayoutManager(this, 3)
            view.adapter = adapter
            view.isNestedScrollingEnabled = false

        }

        viewModel.images.observe(this, Observer<List<MediaStoreImage>> {
            adapter.submitList(it)

        })

        if (!isPermissionsGranted()) {
            requestPermissions()
        } else {
            Log.d(TAG, "permissions are granted already")
            openMediaStore()

        }


    }

    private fun showImages() {
        Log.d(TAG, "inside showImages method")
        viewModel.loadImages()
    }

    private fun openMediaStore() {
        Log.d(TAG, "inside openMediaStore method")
        showImages()

    }


private fun goToSettings() {
    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName")).apply {
        addCategory(Intent.CATEGORY_DEFAULT)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }.also { intent ->
        startActivity(intent)
    }
}

private fun requestPermissions() {
    val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    ActivityCompat.requestPermissions(this, permissions, READ_EXTERNAL_STORAGE_REQUEST)
}

fun isPermissionsGranted(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.READ_EXTERNAL_STORAGE
    ) == PERMISSION_GRANTED
}

override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) {

    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    when (requestCode) {
        READ_EXTERNAL_STORAGE_REQUEST -> {
            if (grantResults.isNotEmpty() && grantResults[0] == PERMISSION_GRANTED) {
                Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_LONG).show()
            } else {
                val showRationale =
                    ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
            }
        }
    }
}}