/* ============================================================
   script.js — Lógica interactiva del blog HTML
   Funciones:
   1. Menú hamburguesa (abrir/cerrar en móvil)
   2. Cerrar menú al hacer click en un enlace
   3. Cerrar menú al hacer click fuera de él
   4. Manejo del envío del formulario con validaciones reales
   5. Botón flotante scroll to top / bottom
   ============================================================ */

document.addEventListener('DOMContentLoaded', () => {

  /* Selecciona el botón hamburguesa y el nav del header */
  const toggle = document.querySelector('.menu-toggle');
  const nav = document.querySelector('.site-header nav');

  if (!toggle || !nav) return;

  /* ── 1. ABRIR / CERRAR MENÚ */
  toggle.addEventListener('click', () => {
    nav.classList.toggle('active');
    toggle.textContent = nav.classList.contains('active') ? '✕' : '☰';
  });

  /* ── 2. CERRAR AL HACER CLICK EN UN ENLACE */
  nav.querySelectorAll('a').forEach(a => {
    a.addEventListener('click', () => {
      nav.classList.remove('active');
      toggle.textContent = '☰';
    });
  });

  /* 3. CERRAR AL HACER CLICK FUERA */
  document.addEventListener('click', e => {
    if (!nav.contains(e.target) && !toggle.contains(e.target)) {
      nav.classList.remove('active');
      toggle.textContent = '☰';
    }
  });
});


/*  4. VALIDACIONES DEL FORMULARIO 
   Valida cada campo antes de mostrar el resultado:
   - Nombre: mínimo 3 caracteres, solo letras y espacios
   - Email: formato válido con regex
   - Contraseña: mínimo 8 caracteres, al menos 1 número
   - Edad: entre 1 y 120
   - Nivel: debe seleccionarse un radio
   - Temas: al menos uno debe estar marcado
   - País: no puede quedar en "Selecciona tu país"
   - Mensaje: mínimo 10 caracteres */

/* Limpia el error de un campo */
function clearError(fieldId) {
  const field = document.getElementById(fieldId);
  if (!field) return;
  field.classList.remove('input-error');
  const msg = field.parentElement.querySelector('.field-error');
  if (msg) msg.remove();
}

/* Muestra un mensaje de error debajo del campo */
function showError(fieldId, message) {
  const field = document.getElementById(fieldId);
  if (!field) return;
  field.classList.add('input-error');
  // Evita duplicar mensajes
  const existing = field.parentElement.querySelector('.field-error');
  if (existing) existing.remove();
  const span = document.createElement('span');
  span.className = 'field-error';
  span.textContent = '⚠ ' + message;
  field.insertAdjacentElement('afterend', span);
}

/* Valida un campo al perder el foco (validación en tiempo real) */
function addLiveValidation() {
  const form = document.getElementById('demoForm');
  if (!form) return;

  form.querySelectorAll('input, select, textarea').forEach(el => {
    el.addEventListener('blur', () => validateField(el));
    el.addEventListener('input', () => {
      if (el.classList.contains('input-error')) validateField(el);
    });
  });
}

/* Valida un campo individual */
function validateField(el) {
  const name = el.name || el.id;
  const val = el.value.trim();

  if (el.id === 'nombre') {
    clearError('nombre');
    if (!val) { showError('nombre', 'El nombre es obligatorio.'); return false; }
    if (val.length < 4) { showError('nombre', 'Mínimo 4 caracteres.'); return false; }
    if (!/^[a-záéíóúüñA-ZÁÉÍÓÚÜÑ\s]+$/.test(val)) { showError('nombre', 'Solo letras y espacios.'); return false; }
  }

  if (el.id === 'email') {
    clearError('email');
    if (!val) { showError('email', 'El correo es obligatorio.'); return false; }
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(val)) { showError('email', 'Ingresa un correo válido (ej: usuario@dominio.com).'); return false; }
  }

  if (el.id === 'password') {
    clearError('password');
    if (val && val.length < 8) { showError('password', 'Mínimo 8 caracteres.'); return false; }
    if (val && !/\d/.test(val)) { showError('password', 'Debe incluir al menos un número.'); return false; }
  }

  if (el.id === 'edad') {
    clearError('edad');
    if (val && (isNaN(val) || +val < 1 || +val > 120)) {
      showError('edad', 'Ingresa una edad válida entre 1 y 120.');
      return false;
    }
  }

  if (el.id === 'fecha') {
    clearError('fecha');
    if (!val) { showError('fecha', 'La fecha de nacimiento es obligatoria.'); return false; }
    const hoy = new Date();
    const nacimiento = new Date(val);
    if (nacimiento >= hoy) { showError('fecha', 'La fecha debe ser anterior a hoy.'); return false; }
    const edad = hoy.getFullYear() - nacimiento.getFullYear();
    if (edad > 120) { showError('fecha', 'Ingresa una fecha válida.'); return false; }
  }

  if (el.id === 'pais') {
    clearError('pais');
    if (!val) { showError('pais', 'Selecciona tu país.'); return false; }
  }

  if (el.id === 'mensaje') {
    clearError('mensaje');
    if (val && val.length < 10) { showError('mensaje', 'El mensaje debe tener al menos 10 caracteres.'); return false; }
  }

  return true;
}

