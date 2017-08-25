package com.example.zcp.coordinatorlayoutdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.zcp.coordinatorlayoutdemo.R;

public class AppBarActivityDemo extends AppCompatActivity {
//    设置的layout_scrollFlags有如下几种选项：
//
//    scroll: 所有想滚动出屏幕的view都需要设置这个flag- 没有设置这个flag的view将被固定在屏幕顶部。
//    enterAlways: 这个flag让任意向下的滚动都会导致该view变为可见，启用快速“返回模式”。
//    enterAlwaysCollapsed: 当你的视图已经设置minHeight属性又使用此标志时，你的视图只能已最小高度进入，只有当滚动视图到达顶部时才扩大到完整高度。
//    exitUntilCollapsed: 滚动退出屏幕，最后折叠在顶端。
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout. main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
}
