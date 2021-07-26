package com.steps.postsapi.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table (name = "posts")
@EntityListeners(AuditingEntityListener.class)
public class Post implements Comparable<Post>{

    @Id
    @Column (name = "id")
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "body", nullable = false)
    private String body;
    @Column(name = "publishDate", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date publishDate;
    @Column(name = "byUser", nullable = false)
    private Long byUser;

    public Post() {
    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Long getByUser() {
        return byUser;
    }

    public void setByUser(Long userId) {
        this.byUser = userId;
    }

    @Override
    public int compareTo(Post o) {
        return getPublishDate().compareTo(o.publishDate);
    }
}
