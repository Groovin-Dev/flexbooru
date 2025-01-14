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

package onlymash.flexbooru.data.repository.post

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import onlymash.flexbooru.data.action.ActionPost
import onlymash.flexbooru.data.api.BooruApis
import onlymash.flexbooru.data.database.MyDatabase
import onlymash.flexbooru.data.model.common.Post

class PostRepositoryImpl(
    private val db: MyDatabase,
    private val booruApis: BooruApis
) : PostRepository {

    override fun getPosts(action: ActionPost): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(
                pageSize = action.limit,
                initialLoadSize = action.limit,
                enablePlaceholders = true
            ),
            remoteMediator = PostRemoteMediator(action, db, booruApis),
            initialKey = 0
        ) {
            db.postDao().getPosts(booruUid = action.booru.uid, query = action.query)
        }.flow
    }
}