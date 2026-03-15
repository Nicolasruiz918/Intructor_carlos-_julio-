function showMsg(id, tipo, txt) {
  const el = document.getElementById(id);
  if (!el) return;
  el.style.display='block';
  el.innerHTML = txt;
  clearTimeout(el._t);
  el._t = setTimeout(() => el.className = 'msg', 5000);
}
function f$(n) { return '$' + parseFloat(n).toFixed(2); }

class Cuenta {
  constructor(nombre) { this.nombre = nombre; this.saldo = 0; this.historial = []; }
  depositar(m) { this.saldo += m; this.historial.unshift({ tipo:'Depósito', m, saldo:this.saldo, t: new Date() }); }
  retirar(m) {
    if (m > this.saldo) return false;
    this.saldo -= m; this.historial.unshift({ tipo:'Retiro', m, saldo:this.saldo, t: new Date() }); return true;
  }
}

let cuentas = {}, cActiva = null;

function crearCuenta() {
  const n = document.getElementById('cliNombre').value.trim();
  if (!n) return showMsg('msgCC','er','Ingresa el nombre del cliente');
  if (cuentas[n]) return showMsg('msgCC','wa',`Ya existe una cuenta para ${n}`);
  cuentas[n] = new Cuenta(n);
  cActiva = cuentas[n];
  showMsg('msgCC','ok',`Cuenta creada para ${n}`);
  document.getElementById('cliNombre').value = '';
  renderChips(); seleccionarCuenta(n);
}

function renderChips() {
  const nombres = Object.keys(cuentas);
  document.getElementById('cuentaChips').innerHTML = nombres.map(n =>
    `<button class="cuenta-chip ${cActiva && cActiva.nombre === n ? 'selected' : ''}" onclick="seleccionarCuenta('${n.replace(/'/g,"\\'")}')">  ${n}</button>`
  ).join('');
  document.getElementById('bancoOperCard').style.display = nombres.length ? 'block' : 'none';
}

function seleccionarCuenta(n) { cActiva = cuentas[n]; renderChips(); actualizarBanco(); }

function depositar() {
  if (!cActiva) return showMsg('msgBOp','er','Selecciona una cuenta primero');
  const m = parseFloat(document.getElementById('monto').value);
  if (isNaN(m) || m <= 0) return showMsg('msgBOp','er','Ingresa un monto válido');
  cActiva.depositar(m);
  showMsg('msgBOp','ok',`Depósito de ${f$(m)} realizado`);
  document.getElementById('monto').value = '';
  actualizarBanco();
}

function retirar() {
  if (!cActiva) return showMsg('msgBOp','er','Selecciona una cuenta primero');
  const m = parseFloat(document.getElementById('monto').value);
  if (isNaN(m) || m <= 0) return showMsg('msgBOp','er','Ingresa un monto válido');
  if (!cActiva.retirar(m)) return showMsg('msgBOp','wa',`Fondos insuficientes. Saldo: ${f$(cActiva.saldo)}`);
  showMsg('msgBOp','ok',`Retiro de ${f$(m)} realizado`);
  document.getElementById('monto').value = '';
  actualizarBanco();
}

function actualizarBanco() {
  if (!cActiva) return;
  document.getElementById('nombreCuentaActiva').textContent = cActiva.nombre;
  document.getElementById('bSaldo').textContent = f$(cActiva.saldo);
  document.getElementById('bOps').textContent = cActiva.historial.length;
  const h = document.getElementById('histBanco');
  if (!cActiva.historial.length) { h.innerHTML = '<div class="empty">Sin movimientos</div>'; return; }
  h.innerHTML = cActiva.historial.slice(0, 8).map(op => {
    const dep = op.tipo === 'Depósito';
    const t = op.t.toLocaleTimeString('es-CO', { hour:'2-digit', minute:'2-digit' });
    return `<div class="hi">
      <div class="hdot" style="background:${dep?'#080':'#c00'}"></div>
      <span style="flex:1">${op.tipo}</span>
      <span style="color:#aaa;font-size:.75rem">${t}</span>
      <span style="font-weight:700;color:${dep?'#080':'#c00'}">${dep?'+':'-'}${f$(op.m)}</span>
      <span style="color:#aaa;font-size:.75rem">→ ${f$(op.saldo)}</span>
    </div>`;
  }).join('');
}
