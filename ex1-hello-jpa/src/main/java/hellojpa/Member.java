package hellojpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Member {

    public Member() {}

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    private Long id;
    private String name;
}
