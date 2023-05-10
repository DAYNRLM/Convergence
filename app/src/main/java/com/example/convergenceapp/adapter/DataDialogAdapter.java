package com.example.convergenceapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.convergenceapp.R;
import com.example.convergenceapp.database.dbBean.OtherMembersName;
import com.example.convergenceapp.database.entity.LanguageEntity;

import java.util.List;

public class DataDialogAdapter extends RecyclerView.Adapter<DataDialogAdapter.MyViewHolder>{


    List<OtherMembersName> memberData;
    Context context;


    public DataDialogAdapter(Context context, List<OtherMembersName> memberData)
    {
        this.memberData=memberData;
        this.context=context;

    }
    @NonNull
    @Override
    public DataDialogAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myDialogView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_custom_layout, parent, false);
        return new DataDialogAdapter.MyViewHolder(myDialogView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataDialogAdapter.MyViewHolder holder, int position) {
        holder.memberName.setText(memberData.get(position).getMembersName());

    }

    @Override
    public int getItemCount() {
         return memberData.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView memberName;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            memberName = (TextView) itemView.findViewById(R.id.memberName);

        }
    }

}
