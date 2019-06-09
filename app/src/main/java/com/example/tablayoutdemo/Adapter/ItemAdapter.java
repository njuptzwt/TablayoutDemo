package com.example.tablayoutdemo.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tablayoutdemo.Model.Item;
import com.example.tablayoutdemo.R;

import java.util.List;

/**
 * 使用ViewHolder和Adapter来做recyclerView的适配
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> mItemlist;

    public ItemAdapter(List<Item> mItemlist) {
        this.mItemlist = mItemlist;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // 获取单个item的实例，构造ViewHolder,ViewHolder在RecyclerView显示
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item_layout, viewGroup,
                false);
        final ViewHolder holder = new ViewHolder(view);
        // 为每一个组件的点击添加监听事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Item item = mItemlist.get(position);
                Toast.makeText(v.getContext(), "you clicked view " + item.getDescription(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Item item = mItemlist.get(position);
                Toast.makeText(v.getContext(), "you clicked view " + item.getDescription(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder viewHolder, int i) {
        Item item = mItemlist.get(i);
        viewHolder.itemImage.setImageResource(item.getImageId());
        viewHolder.itemDescription.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return mItemlist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemDescription = itemView.findViewById(R.id.item_description);
        }
    }
}
