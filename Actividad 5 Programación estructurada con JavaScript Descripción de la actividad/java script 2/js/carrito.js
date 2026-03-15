function showMsg(id, tipo, txt) {
  const el = document.getElementById(id);
  if (!el) return;
  el.style.display='block';
  el.innerHTML = txt;
  clearTimeout(el._t);
  el._t = setTimeout(() => el.className = 'msg', 5000);
}
function f$(n) { return '$' + parseFloat(n).toFixed(2); }

class Item { constructor(nombre, precio) { this.nombre = nombre; this.precio = precio; } }

let carrito = [];

function agregarCarrito() {
  const n = document.getElementById('carProd').value.trim();
  const p = parseFloat(document.getElementById('carPrecio').value);
  if (!n || isNaN(p) || p < 0) return showMsg('msgCar','er','Ingresa nombre y precio válidos');
  carrito.push(new Item(n, p));
  showMsg('msgCar','ok',`${n} agregado al carrito`);
  document.getElementById('carProd').value = '';
  document.getElementById('carPrecio').value = '';
  mostrarCarrito();
}

function eliminarItem(i) { carrito.splice(i, 1); mostrarCarrito(); }
function totalCarrito() { return carrito.reduce((s, item) => s + item.precio, 0); }

function mostrarCarrito() {
  const lista = document.getElementById('listaCarrito');
  const total = totalCarrito();
  document.getElementById('cItems').textContent = carrito.length;
  document.getElementById('cTotal').textContent = f$(total);
  if (!carrito.length) {
    lista.innerHTML = '<li style="display:block"><div class="empty">Carrito vacío</div></li>';
    document.getElementById('carritoFooter').style.display = 'none';
    return;
  }
  lista.innerHTML = carrito.map((item, i) =>
    `<li><span class="in">${item.nombre}</span><span class="iv">${f$(item.precio)}</span>
    <button class="btn bsm bdel" onclick="eliminarItem(${i})">Eliminar</button></li>`
  ).join('');
  document.getElementById('carTotalVal').textContent = f$(total);
  document.getElementById('carritoFooter').style.display = 'block';
}

function abrirModalPago() {
  if (!carrito.length) return;
  const total = totalCarrito();
  document.getElementById('modalContenido').innerHTML = `
    <h3>Resumen de compra</h3>
    <p>Revisa tus artículos antes de confirmar</p>
    ${carrito.map(item => `<div class="resumen-item"><span>${item.nombre}</span><span>${f$(item.precio)}</span></div>`).join('')}
    <div class="total-final"><span>Total</span><span>${f$(total)}</span></div>
    <div class="btns-modal">
      <button class="btn bp btn-full" style="flex:2" onclick="confirmarPago()">Confirmar pago</button>
      <button class="btn bg2btn btn-full" style="flex:1" onclick="cerrarModal()">Cancelar</button>
    </div>`;
  document.getElementById('modalPago').style.display='flex';
}

function confirmarPago() {
  const total = totalCarrito();
  document.getElementById('modalContenido').innerHTML = `
    <div class="success-screen">
      <h3>Pago realizado</h3>
      <p>Tu compra por ${f$(total)} fue procesada exitosamente.</p>
      <button class="btn bp btn-full" style="margin-top:16px" onclick="cerrarModal()">Cerrar</button>
    </div>`;
  carrito = []; mostrarCarrito();
}

function cerrarModal() { document.getElementById('modalPago').style.display='none'; }
document.getElementById('modalPago').addEventListener('click', function(e) { if (e.target === this) cerrarModal(); });
