package personal.board.domain.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import personal.board.domain.member.Member;
import personal.board.dto.BoardDto;
import personal.board.repository.BoardRepository;
import personal.board.repository.MemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 게시판_등록() {
        boardRepository.deleteAll();

        String title = "타이틀1";
        String content = "내용1";

        List<Member> memberList = memberRepository.findAll();
        if(memberList.size() == 0){
            Member member = Member.builder()
                    .name("관리자")
                    .password("1111")
                    .contact("010-1111-2222")
                    .email("ll@na.ck")
                    .age(29)
                    .build();
            //member 저장
            memberRepository.save(member);
        }
        memberList = memberRepository.findAll();
        Member member = memberList.get(0);

        BoardDto boardDto = new BoardDto(title, content);
        Board board = boardDto.toEntity(member);
        boardRepository.save(board);

        //when
        List<Board> boardList = boardRepository.findAll();

        //then
        Board boards = boardList.get(0);
        assertThat(boards.getTitle()).isEqualTo(title);
        assertThat(boards.getContent()).isEqualTo(content);

    }

}