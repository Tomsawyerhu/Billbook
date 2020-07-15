package com.example.billbook;

import android.os.Bundle;
import android.util.Log;

import com.example.billbook.data.model.BillRecord;
import com.example.billbook.widget.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BillRecordActivity extends AppCompatActivity {
    @BindView(R.id.recycler)
    RecyclerView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_record);

        ButterKnife.bind(this);

        List<BillRecord> recordList=new ArrayList<>();
        BillRecord billRecord=new BillRecord();
        billRecord.setId(1);
        billRecord.setNote("买了杯奶茶");
        billRecord.setType(1);
        billRecord.setValue(100);
        billRecord.setTime("2020-07-12 00:00:00");
        recordList.add(billRecord);


        LinearLayoutManager manager=new LinearLayoutManager(this);
        view.setLayoutManager(manager);
        view.setAdapter(new RecyclerViewAdapter(recordList,new ListItemClickListener()));
    }

    class ListItemClickListener implements RecyclerViewAdapter.ListItemClickListener{

        @Override
        public void onListItemClick(int clickedItemIndex) {
            Log.d("click","click："+clickedItemIndex);

        }
    }
}