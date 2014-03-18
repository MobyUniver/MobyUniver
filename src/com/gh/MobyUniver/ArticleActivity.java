package com.gh.MobyUniver;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;



public class ArticleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_layout);
        FragmentTransaction mng = getFragmentManager().beginTransaction();
        ArticleFragment fr = new ArticleFragment();
        mng.add(R.id.frl, fr).commit();

    }
}
