package com.bs.spring.board.controller;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.common.PageFactory;
import com.bs.spring.memo.model.dto.Memo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor //final 일때 쓴다
@Controller
@RequestMapping("/board")

public class BoardController {

    private final BoardService service;


//    @Autowired      <<< RequiredArgsConstructor 이거 때문에 안써도 됨
//    public BoardController (BoardService service) {
//        this.service= service;
//    }

    @GetMapping("/boardlist.do")
    public String boardlist(HttpServletRequest request,Model model,
                            @RequestParam(defaultValue = "1") int cPage,
                            @RequestParam(defaultValue = "5") int numPerPage) {
        List<Board> boards=service.selectBoardList(Map.of("cPage", cPage ,  "numPerPage", numPerPage));
        int totalContents=service.countBoardList();
        String pageBar= PageFactory.pageBar(cPage,numPerPage,totalContents,"boardlist.do");


        model.addAttribute("pageBar", pageBar);
        model.addAttribute("totalContents", totalContents);
        model.addAttribute("boards",boards);
        return "boards/boardList";
    }


    @RequestMapping("/boardwrite.do")
    public String boardwrite (){
        return "boards/boardWrite";
    }


    @PostMapping("/boardwriteend.do")
    public String boardList (Board board , Model model) {
        int result = service.insertBoardList(board);
        String msg,loc,viewName="common/msg";
        if(result>0) {
            viewName="redirect:/";
        } else {
            msg="게시글 등록실패";
            loc="/board/boardEndList.do";
            model.addAttribute("msg",msg);
            model.addAttribute("loc",loc);
        }
        return viewName;
    }
}
