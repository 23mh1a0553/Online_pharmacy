function loadOrders() {

    const userId = localStorage.getItem("userId");

    if (!userId) {
        window.location.href = "/login";
        return;
    }

    const div = document.getElementById("orders");

    div.innerHTML = `<div class="loader"></div>`;

    fetch(API + "/orders/" + userId)
    .then(res => res.json())
    .then(data => {

        div.innerHTML = "";

        if (!data || data.length === 0) {
            div.innerHTML = "<p style='text-align:center;'>No orders yet 📦</p>";
            return;
        }

        data.forEach(o => {

            div.innerHTML += `
                <div class="order-card">
                    <h3>Order #${o.id}</h3>
                    <p><b>Amount:</b> ₹${o.totalAmount}</p>
                    <p style="color:gray;">${o.createdAt || ""}</p>
                </div>
            `;
        });
    })
    .catch(() => {
        div.innerHTML = "<p style='text-align:center;'>Failed to load orders ❌</p>";
    });
}


/* ================= DOWNLOAD ================= */
function downloadInvoice() {

    const userId = localStorage.getItem("userId");

    fetch(API + "/invoice/" + userId)
    .then(res => res.blob())
    .then(blob => {

        const url = window.URL.createObjectURL(blob);

        const a = document.createElement("a");
        a.href = url;
        a.download = "invoice.pdf";
        a.click();

        window.URL.revokeObjectURL(url);
    })
    .catch(() => {
        showToast("Download failed ❌");
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
window.onload = loadOrders;