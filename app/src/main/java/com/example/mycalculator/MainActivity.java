package com.example.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private List<Cal_Result> myCalResultList=new ArrayList<>();

    private RecyclerView myResultContRecyclerView;

    private ResultAdapter resultAdapter;

    private static final String TAG = "MainActivity";

    private Button cal_one;
    private Button cal_two;
    private Button cal_three;
    private Button cal_four;
    private Button cal_five;
    private Button cal_six;
    private Button cal_seven;
    private Button cal_eight;
    private Button cal_night;
    private Button cal_zero;
    private Button cal_clear;
    private Button cal_delete;
    private Button cal_equal;
    private Button cal_percent;
    private Button cal_point;
    private Button cal_plus;
    private Button cal_minus;
    private Button cal_multi;
    private Button cal_divine;

    StringBuilder stringBuilder=new StringBuilder("0");
    String show_string_temp;
    Cal_Result result_temp=new Cal_Result("0");
    int presentLine;

    boolean PRESENT_LINE_FINISH=true;

    int cal_num=0;
    String temp_1;
    String temp_2;
    String temp_3;

    boolean isMultiOrDivide=false;
    boolean isPlusOrMinus=false;

    String equal_show;

    char char_temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initShow();
        myResultContRecyclerView =(RecyclerView)findViewById(R.id.cal_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        myResultContRecyclerView.setLayoutManager(layoutManager);
        resultAdapter=new ResultAdapter(myCalResultList);
        myResultContRecyclerView.setAdapter(resultAdapter);

        myCalResultList.add(result_temp);
        presentLine++;

        cal_one=(Button)findViewById(R.id.cal_one);
        cal_two=(Button)findViewById(R.id.cal_two);
        cal_three=(Button)findViewById(R.id.cal_three);
        cal_four=(Button)findViewById(R.id.cal_four);
        cal_five=(Button)findViewById(R.id.cal_five);
        cal_six=(Button)findViewById(R.id.cal_six);
        cal_seven=(Button)findViewById(R.id.cal_seven);
        cal_eight=(Button)findViewById(R.id.cal_eight);
        cal_night=(Button)findViewById(R.id.cal_night);
        cal_zero=(Button)findViewById(R.id.cal_zero);
        cal_point=(Button)findViewById(R.id.cal_point);
        cal_percent=(Button)findViewById(R.id.cal_percent);
        cal_delete=(Button)findViewById(R.id.cal_delete);
        cal_clear=(Button)findViewById(R.id.cal_clear);
        cal_plus=(Button)findViewById(R.id.cal_plus);
        cal_minus=(Button)findViewById(R.id.cal_minus);
        cal_multi=(Button)findViewById(R.id.cal_multi);
        cal_divine=(Button)findViewById(R.id.cal_divide);
        cal_equal=(Button)findViewById(R.id.cal_equal);

        cal_one.setOnClickListener(this);
        cal_two.setOnClickListener(this);
        cal_three.setOnClickListener(this);
        cal_four.setOnClickListener(this);
        cal_five.setOnClickListener(this);
        cal_six.setOnClickListener(this);
        cal_seven.setOnClickListener(this);
        cal_eight.setOnClickListener(this);
        cal_night.setOnClickListener(this);
        cal_zero.setOnClickListener(this);
        cal_clear.setOnClickListener(this);
        cal_delete.setOnClickListener(this);
        cal_point.setOnClickListener(this);
        cal_percent.setOnClickListener(this);
        cal_equal.setOnClickListener(this);
        cal_plus.setOnClickListener(this);
        cal_minus.setOnClickListener(this);
        cal_multi.setOnClickListener(this);
        cal_divine.setOnClickListener(this);





    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cal_one:
                addNum("1");
                break;

            case R.id.cal_two:
                addNum("2");
                break;

            case R.id.cal_three:
                addNum("3");
                break;

            case R.id.cal_four:
                addNum("4");
                break;

            case R.id.cal_five:
                addNum("5");
                break;

            case R.id.cal_six:
                addNum("6");
                break;

            case R.id.cal_seven:
                addNum("7");
                break;

            case R.id.cal_eight:
                addNum("8");
                break;

            case R.id.cal_night:
                addNum("9");
                break;

            case R.id.cal_zero:
               addNum("0");
                break;

            case R.id.cal_equal:
                if ((stringBuilder.toString()=="") || isSingleSign()){
                    stringBuilder.delete(0,stringBuilder.length());
                    refreshPresentShow("=");
                    //Log.d(TAG, stringBuilder.toString());
                    cal_num--;
                    if (cal_num==0){
                        show_string_temp=temp_1;
                    }else if (cal_num==1){
                        show_string_temp=temp_2;
                    }
                    equal_show=showCalculation();
                    refreshPresentShow(equal_show);
                }else {
                    upMoveOneLineShow("=");
                    equal_show=showCalculation();
                    refreshPresentShow(equal_show);
                }
                break;

            case R.id.cal_plus:
                if ((stringBuilder.toString()=="") || isSingleSign()){
                    stringBuilder.delete(0,stringBuilder.length());
                    refreshPresentShow("+");
                }else {
                   upMoveOneLineShow("+");
                    if (cal_num==0){
                        temp_1=show_string_temp;
                        cal_num++;
                        isPlusOrMinus=true;
                        isMultiOrDivide=false;
                    }else if (cal_num==1){
                        if (isMultiOrDivide){
                            temp_2=show_string_temp;
                            temp_1=doSpecialCalculation(temp_1,temp_2);
                            isMultiOrDivide=false;
                            isPlusOrMinus=true;
                        }else if (isPlusOrMinus){
                            temp_2 = show_string_temp;
                            temp_1=doNormalCalculation(temp_1,temp_2);
                        }
                    }else if (cal_num==2){
                        temp_3 = show_string_temp;
                        temp_1 = doCalculation(temp_1, temp_2, temp_3);
                        cal_num--;
                    }
                }
                break;

            case R.id.cal_minus:
                if ((stringBuilder.toString()=="") || isSingleSign()){
                    stringBuilder.delete(0,stringBuilder.length());
                    refreshPresentShow("-");
                }else {
                    upMoveOneLineShow("-");
                    if (cal_num==0){
                        temp_1=show_string_temp;
                        cal_num++;
                        isPlusOrMinus=true;
                        isMultiOrDivide=false;
                    }else if (cal_num==1){
                        if (isMultiOrDivide){
                            temp_2=show_string_temp;
                            temp_1=doSpecialCalculation(temp_1,temp_2);
                            isMultiOrDivide=false;
                            isPlusOrMinus=true;
                        }else if (isPlusOrMinus){
                            temp_2 = show_string_temp;
                            temp_1=doNormalCalculation(temp_1,temp_2);
                        }
                    }else if (cal_num==2){
                        temp_3 = show_string_temp;
                        temp_1 = doCalculation(temp_1, temp_2, temp_3);
                        cal_num--;
                    }
                }
                break;

            case R.id.cal_multi:
                if ((stringBuilder.toString()=="") || isSingleSign()){
                    stringBuilder.delete(0,stringBuilder.length());
                    refreshPresentShow("*");
                }else {
                    upMoveOneLineShow("*");

                    if (cal_num==0){
                        temp_1=show_string_temp;
                        cal_num++;
                        isMultiOrDivide=true;
                        isPlusOrMinus=false;
                    }else if (cal_num==1){
                        if (isMultiOrDivide){
                            temp_2=show_string_temp;
                            temp_1=doSpecialCalculation(temp_1,temp_2);
                        }else if (isPlusOrMinus){
                            temp_2=show_string_temp;
                            cal_num++;
                        }
                    }else if (cal_num==2){
                        temp_3=show_string_temp;
                        temp_2=doSpecialCalculation(temp_2,temp_3);
                        Log.d(TAG, "multi   "+show_string_temp);
                    }

                }

                break;

            case R.id.cal_divide:
                if ((stringBuilder.toString()=="") || isSingleSign()){
                    stringBuilder.delete(0,stringBuilder.length());
                    refreshPresentShow("/");
                }else {
                    upMoveOneLineShow("/");

                    if (cal_num==0){
                        temp_1=show_string_temp;
                        cal_num++;
                        isMultiOrDivide=true;
                        isPlusOrMinus=false;
                    }else if (cal_num==1){
                        if (isMultiOrDivide){
                            temp_2=show_string_temp;
                            temp_1=doSpecialCalculation(temp_1,temp_2);
                        }else if (isPlusOrMinus){
                            temp_2=show_string_temp;
                            cal_num++;
                        }
                    }else if (cal_num==2){
                        temp_3=show_string_temp;
                        temp_2=doSpecialCalculation(temp_2,temp_3);
                        Log.d(TAG, "multi   "+show_string_temp);
                    }

                }

                break;

            case R.id.cal_delete:
                if (stringBuilder.charAt(0)=='='){
                    stringBuilder.delete(0,stringBuilder.length());
                    stringBuilder.append("0");
                }else {
                    if (stringBuilder.length()!=1){
                        stringBuilder.deleteCharAt(stringBuilder.length()-1);
                    }else {
                        stringBuilder.delete(0,stringBuilder.length());
                        stringBuilder.append("0");
                    }
                }
                show_string_temp=stringBuilder.toString();
                result_temp.setResult_content(show_string_temp);
                resultAdapter.notifyItemChanged(presentLine);
                myResultContRecyclerView.scrollToPosition(presentLine);
                break;

            case R.id.cal_clear:
/*                stringBuilder.delete(0,stringBuilder.length());
                stringBuilder.append("0");
                show_string_temp=stringBuilder.toString();
                result_temp.setResult_content(show_string_temp);
                resultAdapter.notifyItemChanged(presentLine);
                myResultContRecyclerView.scrollToPosition(presentLine);*/

                myResultContRecyclerView.removeAllViews();
                myCalResultList.removeAll(myCalResultList);
                initShow();
                result_temp=new Cal_Result("0");
                myCalResultList.add(result_temp);
                presentLine++;
                stringBuilder.delete(0,stringBuilder.length());
                stringBuilder.append("0");
                resultAdapter.notifyDataSetChanged();
                break;

            case R.id.cal_point:
                addNum(".");
                if (stringBuilder.length()==2){
                    stringBuilder.deleteCharAt(1);
                    if (isSingleSign()){
                        refreshPresentShow("0.");
                    }else {
                        refreshPresentShow("0");
                    }
                }
                break;


            default:
                break;

        }

    }

    private void initShow(){
        Cal_Result result1=new Cal_Result("");
        Cal_Result result2=new Cal_Result("");
        Cal_Result result3=new Cal_Result("");
        Cal_Result result4=new Cal_Result("");

        myCalResultList.add(result1);
        myCalResultList.add(result2);
        myCalResultList.add(result3);
        myCalResultList.add(result4);

        presentLine=myCalResultList.size()-1;

    }

    private void refreshPresentShow(String num){
        stringBuilder.append(num);
        show_string_temp=stringBuilder.toString();
        result_temp.setResult_content(show_string_temp);
        resultAdapter.notifyItemChanged(presentLine);
        myResultContRecyclerView.scrollToPosition(presentLine);
    }

    private void addNum(String num)
    {
        if (stringBuilder.charAt(0)=='='){
            upMoveOneLineShow(num);
        }else {
            if (stringBuilder.toString().equals("0")){
                stringBuilder.deleteCharAt(0);
                refreshPresentShow(num);
            }else {
                if (stringBuilder.length() == 2) {
                    if (stringBuilder.charAt(1) == '0') {
                        stringBuilder.deleteCharAt(1);
                        if (isSingleSign()){
                            refreshPresentShow(num);
                        }else {
                            stringBuilder.append("0");
                            refreshPresentShow(num);
                        }

                    } else {
                        refreshPresentShow(num);
                    }
                } else {
                    refreshPresentShow(num);
                }
            }
        }

    }





    private boolean isSingleSign(){
        if((stringBuilder.toString().equals("+"))||(stringBuilder.toString().equals("-"))||(stringBuilder.toString().equals("*"))||(stringBuilder.toString().equals("/"))){
            return true;
        }else {
            return false;
        }
    }

    private void upMoveOneLineShow(String sign){
        show_string_temp=stringBuilder.toString();
        result_temp.setResult_content(show_string_temp);
        resultAdapter.notifyItemChanged(presentLine);
        myResultContRecyclerView.scrollToPosition(presentLine);

        stringBuilder.delete(0,stringBuilder.length());
        if (sign=="."){
            sign="0"+sign;
        }
        result_temp=new Cal_Result(sign);
        stringBuilder.append(sign);
        myCalResultList.add(result_temp);
        presentLine++;
        resultAdapter.notifyItemInserted(presentLine);
        myResultContRecyclerView.scrollToPosition(presentLine);



    }
    private String doSpecialCalculation(String temp_special_11,String temp_special_2){
        float data_1,data_2;
        data_1=noSignDigit(temp_special_11);
        data_2=noSignDigit(temp_special_2);

        if (temp_special_2.charAt(0)=='*'){
            return String.valueOf(data_1*data_2);
        }else
        {
            return String.valueOf(data_1/data_2);
        }
    }
    private String doNormalCalculation(String temp_special_11,String temp_special_2){
        float data_1,data_2;
        data_1=noSignDigit(temp_special_11);
        data_2=noSignDigit(temp_special_2);
        return String.valueOf(data_1+data_2);
    }


    private String doCalculation(String temp_1,String temp_2,String temp_3){
        float data_1,data_2,data_3;
        data_1=noSignDigit(temp_1);
        data_2=noSignDigit(temp_2);
        data_3=noSignDigit(temp_3);

        if ((temp_2.charAt(0)=='+')||(temp_2.charAt(0)=='-')){
            if ((temp_3.charAt(0)=='+')||(temp_3.charAt(0)=='-')){
                return String.valueOf(data_1+data_2+data_3);
            }else if (temp_3.charAt(0)=='*'){
                return String.valueOf(data_1+data_2*data_3);
            }else {
                return String.valueOf(data_1+data_2/data_3);
            }
        }else if (temp_2.charAt(0)=='*'){
            if ((temp_3.charAt(0)=='+')||(temp_3.charAt(0)=='-')){
                return String.valueOf(data_1*data_2+data_3);
            }else if (temp_3.charAt(0)=='*'){
                return String.valueOf(data_1*data_2*data_3);
            }else {
                return String.valueOf(data_1*data_2/data_3);
            }
        }else {
            if ((temp_3.charAt(0)=='+')||(temp_3.charAt(0)=='-')){
                return String.valueOf(data_1/data_2+data_3);
            }else if (temp_3.charAt(0)=='*'){
                return String.valueOf(data_1/data_2*data_3);
            }else {
                return String.valueOf(data_1/data_2/data_3);
            }
        }
    }

    private float noSignDigit(String data){
        if ((data.charAt(0)=='+')||(data.charAt(0)=='-')){
            return Float.parseFloat(data);
        }else if ((data.charAt(0)=='*')||(data.charAt(0)=='/')||(data.charAt(0)=='=')){
            String modify_data=data.substring(1,data.length());
            return Float.parseFloat(modify_data);
        }else {
            return Float.parseFloat(data);
        }
    }

    private String  showCalculation(){
        if (cal_num==0){
            if (show_string_temp.charAt(0)=='='){
                return show_string_temp.substring(1,show_string_temp.length());
            }else{
                return show_string_temp;
            }
        }else if (cal_num==1){
            temp_2=show_string_temp;
            if (isPlusOrMinus){
                cal_num--;
                return (doNormalCalculation(temp_1,temp_2));
            }else {
                cal_num--;
                return (doSpecialCalculation(temp_1,temp_2));
            }
        }else {
            temp_3=show_string_temp;
            cal_num=0;
            return (doCalculation(temp_1,temp_2,temp_3));
        }

    }
}
