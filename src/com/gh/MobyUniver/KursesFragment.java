package com.gh.MobyUniver;

import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class KursesFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        JSONParse jParse = new JSONParse();
        jParse.execute();
    }

    public class JSONParse extends AsyncTask<Void, Void, JSONArray> {

        private static final String url = "http://gkurs.esy.es/json.php";
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
             ArrayList<String> kurses = new ArrayList<String>();
             ArrayAdapter<String> adapter;
            try {
                JSONObject c;
               for(int i=0; i<json.length(); i++){
                    c = json.getJSONObject(i);
                    kurses.add(c.getString(TAG_FIO));
                }
                adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, kurses);
                setListAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
