const API_BASE_URL = "http://localhost:80";
const ACCESS_TOKEN = "ACCESS_TOKEN";

function call(api, method, request) {
    let headers = new Headers({
        "Content-Type": "application/json",
    });

    //로컬 스토리지에서 ACCESS_TOKEN 가져오기
    const accessToken = localStorage.getItem("ACCESS_TOKEN");
    if (accessToken && accessToken !== null) {
        headers.append("Authorization", "Bearer " + accessToken);
    }

    let options = {
        headers : headers,
        url: API_BASE_URL + api,
        method: method,
    }

    if (request) {
        //GET method
        options.body = JSON.stringify(request);
    }
    return fetch(options.url, options)
        .then((response) =>
            response.json().then((json) => {
                if (!response.ok) {
                    //response.ok가 true이면 정상적인 응답을 받은 것이고 아니면 에러 응답을 받은 것
                    return Promise.reject(json);
                }
                return json;
            })
        )
        .catch((error) => {
            console.log(error.status);
            if (error.status === 403) {
                window.location.href = "/login";  //redirect
            }
            return Promise.reject(error);
        })
}

function signin(employeeDTO) {
    return call("/api/auth/signin", "POST", employeeDTO).then((response) => {
        if (response.token) {
            //로컬 스토리지에 토큰 저장
            localStorage.setItem(ACCESS_TOKEN, response.token);
            //token이 존재하는 경우 리스트 화면으로 Redirect
            window.location.href = "/";
        }
    })
}

function logout() {
    //localStorage.setItem(ACCESS_TOKEN, null);
    localStorage.removeItem(ACCESS_TOKEN);
    window.location.href = "/login";
}

function signup(employeeDTO) {
    return call("/api/auth/signup", "POST", employeeDTO);
}

