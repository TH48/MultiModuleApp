package com.itech.search_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itech.common_utils.Resource
import com.itech.search_domain.use_cases.GetSearchArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchArticleUseCase: GetSearchArticleUseCase
) : ViewModel() {

    private val _searchArticles = MutableStateFlow(SearchState())
    val searchArticle: StateFlow<SearchState> = _searchArticles

    fun getSearchArticle(map: MutableMap<String, String>) {
        getSearchArticleUseCase.invoke(map).onEach {
            when (it) {
                is Resource.Loading -> {
                    _searchArticles.value = SearchState(isLoading = true)
                }
                is Resource.Error -> {
                    _searchArticles.value = SearchState(error = it.message)
                }
                is Resource.Success -> {
                    _searchArticles.value = SearchState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

}