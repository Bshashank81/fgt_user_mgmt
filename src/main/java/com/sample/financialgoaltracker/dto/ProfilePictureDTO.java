package com.sample.financialgoaltracker.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProfilePictureDTO {
    private int id;
    private byte[] photoBlob;
    private Integer photoContentLength;
    private String photoContentType;
}
