package com.sample.financialgoaltracker.dto;

import com.sample.financialgoaltracker.entity.User;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserSettingDTO {

    private int id;

    private String modifiedAt;

    private String modifiedBy;

    private User user;
}
