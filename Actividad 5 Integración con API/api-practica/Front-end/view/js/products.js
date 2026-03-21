/* ══════════════════════════════════════
   products.js  –  Lógica de Productos
   ══════════════════════════════════════ */

async function getAllProducts() {
  showLoading('containerProducts');
  try {
    var products = await request(URL_PRODUCTS, GET);
    document.getElementById('productCount').textContent = products.length;
    renderProducts(products);
  } catch (e) {
    showError('containerProducts', 'No se pudo conectar al servidor');
    showToast('⚠️ Error al cargar productos');
  }
}

async function getFindByIdProduct() {
  var id = document.getElementById("idProductFilter").value.trim();
  if (!id) { getAllProducts(); return; }

  showLoading('containerProducts');
  try {
    let product = await request(URL_PRODUCTS + "/" + id, GET);
    renderProducts(Array.isArray(product) ? product : [product]);
  } catch (e) {
    showError('containerProducts', 'Producto no encontrado con ID: ' + id);
  }
}

function renderProducts(products) {
  var container = document.getElementById("containerProducts");
  clearTable(container);

  if (!products || products.length === 0) {
    showError('containerProducts', 'No se encontraron productos');
    return;
  }

  products.forEach((p, i) => {
    var tr = document.createElement("tr");
    tr.style.animationDelay = (i * 0.04) + 's';
    tr.className = 'fade-row';
    tr.onclick = () => openProductModal(p);

    const inStock = (p.stock || 0) > 20;
    const stars = buildStars(p.rating);
    const discount = p.discountPercentage ? '<span class="discount">-' + Math.round(p.discountPercentage) + '%</span>' : '';
    const stockBadge = '<span class="badge-stock ' + (inStock ? 'in-stock' : 'low-stock') + '">' + (p.stock || 0) + '</span>';

    tr.innerHTML =
      '<td><span class="id-badge">' + p.id + '</span></td>' +
      '<td>' + (p.thumbnail ? '<img src="' + p.thumbnail + '" class="thumb" alt="' + p.title + '" onerror="this.style.display=\'none\'">' : '') +
      '<span class="product-name">' + p.title + '</span></td>' +
      '<td><span class="category-badge">' + (p.category || '—') + '</span></td>' +
      '<td>' + (p.brand || '—') + '</td>' +
      '<td class="price-cell">$' + Number(p.price || 0).toFixed(2) + ' ' + discount + '</td>' +
      '<td><span class="stars">' + stars + '</span> ' + Number(p.rating || 0).toFixed(1) + '</td>' +
      '<td>' + stockBadge + '</td>';

    container.appendChild(tr);
  });
}


/* ══════════════════════════════════════
   MODAL – Detalle de producto
   ══════════════════════════════════════ */

function openProductModal(product) {
  const body = document.getElementById('modalBody');
  const stars = buildStars(product.rating);
  const inStock = (product.stock || 0) > 20;

  const tagsHtml = (product.tags || [])
    .map(function (t) { return '<span class="modal-tag">' + t + '</span>'; })
    .join('');

  const reviewsHtml = (product.reviews || []).map(function (r) {
    return '<div class="review-item">' +
      '<div class="review-header">' +
      '<span class="review-name">' + r.reviewerName + '</span>' +
      '<span class="review-stars">' + '★'.repeat(r.rating) + '☆'.repeat(5 - r.rating) + '</span>' +
      '</div>' +
      '<div class="review-text">' + r.comment + '</div>' +
      '</div>';
  }).join('');

  body.innerHTML =
    // Columna izquierda
    '<div class="modal-left">' +
    (product.thumbnail
      ? '<img class="modal-img" src="' + product.thumbnail + '" alt="' + product.title + '">'
      : '<div class="modal-img-placeholder">🛍</div>') +
    (tagsHtml ? '<div class="modal-tags">' + tagsHtml + '</div>' : '') +
    '</div>' +

    // Columna derecha
    '<div class="modal-right">' +
    '<div class="modal-title">' + product.title + '</div>' +
    '<div class="modal-brand">' + (product.brand || '') + ' &nbsp;·&nbsp; <span class="category-badge">' + (product.category || '') + '</span></div>' +
    '<div class="modal-price-row">' +
    '<span class="modal-price">$' + Number(product.price || 0).toFixed(2) + '</span>' +
    (product.discountPercentage ? '<span class="modal-discount">-' + Math.round(product.discountPercentage) + '% OFF</span>' : '') +
    '</div>' +
    '<div class="modal-desc">' + (product.description || '') + '</div>' +
    '<div class="modal-grid">' +
    '<div class="modal-field"><div class="modal-field-label">Rating</div><div class="modal-field-value"><span style="color:#e8a020">' + stars + '</span> ' + Number(product.rating || 0).toFixed(1) + '</div></div>' +
    '<div class="modal-field"><div class="modal-field-label">Stock</div><div class="modal-field-value"><span class="badge-stock ' + (inStock ? 'in-stock' : 'low-stock') + '">' + (product.stock || 0) + ' uds</span></div></div>' +
    '<div class="modal-field"><div class="modal-field-label">SKU</div><div class="modal-field-value">' + (product.sku || '—') + '</div></div>' +
    '<div class="modal-field"><div class="modal-field-label">Peso</div><div class="modal-field-value">' + (product.weight ? product.weight + ' kg' : '—') + '</div></div>' +
    '<div class="modal-field"><div class="modal-field-label">Garantía</div><div class="modal-field-value">' + (product.warrantyInformation || '—') + '</div></div>' +
    '<div class="modal-field"><div class="modal-field-label">Envío</div><div class="modal-field-value">' + (product.shippingInformation || '—') + '</div></div>' +
    '<div class="modal-field"><div class="modal-field-label">Devolución</div><div class="modal-field-value">' + (product.returnPolicy || '—') + '</div></div>' +
    '<div class="modal-field"><div class="modal-field-label">Orden mínima</div><div class="modal-field-value">' + (product.minimumOrderQuantity || '—') + ' uds</div></div>' +
    '</div>' +
    '</div>' +

    // Reseñas (ancho completo)
    (reviewsHtml ? '<div class="modal-reviews"><div class="modal-reviews-title">Reseñas (' + (product.reviews || []).length + ')</div>' + reviewsHtml + '</div>' : '');

  document.getElementById('modalOverlay').classList.add('open');
}

function closeModal(event) {
  // Cerrar solo si se clickea el overlay, no el modal mismo
  if (event && event.target !== document.getElementById('modalOverlay')) return;
  document.getElementById('modalOverlay').classList.remove('open');
}

document.addEventListener('keydown', function (e) {
  if (e.key === 'Escape') document.getElementById('modalOverlay').classList.remove('open');
});