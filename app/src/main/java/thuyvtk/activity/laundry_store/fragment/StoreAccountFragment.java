package thuyvtk.activity.laundry_store.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import thuyvtk.activity.laundry_store.R;
import thuyvtk.activity.laundry_store.activity.EditProfileActivity;
import thuyvtk.activity.laundry_store.library.SharePreferenceLib;
import thuyvtk.activity.laundry_store.model.StoreDTO;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreAccountFragment extends Fragment {

    CircleImageView image_profile;
    ImageButton imgEdit;
    TextView txtStoreName;
    TextView txtAddress;
    StoreDTO currentUser;

    public StoreAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_account, container, false);
        defineView(view);
        getStoreProfile();
        editStoreProfile();
        return view;
    }

    private void defineView(View view) {
        image_profile = view.findViewById(R.id.image_profile);
        imgEdit = view.findViewById(R.id.imgEdit);
        txtStoreName = view.findViewById(R.id.txtStoreName);
        txtAddress = view.findViewById(R.id.txtAddress);
    }

    private void editStoreProfile () {
        image_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getStoreProfile() {
        SharePreferenceLib sharePreferenceLib = new SharePreferenceLib(getContext());
        currentUser = sharePreferenceLib.getUser();
        txtStoreName.setText(currentUser.getStoreName());
        txtAddress.setText(currentUser.getAddress());
        Picasso.with(getContext()).load(sharePreferenceLib.getUser().getImageUrl()).into(image_profile);
    }

}
