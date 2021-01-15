package com.example.myapplication.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.R;

public class GraphicActivity extends AppCompatActivity {
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        image = findViewById(R.id.imageView);

        final Animation animationRotate = AnimationUtils.loadAnimation(
                this, R.anim.poms_rotate);
        final Animation animationMove = AnimationUtils.loadAnimation(
                this, R.anim.poms_move);
        final Animation animationScale = AnimationUtils.loadAnimation(
                this, R.anim.poms_scale);
        final Animation animationAlpha = AnimationUtils.loadAnimation(
                this, R.anim.poms_alpha);

        Button rotateButton = findViewById(R.id.rotate_button);
        rotateButton.setOnClickListener(v-> animate(animationRotate));
        Button moveButton = findViewById(R.id.move_button);
        moveButton.setOnClickListener(v-> animate(animationMove));
        Button scaleButton = findViewById(R.id.scale_button);
        scaleButton.setOnClickListener(v-> animate(animationScale));
        Button alphaButton = findViewById(R.id.alpha_button);
        alphaButton.setOnClickListener(v-> animate(animationAlpha));
    }

    private void animate(Animation animation) {
        image.startAnimation(animation);
    }
}