package com.gmail.timy2517.homework.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gmail.timy2517.homework.R;
import com.gmail.timy2517.homework.model.RestaurantBank;

/**
 * Created by artem on 5.2.17.
 */

public class RestaurantInfoFragment extends Fragment {

    public static final String ARG_RESTAURANT_ID = "restaurant_id";
    private static final int CALL_PHONE_PERMISSION_REQUEST_CODE = 1;
    Intent intent;

    public static RestaurantInfoFragment newInstance(int restaurant_id){
        Bundle args = new Bundle();
        args.putSerializable(ARG_RESTAURANT_ID, restaurant_id);

        RestaurantInfoFragment fragment = new RestaurantInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int restaurantId = (int) getArguments().getSerializable(ARG_RESTAURANT_ID);

        View view = inflater.inflate(R.layout.fragment_restaurant_info, container, false);

        TextView mRestaurantAddressTextView = (TextView) view.findViewById(R.id.restaurantInfoAddress);
        mRestaurantAddressTextView.setText(RestaurantBank.getInstance().getRestaurantByRestaurantId(restaurantId).getAddress());

        ListView mRestaurantPhonesList = (ListView) view.findViewById(R.id.restaurantInfoPhonesList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                RestaurantBank.getInstance().getRestaurantByRestaurantId(restaurantId).getPhones());
        mRestaurantPhonesList.setAdapter(adapter);

        mRestaurantPhonesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + ((TextView)view).getText().toString()));

                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]
                            {Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION_REQUEST_CODE);
                    return;
                }

                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CALL_PHONE_PERMISSION_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            startActivity(intent);
        }
    }
}
