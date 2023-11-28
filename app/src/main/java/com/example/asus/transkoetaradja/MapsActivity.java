package com.example.asus.transkoetaradja;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double Lat, Lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String titik1 = getIntent().getStringExtra(HalteActivity.TITIK_HALTE1);
        String titik2 = getIntent().getStringExtra(HalteActivity.TITIK_HALTE2);
        String halte = getIntent().getStringExtra(HalteActivity.NAMA_HALTE);
        try {
            Lat = Integer.parseInt(titik1);
            Lng = Integer.parseInt(titik2);
        } catch (NumberFormatException e) {
            //Will Throw exception!
            //do something! anything to handle the exception.
        }
         //Add a marker in Sydney and move the camera
        LatLng Halte = new LatLng(Lat, Lng);
        mMap.addMarker(new MarkerOptions().position(Halte).title("Marker in "+halte));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Halte));
    }
}
