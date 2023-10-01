package hello.hellospring.domain;

import javax.persistence.*;

@Entity // jpa가 관리하는 엔티티
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // id를 자동매핑(=idntity)
    private Long id;

//    @Column(name = "username") // db에 있는 username과 매핑
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
