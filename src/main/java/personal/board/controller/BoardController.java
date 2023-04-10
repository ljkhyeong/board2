package personal.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import personal.board.domain.board.Board;
import personal.board.domain.member.Member;
import personal.board.dto.*;
import personal.board.repository.CustomBoardRepository;
import personal.board.service.BoardService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final CustomBoardRepository customBoardRepository;
    private final BoardService boardService;

    @GetMapping("/")
    public String list(String searchVal, Pageable pageable, Model model) {
        Page<BoardDto> results = customBoardRepository.selectBoardList(searchVal, pageable);
        model.addAttribute("list", results);
        model.addAttribute("maxPage", 5);
        model.addAttribute("searchVal", searchVal);
        pageModelPut(results, model);
        return "board/list";
    }

    private void pageModelPut(Page<BoardDto> results, Model model) {
        model.addAttribute("totalCount", results.getTotalElements());
        model.addAttribute("size", results.getPageable().getPageSize());
        model.addAttribute("number", results.getPageable().getPageNumber());
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("boardDto", new BoardDto());
        return "board/write";
    }

    @PostMapping("/write")
    public String save(@Valid BoardDto boardDto, BindingResult result) {

        if (result.hasErrors()) {
            return "board/write";
        }

        boardService.saveBoard(boardDto);
        return "redirect:/";
    }

    @GetMapping("/update/{boardId}")
    public String detail(@PathVariable Long boardId, Model model) {
        Board board = boardService.selectBoardDetail(boardId);
        BoardDto boardDto = new BoardDto();
        boardDto.setId(boardId);
        boardDto.setTitle(board.getTitle());
        boardDto.setContent(board.getContent());
        model.addAttribute("boardDto", boardDto);

        return "board/update";
    }

    @PostMapping("/update/{boardId}")
    public String update(@Valid BoardDto boardDto, BindingResult result) {

        if (result.hasErrors()) {
            return "board/update";
        }

        boardService.saveBoard(boardDto);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam List<String> boardIds){

        for(int i=0; i<boardIds.size(); i++){
            Long id = Long.valueOf(boardIds.get(i));
            boardService.delete(id);
        }

        return "redirect:/";
    }

}
