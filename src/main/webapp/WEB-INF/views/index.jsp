<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <section id="content">

    <h2>하이요</h2>
    <h3>ajax요청처리하기</h3>

    <button class="btn btn-outline-primary" onclick="basicAjax()">
        기본 ajax요청
    </button>

    <button class="btn btn-outline-success" onclick="dataAjax(event)">
        서버에서 전송하는 데이터 가져오기
    </button>
    <div id="data-container"></div>


    <script>
        const dataAjax=(e)=>{
            fetch("${path}/ajax/dataAjax")
                .then(response=>response.text())
                .then(data=>{
                    console.log(data);
                    document.getElementById("data-container").innerHTML=data;
                })
        }
        const basicAjax=()=>{
            //fetch
            //fetch("url주소"[,{추가설정}])
            //.then((response)=>{
            // response : 서버에서 응답한 데이터를 객체로 생성한것
            // response.ok : 정상적인 응답(true, false), response.status : 상태코드
            // response.text() : 서버가 responsebody에 저장한 데이터를 text로 가져오는 함수
            // response.json() : 서버가 responsebody에 저장한 데이터를 json으로 가져오는 함수
            // return 값 (responsebody 데이터);
            // })
            //.then(data=>) {
            // 이전 콜백함수에서 반환한 데이터가 data변수에 전달됨.
            // })
            //.catch(error=>{
            //  에러처리 로직 작성.
            // })

            fetch("${path}/ajax/basicAjax")
                .then(response=>{console.log(response)
                    return response.text();
                })
                .then(data=>{
                    console.log(data);
                    document.getElementById("data-container").innerHTML=data;

                 });
        }
    </script>


</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
