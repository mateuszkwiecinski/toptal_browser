package pl.mkwiecinski.domain.listing

import androidx.paging.Pager
import androidx.paging.PagingConfig
import pl.mkwiecinski.domain.listing.paging.RepoDataSource
import javax.inject.Inject
import javax.inject.Provider

class GetPagedRepositoriesUseCase @Inject internal constructor(
    private val dataSourceProvider: Provider<RepoDataSource>,
) {

    operator fun invoke(config: PagingConfig) =
        Pager(
            config = config,
            pagingSourceFactory = { dataSourceProvider.get() }
        ).flow

}
