package pl.mkwiecinski.domain.listing

import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.flow.emptyFlow
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import pl.mkwiecinski.domain.listing.paging.PagingSourceFactory
import pl.mkwiecinski.domain.listing.persistences.PagingEventsPersistence

@RunWith(MockitoJUnitRunner::class)
internal class GetPagedRepositoriesUseCaseTest {

    @Mock
    private lateinit var eventsPersistence: PagingEventsPersistence

    @Mock
    private lateinit var factory: PagingSourceFactory

    @Mock
    private lateinit var config: PagedList.Config

    @InjectMocks
    private lateinit var usecase: GetPagedRepositoriesUseCase

    @Before
    fun setUp() {
        eventsPersistence.stub {
            on { networkEvents() } doReturn emptyFlow()
            on { refreshEvents() } doReturn emptyFlow()
        }
        factory.stub {
            on { getPagingList(any()) } doReturn emptyFlow()
        }
    }

    @Test
    fun `returns refreshable paging data`() {
        val result = usecase(config)

        result.refresh()

        verify(factory).refresh()
    }

    @Test
    fun `returns retryable paging data`() {
        val result = usecase(config)

        result.retry()

        verify(factory).retry()
    }
}
