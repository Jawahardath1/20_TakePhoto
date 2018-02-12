package com.learning.dath.takephoto;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static  final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView dathImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button dathButton = findViewById(R.id.dathButton);
        dathImageView = findViewById(R.id.dathImageView);

        //Disable the button if the user has no camera
        if(!hasCamera()) {
            dathButton.setEnabled(false);
        }
    }

    //Check if user has camera
    private boolean hasCamera() {
           return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }


    public void onClickLaunchCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Take a picture and pass results along to onActivityResult
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }


    //If you want to return the image taken
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //Get the photo
            Bundle extras = data.getExtras();
            Bitmap photo  = (Bitmap) extras.get("data");
            dathImageView.setImageBitmap(photo);

        }
    }


}
