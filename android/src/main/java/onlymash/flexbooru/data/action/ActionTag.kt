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

package onlymash.flexbooru.data.action

import okhttp3.HttpUrl
import onlymash.flexbooru.data.model.common.Booru

data class ActionTag(
    var booru: Booru,
    var query: String = "",
    //count name date
    var order: String,
    // Moebooru General: 0, artist: 1, copyright: 3, character: 4, Circle: 5, Faults: 6
    // Danbooru General: 0, artist: 1, copyright: 3, character: 4, meta: 5
    // Danbooru1.x General: 0, artist: 1, copyright: 3, character: 4, model: 5, photo_set: 6
    var type: Int = -1,
    var limit: Int
) {
    fun getDanTagsUrl(page: Int): HttpUrl {
        val builder = HttpUrl.Builder()
            .scheme(booru.scheme)
            .host(booru.host)
            .addPathSegment("tags.json")
            .addQueryParameter("search[name_matches]", query)
            .addQueryParameter("search[order]", order)
            .addQueryParameter("search[hide_empty]", "yes")
            .addQueryParameter("limit", limit.toString())
            .addQueryParameter("page", page.toString())
            .addQueryParameter("commit", "Search")
        if (type > -1) {
            builder.addQueryParameter("search[category]", type.toString())
        }
        booru.user?.let {
            builder.addQueryParameter("login", it.name)
            builder.addQueryParameter("api_key", it.token)
        }
        return builder.build()
    }

    fun getDan1TagsUrl(page: Int): HttpUrl {
        val builder = HttpUrl.Builder()
            .scheme(booru.scheme)
            .host(booru.host)
            .addPathSegment("tag")
            .addPathSegment("index.json")
            .addQueryParameter("name", query)
            .addQueryParameter("order", order)
            .addQueryParameter("limit", limit.toString())
            .addQueryParameter("page", page.toString())
            .addQueryParameter("commit", "Search")
        if (type > -1) {
            builder.addQueryParameter("type", type.toString())
        }
        booru.user?.let {
            builder.addQueryParameter("login", it.name)
            builder.addQueryParameter("password_hash", it.token)
        }
        return builder.build()
    }

    fun getGelTagsUrl(page: Int): HttpUrl {
        return HttpUrl.Builder()
            .scheme(booru.scheme)
            .host(booru.host)
            .addPathSegment("index.php")
            .addQueryParameter("page", "dapi")
            .addQueryParameter("s", "tag")
            .addQueryParameter("q", "index")
            .addQueryParameter("name_pattern", query)
            .addQueryParameter("limit", limit.toString())
            .addQueryParameter("pid", page.toString())
            .addQueryParameter("orderby", order)
            .build()
    }

    fun getMoeTagsUrl(page: Int): HttpUrl {
        val builder = HttpUrl.Builder()
            .scheme(booru.scheme)
            .host(booru.host)
            .addPathSegment("tag.json")
            .addQueryParameter("name", query)
            .addQueryParameter("order", order)
            .addQueryParameter("limit", limit.toString())
            .addQueryParameter("page", page.toString())
            .addQueryParameter("commit", "Search")
        if (type > -1) {
            builder.addQueryParameter("type", type.toString())
        }
        booru.user?.let {
            builder.addQueryParameter("login", it.name)
            builder.addQueryParameter("password_hash", it.token)
        }
        return builder.build()
    }

    fun getSankakuTagsUrl(page: Int): HttpUrl {
        val builder = HttpUrl.Builder()
            .scheme(booru.scheme)
            .host(booru.host)
            .addPathSegment("tags")
            .addQueryParameter("name", query)
            .addQueryParameter("order", order)
            .addQueryParameter("limit", limit.toString())
            .addQueryParameter("page", page.toString())
            .addQueryParameter("commit", "Search")
        if (type > -1) {
            builder.addQueryParameter("type", type.toString())
        }
        booru.user?.let {
            builder.addQueryParameter("login", it.name)
            builder.addQueryParameter("password_hash", it.token)
        }
        return builder.build()
    }
}