package pl.mkwiecinski.presentation.list.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ui.setupWithNavController
import androidx.paging.LoadState
import kotlinx.coroutines.flow.collectLatest
import pl.mkwiecinski.presentation.R
import pl.mkwiecinski.presentation.base.BaseFragment
import pl.mkwiecinski.presentation.databinding.FragmentListBinding
import pl.mkwiecinski.presentation.list.vm.ListViewModel

internal class ListFragment : BaseFragment<FragmentListBinding, ListViewModel>() {

    override val layoutId = R.layout.fragment_list
    override val viewModelClass = ListViewModel::class

    override fun init(savedInstanceState: Bundle?) {
        val adapter = RepoAdapter(viewModel::retryFailed) {
            navController.navigate(ListFragmentDirections.actionListToDetails(it.name))
        }
        binding.repoList.adapter = adapter

        lifecycleScope.launchWhenResumed {
            viewModel.repositories.collectLatest(adapter::submitData)
        }
        lifecycleScope.launchWhenResumed {
            adapter.loadStateFlow.collectLatest { loadStates ->
                adapter.networkState = loadStates.refresh
                binding.swipeRefresh.isRefreshing = when(loadStates.refresh){
                    is LoadState.NotLoading -> false
                    LoadState.Loading -> true
                    is LoadState.Error -> false
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setupWithNavController(navController)
    }

}
