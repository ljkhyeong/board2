package personal.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.board.domain.board.Board;
import personal.board.domain.member.Member;
import personal.board.dto.*;
import personal.board.repository.BoardRepository;
import personal.board.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Board selectBoardDetail(Long id) {
        return boardRepository.findById(id).get();
    }

    @Transactional
    public Long saveBoard(BoardDto boardDto) {
        List<Member> memberList = memberRepository.findAll();
        Member member = memberList.get(0);
        Board board = null;

        if (boardDto.getId() == null) {
            board = boardDto.toEntity(member);
            boardRepository.save(board);
        } else {
            board = boardRepository.findById(boardDto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다"));
            board.update(boardDto.getTitle(), boardDto.getContent());
        }

        return board.getId();
    }

    @Transactional
    public void delete(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다"));

        boardRepository.delete(board);
    }


}
