function loadData(container, registers) {
    clearTable(container);
    if (Array.isArray(registers)) {
        registers.forEach(register => loadTable(container, register));
    } else {
        loadTable(container, registers);
    }
}

function loadTable(container, data) {
    var register = document.createElement("tr");
    Object.values(data).forEach(element => {
        if (typeof element === 'object' && element !== null) return; // skip nested objects
        const cell = document.createElement("td");
        cell.innerText = element;
        register.appendChild(cell);
    });
    container.appendChild(register);
}

function clearTable(container) {
    container.innerHTML = "";
}

function showToast(message) {
    const toast = document.getElementById('toast');
    toast.textContent = message;
    toast.classList.add('show');
    setTimeout(() => toast.classList.remove('show'), 2800);
}

function showLoading(containerId) {
    document.getElementById(containerId).innerHTML =
        '<tr><td colspan="10" class="loading-cell"><div class="spinner"></div></td></tr>';
}

function showError(containerId, msg) {
    document.getElementById(containerId).innerHTML =
        `<tr><td colspan="10" class="empty-cell">🔍 ${msg}</td></tr>`;
}

function buildStars(rating) {
    const full = Math.round(rating || 0);
    return '★'.repeat(full) + '☆'.repeat(5 - full);
}