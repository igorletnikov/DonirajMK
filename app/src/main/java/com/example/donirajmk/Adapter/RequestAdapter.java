package com.example.donirajmk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.donirajmk.DataModels.RequestDataModel;
import com.example.donirajmk.R;

import org.w3c.dom.Text;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {

    private List<RequestDataModel> dataSet;
    private Context context;

    public RequestAdapter(
            List<RequestDataModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.request_item_layout, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,
                                 final int position) {
            holder.massage.setText(dataSet.get(position).getMessage());
            Glide.with(context).load(dataSet.get(position).getUrl()).into(holder.imageView);
            holder.callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //for later
                }
            });

            holder.shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //later
                }
            });

    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView massage;
        ImageView imageView,callButton,shareButton;

        ViewHolder(final View itemView) {
            super(itemView);
            massage = itemView.findViewById(R.id.massage);
            imageView = itemView.findViewById(R.id.image);
            callButton = itemView.findViewById(R.id.callButton);
            shareButton = itemView.findViewById(R.id.shareButton);

        }

    }

}
