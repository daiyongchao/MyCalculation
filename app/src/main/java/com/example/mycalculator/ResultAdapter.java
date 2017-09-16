package com.example.mycalculator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11 0011.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private List<Cal_Result> mResultsList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView resultView;

        public ViewHolder(View view){
            super(view);
            resultView=(TextView)view.findViewById(R.id.result_show);
        }
    }

    public ResultAdapter(List<Cal_Result> mresultslist){
        mResultsList=mresultslist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cal_Result  cal_result=mResultsList.get(position);
        holder.resultView.setText(cal_result.getResult_content());
    }

    @Override
    public int getItemCount() {
        return mResultsList.size();
    }
}
