package br.com.thoughtworks.feature.playlist.business.interactor

import br.com.thoughtworks.base.business.interactor.Output
import br.com.thoughtworks.base.business.interactor.UseCase
import br.com.thoughtworks.base.business.interactor.ValueOutput
import br.com.thoughtworks.feature.playlist.business.dto.TrackList

class ShufflerUseCase(private val shuffleStrategy: ShuffleStrategy): UseCase<TrackList, TrackList>() {

    override fun guard(param: TrackList?): Boolean {
        return param != null
    }

    override fun execute(param: TrackList?): Output<TrackList> {
        val mList = param!!.results.shuffled().toMutableList()
        shuffleStrategy.shuffle(mList)
        return ValueOutput(param.copy(results = mList))
    }
}