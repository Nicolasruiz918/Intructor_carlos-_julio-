function showMsg(id, tipo, txt) {
  const el = document.getElementById(id);
  if (!el) return;
  el.style.display='block';
  el.innerHTML = txt;
  clearTimeout(el._t);
  el._t = setTimeout(() => el.className = 'msg', 5000);
}

class Vehiculo {
  constructor(nombre) { this.nombre = nombre; this.velocidad = 0; }
  acelerar() { this.velocidad += 10; }
  frenar() { this.velocidad = Math.max(0, this.velocidad - 10); }
  estado() {
    if (this.velocidad === 0) return 'Detenido';
    if (this.velocidad <= 40) return 'Lento';
    if (this.velocidad <= 80) return 'Moderado';
    if (this.velocidad <= 120) return 'Rápido';
    return 'Muy rápido';
  }
}

let vehiculos = {};

function crearVehiculo() {
  const n = document.getElementById('vNombre').value.trim();
  if (!n) return showMsg('msgVeh','er','Ingresa el nombre del vehículo');
  if (vehiculos[n]) return showMsg('msgVeh','wa','Ese vehículo ya existe');
  vehiculos[n] = new Vehiculo(n);
  showMsg('msgVeh','ok',`Vehículo ${n} registrado`);
  document.getElementById('vNombre').value = '';
  renderVehiculos();
}

function acV(n) { vehiculos[n].acelerar(); renderVehiculos(); }
function frV(n) { vehiculos[n].frenar(); renderVehiculos(); }
function delV(n) { delete vehiculos[n]; renderVehiculos(); }

function renderVehiculos() {
  const cont = document.getElementById('listaVehiculos');
  const keys = Object.keys(vehiculos);
  if (!keys.length) { cont.innerHTML = '<div class="empty">Sin vehículos registrados</div>'; return; }
  cont.innerHTML = keys.map(n => {
    const v = vehiculos[n];
    const pct = Math.min(v.velocidad / 200 * 100, 100);
    const safe = n.replace(/'/g,"\\'");
    return `<div class="vc">
      <div class="vcn">${v.nombre}</div>
      <div class="vcs"><span>${v.velocidad}</span> km/h — ${v.estado()}</div>
      <div class="pw"><div class="pf fc" style="width:${Math.max(pct,0.5)}%"></div></div>
      <div class="vca" style="margin-top:8px">
        <button class="btn bsm bs" onclick="acV('${safe}')">+10</button>
        <button class="btn bsm bd" onclick="frV('${safe}')">-10</button>
        <button class="btn bsm bg2btn" onclick="delV('${safe}')">Eliminar</button>
      </div>
    </div>`;
  }).join('');
}