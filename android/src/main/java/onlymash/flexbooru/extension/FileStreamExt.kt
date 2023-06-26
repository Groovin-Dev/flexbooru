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

package onlymash.flexbooru.extension

import java.io.Closeable
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

fun Closeable.safeCloseQuietly() {
    try {
        close()
    } catch (_: IOException) {
        // Ignore
    }

}

fun InputStream.copyTo(out: OutputStream?): Long {
    if (out == null) return 0
    return copyTo(out, DEFAULT_BUFFER_SIZE)
}