function showMsg(id, tipo, txt) {
  const el = document.getElementById(id);
  if (!el) return;
  el.style.display='block';
  el.innerHTML = txt;
  clearTimeout(el._t);
  el._t = setTimeout(() => el.className = 'msg', 5000);
}
function f$(n) { return '$' + parseFloat(n).toFixed(2); }

class Producto {
  constructor(nombre, precio, cantidad) { this.nombre = nombre; this.precio = precio; this.cantidad = cantidad; }
  valor() { return this.precio * this.cantidad; }
}

let inventario = [];

function agregarProducto() {
  const n = document.getElementById('pNombre').value.trim();
  const p = parseFloat(document.getElementById('pPrecio').value);
  const c = parseInt(document.getElementById('pCantidad').value);
  if (!n || isNaN(p) || isNaN(c) || p < 0 || c < 1) return showMsg('msgInv','er','Completa todos los campos');
  const idxExacto = inventario.findIndex(prod => prod.nombre.toLowerCase() === n.toLowerCase() && prod.precio === p);
  if (idxExacto !== -1) {
    inventario[idxExacto].cantidad += c;
    showMsg('msgInv','ok',`${n} actualizado. Stock: ${inventario[idxExacto].cantidad}`);
  } else {
    const idxNombre = inventario.findIndex(prod => prod.nombre.toLowerCase() === n.toLowerCase());
    inventario.push(new Producto(n, p, c));
    if (idxNombre !== -1) showMsg('msgInv','wa',`${n} ya existe con otro precio. Se registró como independiente`);
    else showMsg('msgInv','ok',`${n} agregado al inventario`);
  }
  document.getElementById('pNombre').value = '';
  document.getElementById('pPrecio').value = '';
  document.getElementById('pCantidad').value = '';
  mostrarInventario();
}

function quitarUnidadesInput(i) {
  const inputEl = document.getElementById(`quitarInput_${i}`);
  const cantidad = parseInt(inputEl.value);
  if (isNaN(cantidad) || cantidad < 1) return showMsg('msgInv','er','Ingresa cuántas unidades quitar');
  const prod = inventario[i];
  if (cantidad > prod.cantidad) return showMsg('msgInv','wa',`Solo hay ${prod.cantidad} unidad(es)`);
  prod.cantidad -= cantidad;
  if (prod.cantidad === 0) { inventario.splice(i, 1); showMsg('msgInv','wa','Producto eliminado por llegar a 0'); }
  else showMsg('msgInv','ok',`Quitadas ${cantidad} unidad(es). Stock: ${prod.cantidad}`);
  mostrarInventario();
}

function eliminarProductoCompleto(i) {
  inventario.splice(i, 1);
  mostrarInventario();
}

function mostrarInventario() {
  const lista = document.getElementById('listaProductos');
  let total = 0, unds = 0;
  if (!inventario.length) {
    lista.innerHTML = '<li style="display:block"><div class="empty">Sin productos aún</div></li>';
    document.getElementById('invTBar').style.display = 'none';
    document.getElementById('invP').textContent = '0';
    document.getElementById('invU').textContent = '0';
    document.getElementById('invT').textContent = '$0';
    return;
  }
  lista.innerHTML = inventario.map((p, i) => {
    total += p.valor(); unds += p.cantidad;
    return `<li style="flex-direction:column;align-items:stretch;gap:8px">
      <div style="display:flex;align-items:center;gap:10px;flex-wrap:wrap">
        <span class="in">${p.nombre}</span>
        <span class="id2">${f$(p.precio)} c/u</span>
        <span class="badge bb-">Stock: ${p.cantidad}</span>
        <span class="iv">${f$(p.valor())}</span>
        <button class="btn bsm bdel" onclick="eliminarProductoCompleto(${i})">Eliminar</button>
      </div>
      <div style="display:flex;align-items:center;gap:8px">
        <span style="font-size:.75rem;color:#aaa">Quitar:</span>
        <input type="number" id="quitarInput_${i}" min="1" max="${p.cantidad}" placeholder="Cantidad" style="width:90px;padding:4px 8px;font-size:.78rem;flex:none">
        <button class="btn bsm bmin" onclick="quitarUnidadesInput(${i})">Quitar</button>
      </div>
    </li>`;
  }).join('');
  document.getElementById('invP').textContent = inventario.length;
  document.getElementById('invU').textContent = unds;
  document.getElementById('invT').textContent = f$(total);
  document.getElementById('invTVal').textContent = f$(total);
  document.getElementById('invTBar').style.display = 'flex';
}
