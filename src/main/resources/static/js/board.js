let index={

    init:function(){
        $("#btn-save").on("click", ()=>{
            this.save();
        });
        $("#btn-delete").on("click", ()=>{
            this.deleteById();
        });
        $("#btn-update").on("click", ()=>{
            this.updateById();
        });
        $("#btn-reply-save").on("click", ()=>{
            this.replySave();
        });
    },


    save:function(){
        //alert('user의 save 함수가 호출됨');
        let data={ //값을 바인딩
            title: $("#title").val(),
            content: $("#content").val()
        };
        $.ajax({
            //회원가입수행요청
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지 !(MIME)
            dataType: "json" //요청이 서버로해서 응답이 왔을 떄 기본적으로 모든 게 문자열 생긴게 json이라면 =javascript 오브젝트로 변경해줌
        }).done(function(resp){
            //요청 정상이면 수행
            alert("글쓰기 완료");
           console.log(resp);
           location.href="/"
        }).fail(function(error){
            alert(JSON.stringify(error));
        }); //ajax 통신을 이용해 3개의 데이터를 json으로 변경해 insert 요청
    },
    deleteById:function(){
        //alert('user의 save 함수가 호출됨');

        let id=$("#id").text();
        $.ajax({
            //회원가입수행요청
            type: "DELETE",
            url: "/api/board/"+id,
            dataType: "json" //요청이 서버로해서 응답이 왔을 떄 기본적으로 모든 게 문자열 생긴게 json이라면 =javascript 오브젝트로 변경해줌
        }).done(function(resp){
            //요청 정상이면 수행
            alert("삭제가 완료되었습니다.");
            location.href="/"
        }).fail(function(error){
            alert(JSON.stringify(error));
        }); //ajax 통신을 이용해 3개의 데이터를 json으로 변경해 insert 요청
    },
    updateById:function(){
        //alert('user의 save 함수가 호출됨');
        let id=$("#id").val();
        let data={ //값을 바인딩
            title: $("#title").val(),
            content: $("#content").val()
        };
        $.ajax({
            //회원가입수행요청
            type: "PUT",
            url: "/api/board/"+id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지 !(MIME)
            dataType: "json" //요청이 서버로해서 응답이 왔을 떄 기본적으로 모든 게 문자열 생긴게 json이라면 =javascript 오브젝트로 변경해줌
        }).done(function(resp){
            //요청 정상이면 수행
            alert("글 수정 완료");
            console.log(resp);
            location.href="/board/"+id;
        }).fail(function(error){
            alert(JSON.stringify(error));
        }); //ajax 통신을 이용해 3개의 데이터를 json으로 변경해 insert 요청
    },
    replySave:function(){
        //alert('user의 save 함수가 호출됨');
        let data={ //값을 바인딩
            content: $("#reply-content").val(),
            userId: $("#userId").val(),
            boardId: $("#boardId").val()
        };
        console.log(data);
        $.ajax({
            //회원가입수행요청
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지 !(MIME)
            dataType: "json" //요청이 서버로해서 응답이 왔을 떄 기본적으로 모든 게 문자열 생긴게 json이라면 =javascript 오브젝트로 변경해줌
        }).done(function(resp){
            //요청 정상이면 수행
          alert("댓글작성 완료");
          //  console.log(resp);
           location.href=`/board/${data.boardId}`;
        }).fail(function(error){
            alert(JSON.stringify(error));
        }); //ajax 통신을 이용해 3개의 데이터를 json으로 변경해 insert 요청
    },
    replyDelete:function(boardId, replyId){
        //alert('user의 save 함수가 호출됨');
        $.ajax({
            //회원가입수행요청
            type: "DELETE",
            url: `/api/board/${boardId}/reply/${replyId}`,
            dataType: "json" //요청이 서버로해서 응답이 왔을 떄 기본적으로 모든 게 문자열 생긴게 json이라면 =javascript 오브젝트로 변경해줌
        }).done(function(resp){
            //요청 정상이면 수행
            alert("댓글 삭제 완료");
            //  console.log(resp);
            location.href=`/board/${boardId}`;
        }).fail(function(error){
            alert(JSON.stringify(error));
        }); //ajax 통신을 이용해 3개의 데이터를 json으로 변경해 insert 요청
    }

}

index.init();