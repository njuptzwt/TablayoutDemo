package com.example.tablayoutdemo.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tablayoutdemo.Adapter.ItemAdapter;
import com.example.tablayoutdemo.Model.Item;
import com.example.tablayoutdemo.R;

import java.io.Serializable;
import java.util.List;

public class RecyclerViewFragment extends Fragment {

    static String Key="item_list";

    private RecyclerView recyclerView;

    public RecyclerViewFragment() {
        super();
    }
    // 生成RecyclerViewFragment的静态变量实例
    public static RecyclerViewFragment newInstace(List<Item> itemList){

        RecyclerViewFragment recyclerViewFragment=new RecyclerViewFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("itemlist",(Serializable)itemList);
        recyclerViewFragment.setArguments(bundle);
        // 利用bundle传对象,itemList过去
        return recyclerViewFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // 重写获取Fragment的方式,生成View布局
        View view=inflater.inflate(R.layout.fragment_recyclerview, container, false);
        recyclerView=view.findViewById(R.id.RV1);
        // 设置RecyclerView的Adapter
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        // 配置适配器
        Bundle bundle=getArguments();
        // 传递真实值
        ItemAdapter itemAdapter = new ItemAdapter((List<Item>)bundle.get("itemlist"));
        recyclerView.setAdapter(itemAdapter);
        return view;
    }
}
