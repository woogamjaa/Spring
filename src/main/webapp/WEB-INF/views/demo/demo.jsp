<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<section id="content">
    <div id="demo-container">
        <form id="devFrm" method="post">
            <div class="form-group row">
                <label for="devName" class="col-sm-2 col-form-label">이름</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="devName" name="devName">
                </div>
            </div>
            <div class="form-group row">
                <label for="devAge" class="col-sm-2 col-form-label">나이</label>
                <div class="col-sm-10">
                    <input type="number" class="form-control" id="devAge" name="devAge">
                </div>
            </div>
            <div class="form-group row">
                <label for="devEmail" class="col-sm-2 col-form-label">이메일</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="devEmail" name="devEmail">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">성별</label>
                <div class="col-sm-10">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="devGender" id="devGender0" value="M">
                        <label class="form-check-label" for="devGender0">남</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="devGender" id="devGender1" value="F">
                        <label class="form-check-label" for="devGender1">여</label>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">개발언어</label>
                <div class="col-sm-10">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" name="devLang" id="devLang0" value="Java">
                        <label class="form-check-label" for="devLang0">Java</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" name="devLang" id="devLang1" value="C">
                        <label class="form-check-label" for="devLang1">C</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" name="devLang" id="devLang2" value="Javascript">
                        <label class="form-check-label" for="devLang2">Javascript</label>
                    </div>
                </div>
            </div>

            <div class="form-group row">
                <div class="col-sm-12">
                    <button type="button" class="btn btn-outline-primary col-sm-12" onclick="requestTest('demo1.do')">
                        servlet처럼 controller 이용하기
                    </button>
                </div>
            </div>

            <div class="form-group row">
                <div class="col-sm-12">
                    <button type="button" class="btn btn-outline-primary col-sm-12" onclick="requestTest('demo2.do')">
                     파라미터 직접받아 처리하기 (1:1매칭)
                    </button>
                </div>
            </div>

        </form>
    </div>
    <script>
        const requestTest=(url)=>{
            const form=document.getElementById("devFrm");
            form.action="${pageContext.request.contextPath}/demo/"+url;
            form.submit();
        }
    </script>


</section>

<style>

div#demo-container{
width:50%;
padding:15px;
margin:0 auto;
border:1px solid lightgray;
border-radius:10px;
}

</style>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>