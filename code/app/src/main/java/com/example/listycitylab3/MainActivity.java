package com.example.listycitylab3;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        AddCityFragment.AddCityDialogListener, EditCityFragment.EditCityDialogListener {
    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;
    @Override
    public void addCity(City city) {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }
    @Override
    public void editCity(int pos, City city) {
        dataList.set(pos, city);
        cityAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] cities = { "Edmonton", "Vancouver", "Toronto" };
        String[] provinces = { "AB", "BC", "ON" };
        dataList = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
        }
        cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);
        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(v -> {
            new AddCityFragment().show(getSupportFragmentManager(), "Add City");
        });

        FloatingActionButton removeCityBtn = findViewById(R.id.button_edit_city);
        removeCityBtn.setOnClickListener(v -> {
            int pos = cityList.getCheckedItemPosition();
            if (pos != AdapterView.INVALID_POSITION) {
                City c = dataList.get(pos);
                new EditCityFragment(pos, c.getName()).show(getSupportFragmentManager(), "Edit City");
            }
        });
    }
}
