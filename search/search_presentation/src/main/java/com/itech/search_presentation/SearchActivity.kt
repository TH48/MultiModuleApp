package com.itech.search_presentation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.itech.common_utils.Activities
import com.itech.common_utils.Constants
import com.itech.common_utils.Navigator
import com.itech.search_presentation.databinding.ActivitySearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private val TAG = "AppDebug"

    companion object {
        fun LaunchActivity(activity: Activity, bundle: Bundle?) {
            val intent = Intent(activity, SearchActivity::class.java)
            bundle?.let { intent.putExtras(it) }
            activity.startActivity(intent)
        }
    }

    private var _binding: ActivitySearchBinding? = null
    private val binding: ActivitySearchBinding
        get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()
    private val newsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)


        try {
            val bundle = intent.extras
            val name = bundle?.getString("name")
            Log.d(TAG, "onCreate: $name")
        } catch (e: Exception) {
            e.printStackTrace()
        }

        initViews()
        setObservers()
    }

    private fun setObservers() {

        lifecycleScope.launchWhenCreated {
            viewModel.searchArticle.collectLatest {
                if (it.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                }

                if (it.error.isNotBlank()) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@SearchActivity, it.error, Toast.LENGTH_LONG).show()
                }
                it.data?.let {
                    binding.progressBar.visibility = View.GONE
                    newsAdapter.setData(it)
                }
            }
        }

    }

    private fun initViews() {
        binding.rvSearch.adapter = newsAdapter
        //    //https://newsapi.org/v2/everything?q=tesla&from=2022-11-06&sortBy=publishedAt&apiKey=a9dbf145baf64f0c82763cee9a049d05
        binding.searchTitle.doAfterTextChanged {
            val map = mutableMapOf<String, String>()
            map[Constant.apiKey] = Constants.API_KEY
            map[Constant.QUERY] = it.toString()
            viewModel.getSearchArticle(map)
        }
    }
}

class gotoSearchActivity(val bundle: Bundle?) : Navigator {

    override fun navigate(activity: Activity) {
        SearchActivity.LaunchActivity(activity, bundle)
    }

}