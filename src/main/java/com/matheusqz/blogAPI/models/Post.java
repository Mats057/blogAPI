package com.matheusqz.blogAPI.models;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;



@Data
@Table(name="posts")
@Entity(name ="posts")
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String title;
    protected String content;
    protected String category;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "post_tags", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "tag")
    protected List<String> tags;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    protected Timestamp createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    protected Timestamp updatedAt;

    public Post(){
    }

    public Post(RequestPost requestPost){
        this.title = requestPost.title();
        this.content = requestPost.content();
        this.category = requestPost.category();
        this.tags = requestPost.tags();
    }

    @PrePersist
    protected void onCreate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.createdAt = timestamp;
        this.updatedAt = timestamp;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString(){
        return "Post{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", category='" + category + '\'' +
            ", tags=" + tags +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}
