package com.example.rommieskatta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LAdapter extends RecyclerView.Adapter<LAdapter.ViewHolder> {

    Context mcontext;
     ArrayList<ExpensesData> expensesData;
    public LAdapter(Context mcontext,ArrayList<ExpensesData> expensesData) {
        this.mcontext=mcontext;
        this.expensesData=expensesData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listview=layoutInflater.inflate(R.layout.recyclerview,parent,false);
        ViewHolder viewHolder=new ViewHolder(listview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(expensesData.get(position).getName());
        holder.details.setText(expensesData.get(position).getDetail());
        holder.money.setText("₹ "+expensesData.get(position).getAmount());
        holder.date.setText(expensesData.get(position).getDate());
    }


    @Override
    public int getItemCount() {
        return expensesData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       public TextView name;
        TextView details;
        TextView money;
        TextView date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mcontext=itemView.getContext();
            this.name=(TextView)itemView.findViewById(R.id.namer);
            this.details=(TextView)itemView.findViewById(R.id.detailsr);
            this.money=(TextView)itemView.findViewById(R.id.moneyr);
            this.date=(TextView)itemView.findViewById(R.id.dater);
        }
    }
}
