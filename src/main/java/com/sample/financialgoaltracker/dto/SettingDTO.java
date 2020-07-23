package com.sample.financialgoaltracker.dto;

import com.sample.financialgoaltracker.entity.User;
import com.sample.financialgoaltracker.entity.UserSetting;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SettingDTO {

    private int id;

    private boolean settingType1;

    private boolean settingType2;

    private boolean settingType3;

    private String settingType4;

    private String settingType5;

    private String settingType6;

    private User user;
}
