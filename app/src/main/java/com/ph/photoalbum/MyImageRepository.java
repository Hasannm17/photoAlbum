package com.ph.photoalbum;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyImageRepository {


   private  PhotoAlbumDao photoAlbumDao;
   private LiveData<List<photoAlbum>>photoAlbumList;
   //Executer
   ExecutorService executorService= Executors.newSingleThreadExecutor();


public  MyImageRepository (Application application){

MyImagesDatabase database=MyImagesDatabase.getInstance(application);
photoAlbumDao=database.MyImagesDao();
photoAlbumList =photoAlbumDao.getAllImages();

}

public void  insert(photoAlbum photoAlbum )
{
//new InsertImageAsyncTask(photoAlbumDao).execute(photoAlbum);
executorService.execute(new Runnable() {
   @Override
   public void run() {
       photoAlbumDao.insert(photoAlbum);
   }
});


}
public  void  delete(photoAlbum photoAlbum){

photoAlbumDao.delete(photoAlbum);
//new DeleteImageAsyncTask(photoAlbumDao).execute(photoAlbum);


}
public void update(photoAlbum photoAlbum){

//new UpdateImageAsyncTask(photoAlbumDao).execute(photoAlbum);
photoAlbumDao.update(photoAlbum);

}

public  LiveData<List<photoAlbum>> getPhotoAlbumList(){

   return photoAlbumList;

   }




/*
private static  class InsertImageAsyncTask extends AsyncTask<photoAlbum,Void,Void>{
PhotoAlbumDao imagesdao;

   public InsertImageAsyncTask(PhotoAlbumDao photoAlbumDao) {
      this.imagesdao=photoAlbumDao;
   }


   @Override
   protected Void doInBackground(photoAlbum... photoAlbums) {

      imagesdao.insert(photoAlbums[0]);


      return null;
   }
}
   private static  class UpdateImageAsyncTask extends AsyncTask<photoAlbum,Void,Void>{
      PhotoAlbumDao imagesdao;

      public UpdateImageAsyncTask(PhotoAlbumDao photoAlbumDao) {
         this.imagesdao=photoAlbumDao;
      }


      @Override
      protected Void doInBackground(photoAlbum... photoAlbums) {

         imagesdao.update(photoAlbums[0]);


         return null;
      }
   }
   private static  class DeleteImageAsyncTask extends AsyncTask<photoAlbum,Void,Void>{
      PhotoAlbumDao imagesdao;

      public DeleteImageAsyncTask(PhotoAlbumDao photoAlbumDao) {
         this.imagesdao=photoAlbumDao;
      }


      @Override
      protected Void doInBackground(photoAlbum... photoAlbums) {

         imagesdao.delete(photoAlbums[0]);


         return null;
      }
   }*/

}
