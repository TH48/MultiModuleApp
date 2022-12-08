package com.itech.news_presentation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.itech.common_utils.Activities
import com.itech.common_utils.Navigator
import com.itech.news_presentation.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    @Inject
    lateinit var provider: Navigator.Provider

    companion object {
        fun launchActivity(activity: Activity, bundle: Bundle?) {
            val intent = Intent(activity, NewsActivity::class.java)
            bundle?.let { intent.putExtras(it) }
            activity.startActivity(intent)
        }
    }

    private var _binding: ActivityNewsBinding? = null
    private val binding: ActivityNewsBinding
        get() = _binding!!


    private val newsViewModel: NewsViewModel by viewModels()
    private val newsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        setObservers()

    }

    private fun initView() {
        binding.rvArticles.adapter = newsAdapter
        binding.ivSearch.setOnClickListener {
            val name: String = "Talha Hanif"
            val bundle: Bundle = Bundle()
            bundle.putString("name", name)
            provider.getActivities(Activities.SearchActivity, bundle).navigate(this)
        }
    }

    private fun setObservers() {
        lifecycleScope.launchWhenCreated {
            newsViewModel.newsArticle.collectLatest {
                if (it.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@NewsActivity, it.error, Toast.LENGTH_LONG).show()
                }
                it.data?.let {
                    binding.progressBar.visibility = View.GONE
                    newsAdapter.setData(it)
                }
            }
        }
    }
}

class gotoNewsActivity(val bundle: Bundle?) : Navigator {
    override fun navigate(activity: Activity) {
        NewsActivity.launchActivity(activity, bundle)
    }

}