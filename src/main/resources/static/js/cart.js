const userId = localStorage.getItem("userId");

/* ================= LOAD CART ================= */
function loadCart() {

    if (!userId) {
        window.location.href = "/login";
        return;
    }

    const div = document.getElementById("cartItems");

    div.innerHTML = "<p>Loading cart...</p>";

    fetch(API + "/cart/" + userId)
    .then(res => res.json())
    .then(data => {

        div.innerHTML = "";

        let total = 0;

        if (!data || data.length === 0) {
            div.innerHTML = "<p>Your cart is empty 🛒</p>";
            document.getElementById("totalAmount").innerText = "0";
            return;
        }

        data.forEach(item => {
            const t = item.price * item.quantity;
            total += t;

            div.innerHTML += `
                <div class="card">
                    <h4>${item.name}</h4>
                    <p>₹${item.price} × ${item.quantity}</p>
                    <p><b>Total: ₹${t}</b></p>
                </div>
            `;
        });

        document.getElementById("totalAmount").innerText = total;
    })
    .catch(() => {
        div.innerHTML = "<p>Error loading cart ❌</p>";
    });
}


/* ================= CHECKOUT ================= */
function checkout() {

    fetch(API + "/cart/" + userId)
    .then(res => res.json())
    .then(cartItems => {

        if (!cartItems || cartItems.length === 0) {
            alert("Cart is empty ❌");
            return;
        }

        fetch(API + "/order/place/" + userId, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(cartItems)   // ✅ IMPORTANT
        })
        .then(res => {
            if (!res.ok) throw new Error();
            alert("Order placed successfully ✅");
            loadCart();
        })
        .catch(() => {
            alert("Order failed ❌");
        });

    });
}

/* ================= DOWNLOAD ================= */
function downloadInvoice() {
    const userId = localStorage.getItem("userId");
    window.open(API + "/invoice/" + userId);
}


/* ================= AUTO LOAD ================= */
window.onload = loadCart;