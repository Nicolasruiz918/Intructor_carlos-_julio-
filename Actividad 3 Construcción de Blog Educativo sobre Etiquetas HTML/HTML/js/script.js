/* ============================================================
   script.js — Lógica interactiva del blog HTML
   Funciones:
   1. Menú hamburguesa (abrir/cerrar en móvil)
   2. Cerrar menú al hacer click en un enlace
   3. Cerrar menú al hacer click fuera de él
   4. Manejo del envío del formulario de demostración
   ============================================================ */

/* Espera a que el DOM esté completamente cargado
   antes de ejecutar cualquier código */
document.addEventListener('DOMContentLoaded', () => {

  /* Selecciona el botón hamburguesa y el nav del header */
  const toggle = document.querySelector('.menu-toggle');
  const nav = document.querySelector('.site-header nav');

  /* Si alguno de los dos no existe en la página, no hace nada
     (evita errores en páginas sin header o sin nav) */
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

  /* ── 3. CERRAR AL HACER CLICK FUERA  */
  document.addEventListener('click', e => {
    if (!nav.contains(e.target) && !toggle.contains(e.target)) {
      nav.classList.remove('active');
      toggle.textContent = '☰';
    }
  });
});


function handleSubmit(e) {
  e.preventDefault(); /* Cancela el envío por defecto del navegador */

  const result = document.getElementById('form-result');
  const data = new FormData(document.getElementById('demoForm'));

  /* Construye el HTML con los datos del formulario */
  let html = '✅ Formulario enviado correctamente:<br><br>';
  for (const [k, v] of data.entries()) {
    html += `<strong>${k}:</strong> ${v}<br>`;
  }

  /* Inyecta el resultado y lo hace visible */
  result.innerHTML = html;
  result.style.display = 'block';
}