package personal.board.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import personal.board.domain.board.Board;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String password;
    private String email;
    private String contact;
    private int age;

    @OneToMany(mappedBy = "member")
    private List<Board> board = new ArrayList<>();

    @Builder
    public Member(String name, String password, String email, String contact, int age) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.contact = contact;
        this.age = age;
    }
}
