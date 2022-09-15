package com.yisoccer.yiservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class Profile {
    private String profileImage;

    @Length(max = 50)
    private String bio;

    @Length(max = 30)
    private String liveLocation;

    @Length(max = 40)
    private String url;
    @Length(max = 20)
    private String position;


    public Profile(Account account) {  //LOMBOK 사용 안한
        this.profileImage = getProfileImage();
        this.bio = getBio();
        this.liveLocation = getLiveLocation();
        this.url = getUrl();
        this.position = getPosition();
    }
}
