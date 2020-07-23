package com.sample.financialgoaltracker.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="profile_picture")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfilePicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private int id;

    @Column(name="photo_blob")
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] photoBlob;

    @Column(name = "photo_content_length")
    private Integer photoContentLength;

    @Column(name = "photo_content_type", length = 50)
    private String photoContentType;

}