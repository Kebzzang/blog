let index={

    init:function(){
        $("#btn-save").on("click", ()=>{
            this.save();

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
    }
}

index.init();