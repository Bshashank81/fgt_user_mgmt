package com.sample.financialgoaltracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "setting")
public class Setting {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "setting_type1")
    private boolean settingType1;

    @Column(name = "setting_type2")
    private boolean settingType2;

    @Column(name = "setting_type3")
    private boolean settingType3;

    @Column(name = "setting_type4")
    private String settingType4;

    @Column(name = "setting_type5")
    private String settingType5;

    @Column(name = "setting_type6")
    private String settingType6;

    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REMOVE,CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
