package com.pretenders.monish;

import android.content.Context;
import android.location.Location;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by danstavy on 11/15/15.
 */

public class FirebaseConnector  {
	private static final String tag = "FirebaseConnector";
	private static final String FIREBASE_DRIVERS_REF = "https://monish.firebaseio.com/drivers";
    private static final String FIREBASE_PASSENGERS_REF = "https://monish.firebaseio.com/passengers";
    private Firebase driversRef;
    private Firebase passengersRef;
    private Map<String,Marker> markers;
    private Map<String, Passenger> passengers;
    private GoogleMap mMap;
    private Circle searchCircle;
    ChildEventListener cel;
    Driver driver;
    private String mId;
    // private List<Firebase> firebaseRefs;

    public FirebaseConnector(Context context, GoogleMap map, int line, boolean direction) {
        mMap = map;
        Firebase.setAndroidContext(context);
        driversRef = new Firebase(FIREBASE_DRIVERS_REF + "/" + line + "/" + String.valueOf(direction));
        passengersRef = new Firebase(FIREBASE_PASSENGERS_REF + "/" + line + "/" + String.valueOf(direction));
        /*
        spotsRef.authAnonymously(new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                // we've authenticated this session with your Firebase app
            	spotsRef.orderByKey();
            	Log.v(tag, "authenticated");
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
            	spotsRef = null;
            	Log.e(tag, firebaseError.getMessage());
            }
        });
        */
        //spotsRef.orderByKey();

        // setup markers
        this.markers = new HashMap<String, Marker>();
        this.passengers = new HashMap<String, Passenger>();

        cel = passengersRef.addChildEventListener(new ChildEventListener() {
            // Retrieve new posts as they are added to the database
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                Passenger newPassenger = snapshot.getValue(Passenger.class);
                String key = snapshot.getKey();
                Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(newPassenger.getLat(), newPassenger.getLng())).
                        icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                markers.put(key, marker);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //String key = dataSnapshot.getKey();
               // Marker marker = markers.get(key);
               // marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
               // markers.remove(key);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                Marker marker = markers.get(key);
                marker.remove();
                markers.remove(key);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }

    public void onStop() {
        // remove all event listeners to stop updating in the background
        passengersRef.removeEventListener(cel);
        removeDriver();
        for (Marker marker: this.markers.values()) {
            marker.remove();
        }
        this.markers.clear();
    }

    public void setLocation(Location location) {
        driver = new Driver(location.getLatitude(), location.getLongitude());
        Firebase driverRef = driversRef.push();
        driverRef.setValue(driver);

        // Get the unique ID generated by push()
        mId = driverRef.getKey();
    }

    public void updateLocation(Location location) {
        driver.setLat(location.getLatitude());
        driver.setLng(location.getLongitude());
        Firebase driverRef = driversRef.child(mId);
        Map<String, Object> driverMap = new HashMap<>();
        driverMap.put("lat", new Double(driver.getLat()));
        driverMap.put("lng", new Double(driver.getLng()));
        driverRef.updateChildren(driverMap);
    }

    public void updateState(boolean full) {
        Firebase driverRef = driversRef.child(mId);
        Map<String, Object> driverMap = new HashMap<>();
        driverMap.put("full", new Boolean(full));
        driverRef.updateChildren(driverMap);
    }

    public void removeDriver() {
        Firebase driverRef = driversRef.child(mId);
        driverRef.removeValue();
    }
}
