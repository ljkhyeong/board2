package personal.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.board.domain.board.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
