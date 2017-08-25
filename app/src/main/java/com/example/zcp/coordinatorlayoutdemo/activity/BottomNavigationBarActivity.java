package com.example.zcp.coordinatorlayoutdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.zcp.coordinatorlayoutdemo.R;
import com.example.zcp.coordinatorlayoutdemo.ui.MyAdapter;
import com.example.zcp.coordinatorlayoutdemo.ui.MyItemDecoration;

import java.util.ArrayList;

public class BottomNavigationBarActivity extends AppCompatActivity {

    BottomNavigationBar bottomNavigationBar;
    RecyclerView recyclerView;
    ArrayList<String>  list=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_bar_activity);
         recyclerView=(RecyclerView)findViewById(R.id.recycleview);
         bottomNavigationBar=(BottomNavigationBar)findViewById(R.id.bottom_navigation_bar);
       //设置mode
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        //设置背景样式颜色
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setBarBackgroundColor(android.R.color.white);
        //设置item
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_search_black_24dp,"item1").setInActiveColorResource(android.R.color.holo_orange_light));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_search_black_24dp,"item2").setInActiveColorResource(android.R.color.holo_red_light));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_search_black_24dp,"item3").setActiveColorResource(android.R.color.holo_green_light));
        BadgeItem badgeItem=new BadgeItem();
        badgeItem.setBackgroundColorResource(android.R.color.holo_red_light);
        badgeItem.setText("2");
        badgeItem.setTextColorResource(android.R.color.white);
        badgeItem.setGravity(Gravity.RIGHT|Gravity.TOP);
        badgeItem.setHideOnSelect(true);

        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_search_black_24dp,"item4").setActiveColorResource(android.R.color.holo_blue_light).setBadgeItem(badgeItem));
        //初始化
        bottomNavigationBar.setFirstSelectedPosition(0);
        bottomNavigationBar.initialise();


        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

        //设置适配器
        for(int i=0;i<15;i++){
            list.add("测试"+i);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
        recyclerView.addItemDecoration(new MyItemDecoration());
        MyAdapter adapter=new MyAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }
}
