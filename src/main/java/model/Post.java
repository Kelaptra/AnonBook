package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "post")
@Getter @Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    @JsonProperty("post_id")
    private int post_id;
    @Column(name = "time")
    @JsonProperty("time")
    private Instant time;
    @Column(name = "date")
    @JsonProperty("date")
    private Instant date;
    @Column(name = "post_content")
    @JsonProperty("post_content")
    private String post_content;
    @Column(name = "image_url")
    @JsonProperty("image_url")
    private String image_url;
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    @JsonProperty("my_comments")
    private List<Comment> comments;


    @JsonCreator
    public Post(@JsonProperty("post_content") String post_content) {
        this.time = Instant.now();          // questionable
        this.date = Instant.now();          // questionable
        this.post_content = post_content;
    }
    @JsonCreator
    public Post(@JsonProperty("post_content") String post_content,
                @JsonProperty("image_url") String image_url) {
        this.time = Instant.now();          // questionable
        this.date = Instant.now();          // questionable
        this.post_content = post_content;
        this.image_url = image_url;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

//    public Instant getTime() {
//        // calc time from instant
//        return time;
//    }
//
//    public Instant getDate() {
//        // calc date from instant
//        return date;
//    }
}
