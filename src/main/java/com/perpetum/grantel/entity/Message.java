package com.perpetum.grantel.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
public class Message {

    public Message(String text) {
        this.text = text;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @Type(type = "text")
    private String text;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;

    @CreationTimestamp
    @Column
    private Timestamp creation;
}
