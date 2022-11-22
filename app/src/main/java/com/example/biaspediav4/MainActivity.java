package com.example.biaspediav4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    ArrayList<BiasModel> biasModels = new ArrayList<>();

    //Update image list whenever adding new data element
    int[] biasImages = {
            R.drawable.abilene_paradox,
            R.drawable.action_bias,
            R.drawable.affect_heuristic,
            R.drawable.authority_bias,
            R.drawable.automation_bias,
            R.drawable.availability_heuristic,
            R.drawable.backfire_effect,
            R.drawable.base_rate_fallacy,
            R.drawable.ben_franklin_effect,
            R.drawable.bikeshedding};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        setUpBiasModels();

        B_RecyclerViewAdapter adapter = new B_RecyclerViewAdapter(this,
                biasModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    //This function pulls arrays from .xml file
    private void setUpBiasModels() {
        //update here with more columns
        String[] biasNames = getResources().getStringArray(R.array.bias_full_txt);
        String[] biasDefinitions = getResources().getStringArray(R.array.bias_definition);
        String[] biasQuotes = getResources().getStringArray(R.array.bias_quotes);

        //update for loop for column arrays
        for (int i = 0; i < biasNames.length; i++) {
            biasModels.add(new BiasModel(biasNames[i], biasDefinitions[i], biasQuotes[i], biasImages[i]));
        }
    }

    //This function pushes columns to newActivity.java
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, newActivity.class);

        //update here with more columns
        intent.putExtra("Name", biasModels.get(position).getBiasName());
        intent.putExtra("IMAGE", biasModels.get(position).getImage());
        intent.putExtra("DEFINITION", biasModels.get(position).getBiasDefinition());
        intent.putExtra("QUOTE", biasModels.get(position).getBiasQuote());

        startActivity(intent);

    }

    @Override //The following is all new
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bias_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        B_RecyclerViewAdapter adapter = new B_RecyclerViewAdapter(this,
                biasModels, this); //do i need to pass through biasmodelfull?

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { //args slightly different
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //how do i get get getfilter()?
                adapter.getFilter().filter(newText);

                return false;
            }
        });
        return true;
    }
}

