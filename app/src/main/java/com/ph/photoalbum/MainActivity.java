package com.ph.photoalbum;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private FloatingActionButton fab;

    private MyImagesViewModel myImagesViewModel;

    private ActivityResultLauncher<Intent> ActivityResultLauncherAddImage;
    private ActivityResultLauncher<Intent> activityResultLauncherUpdateImage;

    private ActivityResultLauncher<Intent> activityResultLauncherAddImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//register activity
registerActivityForAddImage();
registerActivityForUpdateImage();
        rv=findViewById(R.id.rv);
        fab=findViewById(R.id.tab);

        rv.setLayoutManager(new LinearLayoutManager(this));



        MyImagesAdapter adapter =new MyImagesAdapter();
        rv.setAdapter(adapter);


myImagesViewModel=new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MyImagesViewModel.class);
myImagesViewModel.getImageList().observe(MainActivity.this, new Observer<List<photoAlbum>>() {
    @Override
    public void onChanged(List<photoAlbum> photoAlbums) {

        adapter.setImagesList(photoAlbums);


    }
});

fab.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(MainActivity.this,AddImageActivity.class);

//Activity Result Launcher

        activityResultLauncherAddImage.launch(intent);


    }
});

new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        myImagesViewModel.delete(adapter.getPosition(viewHolder.getAdapterPosition()));



    }
}).attachToRecyclerView(rv);


adapter.setListner(new MyImagesAdapter.onImageListener() {
    @Override
    public void onImageClick(photoAlbum photoAlbum) {


        Intent intent=new Intent(MainActivity.this,UpdateImageActivity.class);
        intent.putExtra("id",photoAlbum.getImageTitle());
        intent.putExtra("title",photoAlbum.getImageTitle());
        intent.putExtra("id",photoAlbum.getImageDescription());


        activityResultLauncherUpdateImage.launch(intent);
    }
});



    }


    public void registerActivityForAddImage(){

activityResultLauncherAddImage=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
    @Override
    public void onActivityResult(ActivityResult result) {

        int resultCode=result.getResultCode();
        Intent data=result.getData();
        if(resultCode== RESULT_OK && data !=null){

            String title=data.getStringExtra("title");
            String description= data.getStringExtra("description");
            byte[] image=data.getByteArrayExtra("image");
            photoAlbum photoAlbum=new photoAlbum(title,description,image);
            myImagesViewModel.insert(photoAlbum);


        }



    }
});

    }

    public void  registerActivityForUpdateImage(){
activityResultLauncherUpdateImage=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

int resultCode=result.getResultCode();
Intent data=result.getData();
if(resultCode==RESULT_OK && data!=null){
    String title=data.getStringExtra("updaetitle");
    String description=data.getStringExtra("updatedescrition");
    byte [] image=data.getByteArrayExtra("image");
    int id=data.getIntExtra("id",-1);
    photoAlbum photoAlbum=new photoAlbum(title,description,image);
    photoAlbum.setImageId(id);
myImagesViewModel.update(photoAlbum);

}




            }
        });
    }








}