package com.ph.photoalbum;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Photo_Album")
public class photoAlbum {
    @PrimaryKey(autoGenerate = true)
    public  int imageId=10;


    public String imageTitle;
    public   String ImageDescription;


    public   byte[] image;

    @Ignore
    public photoAlbum(int imageId, String imageTitle, String imageDescription, byte[] image) {
        this.imageId = imageId;
        this.imageTitle = imageTitle;
        ImageDescription = imageDescription;
        this.image = image;
    }

    public photoAlbum() {
    }

    public photoAlbum(String imageTitle, String imageDescription, byte[] image) {
        this.imageTitle = imageTitle;
       this.ImageDescription = imageDescription;
        this.image = image;
    }



    public String getImageTitle() {
        return imageTitle;
    }

    public String getImageDescription() {
        return ImageDescription;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
