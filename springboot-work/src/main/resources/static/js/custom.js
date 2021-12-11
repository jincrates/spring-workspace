//ajax 사용 method
function ajax(method, url, data) {
    var result = new Object();

    $.ajax({
        method: method,
        url: url,
        data: JSON.stringify(data),
        headers: {
            Authorization: 'Bearer '+ localStorage.getItem("ACCESS_TOKEN")
        },
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        complete: function(xhr) {
            result = xhr;
        }
    });

    return result;
}

//회원가입
function signUp() {
    var data = {
        email: $('input[name="email"]').val(),
        password: $('input[name="password"]').val(),
        username: $('input[name="username"]').val(),
        joinDate: $('input[name="joinDate"]').val(),
    }

    var result = ajax('POST', '/auth/signup', data);

    if(result.status === 200) {
        alert('Sign Up Success');
    }
};

//로그인
function signin() {
    var data = {
        email: $('input[name="loginId"]').val(),
        password: $('input[name="loginPwd"]').val(),
    }

    var result = ajax('POST', '/auth/signin', data);

    //로그인 성공
    if(result.status === 200) {
        var response = result.responseJSON;

        //로컬 스토리지에 저장(key-value)
        //쿠키와 비슷하지만 로컬 스토리지는 도메인마다 따로 저장되기 때문에 다른 도메인의 로컬 스토리지는 알지 못한다.
        localStorage.setItem("ACCESS_TOKEN", response.token);

        //로컬 스토리지에서 ACCESS_TOKEN 가져오기
        //var accessToken = localStorage.getItem("ACCESS_TOKEN");
        //console.log(accessToken);

        //홈으로 이동
        location.href = "/";
    } else {
        alert('로그인 실패');
        location.href = "/login";
    }
}

// 자동하이픈 삽입(YYYY-MM-DD)
function insertHyphen(obj) {
    // DELETE 키버튼이 눌리지 않은 경우에만 실행
    if(event.keyCode != 8) {
        // 숫자와 하이픈(-)기호의 값만 존재하는 경우 실행
        if(obj.value.replace(/[0-9 \-]/g, "").length == 0) {
            // 하이픈(-)기호를 제거한다.
            let number = obj.value.replace(/[^0-9]/g,"");
            let ymd = "";

            // 문자열의 길이에 따라 Year, Month, Day 앞에 하이픈(-)기호를 삽입한다.
            if(number.length < 4) {
                return number;
            } else if(number.length < 6){
                ymd += number.substr(0, 4);
                ymd += "-";
                ymd += number.substr(4);
            } else {
                ymd += number.substr(0, 4);
                ymd += "-";
                ymd += number.substr(4, 2);
                ymd += "-";
                ymd += number.substr(6);
            }

            obj.value = ymd;

        } else {
            alert("숫자 이외의 값은 입력하실 수 없습니다.");

            //숫자와 하이픈(-)기호 이외의 모든 값은 삭제한다.
            obj.value = obj.value.replace(/[^0-9 ^\-]/g,"");

            return false;
        }

    } else {
        return false;
    }
}



