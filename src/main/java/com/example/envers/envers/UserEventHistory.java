package com.example.envers.envers;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserEventHistory {
    private Long rev;

    private String modifiedBy;

    private String modifiedAt;

    private String authorities;

    private String username;

    private Integer revtype;

    private String password;

    private Boolean passwordMod;

    private String name;

    private Boolean nameMod;

    private String phoneNumber;

    private Boolean phoneNumberMod;

    private String email;

    private Boolean emailMod;

    private Boolean confirmYn;

    private Boolean confirmYnMod;

    private Boolean renewPassword;

    private Boolean renewPasswordMod;

//    private String groupName;
//
//    private String pastUsersName;
//
//    private String roleCode;
}
