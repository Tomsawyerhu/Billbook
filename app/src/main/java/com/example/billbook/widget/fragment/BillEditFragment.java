package com.example.billbook.widget.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.billbook.BillEditActivity;
import com.example.billbook.R;
import com.example.billbook.data.model.BillRecord;
import com.example.billbook.data.table.BillOperationHelper;
import com.example.billbook.enums.MessageType;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static com.example.billbook.enums.MessageType.BACK;
import static com.example.billbook.enums.MessageType.CAL;
import static com.example.billbook.enums.MessageType.SAVE;

public class BillEditFragment extends Fragment {
    private View view;
    //tab index
    private int fragmentIndex;
    //activity
    private Context context;

    private HandlerThread dbHandlerThread=new HandlerThread("dbHandlerThread");
    //异步任务handler
    private Handler dbHandler;
    //与activity通信
    private Handler msgHandler;
    //支持数据库操作
    private BillOperationHelper helper;

    private boolean needRefresh=false;

    public int getFragmentIndex() {
        return fragmentIndex;
    }

    public void setFragmentIndex(int fragmentIndex) {
        this.fragmentIndex = fragmentIndex;
    }

    public void setCost(int value){
        ((TextView)view.findViewById(R.id.be_head)).setText(value+".00");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //数据库初始化
        if(helper==null)
            helper=new BillOperationHelper(context);

        if(view==null){
            view = inflater.inflate(R.layout.bill_edit_fragment, container,false);
        }

        if(fragmentIndex==1){
            //收入
            view.findViewById(R.id.be_divider).setBackgroundColor(getResources().getColor(R.color.red));
            ((TextView)view.findViewById(R.id.be_head)).setTextColor(getResources().getColor(R.color.red));
        }

        dbHandlerThread.start();
        dbHandler=new Handler(dbHandlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (MessageType.from(msg.what)){
                    case SAVE :
                        save();
                }
            }
        };

        //设置按钮点击监听
        setSaveListener();
        setCancelListener();
        setAgainListener();
        setChangeCalStatusListener();

        ViewGroup group = (ViewGroup) view.getParent();
        if(group!=null){
            group.removeView(view);
        }

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
        msgHandler=((BillEditActivity)context).getHandler();
        helper=new BillOperationHelper(context);
    }

    protected void setSaveListener(){
        ((Button)view.findViewById(R.id.be_button_save)).setOnClickListener((View.OnClickListener) view -> {
            Message message=new Message();
            message.what=SAVE.getType();
            dbHandler.sendMessage(message);
            //返回后需要刷新
            backToPreviousActivity(1);
        });
    }

    protected void setCancelListener(){
        ((Button)view.findViewById(R.id.be_button_cancel)).setOnClickListener((View.OnClickListener) view -> {
            clear();
            backToPreviousActivity(needRefresh?1:0);
        });
    }

    protected void setAgainListener(){
        ((Button)view.findViewById(R.id.be_button_again)).setOnClickListener((View.OnClickListener) view -> {
            Message message=new Message();
            message.what=SAVE.getType();
            dbHandler.sendMessage(message);
            needRefresh=true;
            clear();
        });
    }

    protected void setChangeCalStatusListener(){
        ((TextView)view.findViewById(R.id.be_head)).setOnClickListener((View.OnClickListener) view -> {
            Message message=new Message();
            message.what=CAL.getType();
            msgHandler.sendMessage(message);
        });
    }

    private void save(){
        BillRecord billRecord=new BillRecord();
        helper.insert(billRecord);
    }

    private void clear(){

    }

    private void backToPreviousActivity(int resultCode){
        //handler通知activity
        Message msg=new Message();
        msg.what= BACK.getType();
        msg.obj=resultCode;
        msgHandler.sendMessage(msg);
    }


}
