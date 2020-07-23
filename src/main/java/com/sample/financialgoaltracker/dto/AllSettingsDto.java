package com.sample.financialgoaltracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AllSettingsDto {
    MessageDTO message;
    NotificationDTO notification;
    SettingDTO setting;
}
