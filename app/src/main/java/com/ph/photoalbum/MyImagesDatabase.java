package com.ph.photoalbum;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = photoAlbum.class, version = 1)
//this is the third class created
public abstract  class MyImagesDatabase extends RoomDatabase {

private  static  MyImagesDatabase instance;
public  abstract  PhotoAlbumDao MyImagesDao();

public  static  synchronized MyImagesDatabase getInstance(Context context){

if (instance == null){

    instance = Room.databaseBuilder(context.getApplicationContext(),
            MyImagesDatabase.class,"my_images_database").fallbackToDestructiveMigration().build();






}
return instance;


}




}
