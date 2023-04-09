package personal.board.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import personal.board.dto.BoardDto;
import personal.board.dto.QBoardDto;

import java.util.List;

import static personal.board.domain.board.QBoard.board;
import static personal.board.domain.member.QMember.member;

@Repository
public class BoardRepositoryImpl implements CustomBoardRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public BoardRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<BoardDto> selectBoardList(String searchVal, Pageable pageable) {
        List<BoardDto> content = getBoardMemberDtos(searchVal, pageable);
        Long count = getCount(searchVal);
        return new PageImpl<>(content, pageable, count);
    }

    private Long getCount(String searchVal) {
        Long count = jpaQueryFactory
                .select(board.count())
                .from(board)
                .where(containsSearch(searchVal))
                .fetchOne();
        return count;
    }

    private List<BoardDto> getBoardMemberDtos(String searchVal, Pageable pageable) {
        List<BoardDto> content = jpaQueryFactory
                .select(new QBoardDto(
                        board.id
                        , board.title
                        , board.content
                        , board.created
                        , board.modified
                        , board.viewCount
                        , member.name))
                .from(board)
                .leftJoin(board.member, member)
                .where(containsSearch(searchVal))
                .orderBy(board.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return content;

    }

    private BooleanExpression containsSearch(String searchVal) {
        return searchVal != null ? board.title.contains(searchVal) : null;
    }



}
