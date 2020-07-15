package com.example.billbook.widget.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.billbook.R;
import com.example.billbook.enums.Operator;

import androidx.annotation.Nullable;

import static com.example.billbook.enums.MessageType.UPDATE;

public class Calculator extends GridLayout implements View.OnClickListener {
    private Handler msgHandler;

    View view;

    TextView result;
    Button clear;
    Button equal;
    Button add;
    Button minus;
    Button mult;
    Button div;

    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button zero;

    private int value=0;
    private Operator currentOperator;

    public Calculator(Context context) {
        this(context,null);
    }

    public Calculator(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public Calculator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //将组件挂载到当前类上
        this.view= (View) View.inflate(context, R.layout.calculator,this);
        result=(TextView) view.findViewById(R.id.cal_result);
        clear=(Button)view.findViewById(R.id.cal_clear);
        add=(Button) view.findViewById(R.id.cal_key_add);
        minus=(Button) view.findViewById(R.id.cal_key_minus);
        mult=(Button) view.findViewById(R.id.cal_key_mult);
        div=(Button) view.findViewById(R.id.cal_key_div);
        equal=(Button) view.findViewById(R.id.cal_key_equal);

        one=(Button) view.findViewById(R.id.cal_key_one);
        two=(Button) view.findViewById(R.id.cal_key_two);
        three=(Button) view.findViewById(R.id.cal_key_three);
        four=(Button) view.findViewById(R.id.cal_key_four);
        five=(Button) view.findViewById(R.id.cal_key_five);
        six=(Button) view.findViewById(R.id.cal_key_six);
        seven=(Button) view.findViewById(R.id.cal_key_seven);
        eight=(Button) view.findViewById(R.id.cal_key_eight);
        nine=(Button) view.findViewById(R.id.cal_key_nine);
        zero=(Button) view.findViewById(R.id.cal_key_zero);

        add.setOnClickListener(this);
        minus.setOnClickListener(this);
        mult.setOnClickListener(this);
        div.setOnClickListener(this);
        equal.setOnClickListener(this);
        clear.setOnClickListener(this);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);

    }

    public Handler getMsgHandler() {
        return msgHandler;
    }

    public void setMsgHandler(Handler msgHandler) {
        this.msgHandler = msgHandler;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cal_clear:
                clear();
                break;
            case R.id.cal_key_add:
                currentOperator=Operator.ADD;
                break;
            case R.id.cal_key_minus:
                currentOperator=Operator.MINUS;
                break;
            case R.id.cal_key_mult:
                currentOperator=Operator.MULTIPLY;
                break;
            case R.id.cal_key_div:
                currentOperator=Operator.DIVIDE;
                break;
            case R.id.cal_key_equal:
                //更新ui
                this.result.setText(String.valueOf(value));
                break;
            case R.id.cal_key_one:
                doCal(1);
                break;
            case R.id.cal_key_two:
                doCal(2);
                break;
            case R.id.cal_key_three:
                doCal(3);
                break;
            case R.id.cal_key_four:
                doCal(4);
                break;
            case R.id.cal_key_five:
                doCal(5);
                break;
            case R.id.cal_key_six:
                doCal(6);
                break;
            case R.id.cal_key_seven:
                doCal(7);
                break;
            case R.id.cal_key_eight:
                doCal(8);
                break;
            case R.id.cal_key_nine:
                doCal(9);
                break;
            case R.id.cal_key_zero:
                doCal(0);
                break;
        }

    }

    public void addResultChangeListener(){
        result.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("tc","text change to "+editable.toString());
                Message message=new Message();
                message.what=UPDATE.getType();
                message.obj=editable.toString();
                msgHandler.sendMessage(message);
            }
        });
    }

    public void popup(Context context){
        //拉起键盘
        /*
        view.setX(100);
        view.setY(100);
        view.bringToFront();

         */
        Animation animation= AnimationUtils.loadAnimation(context, R.animator.popup_animation);
        view.startAnimation(animation);
        view.setVisibility(VISIBLE);
   }

   public void dropdown(Context context){
        //放下键盘
       Animation animation= AnimationUtils.loadAnimation(context, R.animator.dropdown_animation);
       view.startAnimation(animation);
       view.setVisibility(INVISIBLE);
   }

    private void clear(){
        //清空结果
        result.setText("0");
        this.value=0;
    }

    private void doCal(int i){
        if(this.currentOperator==null){
            //乘10+i
            this.value=this.value*10+i;
            //更新ui
            this.result.setText(String.valueOf(value));
        }else{
            if(this.currentOperator==Operator.ADD){
                this.value+=i;
            }
            if(this.currentOperator==Operator.MINUS){
                this.value-=i;
            }
            if(this.currentOperator==Operator.MULTIPLY){
                this.value*=i;
            }
            if(this.currentOperator==Operator.DIVIDE){
                this.value/=i;
            }
            this.currentOperator=null;
        }

    }
}
