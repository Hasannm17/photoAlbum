package com.ph.photoalbum;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UpdateImageActivity extends AppCompatActivity {
private ImageView imageViewUpdate;
private EditText editTextUpdateTitle ,getEditTextUpdateDescription;
private Button buttonUpdate;

private String title,description;
private  int id ;
private byte[] image;
    private Bitmap selectedImage;
    private Bitmap scaledImage;

    ActivityResultLauncher<Intent> activityResultLauncherForSelectImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Update Image");
        setContentView(R.layout.activity_update_image);
        regiterActivityForSelectImage();
        imageViewUpdate=findViewById(R.id.imageViewUpdateImage);
        editTextUpdateTitle=findViewById(R.id.editTextUpdatetitle);
        getEditTextUpdateDescription=findViewById(R.id.editTextTextUpdateMultiLine);
        buttonUpdate=findViewById(R.id.buttonUpdate);
id =getIntent().getIntExtra("id",-1);
title=getIntent().getStringExtra("title");
description=getIntent().getStringExtra("description");
image=getIntent().getByteArrayExtra("image");

editTextUpdateTitle.setText(title);
getEditTextUpdateDescription.setText(description);
imageViewUpdate.setImageBitmap(BitmapFactory.decodeByteArray(image,0,image.length));
imageViewUpdate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        activityResultLauncherForSelectImage.launch(intent);
    }
});

buttonUpdate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        if(selectedImage==null){
            Toast.makeText(UpdateImageActivity.this, "Please select an image!", Toast.LENGTH_SHORT).show();



        }
        else{
            String UpdateTitle= editTextUpdateTitle.getText().toString();
            String UpdateDescription=getEditTextUpdateDescription.getText().toString();
            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
            scaledImage=makesmall(selectedImage,300);

            selectedImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
            byte[] image=outputStream.toByteArray();

            Intent intent=new Intent();
            intent.putExtra("title",title);
            intent.putExtra("description",description);
            intent.putExtra("image",image);


            setResult(RESULT_OK,intent);
            finish();
        }

    }
});
    }


    public void  updateData(){

if(id==-1){

    Toast.makeText(this, "There is a problem", Toast.LENGTH_SHORT).show();
    
}
else{
    String UpdateTitle= editTextUpdateTitle.getText().toString();
    String UpdateDescription=getEditTextUpdateDescription.getText().toString();
    Intent intent=new Intent();
    intent.putExtra("id",id);


    intent.putExtra("updatetitle",title);
    intent.putExtra("updatedescription",description);
    intent.putExtra("image",image);
    if(selectedImage==null){
    intent.putExtra("image",image);




    }
    else{

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        scaledImage=makesmall(selectedImage,300);

        selectedImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
        byte[] image=outputStream.toByteArray();





    }
    setResult(RESULT_OK,intent);
    finish();

}



    }

    public  void regiterActivityForSelectImage(){
        activityResultLauncherForSelectImage=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                int resultCode =result.getResultCode();
                Intent data=result.getData();
                if(resultCode ==RESULT_OK && data !=null){


                    try {
                        selectedImage=MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                        imageViewUpdate.setImageBitmap(selectedImage);


                    } catch (IOException e) {

                    }


                }




            }
        });

    }
    public  Bitmap makesmall(Bitmap image ,int maxsize){

        int width=image.getWidth();
        int height= image.getHeight();
        float ratio=(float) width /(float) height;
        if (ratio>1){
            width=maxsize;
            height=(int) (width/ratio);

        }else
        {

            height=maxsize;
            width=(int) (height* ratio);

        }


        return Bitmap.createScaledBitmap(image,width,height,true);

    }


}