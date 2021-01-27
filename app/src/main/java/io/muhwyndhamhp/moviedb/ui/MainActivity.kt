package io.muhwyndhamhp.moviedb.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import io.muhwyndhamhp.moviedb.databinding.ActivityMainBinding
import io.muhwyndhamhp.moviedb.util.Constants.USER_NAME_KEY
import io.muhwyndhamhp.moviedb.util.Constants.USER_PREF
import io.muhwyndhamhp.baseview.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {


    private val mainViewModel: io.muhwyndhamhp.baseview.MainViewModel by viewModel()

    lateinit var binding: ActivityMainBinding

    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = applicationContext.getSharedPreferences(USER_PREF, 0)
        mainViewModel.setUserName(pref.getString(USER_NAME_KEY, null))

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        attachViewModelObserver()
    }

    private fun attachViewModelObserver() {
        mainViewModel.loading.observe(this, {
            if (it) binding.progressHorizontal.visibility = View.VISIBLE
            else binding.progressHorizontal.visibility = View.GONE
        })

        mainViewModel.error.observe(this, {
            if (it != null && !it.contains("timeout")) Snackbar.make(
                binding.root,
                it,
                Snackbar.LENGTH_SHORT
            ).show()
        })

        mainViewModel.savableUsername.observe(this, {
            if (it != null) {
                val editor = pref.edit()
                editor.putString(USER_NAME_KEY, it)
                editor.apply()
                mainViewModel.savableUsername.postValue(null)
                mainViewModel.setUserName(it)
            }
        })
    }
}