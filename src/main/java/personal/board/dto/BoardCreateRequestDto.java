package personal.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import personal.board.domain.board.Board;
import personal.board.domain.member.Member;

@Getter
@NoArgsConstructor
public class BoardCreateRequestDto {
    private Member member;
    private String title;
    private String content;

    @Builder
    public BoardCreateRequestDto(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }

    public Board toEntity() {
        return Board.builder()
                .member(member)
                .title(title)
                .content(content)
                .build();
    }
}
