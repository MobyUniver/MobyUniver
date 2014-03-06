package com.gh.MobyUniver;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class TestWeb extends Fragment {
    public static TextView tw;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_web,
                container, false);
        tw = (TextView) view.findViewById(R.id.textView);

        JSONParse jParse = new JSONParse();
        jParse.execute();

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
                tw.setText(p);
               /* for(int i=0; i<0; i++){
                    c = json.getJSONObject(i);
                    fio.add(c.getString(TAG_FIO));
                }*/

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
