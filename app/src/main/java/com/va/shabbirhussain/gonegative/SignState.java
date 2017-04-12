package com.va.shabbirhussain.gonegative;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

public class SignState extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_state);

        Bundle b=getIntent().getExtras();
        String name=b.getString("name");
        String email = b.getString("email");
        String photourl = b.getString("image");



        TextView txt=(TextView)findViewById(R.id.name);
        txt.setText(name);
        txt=(TextView)findViewById(R.id.email);
        txt.setText(email);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);

        Ion.with(imageView)
                .load(photourl);

    }

}
