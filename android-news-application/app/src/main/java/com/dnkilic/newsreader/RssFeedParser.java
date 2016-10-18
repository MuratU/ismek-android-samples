package com.dnkilic.newsreader;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class RssFeedParser {

	private ArrayList<NewsEntry> mFeeds;

    public final static int ERROR = 0;
    public final static int SUCCESS = 1;
    public final static int CHECK_RSS_SOURCE = 2;
	
	public RssFeedParser() {
	}

	public Integer parseCurrencyXml() {
        mFeeds = new ArrayList<>();
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			URL rssUrl = new URL("http://www.ntv.com.tr/gundem.rss");
			Document doc = builder.parse(rssUrl.openStream());
			
			NodeList nodesEntry = doc.getElementsByTagName("entry");
			for (int i = 0; i < nodesEntry.getLength(); i++) {
                NodeList nodesElement = nodesEntry.item(i).getChildNodes();

                for (int j = 0; j < nodesElement.getLength(); j++) {
                    Element element = (Element) nodesEntry.item(j);
                    NewsEntry newsEntry = new NewsEntry();
                    newsEntry.setId(getElementValue(element,"id"));
                    newsEntry.setLink(getElementValue(element, "link"));
                    newsEntry.setPublishDate(getElementValue(element, "published"));
                    newsEntry.setSummary(getElementValue(element, "summary"));
                    newsEntry.setUpdateDate(getElementValue(element, "updated"));
                    newsEntry.setTitle(getElementValue(element,"title"));
                    mFeeds.add(newsEntry);
                }
			}

		} catch (Exception e) {
			e.printStackTrace();
            return ERROR;
		}

        if(mFeeds == null || mFeeds.isEmpty())
        {
            return CHECK_RSS_SOURCE;
        }

		return SUCCESS;
	}

	private String getCharacterDataFromElement(Element element) {
		try {
			Node child = element.getFirstChild();
			if (child instanceof CharacterData) {
				CharacterData data = (CharacterData) child;
				return data.getData();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	protected String getElementValue(Element parent, String label) {
		return getCharacterDataFromElement((Element) parent
				.getElementsByTagName(label).item(0));
	}

	public ArrayList<NewsEntry> getNews() {
		return mFeeds;
	}
}
