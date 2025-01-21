<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<section id="board-container" class="container">
    <p>총 ${totalContents }건의 게시물이 있습니다.</p>
    <table id="tbl-board" class="table table-striped table-hover">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>첨부파일</th>
            <th>조회수</th>
        </tr>
<c:forEach var="board" items="${boards}" varStatus="vs">
        <tr>
            <td>${vs.count}</td>
            <td>${board.boardTitle}</td>
            <td>${board.boardWriter}</td>
            <td><img src="${pageContext.request.contextPath}/resources/images/file.png"></td>
            <td>${board.boardDate}</td>
            <td>${board.boardReadCount}</td>
        </tr>
</c:forEach>
    </table>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>