let index={

    init:function(){
        $("#btn-save").on("click", ()=>{
            if($('#username').val()=='') {
                alert("Username을 입력해주세요");
            }
            else if($('#password').val()=='') {
                alert("Password를 입력해주세요");
            }
            else if($('#email').val()=='') {
                alert("Email을 입력해주세요");
            }

            else{ this.save();}

        });
        $("#btn-update").on("click",()=>{
            this.update();
        })

        $('.myButton').on("click", ()=>{
            $.ajax({
                type: "GET",
                url: "/auth/idCheck",
                data: {
                    "username": $('#username').val()
                },
                success: function(data){
                    if ($.trim(data)=="YES"){
                        if($('#username').val() !='') {
                            $('#checkMsg').html('<p style="color:blue">사용가능한 Username입니다!</p>');
                        }
                    }
                    else{
                        if ($('#username').val() !=''){
                            $('#checkMsg').html('<p style="color:red">이미 사용 중인 Username입니다! 다른 Username을 입력하세요</p>');
                            $('#username').val('');
                            $('#username').focus();

                        }
                    }
                }
            })
        });
    },


    save:function(){
        //alert('user의 save 함수가 호출됨');
        let data={ //값을 바인딩
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        //console.log(data);
        //ajax 호출시 default가 비동기 호출
        //ajax 통신을 이용해서 3개 데이터를 제이슨으로 변경해 인서트 요청
        //ajax가 통신을 성공하고 서버가 제이슨을 리턴해주면 자동으로 자바 오브젝트로 변환
        $.ajax({
            //회원가입수행요청
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), //위의 이 데이터는 자바스크립트 오브젝트 -> 제이슨으로 변경 , http body 데이터
            contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지 !(MIME)
            dataType: "json" //요청이 서버로해서 응답이 왔을 떄 기본적으로 모든 게 문자열 생긴게 json이라면 =javascript 오브젝트로 변경해줌
        }).done(function(resp){
            //요청 정상이면 수행
            alert("회원가입 완료");
           console.log(resp);
           location.href="/"
        }).fail(function(error){
            alert(JSON.stringify(error));
        }); //ajax 통신을 이용해 3개의 데이터를 json으로 변경해 insert 요청
    },
    update:function(){
        //alert('user의 save 함수가 호출됨');
        let data={ //값을 바인딩
            id: $("#id").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };
        $.ajax({
            //회원가입수행요청
            type: "PUT",
            url: "/user",
            data: JSON.stringify(data), //위의 이 데이터는 자바스크립트 오브젝트 -> 제이슨으로 변경 , http body 데이터
            contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지 !(MIME)
            dataType: "json" //요청이 서버로해서 응답이 왔을 떄 기본적으로 모든 게 문자열 생긴게 json이라면 =javascript 오브젝트로 변경해줌
        }).done(function(resp){
            //요청 정상이면 수행
            alert("회원수정 완료");
            console.log(resp);
            location.href="/"
        }).fail(function(error){
            alert(JSON.stringify(error));
        }); //ajax 통신을 이용해 3개의 데이터를 json으로 변경해 insert 요청
    }
}

index.init();