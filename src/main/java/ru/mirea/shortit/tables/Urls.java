package ru.mirea.shortit.tables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="urls")
public class Urls {

    @Id
    @SequenceGenerator(name = "urls_seq", sequenceName = "urls_sequence", allocationSize = 1)
    @GeneratedValue(generator = "urls_seq", strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="oldurl", length = 10485760)
    private String oldurl;

    @Column(name="newurl")
    private String newurl;

    @OneToMany(mappedBy = "url")
    private List<Redirections> redirections;

}
