package com.kizias.thuchanh_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    Context context;
    ArrayList<SanPham> list;

    public Adapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.view_item, null);
            holder.tensp = view.findViewById(R.id.tensp);
            holder.soluong = view.findViewById(R.id.soluong);
            holder.dongia = view.findViewById(R.id.dongia);
            holder.tongtien = view.findViewById(R.id.tongtien);
            //holder.img = view.findViewById(R.id.img);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        SanPham sp = list.get(position);
        holder.tensp.setText(sp.getTensp());
        holder.soluong.setText(sp.getSoluong() + "");
        holder.dongia.setText(sp.getDongia() + "");
        holder.tongtien.setText((sp.getDongia() * sp.getSoluong()) + "");
        //int imgID = getResID(sp.img)
        //holder.img.setImageResource(imgID);
        return view;
    }

    public int getResID(String resName) {
        String pkgName = context.getPackageName();
        int resID = context.getResources().getIdentifier(resName, "drawable", pkgName);
        return resID;
    }

    class ViewHolder {
        TextView tensp, soluong, dongia, tongtien;
        ImageView img;
    }
}
