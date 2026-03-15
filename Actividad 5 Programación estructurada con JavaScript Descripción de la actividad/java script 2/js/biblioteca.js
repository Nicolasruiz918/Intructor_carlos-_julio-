function showMsg(id, tipo, txt) {
  const el = document.getElementById(id);
  if (!el) return;
  el.style.display='block';
  el.innerHTML = txt;
  clearTimeout(el._t);
  el._t = setTimeout(() => el.className = 'msg', 5000);
}

class Libro {
  constructor(titulo, autor) { this.titulo = titulo; this.autor = autor; this.disponible = true; }
  prestar() { this.disponible = false; }
  devolver() { this.disponible = true; }
}

let libros = [];

function agregarLibro() {
  const t = document.getElementById('lTitulo').value.trim();
  const a = document.getElementById('lAutor').value.trim();
  if (!t || !a) return showMsg('msgLib','er','Completa el título y el autor');
  libros.push(new Libro(t, a));
  showMsg('msgLib','ok',`${t} agregado a la biblioteca`);
  document.getElementById('lTitulo').value = '';
  document.getElementById('lAutor').value = '';
  mostrarLibros();
}

function prestarLibro(i) { libros[i].prestar(); mostrarLibros(); }
function devolverLibro(i) { libros[i].devolver(); mostrarLibros(); }
function eliminarLibro(i) { libros.splice(i, 1); mostrarLibros(); }

function mostrarLibros() {
  const lista = document.getElementById('listaLibros');
  const disp = libros.filter(l => l.disponible).length;
  document.getElementById('lDisp').textContent = disp;
  document.getElementById('lPrest').textContent = libros.length - disp;
  if (!libros.length) { lista.innerHTML = '<li style="display:block"><div class="empty">Sin libros registrados</div></li>'; return; }
  lista.innerHTML = libros.map((l, i) => {
    const d = l.disponible;
    return `<li>
      <div style="flex:1;min-width:0">
        <div class="in">${l.titulo}</div>
        <div class="id2">${l.autor}</div>
      </div>
      <span class="badge ${d?'bg-':'br-'}">${d ? 'Disponible' : 'Prestado'}</span>
      <div class="fa">
        ${d ? `<button class="btn bsm bw" onclick="prestarLibro(${i})">Prestar</button>`
            : `<button class="btn bsm bs" onclick="devolverLibro(${i})">Devolver</button>`}
        <button class="btn bsm bdel" onclick="eliminarLibro(${i})">Eliminar</button>
      </div>
    </li>`;
  }).join('');
}