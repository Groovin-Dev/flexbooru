/*
 * Copyright (C) 2020. by onlymash <fiepi.dev@gmail.com>, All rights reserved
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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import onlymash.flexbooru.data.model.common.TagFilter
import onlymash.flexbooru.data.repository.tagfilter.TagFilterRepository

class TagFilterViewModel(private val repo: TagFilterRepository) : ScopeViewModel() {

    private val _tagsFilter: MediatorLiveData<List<TagFilter>> = MediatorLiveData()

    fun loadTags(): LiveData<List<TagFilter>> {
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO) {
                repo.loadTagsFilter()
            }
            _tagsFilter.addSource(data) {
                _tagsFilter.postValue(it ?: listOf())
            }
        }
        return _tagsFilter
    }
}