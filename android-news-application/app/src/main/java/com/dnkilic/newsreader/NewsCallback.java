package com.dnkilic.newsreader;

import java.util.ArrayList;

public interface NewsCallback {
	void onError(int result);
	void onRssReadSuccess(ArrayList<NewsEntry> news);
}
