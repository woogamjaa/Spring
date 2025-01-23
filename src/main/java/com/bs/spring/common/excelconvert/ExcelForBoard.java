package com.bs.spring.common.excelconvert;

import com.bs.spring.board.model.dto.Board;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;



public class ExcelForBoard extends AbstractXlsView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
     List<Board> boards=(List<Board>)model.get("boards");

     final Sheet sheet=workbook.createSheet("게시글");
     addHeader(sheet,List.of("번호","제목","작성자","내용","작성일"));
     boards.forEach(board->{
         addCell(sheet,board);
     });

    }
    private void addHeader(Sheet sheet,List<String> headers){
        Row headerRow=sheet.createRow(0);
        for(int i=0;i<headers.size();i++){
            headerRow.createCell(i).setCellValue(headers.get(i));
        }
    }
    private void addCell(Sheet sheet,Board board){
        Row row=sheet.createRow(sheet.getLastRowNum()+1);
        row.createCell(0).setCellValue(board.getBoardNo());
        row.createCell(1).setCellValue(board.getBoardTitle());
        row.createCell(2).setCellValue(board.getBoardWriter());
        row.createCell(3).setCellValue(board.getBoardContent());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String boardDate=sdf.format(new Date(board.getBoardDate().getTime()));
        row.createCell(4).setCellValue(board.getBoardDate());
    }
}
