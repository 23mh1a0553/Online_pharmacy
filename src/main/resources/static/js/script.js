const userId = localStorage.getItem("userId");

/* ================= LOAD MEDICINES ================= */
function loadMedicines() {

    if (!userId) {
        window.location.href = "/login";
        return;
    }

    fetch(API + "/medicines")
    .then(res => res.json())
    .then(data => {

        const container = document.getElementById("medicineList");
        container.innerHTML = "";

        if (!data || data.length === 0) {
            container.innerHTML = "<p>No medicines available 💊</p>";
            return;
        }

        data.forEach(med => {

            container.innerHTML += `
                <div class="card">
                    <img src="${med.imageUrl}" 
                         onerror="this.src='https://via.placeholder.com/300x150?text=Medicine'">
                    <h3>${med.name}</h3>
                    <p>₹${med.price}</p>

                    <button class="btn btn-primary" onclick="addToCart(${med.id})">
                        Add to Cart
                    </button>

                    <button class="btn btn-secondary" onclick="buyNow(${med.id})">
                        Buy Now
                    </button>
                </div>
            `;
        });
    })
    .catch(() => {
        document.getElementById("medicineList").innerHTML = "<p>Error loading medicines ❌</p>";
    });
}


/* ================= ADD TO CART ================= */
function addToCart(medicineId) {

    fetch(API + "/cart/add", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            userId: parseInt(userId),
            medicineId: medicineId,
            quantity: 1
        })
    })
    .then(res => {
        if (!res.ok) throw new Error();
        alert("Added to cart ✅");
    })
    .catch(() => {
        alert("Error adding to cart ❌");
    });
}


/* ================= BUY NOW ================= */
function buyNow(medicineId) {

    fetch(API + "/cart/add", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            userId: parseInt(userId),
            medicineId: medicineId,
            quantity: 1
        })
    })
    .then(() => fetch(API + "/order/place/" + userId, { method: "POST" }))
    .then(res => {
        if (!res.ok) throw new Error();
        alert("Order placed ✅");
        window.open(API + "/invoice/" + userId);
    })
    .catch(() => {
        alert("Order failed ❌");
    });
}


/* ================= LOGOUT ================= */
function logout() {
    localStorage.clear();
    window.location.href = "/login";
}


/* ================= AUTO LOAD ================= */
window.onload = loadMedicines;