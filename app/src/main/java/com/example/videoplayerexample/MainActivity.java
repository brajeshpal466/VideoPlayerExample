package com.example.videoplayerexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    Button button;
    final int CODE_REQ = 191;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.video);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        //use this for play video from url
//        videoView.setVideoURI(Uri.parse("http://hdvideo9.com/siteuploads//files/sfd1/324/Garmi%20Full%20Video%20Song_FHD-(HDvideo9).mp4"));

        //this is for play video from row directory
//        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video ));

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("video/*");
                startActivityForResult(Intent.createChooser(intent,"Select Video"),CODE_REQ);
            }
        });
    }

    @Override
    protected void onPause() {
        videoView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
       videoView.resume();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODE_REQ && resultCode == RESULT_OK && data!=null){
            Uri uri = data.getData();
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
        }
    }
}
