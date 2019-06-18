package br.com.thoughtworks.feature.playlist.view.recyclerview

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.thoughtworks.R
import br.com.thoughtworks.base.view.recyclerview.Adapter
import br.com.thoughtworks.base.view.recyclerview.ViewHolder
import br.com.thoughtworks.base.view.recyclerview.ViewHolderFactory
import br.com.thoughtworks.base.view.recyclerview.ViewHolderFactoryMediator
import br.com.thoughtworks.base.view.util.loadImageIntoView
import br.com.thoughtworks.model.Track
import kotlinx.android.synthetic.main.item_playlist.view.*

class PlaylistAdapter(items: List<Track> = emptyList(), listener:(Track)->Unit): Adapter<Track>(items, listener) {

    class Mediator: ViewHolderFactoryMediator<Track>() {
        override fun createViewHolderFactory(code: Int): ViewHolderFactory<Track> {
            return Factory()
        }
    }

    class Factory: ViewHolderFactory<Track>() {
        override fun create(parent: ViewGroup): ViewHolder<Track> {
            return PlaylistViewHolder(inflate(R.layout.item_playlist, parent))
        }
    }

    class PlaylistViewHolder(itemView: View): ViewHolder<Track>(itemView) {
        private val albumImg: ImageView = itemView.albumImg
        private val title: TextView = itemView.title
        private val artistAndGenre: TextView = itemView.artistAndGenre
        private val itemTrack: ViewGroup = itemView.itemTrack

        @SuppressLint("SetTextI18n")
        override fun bind(item: Track, listener: (Track) -> Unit) {
            loadImageIntoView(itemView, albumImg, item.artworkUrl)
            title.text = item.trackName
            artistAndGenre.text = "${item.artistName} (${item.primaryGenreName})"
            super.bind(item, listener)
        }
    }

    override fun getFactoryMediator(): ViewHolderFactoryMediator<Track> {
        return Mediator()
    }
}