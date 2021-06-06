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

package onlymash.flexbooru.data.model.sankaku

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import onlymash.flexbooru.app.Values.BOORU_TYPE_SANKAKU
import onlymash.flexbooru.data.model.common.Tag

@Serializable
data class TagSankaku(
    @SerialName("count")
    val count: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("locale")
    val locale: String,
    @SerialName("name")
    val name: String,
    @SerialName("name_en")
    val nameEn: String,
    @SerialName("name_ja")
    val nameJa: String?,
    @SerialName("pool_count")
    val poolCount: Int,
    @SerialName("post_count")
    val postCount: Int,
    @SerialName("rating")
    val rating: String? = "e",
    @SerialName("type")
    val type: Int
) {
    fun toTag(): Tag {
        return Tag(
            booruType = BOORU_TYPE_SANKAKU,
            id = id,
            name = name,
            category = type,
            count = count
        )
    }
}