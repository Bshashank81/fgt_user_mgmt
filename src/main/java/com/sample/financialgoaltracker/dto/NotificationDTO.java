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
public class NotificationDTO {

    private int id;

    private boolean notificationType1;

    private boolean notificationType2;

    private boolean notificationType3;

    private boolean notificationType4;

    private boolean notificationType5;

    private boolean notificationType6;

    private boolean notificationType7;

    private User user;

}
