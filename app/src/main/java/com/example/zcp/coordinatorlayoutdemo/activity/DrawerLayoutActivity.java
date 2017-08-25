package com.example.zcp.coordinatorlayoutdemo.activity;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.zcp.coordinatorlayoutdemo.R;
import com.squareup.picasso.Picasso;

public class DrawerLayoutActivity extends AppCompatActivity {
    FrameLayout container;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    Toolbar toolbar;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        fragmentManager=getSupportFragmentManager();
        //添加ui界面
        if(fragmentManager.findFragmentByTag("main")==null){
//            container.add
            MainFragment mainFragment=new MainFragment();
            Bundle bundle=new Bundle();
            bundle.putString("param1","这是主界面");
            mainFragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.container,mainFragment,"main").commit();
            fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    Snackbar.make(navigationView,"后退Stack变化",Snackbar.LENGTH_SHORT).show();

                }
            });
        }
        container=(FrameLayout)findViewById(R.id.container);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        navigationView=(NavigationView)findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);

        //appbar的导航图片
        toolbar=(Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        toolbar.setNavigationIcon(R.mipmap.ic_list_black_48dp);
        //设置改变图片的颜色
       ColorStateList colorStateLis= ColorStateList.valueOf(getResources().getColor(R.color.dark));
       Drawable drawable= ContextCompat.getDrawable(this,R.mipmap.ic_list_black_48dp);
        DrawableCompat.setTintList(drawable,colorStateLis);

        toolbar.setNavigationIcon(drawable);
        toolbar.setTitle("navigationView");
        toolbar.setTitleTextColor(getResources().getColor(R.color.dark));

        //设置top点击事件
      View headerView=navigationView.getHeaderView(0);
       ImageView circleView=(ImageView) headerView.findViewById(R.id.imageView);
        Picasso.with(this).load("http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg").resize(200,200).transform(new CircleTransform()).into(circleView);

        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(navigationView,"这是点击头部区域",Snackbar.LENGTH_SHORT).show();
            }
        });

        //设置底部item事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                 if(item.getItemId()==R.id.wallet){
//                     Snackbar.make(navigationView,"这是点击item",Snackbar.LENGTH_SHORT).show();
                     replaceFragment("这是点赞界面","first");

                 }else if(item.getItemId()==R.id.favorite){
                     replaceFragment("这是收藏界面","second");

                 }else if(item.getItemId()==R.id.photo){
                     replaceFragment("这是相册界面","third");
                 }
                item.setChecked(true);
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });
    }


    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    private void replaceFragment(String content,String tag){
        CollectFragment collectFragment=new CollectFragment();
        Bundle bundle=new Bundle();
        bundle.putString("param1",content);
        collectFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.container,collectFragment,tag).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_settings){
            Snackbar.make(navigationView,"这是设置按钮",Snackbar.LENGTH_SHORT).show();
            return true;
        }
        if(item.getItemId()==android.R.id.home){
            drawerLayout.openDrawer(navigationView);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
