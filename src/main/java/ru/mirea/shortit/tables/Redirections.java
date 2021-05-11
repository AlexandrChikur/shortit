package ru.mirea.shortit.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="redirections")
public class Redirections {

    @Id
    @SequenceGenerator(name = "redirections_seq", sequenceName =
            "redirections_sequence", allocationSize = 1)
    @GeneratedValue(generator = "redirections_seq", strategy =
            GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ip")
    private String ip;

    @Column(name = "device")
    private String device;

    @Column(name = "url_id")
    @JsonIgnore
    private Long urlId;

    @ManyToOne
    @JoinColumn(name = "url_id", insertable = false, updatable = false)
    @JsonIgnore
    private Urls url;
}
