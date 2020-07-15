package com.example.billbook.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.billbook.R;
import com.example.billbook.data.model.BillRecord;
import com.example.billbook.utils.TimeUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<BillRecord> dataList;
    private int viewHolderCount=0;
    private ListItemClickListener listener;

    public RecyclerViewAdapter(List<BillRecord> list,ListItemClickListener listener){
        this.dataList=list;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem= R.layout.bill_record;
        LayoutInflater layoutInflater=LayoutInflater.from(context);

        View view = layoutInflater.inflate(layoutIdForListItem, parent, false);
        ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.setIndex(viewHolderCount);
        viewHolderCount+=1;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BillRecord billRecord=dataList.get(position);
        holder.setDay(billRecord.getTime());
        holder.setIncome(billRecord.getValue());
        holder.setOutcome(billRecord.getValue());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private int index;
        private View view;
        private TimeUtils timeUtils=new TimeUtils();

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view=itemView;
            itemView.setOnClickListener(this);
        }

        public void setDay(String rawStr){
            ((TextView)this.view.findViewById(R.id.br_header_day)).setText(timeUtils.getDay(rawStr));
        }

        public void setWeek(String rawStr){
            // todo 先写死数据
            ((TextView)this.view.findViewById(R.id.br_header_week)).setText("1");
        }

        public void setMonth(String rawStr){
            // todo 先写死数据
            ((TextView)this.view.findViewById(R.id.br_header_month)).setText("1");
        }

        /*
        public void setNote(String note){
            // todo 先写死数据
            ((TextView)this.view.findViewById(R.id.br_header_note)).setText("1");
        }
        */

        public void setIncome(int value){
            // todo 先写死数据
            ((TextView)this.view.findViewById(R.id.br_body_slot2_income)).setText("收入:"+value);
        }

        public void setOutcome(int value){
            // todo 先写死数据
            ((TextView)this.view.findViewById(R.id.br_body_slot2_outcome)).setText("支出:"+value);
        }


        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onListItemClick(index);
            }
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
