package personal.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import personal.board.domain.BaseTimeEntity;
import personal.board.domain.board.Board;
import personal.board.domain.member.Member;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class BoardDto {

    private Long id;

    @NotBlank(message = "제목은 필수입니다.")
    private String title;
    private String content;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Long viewCount;
    private String name;

    public BoardDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @QueryProjection
    public BoardDto(Long id, String title, String content, LocalDateTime created, LocalDateTime updated, Long viewCount, String name) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.viewCount = viewCount;
        this.name = name;
    }

    public Board toEntity(Member member) {
        return Board.builder()
                .member(member)
                .title(title)
                .content(content)
                .build();
    }
}
