package thuyvtk.activity.laundry_store.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import thuyvtk.activity.laundry_store.R;
import thuyvtk.activity.laundry_store.fragment.AddNewFragment;
import thuyvtk.activity.laundry_store.fragment.ServiceFragment;
import thuyvtk.activity.laundry_store.fragment.OrderFragment;
import thuyvtk.activity.laundry_store.fragment.StoreAccountFragment;

public class MainActivity extends FragmentActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(this);
        loadFrament(new AddNewFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_service:
                fragment = new ServiceFragment();
                break;
            case R.id.navigation_order:
                fragment = new OrderFragment();
                break;
            case R.id.navigation_shop_account:
                fragment = new StoreAccountFragment();
                break;

            default:
                fragment = new AddNewFragment();

        }
        return loadFrament(fragment);
    }

    private boolean loadFrament(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
