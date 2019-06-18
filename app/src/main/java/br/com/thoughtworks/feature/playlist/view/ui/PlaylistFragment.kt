package br.com.thoughtworks.feature.playlist.view.ui

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.thoughtworks.R
import br.com.thoughtworks.base.business.exception.HttpException
import br.com.thoughtworks.base.view.dialog.SimpleDialogConfig
import br.com.thoughtworks.base.view.navigation.GlobalNavigations
import br.com.thoughtworks.base.view.ui.BaseFragment
import br.com.thoughtworks.feature.playlist.business.dto.ShuffledList
import br.com.thoughtworks.feature.playlist.business.dto.TrackList
import br.com.thoughtworks.feature.playlist.gateway.mvvm.PlaylistViewModel
import br.com.thoughtworks.feature.playlist.view.di.PlaylistViewInjector
import br.com.thoughtworks.feature.playlist.view.recyclerview.PlaylistAdapter
import br.com.thoughtworks.model.Track
import kotlinx.android.synthetic.main.fragment_playlist.*
import kotlinx.android.synthetic.main.toolbar.*


class PlaylistFragment: BaseFragment<PlaylistViewModel>() {
    companion object {
        private const val TAG = "TRACKS"
    }

    private val dialogStrategy by lazy { PlaylistViewInjector.injector.dialogStrategy }

    override fun getViewModelClass(): Class<PlaylistViewModel> {
        return PlaylistViewModel::class.java
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_playlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTracks()
    }

    override fun setupViews(view: View) {
        setupToolbar()
        setupRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_playlist, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.shuffle -> shuffle()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shuffle(): Boolean {
        viewModel.shuffle()
        return true
    }

    private fun getTracks() {
        showLoading()
        viewModel.getTracks()
    }

    override fun handleSuccess(value: Any?) {
        when (value) {
            is TrackList -> handleTrackList(value)
            is ShuffledList -> setShuffledList(value)
        }
    }

    private fun handleTrackList(value: TrackList) {
        hideLoading()
        playlist.adapter = PlaylistAdapter(value.results, ::onTrackClicked)
    }

    private fun setShuffledList(shuffledList: ShuffledList) {
        val adapter = playlist.adapter as PlaylistAdapter
        if(shuffledList.results != null) {
            adapter.replace(shuffledList.results)
        }
    }

    override fun handleConnectionError() {
        hideLoading()
        if(!viewModel.showingConnectionDialog) {
            val config = getConnectionDialogConfig()
            dialogStrategy.show(requireContext(),config)
            viewModel.showingConnectionDialog = true
        }
    }

    private fun getConnectionDialogConfig(): SimpleDialogConfig {
        val okListener: (DialogInterface, Int) -> Unit = {
                d,_ ->
            dismissConnectionError(d)
            GlobalNavigations.navigateToOffline(findNavController())
        }

        val noListener: (DialogInterface, Int) -> Unit = {
                d,_ ->
            dismissConnectionError(d)
            getTracks()
        }

        return SimpleDialogConfig(
            message = getString(R.string.connection_unavailable),
            noTxt = R.string.refresh,
            okListener = okListener,
            noListener = noListener)
    }

    private fun dismissConnectionError(dialogInterface: DialogInterface) {
        dialogInterface.dismiss()
        viewModel.showingConnectionDialog = false
    }

    override fun handleHttpError(error: HttpException) {
        hideLoading()
        Toast.makeText(context, getString(R.string.http_error, error.code), Toast.LENGTH_LONG).show()
    }

    private fun setupToolbar() {
        setupToolbar(toolbar, false)
        setHasOptionsMenu(true)
    }

    private fun setupRecyclerView() {
        val divider = DividerItemDecoration(context, RecyclerView.VERTICAL)
        divider.setDrawable(context!!.getDrawable(R.drawable.divider)!!)
        playlist.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(divider)
        }
    }

    private fun hideLoading() {
        loading?.visibility = View.GONE
    }

    private fun showLoading() {
        loading?.visibility = View.VISIBLE
    }

    private fun onTrackClicked(track: Track) {
        Log.i(TAG, "clicked on track #${track.id}")
    }

    override fun handleError(error: Throwable?) {
        Log.e(TAG, "error", error)
    }
}