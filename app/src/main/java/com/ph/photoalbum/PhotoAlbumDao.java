package com.ph.photoalbum;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PhotoAlbumDao {


    @Insert

    void insert(photoAlbum photoAlbum);



    @Update
    void update(photoAlbum photoAlbum);

    @Delete
    void  delete(photoAlbum photoAlbum);

    @Query("SELECT * FROM Photo_Album ORDER BY imageId ASC")
    LiveData<List<photoAlbum>> getAllImages();



}
