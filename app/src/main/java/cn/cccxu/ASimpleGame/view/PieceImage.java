package cn.cccxu.ASimpleGame.view;

import android.graphics.Bitmap;

public class PieceImage {

    private Bitmap image;

    private int imageId;

    public PieceImage(Bitmap image, int imageId) {
        super();
        this.image = image;
        this.imageId = imageId;
    }

    Bitmap getImage() {
        return image;
    }

    int getImageId() {
        return imageId;
    }

}
