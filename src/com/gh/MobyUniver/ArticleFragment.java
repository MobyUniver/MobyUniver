package com.gh.MobyUniver;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ArticleFragment extends Fragment {
    static View view;
    String id;
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
        VideoView vid = (VideoView) view.findViewById(R.id.videoLess);
        new JSONParseArticle().execute();
        String videoSourse = "http://gkurs.esy.es/Command & Conquer 2013 (Generals 2) Alpha Gameplay Trailer.mp4";
        vid.setVideoURI(Uri.parse(videoSourse));
        vid.setMediaController(new MediaController(getActivity()));
        vid.requestFocus(0);
        vid.start();
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
