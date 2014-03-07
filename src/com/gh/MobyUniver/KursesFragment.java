package com.gh.MobyUniver;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class KursesFragment extends Fragment {
    static ListView lvMain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kurses_layout_fragment,
                container, false);

       // assert view != null;
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
            ArrayList<KursesItems> kurses = new ArrayList<KursesItems>();
            KursesAdapter adapter;

            try {
                JSONObject c;
                for(int i=0; i<json.length(); i++){
                    c = json.getJSONObject(i);
                    kurses.add(new KursesItems(c.getString("id_stud"), c.getString("FIO"), "http://gkurs.esy.es/images/1.png" ));
                    //Toast.makeText(getActivity(), kurses.get(i).id, Toast.LENGTH_SHORT).show();

                }
                adapter = new KursesAdapter(getActivity(), kurses);
                lvMain.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
