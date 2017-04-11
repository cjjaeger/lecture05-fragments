package edu.uw.fragmentdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import layout.DetailFragment;

public class MainActivity extends AppCompatActivity implements  MoviesFragment.OnMovieClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    //respond to search button clicking
    public void handleSearchClick(View v){
        EditText text = (EditText)findViewById(R.id.txtSearch);
        String searchTerm = text.getText().toString();
        //create fragment
        MoviesFragment fragment = MoviesFragment.newInstance(searchTerm);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, fragment, "MovieFragment");
        ft.commit();
//        MoviesFragment  mf= (MoviesFragment)fm.findFragmentById(R.id.fragment);
//
//        mf.downloadMovieData(searchTerm);
    }

    @Override
    public void onMovieClicked(Movie movie) {
        DetailFragment frag = DetailFragment.newInstance(movie);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,frag, "DetailFragment")
                .addToBackStack(null)
                .commit();
    }
}
