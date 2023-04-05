package personal.board.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import personal.board.domain.BaseTimeEntity;
import personal.board.domain.member.Member;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "board")
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Member.class)
    @JoinColumn(name = "member_id", updatable = false)
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Builder
    public Board(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
