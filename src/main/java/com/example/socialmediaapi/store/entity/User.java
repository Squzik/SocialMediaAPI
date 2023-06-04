package com.example.socialmediaapi.store.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "mail")
    private String mail;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Friend> friends = new HashSet<>();

    @OneToMany(mappedBy = "friend", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Friend> friendOf = new HashSet<>();

    @OneToMany(mappedBy = "senderId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<FriendRequest> sentRequests = new HashSet<>();

    @OneToMany(mappedBy = "receiverId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<FriendRequest> receivedRequests = new HashSet<>();

    @OneToMany(mappedBy = "authorId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Post> posts = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<Message> messages;

    @ManyToMany(mappedBy = "users")
    private List<Chat> chats;

}
