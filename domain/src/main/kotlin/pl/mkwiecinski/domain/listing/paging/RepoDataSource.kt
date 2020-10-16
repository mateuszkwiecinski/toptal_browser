package pl.mkwiecinski.domain.listing.paging

import androidx.paging.PagingSource
import pl.mkwiecinski.domain.listing.entities.RepositoryInfo
import pl.mkwiecinski.domain.listing.entities.RepositoryOwner
import pl.mkwiecinski.domain.listing.gateways.ListingGateway
import javax.inject.Inject

internal class RepoDataSource @Inject constructor(
    private val gateway: ListingGateway,
    private val repositoryOwner: RepositoryOwner
) : PagingSource<String, RepositoryInfo>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, RepositoryInfo> =
        when (params) {
            is LoadParams.Refresh -> {
                runCatching { gateway.getFirstPage(repositoryOwner, params.loadSize) }
                    .fold(
                        onSuccess = { LoadResult.Page(it.data, null, it.nextPageKey) },
                        onFailure = { LoadResult.Error(it) }
                    )
            }
            is LoadParams.Append -> {
                runCatching { gateway.getPageAfter(repositoryOwner, params.key, params.loadSize) }
                    .fold(
                        onSuccess = { LoadResult.Page(it.data, null, it.nextPageKey) },
                        onFailure = { LoadResult.Error(it) }
                    )
            }
            is LoadParams.Prepend -> throw UnsupportedOperationException()
        }
}
