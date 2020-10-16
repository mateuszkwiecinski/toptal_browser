package pl.mkwiecinski.presentation.list.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.first
import pl.mkwiecinski.domain.listing.GetPagedRepositoriesUseCase
import pl.mkwiecinski.presentation.base.BaseViewModel
import javax.inject.Inject

internal class ListViewModel @Inject constructor(
    getPagedRepositories: GetPagedRepositoriesUseCase
) : BaseViewModel() {

    private val pagingConfig = PagingConfig(
        initialLoadSize = INITIAL_PAGE_SIZE,
        pageSize = DEFAULT_PAGE_SIZE,
    )

    private val listPaging = getPagedRepositories(pagingConfig)

    val repositories = listPaging.cachedIn(viewModelScope)

    fun retryFailed() {

    }

    fun refresh() {
    }

    companion object {

        private const val INITIAL_PAGE_SIZE = 10
        private const val DEFAULT_PAGE_SIZE = 10
    }
}
