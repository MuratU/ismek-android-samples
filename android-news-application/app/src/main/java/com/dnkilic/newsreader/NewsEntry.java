package com.dnkilic.newsreader;

public class NewsEntry {

	private String id;
	private String title;
	private String summary;
	private String publishDate;
	private String updateDate;
	private String link;

	public NewsEntry() {
	}

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getSummary() {
		return summary;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public String getLink() {
		return link;
	}
}
