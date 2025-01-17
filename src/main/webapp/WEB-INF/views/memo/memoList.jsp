<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>


<style>
    div#memo-container{width:60%; margin:0 auto;}
</style>
<div id="memo-container">
    <form action="" class="form-inline">
        <input type="text" class="form-control col-sm-6" name="memo" placeholder="메모" required/>&nbsp;
        <input type="password" class="form-control col-sm-2" name="password" maxlength="4" placeholder="비밀번호" required/>&nbsp;
        <button class="btn btn-outline-success" type="submit" >저장</button>
    </form>
</div>

<br />
<!-- 메모목록 -->
<table class="table">
    <tr>
        <th scope="col">번호</th>
        <th scope="col">메모</th>
        <th scope="col">날짜</th>
        <th scope="col">삭제</th>
    </tr>
    내용출력
</table>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>