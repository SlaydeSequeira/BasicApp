package com.example.basiclogintoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity2 extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, LocationListener {
    private GoogleMap googleMap;
    private LocationManager locationManager;
    private FloatingActionButton centerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        centerMapToUserLocation();
        centerButton = findViewById(R.id.centerButton);
        centerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                centerMapToUserLocation();
            }
        });

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Request location updates
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, this); // Update every 10 seconds or 10 meters change
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        List<Place> places = new ArrayList<>();
        places.add(new Place("The Green Store", 19.0553, 72.8299)); // Shop 1 coordinates
        places.add(new Place("Cafe Delights", 19.0632, 72.8347)); // Shop 2 coordinates
        places.add(new Place("Electro Hub", 19.0628, 72.8327)); // Shop 3 coordinates

        for (Place place : places) {
            LatLng placeLatLng = new LatLng(place.getLatitude(), place.getLongitude());
            googleMap.addMarker(new MarkerOptions()
                    .position(placeLatLng)
                    .title(place.getName()));
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Location permission not granted", Toast.LENGTH_SHORT).show();
            return true;
        }

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!isGPSEnabled) {
            Toast.makeText(this, "Please enable GPS", Toast.LENGTH_SHORT).show();
            return true;
        }

        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastKnownLocation != null) {
            LatLng destinationLatLng = marker.getPosition();
            LatLng originLatLng = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
            String directionsUrl = getDirectionsUrl(originLatLng, destinationLatLng);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(directionsUrl));
            startActivity(intent);
        } else {
            Toast.makeText(this, "Unable to retrieve current location", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private String getDirectionsUrl(LatLng origin, LatLng destination) {
        String strOrigin = "origin=" + origin.latitude + "," + origin.longitude;
        String strDestination = "destination=" + destination.latitude + "," + destination.longitude;
        String sensor = "sensor=false";
        String parameters = strOrigin + "&" + strDestination + "&" + sensor;
        String output = "json";
        return "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
    }

    private class Place {
        private String name;
        private double latitude;
        private double longitude;

        public Place(String name, double latitude, double longitude) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (googleMap != null) {
            LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));

            // Save the last known location
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat("lastLatitude", (float) location.getLatitude());
            editor.putFloat("lastLongitude", (float) location.getLongitude());
            editor.apply();

            // Remove location updates after receiving a valid location
            locationManager.removeUpdates(this);
        }
    }

    private void centerMapToUserLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
                    } else {
                        LatLng defaultLatLng = new LatLng(19.044307, 72.820399);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(defaultLatLng, 15));
                    }
                }
            });
        } else {
            LatLng defaultLatLng = new LatLng(19.044307, 72.820399);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(defaultLatLng, 15));
        }
    }
}
