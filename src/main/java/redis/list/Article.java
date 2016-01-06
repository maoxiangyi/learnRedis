package redis.list;

import java.util.Date;

import org.jsoup.helper.StringUtil;

/**
 * Describe: Œƒ’¬¿‡
 * Author:   maoxiangyi
 * Domain:   www.maoxiangyi.cn
 * Data:     2016/1/6.
 */
public class Article {
	private String id;
	private String title;
	private String content;
	private String author;
	private Date date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content="
				+ content + ", author=" + author + ", date=" + date + "]";
	}
	
	public boolean isNotNull() {
		if (StringUtil.isBlank(content)) {
			return false;
		}
		return true;
	}

}
