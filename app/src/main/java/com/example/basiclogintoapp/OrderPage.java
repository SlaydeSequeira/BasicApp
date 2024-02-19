package com.example.basiclogintoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basiclogintoapp.Model.OrderItem;
import com.example.basiclogintoapp.adapter.OrderAdapter;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basiclogintoapp.Model.OrderItem;
import com.example.basiclogintoapp.adapter.OrderAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private Spinner sortSpinner;
    private Spinner filterSpinner;
    private EditText searchEditText;
    private ImageView searchImageView;
    private List<OrderItem> dummyData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create dummy data
        dummyData = createDummyData();
        // Initialize the adapter with the dummy data
        orderAdapter = new OrderAdapter(this, dummyData);

        // Set the adapter to the RecyclerView
        recyclerView.setAdapter(orderAdapter);

        sortSpinner = findViewById(R.id.sortSpinner);
        filterSpinner = findViewById(R.id.filterSpinner);
        searchEditText = findViewById(R.id.edittext);

        searchImageView = findViewById(R.id.image);

        // Set up the sort spinner
        setupSortSpinner();

        // Set up the filter spinner
        setupFilterSpinner();

        // Set up the search functionality
        setupSearch();
    }

    private void setupSortSpinner() {
        ArrayAdapter<CharSequence> sortAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.sort_options,
                android.R.layout.simple_spinner_item
        );
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortAdapter);

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position) {
                    case 0:
                        // Sort by Price Low to High
                        Collections.sort(dummyData, Comparator.comparingDouble(item -> parsePrice(item.getPrice())));
                        break;
                    case 1:
                        // Sort by Price High to Low
                        Collections.sort(dummyData, (item1, item2) -> Double.compare(parsePrice(item2.getPrice()), parsePrice(item1.getPrice())));
                        break;
                }
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
    }

    private void setupFilterSpinner() {
        ArrayAdapter<CharSequence> filterAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.filter_options,
                android.R.layout.simple_spinner_item
        );
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(filterAdapter);

        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Inside onItemSelected for filterSpinner
                String selectedFilter = parentView.getItemAtPosition(position).toString();
                List<OrderItem> filteredData = filterData(dummyData, selectedFilter);
                orderAdapter.setData(filteredData);
                orderAdapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
    }

    private void setupSearch() {
        // Implement search functionality based on the entered text in searchEditText
        // You may need to update the RecyclerView data and notify the adapter
    }

    private double parsePrice(String price) {
        // Implement logic to parse the price string to a double
        // You may need to handle currency symbols, commas, etc.
        return Double.parseDouble(price.replaceAll("[^\\d.]", ""));
    }

    private List<OrderItem> filterData(List<OrderItem> data, String filter) {
        List<OrderItem> filteredData = new ArrayList<>();

        for (OrderItem item : data) {
            // Use equalsIgnoreCase to make the comparison case-insensitive
            if (item.getDescription().equalsIgnoreCase(filter)) {
                filteredData.add(item);
            }
        }

        return filteredData;
    }



    private List<OrderItem> createDummyData() {
        List<OrderItem> dummyData = new ArrayList<>();

        // Add some dummy items
        dummyData.add(new OrderItem("Title 1", "Delux", "Rs1800", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 2", "Regular", "Rs1500", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 3", "Premium", "Rs2000", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 4", "Delux", "Rs1700", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 5", "Regular", "Rs1600", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 6", "Premium", "Rs2100", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 7", "Delux", "Rs1900", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 8", "Regular", "Rs1400", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 9", "Premium", "Rs2200", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 10", "Delux", "Rs2000", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 11", "Regular", "Rs1700", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 12", "Premium", "Rs2400", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 13", "Delux", "Rs1800", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 14", "Regular", "Rs1900", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 15", "Premium", "Rs2300", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 16", "Delux", "Rs2100", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 17", "Regular", "Rs1600", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 18", "Premium", "Rs2500", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 19", "Delux", "Rs2200", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 20", "Regular", "Rs1800", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 21", "Premium", "Rs2600", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 22", "Delux", "Rs2300", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 23", "Regular", "Rs1500", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 24", "Premium", "Rs2700", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 25", "Delux", "Rs2400", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 26", "Regular", "Rs1900", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 27", "Premium", "Rs2800", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 28", "Delux", "Rs2500", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 29", "Regular", "Rs2000", "https://dummyimage.com/100x100/000/fff"));
        dummyData.add(new OrderItem("Title 30", "Premium", "Rs2900", "https://dummyimage.com/100x100/000/fff"));
        return dummyData;
    }
}
