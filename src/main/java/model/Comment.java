package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    @JsonProperty("comment_id")
    private int comment_id;
    @Column(name = "comment_content")
    private String comment_content;
    @ManyToOne
    @JoinColumn(name = "id_for_post")
    @JsonProperty("commented_post")
    private Post post;

    @JsonCreator
    public Comment(@JsonProperty String comment_content) {
        this.comment_content = comment_content;
    }

}