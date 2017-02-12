package com.example.ravi.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.LinkedList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
     MaterialBetterSpinner spinner;
    private static final String Root_url="http://api.androidhive.info";
    String[] names= {"america","new zealand","india","australia","canada","paris"};
    List<String> strings=new LinkedList<>();
    ArrayAdapter<String> mAdapter;
    LokeshSpinner lokeshSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertUser();
        lokeshSpinner = (LokeshSpinner) findViewById(R.id.lokesh);
        mAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, strings);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lokeshSpinner.setAdapter(mAdapter);

    }

    private void insertUser() {

        final RestAdapter adapter = new RestAdapter.Builder().setEndpoint(Root_url).build();

        DetailsAPI api = adapter.create(DetailsAPI.class);
        api.Mymmeth(new Callback<JsonArray>() {
            @Override
            public void success(JsonArray jsonElements, Response response) {
                for (int i = 0; i < jsonElements.size(); i++) {
                    JsonObject jsonObject1 = jsonElements.get(i).getAsJsonObject();
                    strings.add(jsonObject1.get("title").getAsString());


                }


                      mAdapter.notifyDataSetChanged();


            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
