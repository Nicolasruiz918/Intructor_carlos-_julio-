async function getAllUsers() {
  showLoading('containerUsers');
  try {
    var users = await request(URL_USERS, GET);
    document.getElementById('userCount').textContent = users.length;
    renderUsers(users);
  } catch (e) {
    showError('containerUsers', 'No se pudo conectar al servidor');
    showToast('⚠️ Error al cargar usuarios');
  }
}

async function getFindByIdUser() {
  var id = document.getElementById("idUserFilter").value.trim();
  if (!id) { getAllUsers(); return; }

  showLoading('containerUsers');
  try {
    let user = await request(URL_USERS + "/" + id, GET);
    renderUsers(Array.isArray(user) ? user : [user]);
  } catch (e) {
    showError('containerUsers', 'Usuario no encontrado con ID: ' + id);
  }
}

function renderUsers(users) {
  var container = document.getElementById("containerUsers");
  clearTable(container);

  if (!users || users.length === 0) {
    showError('containerUsers', 'No se encontraron usuarios');
    return;
  }

  users.forEach((u, i) => {
    var tr = document.createElement("tr");
    tr.style.animationDelay = (i * 0.04) + 's';
    tr.className = 'fade-row';

    const name = `${u.firstName || ''} ${u.lastName || ''}`.trim() || u.username || '—';
    const avatar = u.image
      ? `<img src="${u.image}" class="avatar-sm" alt="${name}" onerror="this.style.display='none'">`
      : `<span class="avatar-placeholder">👤</span>`;
    const city = u.address?.city || '—';

    tr.innerHTML = `
      <td><span class="id-badge">${u.id}</span></td>
      <td>${avatar} <span class="product-name">${name}</span></td>
      <td>${u.username || '—'}</td>
      <td>${u.email || '—'}</td>
      <td>${u.phone || '—'}</td>
      <td>${city}</td>
      <td><span class="role-badge">${u.role || 'user'}</span></td>
    `;
    container.appendChild(tr);
  });
}