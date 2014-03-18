package com.gh.MobyUniver;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ArticleFragment extends Fragment {
    static View view;
    static String id = "k";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.article_layout_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        id = getActivity().getIntent().getExtras().getString("id");
        ((ImageView) view.findViewById(R.id.imageView)).setImageBitmap((Bitmap) getActivity().getIntent().getParcelableExtra("img"));
        new JSONParseArticle().execute();
    }

    private class JSONParseArticle extends AsyncTask<Void, Void, JSONArray> {
        private static final String url = "http://gkurs.esy.es/article.php";
        @Override
        protected JSONArray doInBackground(Void... voids) {
            JSONParser jParser = new JSONParser();
            JSONArray json = jParser.getJSONFromUrl(url, id);
            return json;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //view.findViewById(R.id.progressLayout).setVisibility(View.VISIBLE);
        }
        @Override
        protected void onPostExecute(JSONArray json) {
            //view.findViewById(R.id.progressLayout).setVisibility(View.GONE);


            try {
                JSONObject c = json.getJSONObject(0);
               ((TextView) view.findViewById(R.id.textView)).setText(c.getString("content"));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
