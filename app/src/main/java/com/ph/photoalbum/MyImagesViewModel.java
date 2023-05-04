package com.ph.photoalbum;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyImagesViewModel extends AndroidViewModel {

    private MyImageRepository repository;
    private LiveData<List<photoAlbum>> imageList;



    public MyImagesViewModel(@NonNull Application application) {
        super(application);

        repository=new MyImageRepository(application);
        imageList=repository.getPhotoAlbumList();



    }

public  void insert (photoAlbum photoAlbum){
repository.insert(photoAlbum);


}
    public  void update (photoAlbum photoAlbum){
        repository.update(photoAlbum);


    }
    public  void delete (photoAlbum photoAlbum){
        repository.delete(photoAlbum);


    }

    public  LiveData<List<photoAlbum>> getImageList(){

return imageList;


    }

}
