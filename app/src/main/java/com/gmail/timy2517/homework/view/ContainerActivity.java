package com.gmail.timy2517.homework.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gmail.timy2517.homework.R;
import com.gmail.timy2517.homework.map.MyMapFragment;

/**
 * Created by Artem Novik on 17.11.2016.
 */
public class ContainerActivity extends SingleFragmentActivity{

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private String[] mMenuItems = {"Категории", "Контакты"}; //FIXME

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mMenuItems));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                FragmentManager fragmentManager = getSupportFragmentManager();

                if(position == 0){
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, new CategoryListFragment())
                            .addToBackStack("CategoryListFragment")
                            .commit();
                    getSupportActionBar().setSubtitle("Категории");
                } else if (position == 1){
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, new MyMapFragment())
                            .addToBackStack("MyMapFragment")
                            .commit();
                    getSupportActionBar().setSubtitle("Карта");
                }
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        });
    }

    @Override
    protected Fragment createFragment() {
        return new CategoryListFragment();
        //return new MyMapFragment();

    }
}
