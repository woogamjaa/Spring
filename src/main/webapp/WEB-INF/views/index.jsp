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


    <button class="btn btn-outline-dark" onclick="searchBoard();">
        게시글 조회
    </button>
    <div id="data-container"></div>

    <input type="text" id="boardNo">
    <button class="btn btn-outline-dark" onclick="searchBoardByNo();">
        게시글 번호로 조회
    </button>

    <button class="btn btn-outline-warning" onclick="saveMember();">회원가입</button>
    <button onclick="excelDownload();">엑셀다운로드</button>

    <script>
        const excelDownload=()=>{
            location.assign("${path}/board/exceldownload")
        }




        const saveMember=()=>{
            //post 방식으로 저장
            fetch("${path}/api/member",{
                method:"post",
                headers:{
                    "Content-Type":"application/json"
                },
                body:JSON.stringify({
                    userId:"user99", password:"1234", userName:"양커비", gender:"M", age:"30",
                    phone:"01012341234",address:"서울시 동작구",hobby:["판서","그림","코딩"]
                })
            }).then(response=>{
                if(response.ok) return response.json();
                else throw new Error("입력 실패!");
            }).then(data=>{
                console.log(data);
            }).catch(e=>{
                alert(e);
            })
        }
        const searchBoard=()=>{
            loadingfunc("data-container");
            fetch("${path}/api/boards")
                .then(response=>{
                    if(response.ok) return response.json();
                    else throw new Error("게시글 조회실패");
                }).then (boards=>{
                    //요청성공
                const table=document.createElement("table");
                const tr=document.createElement("tr");
                Object.keys(boards[0]).forEach(header=>{
                    const th=document.createElement("td");
                    th.innerText=header;
                    tr.appendChild(th);
                })
                table.appendChild(tr);
                boards.forEach(data=>{
                    const tr=document.createElement("tr");
                    Object.values(data).forEach(body=>{
                        const td=document.createElement("td");
                        td.innerText=body;
                        tr.appendChild(td);
                    })
                    table.appendChild(tr);
                });
                document.getElementById("data-container").innerHTML="";
                document.getElementById("data-container").appendChild(table);
                }).catch(e=>{
                alert(e);
            });
        }
        const searchBoardByNo=()=>{
            const boardNo=document.querySelector("#boardNo").value;
            fetch(`${path}/api/boards/\${boardNo}`)
                .then(response=>{
                    if(response.ok) return response.json();
                    else throw new Error(`\${boardNo}는 없습니다.`)
                })
                .then(board=>{
                    console.log(board);
                }).catch(e=>{
                alert(e);
            });
        }
        const loadingfunc=(id)=> {
            document.getElementById(id).innerHTML = `<div class="spinner-border text-dark" role="status">
            <span class="visually-hidden"></span>
            </div>`
            }
        const dataAjax=(e)=>{
            loadingfunc("data-container");
            fetch("${path}/ajax/dataAjax")
            .then(response=>response.json())
            .then(data=>{
            console.log(data);
            const ul=document.createElement("ul");
            data.map(e=>{
            const li=document.createElement("li");
            li.innerText=e;
            return li;
        }).forEach(li=>{
            ul.appendChild(li);
        });
            document.getElementById("data-container").innerHTML="";
            document.getElementById("data-container").appendChild(ul);
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
            loadingfunc("data-container");
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
