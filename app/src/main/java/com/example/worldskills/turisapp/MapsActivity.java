package com.example.worldskills.turisapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.worldskills.turisapp.Other.Conexion;
import com.example.worldskills.turisapp.Other.Tools;
import com.example.worldskills.turisapp.Other.Variables;
import com.example.worldskills.turisapp.Other.getMaker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Location location;
    double latitudOrigen;
    double longitudOrigen;

    LatLng vista = null;
    ArrayList<LatLng> points = null;
    PolylineOptions lineOptions = null;


    //variables externas de sitios, hoteles, restaurantes...
    double latitudFinal = 4.9945845;
    double longitudFinal = -59.09094;
    String categoria = Variables.categoria;
    String id = Variables.id;
    String nombre;


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

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
        }

        obtenerMiUbicacion();

        if (categoria.equals("")) {

                cargarLugar();
                String latInicial = String.valueOf(latitudOrigen);
                String latFinal = String.valueOf(latitudFinal);
                String longInicial = String.valueOf(longitudOrigen);
                String longFinal = String.valueOf(longitudFinal);

                Toast.makeText(this, "enter", Toast.LENGTH_SHORT).show();
                getMaker getMaker = new getMaker(this);

                getMaker.getMaker(latInicial, longInicial, latFinal, longFinal);


                for(int i = 0; i< Tools.markers.size(); i++){
                    points = new ArrayList<LatLng>();
                    lineOptions = new PolylineOptions();
                    List<HashMap<String, String>> path = Tools.markers.get(i);
                    for(int j=0;j<path.size();j++){
                        HashMap<String,String> point = path.get(j);
                        double lat = Double.parseDouble(point.get("lat"));
                        double lng = Double.parseDouble(point.get("lng"));
                        LatLng position = new LatLng(lat, lng);
                        if (vista == null) {
                            vista = new LatLng(lat, lng);
                        }
                        points.add(position);
                    }
                    lineOptions.addAll(points);
                    lineOptions.width(10);
                    lineOptions.color(Color.GRAY);
                }
            try {

                mMap.addPolyline(lineOptions);
                LatLng origen = new LatLng(Tools.marker.getLatitudInicial(),Tools.marker.getLongitudFinal());
                mMap.addMarker(new MarkerOptions().position(origen).title("Lat: "+Tools.marker.getLatitudInicial()+" - Long: "+Tools.marker.getLongitudInicial()));
                LatLng destino = new LatLng(Tools.marker.getLatitudFinal(), Tools.marker.getLongitudFinal());
                mMap.addMarker(new MarkerOptions().position(destino).title("Lat: "+Tools.marker.getLatitudFinal()+" - Long: "+Tools.marker.getLongitudFinal()));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(vista, 12));
            } catch (Exception e){
                Toast.makeText(this, "jaja"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            cargarLugares();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mMap.setMyLocationEnabled(true);
            } else {
                // Permission was denied. Display an error message.
            }
        }
    }

    private void cargarLugar() {
        Conexion conexion =new Conexion(this);
        SQLiteDatabase sqLiteDatabase = conexion.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT nombre, latitud, longitud FROM turismo WHERE id = "+id,null);
        cursor.moveToFirst();
        nombre = cursor.getString(0);
        latitudFinal = Double.parseDouble(cursor.getString(1));
        longitudFinal = Double.parseDouble(cursor.getString(2));
        agregarMarker(latitudFinal, longitudFinal);
    }

    private void cargarLugares() {
        try {
            Conexion conexion =new Conexion(this);
            SQLiteDatabase sqLiteDatabase = conexion.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM turismo WHERE categoria = '"+ categoria+"'",null);
            while (cursor.moveToNext()){
                nombre = cursor.getString(1);
                latitudFinal = Double.parseDouble(cursor.getString(5));
                longitudFinal = Double.parseDouble(cursor.getString(6));
                agregarMarker(latitudFinal, longitudFinal);
            }

        } catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private void obtenerMiUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
        actualizarLocation(location);
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 10000, 0, locationListener);
    }

    private void actualizarLocation(Location location) {
        if (location != null ){
            latitudOrigen = location.getLatitude();
            longitudOrigen = location.getLongitude();
            agregarMiMarker(latitudOrigen, longitudOrigen);
        }
    }

    private void agregarMiMarker(double latitudOrigen, double longitudOrigen) {
        LatLng coordenadas = new LatLng(latitudOrigen, longitudOrigen);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(coordenadas, 12);
        mMap.addMarker(new MarkerOptions().position(coordenadas).title("ubicación actual")
                /*.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_round))*/);
        mMap.animateCamera(cameraUpdate);
    }

    private void agregarMarker(double latitudFinal, double longitudFinal) {
        LatLng sydney = new LatLng(latitudFinal, longitudFinal);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(sydney, 12);
        mMap.addMarker(new MarkerOptions().position(sydney).title(nombre));
        mMap.animateCamera(cameraUpdate);
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


}
