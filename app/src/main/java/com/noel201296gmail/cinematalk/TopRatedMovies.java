package com.noel201296gmail.cinematalk;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OMOSEFE NOEL OBASEKI on 09/04/2017.
 */
public class TopRatedMovies extends AppCompatActivity {

    RequestQueue queue;
    String url = "http://api.themoviedb.org/3/movie/top_rated?api_key=INSERT_API_KEY_HERE";
    RecyclerView recyclerView;
    List<NewsFeeds1> feedsList = new ArrayList<NewsFeeds1>();
    MyRecyclerAdapter adapter;
    private MyRecyclerAdapter.ListItemClickListener onClickListener ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //recyclerView.setHasFixedSize(true);
        adapter = new MyRecyclerAdapter(this, feedsList, onClickListener);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
        //Getting Instance of Volley Request Queue
        queue = NetworkController.getInstance(this).getRequestQueue();
        //Volley's inbuilt class to make Json array request
        final ProgressDialog loading = ProgressDialog.show(this,"Loading Data", "Please wait...");



        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Dismissing progress dialog
                        loading.dismiss();



                        try {

                            response.getInt("page");
                            JSONArray jsonArray = response.getJSONArray("results");
                            Log.d("DEBUG", response.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                NewsFeeds1 dataSet = new NewsFeeds1();
                                dataSet.setPosterPath(jsonObject.getString("poster_path"));
                                dataSet.setAdult(jsonObject.getBoolean("adult"));
                                dataSet.setOverview(jsonObject.getString("overview"));
                                dataSet.setReleaseDate(jsonObject.getString("release_date"));
                                dataSet.setGenreIds(jsonObject.getJSONArray("genre_ids" ));
                                dataSet.setId(jsonObject.getInt("id"));
                                dataSet.setOriginalTitle(jsonObject.getString("original_title"));
                                dataSet.setOriginalLanguage(jsonObject.getString("original_language"));
                                dataSet.setTitle(jsonObject.getString("title"));
                                dataSet.setBackdropPath(jsonObject.getString("backdrop_path"));
                                dataSet.setPopularity(jsonObject.getDouble("popularity"));
                                dataSet.setVoteCount(jsonObject.getInt("vote_count"));
                                dataSet.setVideo(jsonObject.getBoolean("video"));
                                dataSet.setVoteAverage(jsonObject.getDouble("vote_average"));

                                feedsList.add(dataSet);
                                adapter.notifyItemChanged(i);


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
        queue.add(jsonRequest);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            /*
             * When you click the reset menu item, we want to start all over
             * and display the pretty gradient again. There are a few similar
             * ways of doing this, with this one being the simplest of those
             * ways. (in our humble opinion)
             */
            case R.id.action_top_rated:

                Intent Tr = new Intent(this,MainActivity.class);
                startActivity(Tr);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
