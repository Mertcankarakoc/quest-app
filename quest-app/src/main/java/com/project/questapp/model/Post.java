package com.project.questapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "post")
@Data
public class Post {

    @Id
    Long id;

    @ManyToOne(fetch = FetchType.LAZY) //user objesini databaseden hemen çekme ilgili userı getirmene gerek yok.
    @JoinColumn(name = "user_id",nullable = false) //bağladık
    @OnDelete(action = OnDeleteAction.CASCADE)// user silindiğinde bütün postları sil.
    @JsonIgnore //bu alanı ignore et.
    User user;

    String title;
    @Lob
    @Column(columnDefinition = "text")
    String text;

}
