package personal.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import personal.board.domain.member.Member;
import personal.board.repository.BoardRepository;
import personal.board.repository.MemberRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.userDBInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final MemberRepository memberRepository;
        private final BoardRepository boardRepository;
        public void userDBInit(){

            List<Member> memberList = memberRepository.findAll();
            if(memberList.size() == 0){
                Member member = Member.builder()
                        .name("관리자")
                        .password("1111")
                        .contact("010-1111-2222")
                        .email("jo4@naver.com")
                        .age(29)
                        .build();
                //member 저장
                memberRepository.save(member);
            }
        }
    }
}
