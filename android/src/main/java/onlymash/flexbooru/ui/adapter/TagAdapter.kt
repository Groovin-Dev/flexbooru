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

package onlymash.flexbooru.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import onlymash.flexbooru.R
import onlymash.flexbooru.app.Values.BOORU_TYPE_DAN
import onlymash.flexbooru.app.Values.BOORU_TYPE_DAN1
import onlymash.flexbooru.app.Values.BOORU_TYPE_GEL
import onlymash.flexbooru.app.Values.BOORU_TYPE_GEL_LEGACY
import onlymash.flexbooru.app.Values.BOORU_TYPE_MOE
import onlymash.flexbooru.app.Values.BOORU_TYPE_SANKAKU
import onlymash.flexbooru.app.Values.Tags
import onlymash.flexbooru.data.model.common.Tag
import onlymash.flexbooru.databinding.ItemTagBinding
import onlymash.flexbooru.extension.copyText
import onlymash.flexbooru.ui.activity.SearchActivity
import onlymash.flexbooru.ui.viewbinding.viewBinding

class TagAdapter : PagingDataAdapter<Tag, TagAdapter.TagViewHolder>(TAG_COMPARATOR) {

    companion object {
        val TAG_COMPARATOR = object : DiffUtil.ItemCallback<Tag>() {
            override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean =
                oldItem == newItem
            override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean =
                oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        return TagViewHolder(parent)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TagViewHolder(binding: ItemTagBinding) : RecyclerView.ViewHolder(binding.root) {

        constructor(parent: ViewGroup): this(parent.viewBinding(ItemTagBinding::inflate))

        private val tagName = binding.tagName
        private val tagType = binding.tagType
        private val count = binding.postCount
        private var data: Tag? = null

        init {
            itemView.apply {
                setOnClickListener {
                    data?.let { d ->
                        SearchActivity.startSearch(context, d.name)
                    }
                }
                setOnLongClickListener {
                    context.copyText(data?.name)
                    true
                }
            }
        }

        fun bind(data: Tag?) {
            this.data = data
            val res = itemView.resources
            when (data?.booruType) {
                BOORU_TYPE_DAN -> {
                    tagName.text = data.name
                    tagType.text = when (data.category) {
                        Tags.TYPE_GENERAL -> res.getString(R.string.tag_type_general)
                        Tags.TYPE_ARTIST -> res.getString(R.string.tag_type_artist)
                        Tags.TYPE_COPYRIGHT -> res.getString(R.string.tag_type_copyright)
                        Tags.TYPE_CHARACTER -> res.getString(R.string.tag_type_character)
                        Tags.TYPE_META -> res.getString(R.string.tag_type_meta)
                        else -> res.getString(R.string.tag_type_unknown)
                    }
                    count.text = data.count.toString()
                }
                BOORU_TYPE_MOE -> {
                    tagName.text = data.name
                    tagType.text = when (data.category) {
                        Tags.TYPE_GENERAL -> res.getString(R.string.tag_type_general)
                        Tags.TYPE_ARTIST -> res.getString(R.string.tag_type_artist)
                        Tags.TYPE_COPYRIGHT -> res.getString(R.string.tag_type_copyright)
                        Tags.TYPE_CHARACTER -> res.getString(R.string.tag_type_character)
                        Tags.TYPE_CIRCLE -> res.getString(R.string.tag_type_circle)
                        Tags.TYPE_FAULTS -> res.getString(R.string.tag_type_faults)
                        else -> res.getString(R.string.tag_type_unknown)
                    }
                    count.text = data.count.toString()
                }
                BOORU_TYPE_DAN1 -> {
                    tagName.text = data.name
                    tagType.text = when (data.category) {
                        Tags.TYPE_GENERAL -> res.getString(R.string.tag_type_general)
                        Tags.TYPE_ARTIST -> res.getString(R.string.tag_type_artist)
                        Tags.TYPE_COPYRIGHT -> res.getString(R.string.tag_type_copyright)
                        Tags.TYPE_CHARACTER -> res.getString(R.string.tag_type_character)
                        Tags.TYPE_MODEL -> res.getString(R.string.tag_type_model)
                        Tags.TYPE_PHOTO_SET -> res.getString(R.string.tag_type_photo_set)
                        else -> res.getString(R.string.tag_type_unknown)
                    }
                    count.text = data.count.toString()
                }
                BOORU_TYPE_GEL -> {
                    tagName.text = data.name
                    tagType.text = when (data.category) {
                        Tags.TYPE_GENERAL -> res.getString(R.string.tag_type_general)
                        Tags.TYPE_ARTIST -> res.getString(R.string.tag_type_artist)
                        Tags.TYPE_COPYRIGHT -> res.getString(R.string.tag_type_copyright)
                        Tags.TYPE_CHARACTER -> res.getString(R.string.tag_type_character)
                        Tags.TYPE_META -> res.getString(R.string.tag_type_meta)
                        else -> res.getString(R.string.tag_type_unknown)
                    }
                    count.text = data.count.toString()
                }
                BOORU_TYPE_GEL_LEGACY -> {
                    tagName.text = data.name
                    tagType.text = when (data.category) {
                        Tags.TYPE_GENERAL -> res.getString(R.string.tag_type_general)
                        Tags.TYPE_ARTIST -> res.getString(R.string.tag_type_artist)
                        Tags.TYPE_COPYRIGHT -> res.getString(R.string.tag_type_copyright)
                        Tags.TYPE_CHARACTER -> res.getString(R.string.tag_type_character)
                        Tags.TYPE_META -> res.getString(R.string.tag_type_meta)
                        else -> res.getString(R.string.tag_type_unknown)
                    }
                    count.text = data.count.toString()
                }
                BOORU_TYPE_SANKAKU -> {
                    tagName.text = data.name
                    tagType.text = when (data.category) {
                        Tags.TYPE_GENERAL -> res.getString(R.string.tag_type_general)
                        Tags.TYPE_ARTIST -> res.getString(R.string.tag_type_artist)
                        Tags.TYPE_COPYRIGHT -> res.getString(R.string.tag_type_copyright)
                        Tags.TYPE_CHARACTER -> res.getString(R.string.tag_type_character)
                        Tags.TYPE_MEDIUM -> res.getString(R.string.tag_type_medium)
                        Tags.TYPE_META_SANKAKU -> res.getString(R.string.tag_type_meta)
                        Tags.TYPE_STUDIO -> res.getString(R.string.tag_type_studio)
                        Tags.TYPE_GENRE -> res.getString(R.string.tag_type_genre)
                        else -> res.getString(R.string.tag_type_unknown)
                    }
                    count.text = data.count.toString()
                }
            }
        }
    }
}