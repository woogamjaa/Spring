package com.bs.spring.board.controller;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.model.service.BoardService;
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

        int totalData= service.countBoardList();
        int totalPage=(int)Math.ceil((double)totalData/numPerPage);
        int pageBarSize=5;
        int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
        int pageEnd=pageNo+pageBarSize-1;

        String pageBar="<ul class='pagination justify-content-center'>";

        if(pageNo==1) {
            pageBar+="<li class='page-item disabled'>"; // 첫번쨰 페이지라 안눌리게 만듬.
            pageBar+="<a class='page-link' href='#'>이전</a>";
            pageBar+="</li>";

        } else {
            pageBar+="<li class='page-item'>";
            pageBar+="<a class='page-link' href='"+

                    request.getRequestURI()    // 두개의 값을 보낸다.
                    + "?cPage="+(pageNo-1)
                    +"&numPerPage="+numPerPage //다수값일때 &로 보낸다.
                    +"'>이전</a>";

            pageBar+="</li>";
        }

        while(!(pageNo>pageEnd||pageNo>totalPage)) {
            if(pageNo==cPage) {   //클릭되면 안됀당.
                pageBar+="<li class='page-item disabled'>";
                pageBar+="<a class='page-link' href='#'>"+pageNo+"</a>";   // 숫자가 들어감.
                pageBar+="</li>";
            } else {
                pageBar+="<li class='page-item'>";
                pageBar+="<a class='page-link' href='"+

                        request.getRequestURI()
                        + "?cPage="+(pageNo) // 요청하는페이지가 그 페이지가 된다 -를 지워서
                        +"&numPerPage="+numPerPage
                        +"'>"+pageNo+"</a>";  // 숫자가 들어감.

                pageBar+="</li>";
            }
            pageNo++; //no값을 증가하면서 위 숫자값에 들어가게 됨.
        }
        if(pageNo>totalPage) {
            pageBar+="<li class='page-item disabled'>";
            pageBar+="<a class='page-link' href='#'>다음</a>";   // 숫자가 들어감.
            pageBar+="</li>";

        } else {
            pageBar+="<li class='page-item'>";
            pageBar+="<a class='page-link' href='"+

                    request.getRequestURI()
                    + "?cPage="+(pageNo) // 요청하는페이지가 그 페이지가 된다 -를 지워서
                    +"&numPerPage="+numPerPage
                    +"'>다음</a>";  // 숫자가 들어감.

            pageBar+="</li>";
        }
        pageBar+="</ul>";


        model.addAttribute("pageBar", pageBar);
        model.addAttribute("totalContents", totalContents);
        model.addAttribute("boards",boards);
        return "boards/boardList";
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
