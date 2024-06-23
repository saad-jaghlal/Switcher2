package com.example.switcher2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView myListView;
    TextView tel  , prix_gasoil,prix_essence;
    Switch isAdditif;
    Retrofit retrofit;
    ApiService apiService;
    ArrayList<String> lstSata;
    ArrayAdapter<String> adapter;
    Carburant carburant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListView = findViewById(R.id.my_listView);
        tel = findViewById(R.id.num_tel);
        prix_gasoil = findViewById(R.id.tx_prix_gsoil);
        prix_essence = findViewById(R.id.tx_prix_essence);
        isAdditif = findViewById(R.id.isAdditif);

        lstSata = new ArrayList<>();
//        Exercice 2
//        q1)
        retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/saad-jaghlal/Api_Carburant/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        //        q3)
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,lstSata);
        myListView.setAdapter(adapter);
//        q1
        apiService.getAllCarburant().enqueue(new Callback<List<Carburant>>() {
            @Override
            public void onResponse(Call<List<Carburant>> call, Response<List<Carburant>> response) {
                if(response.isSuccessful()){
                    for( Carburant Cr : response.body() ){
                        lstSata.add(Cr.getStation());
                    }
                    //        q3)
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Carburant>> call, Throwable t) {
                Log.d("Mytag",t.getMessage());
            }
        });
//        Q4
        myListView.setOnItemClickListener(this);



    }
//     q4
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String nomStation = lstSata.get(position);
        Toast.makeText(MainActivity.this, nomStation, Toast.LENGTH_SHORT).show();
        apiService.getAllCarburant().enqueue(new Callback<List<Carburant>>() {
            @Override
            public void onResponse(Call<List<Carburant>> call, Response<List<Carburant>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (Carburant cr : response.body()) {
                        if (cr.getStation().equals(nomStation)) {
                            tel.setText(cr.getTel());
                            prix_gasoil.setText(String.valueOf(cr.getPrixgasoil()));
                            prix_essence.setText(String.valueOf(cr.getPrixessence()));
                            isAdditif.setChecked(cr.isAvecAdditif());

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Carburant>> call, Throwable t) {
                Log.e("CarburantData", "API call failed: " + t.getMessage());
            }
        });

    }
}