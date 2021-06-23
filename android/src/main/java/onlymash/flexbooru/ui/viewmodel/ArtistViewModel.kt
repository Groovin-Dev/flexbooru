/*
 * Copyright (C) 2020. by onlymash <im@fiepi.me>, All rights reserved
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package onlymash.flexbooru.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import onlymash.flexbooru.data.action.ActionArtist
import onlymash.flexbooru.data.repository.artist.ArtistRepository

class ArtistViewModel(private val repository: ArtistRepository) : ScopeViewModel() {

    private val _action: MutableLiveData<ActionArtist> = MutableLiveData()

    private val _clearListCh = Channel<Unit>(Channel.CONFLATED)

    val artists = flowOf(
        _clearListCh.receiveAsFlow().map { PagingData.empty() },
        _action.asFlow()
            .flatMapLatest { action ->
                repository.getArtists(action)
            }
            .cachedIn(viewModelScope)
    ).flattenMerge(2)

    fun show(action: ActionArtist): Boolean {
        if (_action.value == action) return false
        _clearListCh.trySend(Unit)
        _action.value = action
        return true
    }
}