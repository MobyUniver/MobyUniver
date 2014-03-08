package com.gh.MobyUniver;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class KursesAdapter extends BaseAdapter {
    private static final String TAG ="RR" ;
    static  View view;
    Context context;
    LayoutInflater lInflater;
    ArrayList<KursesItems> objects;

    KursesAdapter(Context context, ArrayList<KursesItems> items) {
        this.context = context;
        objects = items;
        lInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       // new ImgGet().execute();
    }

    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e(TAG, "Error getting bitmap", e);
        }
        return bm;
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
         view = convertView;
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
    /*private class ImgGet extends AsyncTask<Void, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(Void... voids) {

            return getImageBitmap("http://gkurs.esy.es/images/1.png");
        }
        @Override
        protected void onPostExecute(Bitmap b) {
            ((ImageView) view.findViewById(R.id.kursImage)).setImageBitmap(b);
        }
    }*/

}