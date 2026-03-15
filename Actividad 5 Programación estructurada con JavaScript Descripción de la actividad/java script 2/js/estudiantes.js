function showMsg(id, tipo, txt) {
  const el = document.getElementById(id);
  if (!el) return;
  el.style.display='block';
  el.innerHTML = txt;
  clearTimeout(el._t);
  el._t = setTimeout(() => el.className = 'msg', 5000);
}

class Estudiante {
  constructor(nombre, asignatura, nota) { this.nombre = nombre; this.asignatura = asignatura; this.nota = nota; }
  aprobado() { return this.nota >= 3; }
  estado() { return this.aprobado() ? 'Aprobó' : 'Reprobó'; }
}

let estudiantes = [];

function registrarEstudiante() {
  const n = document.getElementById('eNombre').value.trim();
  const a = document.getElementById('eAsignatura').value.trim();
  const nota = parseFloat(document.getElementById('eNota').value);
  if (!n || !a || isNaN(nota) || nota < 0 || nota > 5) return showMsg('msgEst','er','Completa todos los campos (nota 0.0–5.0)');
  estudiantes.push(new Estudiante(n, a, nota));
  showMsg('msgEst','ok',`${n} registrado — ${nota >= 3 ? 'Aprobó' : 'Reprobó'}`);
  document.getElementById('eNombre').value = '';
  document.getElementById('eAsignatura').value = '';
  document.getElementById('eNota').value = '';
  mostrarEstudiantes();
}

function eliminarEstudiante(i) { estudiantes.splice(i, 1); mostrarEstudiantes(); }

function mostrarEstudiantes() {
  const lista = document.getElementById('listaEstudiantes');
  const ap = estudiantes.filter(e => e.aprobado()).length;
  document.getElementById('eTot').textContent = estudiantes.length;
  document.getElementById('eAp').textContent = ap;
  document.getElementById('eRep').textContent = estudiantes.length - ap;
  if (!estudiantes.length) { lista.innerHTML = '<li style="display:block"><div class="empty">Sin estudiantes aún</div></li>'; return; }
  lista.innerHTML = estudiantes.map((e, i) => {
    const ok = e.aprobado();
    const pct = (e.nota / 5) * 100;
    return `<li style="flex-direction:column;align-items:stretch">
      <div style="display:flex;align-items:center;gap:10px;flex-wrap:wrap">
        <span class="in">${e.nombre}</span>
        <span class="badge bb-">${e.asignatura}</span>
        <span class="badge ${ok?'bg-':'br-'}">${e.estado()}</span>
        <span style="font-weight:700">${e.nota.toFixed(1)}</span>
        <button class="btn bsm bdel" style="margin-left:auto" onclick="eliminarEstudiante(${i})">Eliminar</button>
      </div>
      <div class="pw" style="margin-top:8px"><div class="pf ${ok?'fg':'fr2'}" style="width:${pct}%"></div></div>
    </li>`;
  }).join('');
}
