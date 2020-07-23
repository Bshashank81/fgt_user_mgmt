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
public class MessageDTO {

    private int id;

    private boolean emailMessages;

    private boolean textMessages;

    private User user;
}
