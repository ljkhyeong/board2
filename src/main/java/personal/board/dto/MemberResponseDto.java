package personal.board.dto;

import lombok.Getter;
import personal.board.domain.member.Member;

@Getter
public class MemberResponseDto {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String contact;


    public MemberResponseDto(Member entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.password = entity.getPassword();
        this.email = entity.getEmail();
        this.contact = entity.getContact();
    }
}
