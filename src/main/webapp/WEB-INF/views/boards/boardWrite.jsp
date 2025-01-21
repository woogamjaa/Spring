<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div id="board-container">
    <form name="boardFrm" method="POST" action="${pageContext.request.contextPath}/board/boardwriteend.do">
        <input type="text" class="form-control" placeholder="제목" name="boardTitle" id="boardTitle" required>
        <input type="text" class="form-control" placeholder="아이디 (4글자이상)" name="boardWriter" value="${sessionScope.loginMember.userId}" readonly required>
        <div class="input-group mb-3" style="padding:0px;">
            <div class="input-group-prepend" style="padding:0px;">
                <span class="input-group-text">첨부파일1</span>
            </div>
            <div class="custom-file">
                <input type="file" class="custom-file-input" name="upFile" id="upFile1">
                <label class="custom-file-label" for="upFile1">파일을 선택하세요</label>
            </div>
        </div>
        <textarea class="form-control" name="boardContent" placeholder="내용" required></textarea>
        <br />
        <input type="submit" class="btn btn-outline-success" value="저장" >
    </form>
</div>

<style>
    div#board-container{width:400px; margin:0 auto; text-align:center;}
    div#board-container input{margin-bottom:15px;}
</style>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>