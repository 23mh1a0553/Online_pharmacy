const userId = localStorage.getItem("userId");

/* ================= LOAD MEDICINES ================= */
function loadMedicines() {

    const container = document.getElementById("medicineList");

    container.innerHTML = `
        <div style="text-align:center;">
            <div class="loader"></div>
            <p>Loading medicines...</p>
        </div>
    `;

    fetch(API + "/medicines")
        .then(res => res.json())
        .then(data => {

            container.innerHTML = "";

            if (!data || data.length === 0) {
                container.innerHTML = "<h3>No medicines available 💊</h3>";
                return;
            }

            data.forEach(med => {

                container.innerHTML += `
                    <div class="card">

                        <!-- ✅ FIXED IMAGE HANDLING -->
                        <img src="${med.imageUrl}" 
                             alt="${med.name}"
                             loading="lazy"
                             style="height:150px; object-fit:contain; background:#f5f5f5;"
                             onerror="this.onerror=null; this.src='/images/default-medicine.png'">

                        <h3>${med.name}</h3>

                        <p style="color:#2e7d32; font-weight:bold;">
                            ₹${med.price}
                        </p>

                        <button class="btn btn-primary" onclick="addToCart(${med.id})">
                            🛒 Add to Cart
                        </button>

                        <button class="btn btn-secondary" onclick="buyNow(${med.id})">
                            ⚡ Buy Now
                        </button>

                    </div>
                `;
            });
        })
        .catch(err => {
            console.error("ERROR:", err);
            container.innerHTML = "<h3>Error loading medicines ❌</h3>";
        });
}


/* ================= ADD TO CART ================= */
function addToCart(id) {

    if (!userId) {
        showToast("Please login first ❌");
        setTimeout(() => window.location.href = "/login", 1200);
        return;
    }

    fetch(API + "/cart/add", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            userId: parseInt(userId),
            medicineId: id,
            quantity: 1
        })
    })
    .then(res => {
        if (!res.ok) throw new Error();
        showToast("Added to cart ✅");
    })
    .catch(() => {
        showToast("Error adding to cart ❌");
    });
}


/* ================= BUY NOW ================= */
function buyNow(id) {

    if (!userId) {
        showToast("Please login first ❌");
        setTimeout(() => window.location.href = "/login", 1200);
        return;
    }

    fetch(API + "/cart/add", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            userId: parseInt(userId),
            medicineId: id,
            quantity: 1
        })
    })
    .then(() => fetch(API + "/order/place/" + userId, { method: "POST" }))
    .then(res => {
        if (!res.ok) throw new Error();

        showToast("Order placed successfully ✅");

        setTimeout(() => {
            window.location.href = "/orders";
        }, 1200);
    })
    .catch(() => {
        showToast("Order failed ❌");
    });
}


/* ================= TOAST ================= */
function showToast(message) {

    let toast = document.createElement("div");

    toast.innerText = message;

    toast.style.position = "fixed";
    toast.style.bottom = "30px";
    toast.style.right = "30px";
    toast.style.background = "#333";
    toast.style.color = "#fff";
    toast.style.padding = "12px 20px";
    toast.style.borderRadius = "8px";
    toast.style.boxShadow = "0 5px 15px rgba(0,0,0,0.2)";
    toast.style.zIndex = "9999";

    document.body.appendChild(toast);

    setTimeout(() => toast.remove(), 2500);
}


/* ================= AUTO LOAD ================= */
window.onload = loadMedicines;