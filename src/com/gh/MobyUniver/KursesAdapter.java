package com.gh.MobyUniver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class KursesAdapter extends BaseAdapter {
    Context context;
    LayoutInflater lInflater;
    ArrayList<KursesItems> objects;

    KursesAdapter(Context context, ArrayList<KursesItems> items) {
        this.context = context;
        objects = items;
        lInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // товар по позиции
    KursesItems getItemObj(int position) {
        return ((KursesItems) getItem(position));
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.kurses_items_adaper, parent, false);
        }

        KursesItems p = getItemObj(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        assert view != null;
        //noinspection ConstantConditions
        ((TextView) view.findViewById(R.id.kursDescr)).setText(p.kurs);
        ((ImageView) view.findViewById(R.id.kursImage)).setImageResource(p.img);


        return view;
    }
}