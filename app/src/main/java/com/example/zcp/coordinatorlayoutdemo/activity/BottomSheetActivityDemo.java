package com.example.zcp.coordinatorlayoutdemo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zcp.coordinatorlayoutdemo.R;
import com.example.zcp.coordinatorlayoutdemo.ui.MyAdapter;
import com.example.zcp.coordinatorlayoutdemo.ui.MyItemDecoration;

import java.util.ArrayList;

public class BottomSheetActivityDemo extends AppCompatActivity {
    String TAG="BottomSheetActivityDemo";
//    View view;
    RecyclerView  recyclerView;
    BottomSheetBehavior bottomSheetBehavior;
    ArrayList<String>  list=new ArrayList<>();
    BottomSheetDialog bottomSheetDialog;
    MyBottomSheetDialogFragment sheetDialogFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet_demo);
//        view=(View)findViewById(R.id.bottom_sheet);
        recyclerView=(RecyclerView)findViewById(R.id.bottom_sheet) ;
        recyclerView.addItemDecoration(new MyItemDecoration());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i=0;i<10;i++){
            list.add("item"+i);
        }
        MyAdapter adapter=new MyAdapter(this,list);
        recyclerView.setAdapter(adapter);


        //将view和bottomSheetbehavior关联起来
        bottomSheetBehavior=BottomSheetBehavior.from(recyclerView);
        TextView textView=(TextView)findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.i(TAG,"------------------newState--"+newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

       //设置bottomSheetDialog
        Button button=(Button)findViewById(R.id.button);
        Button button2=(Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment();
            }
        });


    }


    private void showDialog(){
        bottomSheetDialog=new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.add_bottom_view);
        bottomSheetDialog.show();
    }

    private void showFragment(){
        sheetDialogFragment= MyBottomSheetDialogFragment.getInstance();
        sheetDialogFragment.show(getSupportFragmentManager(),"DialogFragment");
    }
}
