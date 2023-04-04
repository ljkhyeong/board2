package personal.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import personal.board.dto.BoardCreateRequestDto;
import personal.board.dto.BoardListResponseDto;
import personal.board.dto.BoardResponseDto;
import personal.board.dto.BoardUpdateRequestDto;
import personal.board.service.BoardService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public Long create(@RequestBody BoardCreateRequestDto requestDto) {
        return boardService.create(requestDto);
    }

    @PutMapping("/board/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto requestDto) {
        return boardService.update(id, requestDto);
    }

    @GetMapping("/board/{id}")
    public BoardResponseDto searchById(@PathVariable Long id) {
        return boardService.searchById(id);
    }

    @GetMapping("/board")
    public List<BoardListResponseDto> searchAll() {
        return boardService.searchAll();
    }
}
