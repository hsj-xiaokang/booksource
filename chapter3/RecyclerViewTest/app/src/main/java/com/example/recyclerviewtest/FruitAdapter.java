package com.example.recyclerviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder>{
     //数据源
    private List<Fruit> mFruitList;
    //holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        //水果对象
        View fruitView;
        //自定义布局的图像
        ImageView fruitImage;
        //自定义布局的文字
        TextView fruitName;

        public ViewHolder(View view) {
            super(view);
            fruitView = view;
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
        }
    }
     //构造器
    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //自定义布局view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
       //实例化viewHolder
        final ViewHolder holder = new ViewHolder(view);
        //水果对象的点击事件
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当前的水果实例对象位置position
                int position = holder.getAdapterPosition();
                //根据当前的水果对象位置获取当前的水果实例对象
                Fruit fruit = mFruitList.get(position);
                //toast提示
                Toast.makeText(v.getContext(), "you clicked view " + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        //水果对象属性图片的点击事件
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(), "you clicked image " + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    /**
     * 绑定viewHolder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }

    /**
     * 重写count
     * @return
     */
    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

}