/* Función principal llamada al hacer submit */
function handleSubmit(e) {
  e.preventDefault();

  const form = document.getElementById('demoForm');
  const result = document.getElementById('form-result');
  let valid = true;

  /* Limpiar todos los errores previos */
  form.querySelectorAll('.field-error').forEach(el => el.remove());
  form.querySelectorAll('.input-error').forEach(el => el.classList.remove('input-error'));

  /* Validar nombre */
  const nombre = document.getElementById('nombre').value.trim();
  if (!nombre) {
    showError('nombre', 'El nombre es obligatorio.'); valid = false;
  } else if (nombre.length < 4) {
    showError('nombre', 'Mínimo 4 caracteres.'); valid = false;
  } else if (!/^[a-záéíóúüñA-ZÁÉÍÓÚÜÑ\s]+$/.test(nombre)) {
    showError('nombre', 'Solo letras y espacios.'); valid = false;
  }

  /* Validar email */
  const email = document.getElementById('email').value.trim();
  if (!email) {
    showError('email', 'El correo es obligatorio.'); valid = false;
  } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
    showError('email', 'Ingresa un correo válido (ej: usuario@dominio.com).'); valid = false;
  }

  /* Validar contraseña */
  const password = document.getElementById('password').value;
  if (password) {
    if (password.length < 8) {
      showError('password', 'Mínimo 8 caracteres.'); valid = false;
    } else if (!/\d/.test(password)) {
      showError('password', 'Debe incluir al menos un número.'); valid = false;
    }
  }

  /* Validar edad */
  const edad = document.getElementById('edad').value;
  if (edad && (isNaN(edad) || +edad < 1 || +edad > 120)) {
    showError('edad', 'Ingresa una edad válida entre 1 y 120.'); valid = false;
  }

  /* Validar fecha de nacimiento */
  const fecha = document.getElementById('fecha').value;
  if (!fecha) {
    showError('fecha', 'La fecha de nacimiento es obligatoria.'); valid = false;
  } else {
    const hoy = new Date();
    const nacimiento = new Date(fecha);
    if (nacimiento >= hoy) {
      showError('fecha', 'La fecha debe ser anterior a hoy.'); valid = false;
    } else if (hoy.getFullYear() - nacimiento.getFullYear() > 120) {
      showError('fecha', 'Ingresa una fecha válida.'); valid = false;
    }
  }

  /* Validar nivel (radio) */
  const nivel = form.querySelector('input[name="nivel"]:checked');
  if (!nivel) {
    const radioGroup = form.querySelector('.radio-group');
    const existing = radioGroup.querySelector('.field-error');
    if (!existing) {
      const span = document.createElement('span');
      span.className = 'field-error';
      span.textContent = 'Selecciona tu nivel de HTML.';
      radioGroup.appendChild(span);
    }
    valid = false;
  }

  /* Validar temas (checkbox) — al menos uno */
  const temas = form.querySelectorAll('input[name="temas"]:checked');
  if (temas.length === 0) {
    const checkGroup = form.querySelector('.check-group');
    const existing = checkGroup.querySelector('.field-error');
    if (!existing) {
      const span = document.createElement('span');
      span.className = 'field-error';
      span.textContent = ' Selecciona al menos un tema de interés.';
      checkGroup.appendChild(span);
    }
    valid = false;
  }

  /* Validar país */
  const pais = document.getElementById('pais').value;
  if (!pais) {
    showError('pais', 'Selecciona tu país.'); valid = false;
  }

  /* Validar mensaje */
  const mensaje = document.getElementById('mensaje').value.trim();
  if (mensaje && mensaje.length < 10) {
    showError('mensaje', 'El mensaje debe tener al menos 10 caracteres.'); valid = false;
  }

  /* Si hay errores, mostrar resumen y no continuar */
  if (!valid) {
    result.className = 'error';
    result.innerHTML = ' Por favor corrige los errores marcados antes de enviar.';
    result.style.display = 'block';
    // Hacer scroll al primer error
    const firstError = form.querySelector('.input-error');
    if (firstError) firstError.scrollIntoView({ behavior: 'smooth', block: 'center' });
    return;
  }

  /* Todo válido: construir resumen de datos */
  const data = new FormData(form);
  let html = ' <strong>Formulario enviado correctamente</strong><br><br>';
  const labels = {
    nombre: 'Nombre', email: 'Correo', edad: 'Edad',
    fecha: 'Fecha de nacimiento', nivel: 'Nivel', pais: 'País', mensaje: 'Mensaje'
  };

  for (const [k, v] of data.entries()) {
    if (k === 'password' || k === 'archivo') continue;
    const label = labels[k] || k;
    html += `<strong>${label}:</strong> ${v}<br>`;
  }

  result.className = 'success';
  result.innerHTML = html;
  result.style.display = 'block';
}

/* Activar validación en tiempo real cuando el DOM esté listo */
document.addEventListener('DOMContentLoaded', addLiveValidation);


/* 5. BOTÓN FLOTANTE 
   Inyecta dos botones en todas las páginas:
   ↑ Ir al inicio  (aparece al bajar 200px)
   ↓ Ir al final   (siempre visible) */
(function () {
  const fab = document.createElement('div');
  fab.className = 'scroll-fab';
  fab.innerHTML = `
    <button class="fab-top" title="Ir al inicio" aria-label="Ir al inicio">↑</button>
    <button class="fab-down" title="Ir al final"  aria-label="Ir al final">↓</button>
  `;
  document.body.appendChild(fab);

  const btnTop  = fab.querySelector('.fab-top');
  const btnDown = fab.querySelector('.fab-down');

  window.addEventListener('scroll', () => {
    btnTop.classList.toggle('visible', window.scrollY > 200);
  });

  btnTop.addEventListener('click', () => window.scrollTo({ top: 0, behavior: 'smooth' }));
  btnDown.addEventListener('click', () => window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' }));
})();