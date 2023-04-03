package personal.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import personal.board.dto.BoardCreateRequestDto;
import personal.board.service.BoardService;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public Long create(@RequestBody BoardCreateRequestDto requestDto) {
        return boardService.create(requestDto);
    }
}
