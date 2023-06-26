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
import onlymash.flexbooru.data.database.dao.MuzeiDao
import onlymash.flexbooru.data.model.common.Muzei

class MuzeiViewModel(private val muzeiDao: MuzeiDao) : ScopeViewModel() {

    private val _muzeiOutcome: MediatorLiveData<List<Muzei>> = MediatorLiveData()

    fun loadMuzei(booruUid: Long): LiveData<List<Muzei>> {
        viewModelScope.launch{
            val data = withContext(Dispatchers.IO) {
                muzeiDao.getMuzeiByBooruUidLiveData(booruUid)
            }
            _muzeiOutcome.addSource(data) {
                _muzeiOutcome.postValue(it ?: listOf())
            }
        }
        return _muzeiOutcome
    }

    fun create(muzei: Muzei) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                muzeiDao.insert(muzei)
            } catch (_: Exception) {}
        }
    }

    fun deleteByUid(uid: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                muzeiDao.deleteByUid(uid)
            } catch (_: Exception) {}
        }
    }
}