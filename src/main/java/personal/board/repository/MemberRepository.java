package personal.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.board.domain.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
