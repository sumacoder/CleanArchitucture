/***
 * Copyright 2018 Hadi Lashkari Ghouchani
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * */
package com.gitlab.sample.data.album.datasource

import com.gitlab.sample.data.album.extensions.map
import com.gitlab.sample.data.common.db.dao.AlbumDao
import com.gitlab.sample.domain.album.entities.AlbumEntity
import com.gitlab.sample.presentation.album.entities.AlbumData
import io.reactivex.Observable

class AlbumsDatabaseSource(private val albumDao: AlbumDao) : AlbumsDatabaseDataSource {

    override fun getAlbums(): Observable<List<AlbumEntity>> = albumDao.getAlbums()
            .map { it.map(AlbumData::map) }

    override fun saveAll(albums: List<AlbumEntity>) {
        albumDao.clear()
        albumDao.saveAlbums(albums.map(AlbumEntity::map))
    }
}