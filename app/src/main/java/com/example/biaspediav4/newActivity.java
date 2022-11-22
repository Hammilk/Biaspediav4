package com.example.biaspediav4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class newActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        String name = getIntent().getStringExtra("NAME");
        String definition = getIntent().getStringExtra("DEFINITION");
        String quote = getIntent().getStringExtra("QUOTE");
        int image = getIntent().getIntExtra("IMAGE", 0);

        TextView nameTextView = findViewById(R.id.textView2);
        TextView definitionTextView = findViewById(R.id.textView3);
        TextView quoteTextView = findViewById(R.id.textView4);
        ImageView B_Image = findViewById(R.id.imageView2);

        nameTextView.setText(name);
        definitionTextView.setText(definition);
        quoteTextView.setText(quote);
        B_Image.setImageResource(image);


    }
}