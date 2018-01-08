package com.example.coderlt.pathtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.coderlt.pathtest.view.PathCanvas1;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final static String TAG="MainActivity";
    private Button offsetButton;
    private Button bezierButton;
    private Button bezierVeButton;
    private PathCanvas1 pathCanvas1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        offsetButton=(Button)findViewById(R.id.offset_button);
        bezierButton=(Button)findViewById(R.id.bezier_button);
        bezierVeButton=(Button)findViewById(R.id.bezier_ve_button);
        pathCanvas1=(PathCanvas1)findViewById(R.id.path_canvas1);
        bezierButton.setOnClickListener(this);
        offsetButton.setOnClickListener(this);
        bezierVeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.offset_button:
                //Log.d(TAG,"Button clicked");
                pathCanvas1.setOffset();
                break;
            case R.id.bezier_button:
                Log.d(TAG,"Bezier Curve");
                startActivity(new Intent(this,BezierActivity.class));
                break;
            case R.id.bezier_ve_button:
                Log.d(TAG,"Bezier ev loading");
                startActivity(new Intent(this,BezierVeActivity.class));
            default:
                break;
        }
    }
}
