package personal.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.board.domain.board.BoardRepository;
import personal.board.dto.BoardCreateRequestDto;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long create(BoardCreateRequestDto requestDto) {
        return boardRepository.save(requestDto.toEntity()).getId();
    }
}
