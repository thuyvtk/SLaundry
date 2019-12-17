package thuyvtk.activity.laundry_store.library;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class LocationLibrary {
    Context context;
    Activity activity;
    private LatLng currentLatLng;

    public LocationLibrary(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public LatLng  getCurrentLocation() {
        android.location.Location location = getLocationFromNetWorkProvider();

        if (location == null) {
            location = getLocationDataByGPS();
        }

        if (location == null) {
            location = getLocationFromBestProvider();
        }
        if (location != null) {

          return  currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        }
        return  null;
    }

    public List<Address>  getCurrentAddress() {
        android.location.Location location = getLocationFromNetWorkProvider();

        if (location == null) {
            location = getLocationDataByGPS();
        }

        if (location == null) {
            location = getLocationFromBestProvider();
        }
        if (location != null) {

            currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            return   convertLatLngToAddress(currentLatLng, BitmapDescriptorFactory.HUE_AZURE);

        }
        return  null;
    }

    private  List<Address>  convertLatLngToAddress(LatLng latLng, float color) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            return  addresses;
        } catch (IOException e) {

        }
        return null;
    }

    private android.location.Location getLocationFromNetWorkProvider() {
        if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        LocationManager locationManager = (LocationManager)
                activity.getSystemService(Context.LOCATION_SERVICE);
        android.location.Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        return location;
    }

    private android.location.Location getLocationDataByGPS() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        LocationManager locationManager = (LocationManager)
                activity.getSystemService(Context.LOCATION_SERVICE);
        android.location.Location location = null;
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        return location;
    }

    private android.location.Location getLocationFromBestProvider() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        LocationManager locationManager = (LocationManager)
                activity.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        android.location.Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        return location;
    }

    private Boolean isNearByLocation(LatLng checkPoint, LatLng centerPoint, int km) {

        double ky = 40000 / 360;
        double kx = Math.cos(Math.PI * centerPoint.latitude / 180) * ky;
        double dx = Math.abs(centerPoint.longitude - checkPoint.longitude) * kx;
        double dy = Math.abs(centerPoint.latitude - checkPoint.latitude) * ky;
        return Math.sqrt(dx * dx + dy * dy) <= km;

    }

    public Map<String, LatLng> getNearByLocation(int km, Map<String, LatLng> latLngMap) {

        Map<String, LatLng> nearByLatLngMap = new HashMap<>();
        Set<String> keySet = latLngMap.keySet();
        for (String key : keySet) {
            if (isNearByLocation(latLngMap.get(key), getCurrentLocation(), km)) {
                nearByLatLngMap.put(key, latLngMap.get(key));
            }
        }
        return nearByLatLngMap;
    }

    public Map<String, LatLng> getNearByLocation(int km, LatLng latLng ,Map<String, LatLng> latLngMap) {

        Map<String, LatLng> nearByLatLngMap = new HashMap<>();
        Set<String> keySet = latLngMap.keySet();
        for (String key : keySet) {
            if (isNearByLocation(latLngMap.get(key), latLng, km)) {
                nearByLatLngMap.put(key, latLngMap.get(key));
            }
        }
        return nearByLatLngMap;
    }

}
