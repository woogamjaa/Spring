package com.bs.spring.board.controller;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.memo.model.dto.Memo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService service;


    @Autowired
    public BoardController (BoardService service) {
        this.service= service;
    }

    @GetMapping("/boardlist.do")
    public String boardlist(Model model) {
        List<Board> boards=service.selectBoardList();
        int totalContents=service.countBoardList();

        model.addAttribute("totalContents", totalContents);
        model.addAttribute("boards",boards);
        return "/boards/boardList";
    }


//    @PostMapping("/boardEndList.do")
//    public String boardList (Board board , Model model) {
//        int result = service.saveBoard(board);
//        String msg,loc,viewName="common/msg";
//        if(result>0) {
//            viewName="redirect:/";
//        } else {
//            msg="게시글 등록실패";
//            loc="/board/boardEndList.do";
//            model.addAttribute("msg",msg);
//            model.addAttribute("loc",loc);
//        }
//        return viewName;
//    }
}
