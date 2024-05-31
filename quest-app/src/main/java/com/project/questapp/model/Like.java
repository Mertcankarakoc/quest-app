package com.project.questapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "p_like")
@Data
public class Like {

    @Id
    Long id;

    @ManyToOne(fetch = FetchType.LAZY) //post objesini databaseden hemen çekme ilgili userı getirmene gerek yok.
    @JoinColumn(name = "post_id",nullable = false) //bağladık
    @OnDelete(action = OnDeleteAction.CASCADE)// post silindiğinde bütün postları sil.
    @JsonIgnore //bu alanı ignore et.
    Post post;

    @ManyToOne(fetch = FetchType.LAZY) //user objesini databaseden hemen çekme ilgili userı getirmene gerek yok.
    @JoinColumn(name = "user_id",nullable = false) //bağladık
    @OnDelete(action = OnDeleteAction.CASCADE)// user silindiğinde bütün postları sil.
    @JsonIgnore //bu alanı ignore et.
    User user;
}
