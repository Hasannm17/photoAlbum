package com.ph.photoalbum;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyImagesAdapter extends RecyclerView.Adapter<MyImagesAdapter.MyImagesHolder> {

    List<photoAlbum> imagesList =new ArrayList<>();
        private  onImageListener listner;

    public void setListner(onImageListener listner) {
        this.listner = listner;
    }

    public void setImagesList(List<photoAlbum> imagesList) {
        this.imagesList = imagesList;


        notifyDataSetChanged();
    }


    public  interface onImageListener{

        void  onImageClick(photoAlbum photoAlbum);



    }

    public photoAlbum getPosition(int position){
        return  imagesList.get(position);
    }
    @NonNull
    @Override
    public MyImagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_card
        ,parent,false);



        return new MyImagesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyImagesHolder holder, int position) {


        photoAlbum photoAlbum=imagesList.get(position);
        holder.textViewTitle.setText(photoAlbum.getImageTitle());
        holder.textViewDescription.setText(photoAlbum.getImageDescription());
        holder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(photoAlbum.getImage(),0,photoAlbum.getImage().length));



    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public  class MyImagesHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewTitle,textViewDescription;


        public MyImagesHolder(@NonNull View itemView) {
            super(itemView);


            imageView=itemView.findViewById(R.id.imageView);
            textViewTitle=itemView.findViewById(R.id.textViewTitle);
            textViewDescription=itemView.findViewById(R.id.textViewdesciption);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();

                    if (listner!=null && position!=RecyclerView.NO_POSITION){

                        listner.onImageClick(imagesList.get(position));

                    }


                }
            });



        }
    }




}
