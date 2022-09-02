package com.yisoccer.yiservice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @EqualsAndHashCode(of= "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true) //중복허용 X
    private String email;
    @Column(unique = true)
    private String nickname;

    private String password;
    private LocalDateTime joinedAt;

    private String bio;
    private String url;
    private String position;
    private String liveLocation;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String profileImage;
}
