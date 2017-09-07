package cn.jhc.startdemo.main.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.jhc.startdemo.R;
import cn.jhc.startdemo.common.bean.NewsResultsEntity;

/**
 * Created by Administrator on 2017/9/5.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyHold> {

    private List<NewsResultsEntity> list;

    public MainAdapter(List<NewsResultsEntity> data) {
        list = data;
    }

    public void addData(List<NewsResultsEntity> data) {
        list.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public MyHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recyclerview, parent, false);
        return new MyHold(root);
    }

    @Override
    public void onBindViewHolder(MyHold holder, int position) {
        holder.des.setText(list.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHold extends RecyclerView.ViewHolder {

        private TextView des;

        public MyHold(View itemView) {
            super(itemView);
            des = itemView.findViewById(R.id.describe);
        }
    }
}
