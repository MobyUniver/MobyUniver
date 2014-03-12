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
import android.widget.LinearLayout;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class KursesFragment extends Fragment {
    static ListView lvMain;
    static View view;
    static final int JSON_LENGTH = 6;
    static final Bitmap[] image = new Bitmap[JSON_LENGTH];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.kurses_layout_fragment,
                container, false);
        lvMain = (ListView) view.findViewById(R.id.lvMain);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        JSONParse jParse = new JSONParse();
        jParse.execute();
    }

    public class JSONParse extends AsyncTask<Void, Void, JSONArray> {
        private static final String url = "http://gkurs.esy.es/kurses.php";
        @Override
        protected JSONArray doInBackground(Void... voids) {
            JSONParser jParser = new JSONParser();
            // Getting JSON from URL
            JSONArray json = jParser.getJSONFromUrl(url);
            return json;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           view.findViewById(R.id.progressLayout).setVisibility(View.VISIBLE);
        }
        @Override
        protected void onPostExecute(JSONArray json) {
            view.findViewById(R.id.progressLayout).setVisibility(View.GONE);
            ArrayList<KursesItems> kurses = new ArrayList<KursesItems>();
            KursesAdapter adapter;
            int items_images[] = {
                    R.drawable.android,
                    R.drawable.html5,
                    R.drawable.java,
                    R.drawable.sql,
                    R.drawable.ubuntu,
                    R.drawable.unity3d
            };
            String img_url[] = new String[json.length()];
            try {
                JSONObject c;
                //image = new Bitmap[json.length()];
                for(int i=0; i< json.length(); i++){
                    c = json.getJSONObject(i);
                    img_url[i] = "http://gkurs.esy.es/images/"+c.getString("img");
                    kurses.add(new KursesItems(c.getString("id_kurs"), c.getString("title"), image[i]));
                    // Toast.makeText(getActivity(), img_url[i], Toast.LENGTH_SHORT).show();
                }
                ImgGet gth = new ImgGet();
                gth.execute(img_url);
                adapter = new KursesAdapter(getActivity(), kurses);
                lvMain.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private class ImgGet extends AsyncTask<String, Void, Void> {
        public  Bitmap getImageBitmap(String url) {
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
                Log.e("GetBitmap: ", "Error getting bitmap", e);
            }
            return bm;
        }
        @Override
        protected Void doInBackground(String... urls) {
            for (int i = 0; i< JSON_LENGTH; i++) image[i] = getImageBitmap(urls[i]);
            return null;
        }
    }
}
