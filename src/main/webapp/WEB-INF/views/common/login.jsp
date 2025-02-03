
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<section style="display:flex;justify-content: center">
    <h2>로그인</h2>

    <div style="width:80%">
        <%--processing 은 로그인을 처리하는 url로 거기로 가야함.--%>
        <%--파라미터값이 같기 때문에 시큐리티가 알아서 해준다.--%>
    <form action="${path}/loginend.do" method="post">
        <div class="modal-body">
            <input type="text" name="userId" class="form-control"
                   placeholder="아이디입력" required><br/>
            <input type="password" name="pw" class="form-control"
                   placeholder="패스워드입력" required><br/>
            <label><input type="checkbox" name="saveUser">로그인유지</label>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-outline-success">로그인</button>
            <button type="button" class="btn btn-outline-success"
                    data-dismiss="modal">취소</button>
        </div>
    </form>
    </div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
