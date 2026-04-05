console.log("Dashboard JS Loaded");

/* ================= INIT ================= */
window.onload = function () {
    checkAuth();
    loadUser();
    loadMedicines();
};


/* ================= USER ================= */
function loadUser() {

    const userId = localStorage.getItem("userId");

    if (!userId) {
        window.location.href = "/login";
        return;
    }

    const name = localStorage.getItem("userName") || "User";

    const welcome = document.getElementById("welcomeMsg");
    if (welcome) {
        welcome.innerText = "👋 Welcome, " + name;
    }
}


/* ================= LOAD MEDICINES ================= */
async function loadMedicines() {

    const container = document.getElementById("medicines");

    container.innerHTML = "<p>Loading medicines...</p>";

    try {
        const res = await fetch(API + "/medicines");
        const medicines = await res.json();

        container.innerHTML = "";

        if (!medicines || medicines.length === 0) {
            container.innerHTML = "<h3>No medicines available 💊</h3>";
            return;
        }

        medicines.forEach(m => {

            let img = m.imageUrl || "https://via.placeholder.com/300x150?text=Medicine";

            container.innerHTML += `
            <div class="card">

                <img src="${img}" 
                     onerror="this.src='https://via.placeholder.com/300x150?text=Medicine'">

                <h4>${m.name}</h4>

                <p style="font-weight:bold;">₹${m.price}</p>

                <button onclick="addToCart(${m.id})" class="btn btn-primary">
                    🛒 Add to Cart
                </button>

            </div>
            `;
        });

    } catch (err) {
        console.error(err);
        container.innerHTML = "<h3>Error loading medicines ❌</h3>";
    }
}


/* ================= ADD TO CART ================= */
async function addToCart(medicineId) {

    const userId = localStorage.getItem("userId");

    if (!userId) {
        window.location.href = "/login";
        return;
    }

    try {
        await fetch(API + "/cart/add", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                userId: parseInt(userId),
                medicineId: medicineId,
                quantity: 1
            })
        });

        showToast("Added to Cart ✅");

    } catch (err) {
        console.error(err);
        showToast("Error adding to cart ❌");
    }
}


/* ================= TOAST ================= */
function showToast(message) {

    let toast = document.createElement("div");

    toast.innerText = message;
    toast.style.position = "fixed";
    toast.style.bottom = "20px";
    toast.style.right = "20px";
    toast.style.background = "#2e7d32";
    toast.style.color = "white";
    toast.style.padding = "10px 15px";
    toast.style.borderRadius = "5px";
    toast.style.boxShadow = "0 2px 10px rgba(0,0,0,0.2)";
    toast.style.zIndex = "999";

    document.body.appendChild(toast);

    setTimeout(() => {
        toast.remove();
    }, 2000);
}


/* ================= LOGOUT ================= */
function logout() {
    localStorage.clear();
    window.location.href = "/login";
}