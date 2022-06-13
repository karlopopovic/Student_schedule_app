package com.example.student_schedule_app.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student_schedule_app.R;
import com.example.student_schedule_app.listeners.OnLongDayViewItemClickListener;
import com.example.student_schedule_app.model.Day;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayView> {


    private ArrayList<Day> days;
    private OnLongDayViewItemClickListener listener;
    private SimpleDateFormat formatter;


    public DayAdapter(ArrayList<Day> days) {
        this.days = days;
    }

    public DayAdapter(ArrayList<Day> days, OnLongDayViewItemClickListener listener) {
        this.days = days;
        this.listener = listener;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ITALY);
    }

    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }

    @NonNull
    @Override
    public DayAdapter.DayView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_view,parent,false);
        return new DayView(view);
    }



    @Override
    public void onBindViewHolder(@NonNull DayAdapter.DayView holder, int position) {
        holder.desc.setText( days.get(position).getDescription() );
        holder.subDesc.setText(days.get(position).getSubDescription());

        if(listener!=null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.OnLongClick(position);
                    return true;
                }
            });
        }

        holder.card.setCardBackgroundColor(Color.BLACK);
        holder.subDesc.setTextColor(Color.WHITE);
        holder.desc.setTextColor(Color.WHITE);
        holder.bTime.setTextColor(Color.WHITE);
        holder.eTime.setTextColor(Color.WHITE);

        try {
            Date beginDate = formatter.parse(days.get(position).getBegin_time());
            Date endDate = formatter.parse(days.get(position).getEnd_time());
            holder.bTime.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(beginDate));
            holder.eTime.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return days.size();
    }

    public static class DayView extends RecyclerView.ViewHolder{

        private TextView desc, subDesc, bTime, eTime;
        private CardView card;

        public DayView(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.card);
            desc = itemView.findViewById(R.id.desc);
            subDesc = itemView.findViewById(R.id.subDesc);
            bTime = itemView.findViewById(R.id.dayBegin);
            eTime = itemView.findViewById(R.id.dayEnd);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.pop_up);
        }
    }
}
