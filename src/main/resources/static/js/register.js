function registerUser(event) {

    event.preventDefault(); // 🔥 VERY IMPORTANT

    const fullName = document.getElementById("fullName").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const phone = document.getElementById("phone").value;

    fetch(API + "/users/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            fullName: fullName,
            email: email,
            password: password,
            phone: phone
        })
    })
    .then(res => {
        if (!res.ok) {
            throw new Error("Registration failed");
        }
        return res.json();
    })
    .then(data => {

        alert("Registered Successfully ✅");

        // 🔥 REDIRECT
        window.location.href = "/login";
    })
    .catch(err => {
        console.error(err);
        alert("Registration failed ❌");
    });
}