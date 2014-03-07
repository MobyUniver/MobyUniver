package com.gh.MobyUniver;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class TestWeb extends Fragment {
    public static WebView tw;
    public static ImageView imgw;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_web,
                container, false);
        //tw.loadUrl("http:\\/\\/gkurs.esy.es\\/images\\/1.png");

        //JSONParse jParse = new JSONParse();
        //jParse.execute();
       imgw = (ImageView) view.findViewById(R.id.imageView);
        new ImgGet().execute();

        return view;

    }

    public static class JSONParse extends AsyncTask<Void, Void, JSONArray> {

        private static String url = "http://gkurs.esy.es/json.php";
        private static final String TAG_FIO = "FIO";

        @Override
        protected JSONArray doInBackground(Void... voids) {
            JSONParser jParser = new JSONParser();
            // Getting JSON from URL
            JSONArray json = jParser.getJSONFromUrl(url);
            return json;
        }

        @Override
        protected void onPostExecute(JSONArray json) {
            try {
                JSONObject c = json.getJSONObject(5);

              String  p = c.getString("FIO");
                //tw.setText(p);
               /* for(int i=0; i<0; i++){
                    c = json.getJSONObject(i);
                    fio.add(c.getString(TAG_FIO));
                }*/

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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
            Log.e("afsaef", "Error getting bitmap", e);
        }
        return bm;
    }
    private class ImgGet extends AsyncTask<Void, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(Void... voids) {

            return getImageBitmap("http://gkurs.esy.es/images/1.png");
        }
        @Override
        protected void onPostExecute(Bitmap b) {
            imgw.setImageBitmap(b);
        }
    }

}
