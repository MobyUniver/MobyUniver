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
        String data[] = new String[] { "one", "two", "three", "four" };
        if (jParse.getKurs()!=null)
        data[3]= jParse.getKurs();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);
    }

    public static class JSONParse extends AsyncTask<Void, Void, JSONArray> {

        private static String url = "http://gkurs.esy.es/json2.php";
        private static final String TAG_FIO = "FIO";
        private ArrayList<String> fio;
        private   String p ;

        public String getKurs(){
            return p;
        }

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
                JSONObject c;
                c = json.getJSONObject(0);
                p = c.getString("FIO");
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
