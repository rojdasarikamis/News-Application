package com.androidevreni.rsstutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.androidevreni.rsstutorial.adapters.PostsAdapter;
import com.androidevreni.rsstutorial.models.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    // LOG MESAJLARI İÇİN KULLANACAĞIMIZ TAG
    private String TAG = getClass().getSimpleName();

    // RSS İ ÇEKECEĞİMİZ ADRES - WEBRAZZİ RSS
    private String RSS_URL = "http://www.trthaber.com/sondakika.rss";

    // XML İ / RSS İ ÇEKTİRİP AYRIŞTIRACAĞIMIZ SINIFIMIZ
    private PullAndParseXML pullAndParseXML;

    // YAZILARA/POSTLARA AİT DATALARI NESNELER HALİNDE TUTACAĞIMIZ LİSTEMİZ
    private List<Post> posts;

    // LISTEMİZ, RECYCLERVIEW
    private RecyclerView recyclerView;

    // LISTE VE VERİ ARASINDAKİ BAĞLANTI - ADAPTER
    private PostsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pullAndParseXML = new PullAndParseXML(RSS_URL);
        pullAndParseXML.downloadXML();

        while (pullAndParseXML.parsingComplete) ;
        posts = pullAndParseXML.getPostList().subList(1, pullAndParseXML.getPostList().size());

        for (int i = 0; i < posts.size(); i++) {
            Log.i(TAG, "title #" + (i + 1) + " > : " + posts.get(i).getTitle());
            Log.i(TAG, "image #" + (i + 1) + " > : " + posts.get(i).getImageUrl());
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        adapter = new PostsAdapter(this, posts);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    /**
     * GET IMAGE URL
     *
     * @param text
     * @return
     */
    private ArrayList getImageUrls(String text) {

        ArrayList links = new ArrayList();
        String patternString = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String urlStr = matcher.group();
            if (urlStr.startsWith("(") && urlStr.endsWith(")")) {
                urlStr = urlStr.substring(1, urlStr.length() - 1);
            }
            if (urlStr.endsWith(".jpg") || urlStr.endsWith(".png") || urlStr.endsWith(".gif"))
                links.add(urlStr);
        }
        return links;
    }

}
