package com.sample.financialgoaltracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "notification_type1")
    private boolean notificationType1;

    @Column(name = "notification_type2")
    private boolean notificationType2;

    @Column(name = "notification_type3")
    private boolean notificationType3;

    @Column(name = "notification_type4")
    private boolean notificationType4;

    @Column(name = "notification_type5")
    private boolean notificationType5;

    @Column(name = "notification_type6")
    private boolean notificationType6;

    @Column(name = "notification_type7")
    private boolean notificationType7;

    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REMOVE,CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
