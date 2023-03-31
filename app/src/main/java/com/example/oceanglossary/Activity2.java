package com.example.oceanglossary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    private Button button,cam,upload,next;
    ImageView image;
    TextView tv4;
    int SELECT_PICTURE = 200;
    int REQUEST_FROM_CAMERA = 1337;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        next = (Button)findViewById(R.id.button5);
        next.setVisibility(View.GONE);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this,Activity3.class);
                startActivity(intent);
            }
        });
        button = (Button)findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });

        cam = (Button)findViewById(R.id.button2);
        cam.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }));

        upload = (Button)findViewById(R.id.button3);
        upload.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent i = new Intent();
                    i.setType("image/*");
                    i.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }));
    }
    public void openActivity1(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK ) {
                image = (ImageView) findViewById(R.id.image);

                Uri selectedImage = data.getData();
                if(selectedImage!=null)
                {
                    image.setImageURI(selectedImage);
                    cam.setVisibility(View.GONE);
                    upload.setVisibility(View.GONE);
                    button.setVisibility(View.GONE);
                    next.setVisibility(View.VISIBLE);

                }

        }
    }
}