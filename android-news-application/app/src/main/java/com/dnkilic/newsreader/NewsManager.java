package com.dnkilic.newsreader;

import android.app.Activity;
import java.util.ArrayList;

public class NewsManager implements NewsCallback {

    Activity mAct;
    private RssRepository mRssRepository;

    public NewsManager(Activity act) {
        mAct = act;
    }

    public void getNews() {
        mRssRepository = new RssRepository(this);
        mRssRepository.loadRssData();
    }


    @Override
    public void onError(int result) {
        switch (result)
        {
            case RssFeedParser.CHECK_RSS_SOURCE:
                //Haber okuma kaynağının doğruluğundan emin olun
                break;
            case RssFeedParser.ERROR:
                //Haber kaynağına gidilirken bir hata oluştu
                break;
        }
    }

    @Override
    public void onRssReadSuccess(ArrayList<NewsEntry> news) {

        for(NewsEntry entry : news)
        {
            System.out.println(entry.getSummary());
        }

    }
}
