package com.bs.spring.board.controller;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.common.PageFactory;

import com.bs.spring.common.error.MyException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor //final 일때 쓴다
@Controller
@RequestMapping("/board")
@Slf4j // log 보고싶을때.

public class BoardController {

    private final BoardService service;


//    @Autowired      <<< RequiredArgsConstructor 이거 때문에 안써도 됨
//    public BoardController (BoardService service) {
//        this.service= service;
//    }

    @GetMapping("/boardlist.do")
    public String boardlist(Model model,
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


    @PostMapping(value = "/boardwriteend.do")
    public String insertboardList (Board board , Model model, MultipartFile[] upFile,
                                   HttpSession session) { //따로 저장하는 짓을 해야함.

//        log.debug("파일명" + upFile.getOriginalFilename());
//        log.debug("파일크기" + upFile.getSize());
//        log.debug("{}",board);

        //파일 저장하기
        //1. 파일을 저장할 절대 경로 필요 ->
        String path=session.getServletContext().getRealPath("/resources/upload/board");
        File dir=new File(path);
        if(!dir.exists()){dir.mkdirs();} // 존재하지 않으면 폴더를 생성한다.

        List<Attachment> files=new ArrayList<Attachment>();

        if (upFile!=null){

            for(MultipartFile file : upFile){

            //2.리네임규칙을 생성
            String oriname=file.getOriginalFilename();

            //2-1 원본 파일 확장자 가져오기
            String ext=oriname.substring(oriname.lastIndexOf("."));

            //2-2 이름값이 중복되지 않게 랜덤값 가져오기
            int rnd=(int) (Math.random()*1000)+1;

            //2-3 파일명이 중복되지 않게 업로드 날짜를 밀리세컨초까지 가져오기
            Date d=new Date(System.currentTimeMillis());

            //2-4 날짜를 문자열로 패턴에 맞춰서 변경해주기
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");

            //2-5 위 생성한 데이터들로 파일명 생성하기.
            String rename="BSLOVE_"+sdf.format(d)+"_"+rnd+ext;

            //3. 생성한 파일명으로 파일 저장하기
            //MultipartFile 클래스가 제공하는 tranferTo메소드를 이용해서 저장

            try{
                file.transferTo(new File(path, rename));
                files.add(Attachment.builder()
                                .originalFileName(oriname)
                                .renamedFileName(rename)
                                .build());
            }catch(IOException e) {
                e.printStackTrace();
                log.error(file.getOriginalFilename()+"파일 저장 실패");
                if(files.size()>0){
                    for(Attachment attachment:files){
                        File delFile=new File(path,attachment.getOriginalFileName());
                        if(delFile.exists()){
                            delFile.delete();
                        }
                    }
                }
                //실패처리

            }
        }
            }

        //DB에 저장하기
        String viewName;
        try{
            service.insertBoardList(board,files);
            //게시글저장 성공
            viewName="redirect:/board/boardlist.do";
        }catch(MyException e) {
            //게시글저장 실패
            viewName="redirect:/board/boardwrite.do";
        }
        return viewName;
    }

    @GetMapping("/boarddetail.do")
    public String boardDetail(int no, Model model) {
        Board d = service.findBoardByNo(no);
        model.addAttribute("board", d);
        return "boards/boardDetail";
    }

    @GetMapping("/filedown.do")
    public void fileDownload(String oriname, String rename,
                             HttpServletResponse response,
                             OutputStream out, HttpSession session,
                             @RequestHeader(value="user-agent") String header) {
        String path=session.getServletContext().getRealPath("/resources/upload/board");
        File downloadFile=new File(path,rename);
        if(downloadFile.exists()){
            //파일없음 !
            //에러페이지 전송
            //throw new

            //트라이 위드 리소스
        } else {
            try(FileInputStream fis=new FileInputStream(downloadFile);
                BufferedInputStream bis=new BufferedInputStream(fis);
                BufferedOutputStream bos=new BufferedOutputStream(out);){

                //원본파일명으로 다운로드 하기
                //원본파일명이 한글일때 한글이 깨짐
                //한글이 깨지지 않게 인코딩 처리하기.
                boolean isMs=header.contains("Trident") || header.contains("MSIE");
                String endcodeFilename="";
                if(isMs){
                    endcodeFilename= URLEncoder.encode(oriname,"utf-8");
                    endcodeFilename= endcodeFilename.replace("\\+","%20");

                } else {
                    endcodeFilename=new String(oriname.getBytes("utf-8"),"ISO-8859-1");
                }

                response.setContentType("application/octet-stream;charset=utf-8");
                response.setHeader("Content-Disposition",
                        "attachment;filename="+endcodeFilename);

                int data=1;
                while((data=bis.read())!=-1){
                    bos.write(data);
                }

            }catch(IOException e) {
                e.printStackTrace();
            }

        }

    }

}
