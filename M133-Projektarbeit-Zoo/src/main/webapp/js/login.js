function userLogin() {
    let usernameValue = document.getElementById("usernameInput").value
    let passwordValue = document.getElementById("passwordInput").value
    fetch('/M133-Projektarbeit-Zoo-1.0-SNAPSHOT/resource/user/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        body: new URLSearchParams({
            'username': usernameValue,
            'password': passwordValue
        })
    })
        .then(response => response.text())
        .then(data =>alert("Your Login was correct, logged in as " + data));
}