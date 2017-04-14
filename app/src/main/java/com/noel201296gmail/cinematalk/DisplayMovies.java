package com.noel201296gmail.cinematalk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by OMOSEFE NOEL OBASEKI on 09/04/2017.
 */
public class DisplayMovies extends AppCompatActivity {


    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_movie);

        Intent i = getIntent();
        String Title= i.getStringExtra("title");
        String Vote = i.getStringExtra("vote_average");
        String Image = i.getStringExtra("backdrop_path");
        String Overview =i.getStringExtra("overview");
        String Release_date =i.getStringExtra("release_date");

         TextView txtname = (TextView) findViewById(R.id.TITLE);
         TextView txtname1 = (TextView) findViewById(R.id.OVERVIEW);
         TextView txtname2 = (TextView) findViewById(R.id.VOTE);
         TextView txtname3 = (TextView) findViewById(R.id.DATE);
        ImageView imageView1 = (ImageView) findViewById(R.id.IMAGEVIEW);




        txtname.setText(Title);
        txtname1.setText(Overview);
        txtname2.setText(Vote);
        txtname3.setText(Release_date);

                Picasso.with(this)
                .load(Image)
                .placeholder(R.color.colorAccent)
                .into(imageView1);




}}
