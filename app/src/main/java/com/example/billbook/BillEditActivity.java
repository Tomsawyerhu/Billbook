package com.example.billbook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.LinearLayout;

import com.astuetz.PagerSlidingTabStrip;
import com.example.billbook.enums.MessageType;
import com.example.billbook.widget.adapter.PagerAdapter;
import com.example.billbook.widget.fragment.BillEditFragment;
import com.example.billbook.widget.view.Calculator;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BillEditActivity extends AppCompatActivity {
    @BindView(R.id.be_indicator)
    PagerSlidingTabStrip beIndicator;
    @BindView(R.id.be_linear)
    LinearLayout activity;
    @BindView(R.id.be_content)
    ViewPager beContent;
    @BindView(R.id.be_cal_wrapper)
    LinearLayout wrapper;
    @BindView(R.id.be_cal)
    Calculator calculator;

    private boolean isCalUp=false;
    private int currentPageIndex=0;
    private List<Fragment> list=new ArrayList<>();

    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (MessageType.from(msg.what)){
                case BACK:
                    //回到上一个activity
                    Intent intent=new Intent();
                    setResult(msg.what);
                    finish();
                    break;
                case CAL:
                    //改变计算器状态
                    if(isCalUp){
                        Log.d("cal","calState change to down");
                        calculator.dropdown(BillEditActivity.this);
                        isCalUp=false;
                    }else{
                        Log.d("cal","calState change to up");
                        calculator.popup(BillEditActivity.this);
                        isCalUp=true;
                    }
                    break;
                case UPDATE:
                    //更新计算器的结果到textview上
                    int result=Integer.parseInt(msg.obj.toString());
                    //更新textview
                    //Log.d("t","result:"+result);
                    ((BillEditFragment)list.get(currentPageIndex)).setCost(result);
            }

        }
    };

    public Handler getHandler() {
        return handler;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_edit);

        ButterKnife.bind(this);

        calculator.setMsgHandler(handler);

        BillEditFragment fragment1=new BillEditFragment();
        BillEditFragment fragment2=new BillEditFragment();
        fragment1.setFragmentIndex(0);
        fragment2.setFragmentIndex(1);
        list.add(fragment1);
        list.add(fragment2);

        calculator.addResultChangeListener();
        //初始时为隐藏状态
        calculator.dropdown(this);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),list);
        beContent.setAdapter(pagerAdapter);
        beContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.d("position","p"+position);
                currentPageIndex=position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setStyle(beIndicator);
        beIndicator.setViewPager(beContent);
    }

    private void setStyle(PagerSlidingTabStrip tabs){
        // 设置Tab底部选中的指示器 Indicator的颜色
        tabs.setIndicatorColor(Color.BLUE);
        //设置Tab标题文字的颜色
        tabs.setTextColor(Color.BLACK);
        // 设置Tab标题文字的大小
        tabs.setTextSize(40);
        //设置Tab底部分割线的颜色
        tabs.setUnderlineColor(Color.TRANSPARENT);
        // 设置点击某个Tab时的背景色,设置为0时取消背景色
        tabs.setTabBackground(0);
        // 设置Tab是自动填充满屏幕的
        tabs.setShouldExpand(true);
        //tab间的分割线
        tabs.setDividerColor(Color.GRAY);

    }

}