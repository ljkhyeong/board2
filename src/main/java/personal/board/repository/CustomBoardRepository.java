package personal.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import personal.board.dto.BoardDto;

public interface CustomBoardRepository {
    Page<BoardDto> selectBoardList(String searchVal, Pageable pageable);
}
