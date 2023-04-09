package personal.board.domain.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import personal.board.domain.member.Member;
import personal.board.repository.BoardRepository;
import personal.board.repository.MemberRepository;

import java.util.List;

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
        Member member = memberList.get(0);


    }

}