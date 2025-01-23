package com.bs.spring.ajax.controller;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class RestAPIController {
    private final BoardService boardService;
    private final MemberService memberService;


    @GetMapping("/boards")
    public List<Board> getBoards(
            @RequestParam(defaultValue = "1") int cPage,
            @RequestParam(defaultValue = "10") int numPerPage) {
        List<Board> boards=boardService.selectBoardList(Map.of("cPage", cPage, "numPerPage", numPerPage));
        return boards;

    }

    @GetMapping("/boards/{boardNo}")
    public Board getBoard(@PathVariable int boardNo) {
        Board board=boardService.findBoardByNo(boardNo);
        return board;


    }

    //ResponseEntity클래스를 잉ㅇ해서 응답처리 할 수 도 있음.
    @PostMapping("/member")
    public Member createMember(@RequestBody Member member) {
        log.debug("{}", member);
        int result=memberService.saveMember(member);
        return result>0?member:null;
    }

}
