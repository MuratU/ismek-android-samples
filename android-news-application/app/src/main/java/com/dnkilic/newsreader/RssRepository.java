package com.dnkilic.newsreader;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.ArrayList;

public class RssRepository {

	private RssFeedParser mRssFeedParser;
	private NewsCallback mNewsListener;

	public RssRepository(NewsCallback newsCallback) {
		mNewsListener = newsCallback;
	}

	public void loadRssData()
	{
		new LoadRssData().execute();
	}	

	private class LoadRssData extends AsyncTask<Void, Void, Integer> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mRssFeedParser = new RssFeedParser();
		}

		@Override
		protected Integer doInBackground(Void... params) {
			return mRssFeedParser.parseCurrencyXml();
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);

            switch (result)
            {
                case RssFeedParser.SUCCESS:
                    mNewsListener.onRssReadSuccess(mRssFeedParser.getNews());
                    break;
                case RssFeedParser.CHECK_RSS_SOURCE:
                    mNewsListener.onError(RssFeedParser.CHECK_RSS_SOURCE);
                    break;
                case RssFeedParser.ERROR:
                    mNewsListener.onError(RssFeedParser.ERROR);
                    break;
            }

		}
	}
}
