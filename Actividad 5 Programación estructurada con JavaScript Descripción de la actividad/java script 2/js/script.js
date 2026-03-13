// script.js  → versión corregida y robusta

document.addEventListener("DOMContentLoaded", () => {

 

    // 1. Mostrar mensaje
    const btnMensaje = document.getElementById("btnMensaje");
    if (btnMensaje) {
        btnMensaje.addEventListener("click", () => {
            const res = document.getElementById("resultado1");
            if (res) res.textContent = "Hola mundo desde JavaScript";
        });
    }

    // 2. Suma simple
    const btnSumar = document.getElementById("btnSumar");
    if (btnSumar) {
        btnSumar.addEventListener("click", () => {
            const a = Number(document.getElementById("num1")?.value) || 0;
            const b = Number(document.getElementById("num2")?.value) || 0;
            const res = document.getElementById("resultado2");
            if (res) res.textContent = "Resultado: " + (a + b);
        });
    }

    // 3. Cambiar color de fondo
    const btnRojo = document.getElementById("btnRojo");
    const btnAzul = document.getElementById("btnAzul");
    const btnVerde = document.getElementById("btnVerde");

    function cambiarColor(clase){
        document.body.classList.remove("bg-rojo","bg-azul","bg-verde");
        document.body.classList.add(clase);


    }

    if(btnRojo){
        btnRojo.addEventListener("click", ()=> cambiarColor("bg-rojo"));
    }

    if(btnAzul){
        btnAzul.addEventListener("click", ()=> cambiarColor("bg-azul"));
    }

    if(btnVerde){
        btnVerde.addEventListener("click", ()=> cambiarColor("bg-verde"));
    }

    // 4. Contador
    let contador = 0;
    const btnContar = document.getElementById("btnContar");
    const displayContador = document.getElementById("contador");
    if (btnContar && displayContador) {
        btnContar.addEventListener("click", () => {
            contador++;
            displayContador.textContent = "Clics: " + contador;
        });
    }

    // 5. Lista tareas (agregar)
    const btnAgregar = document.getElementById("btnAgregar");
    const tareaInput = document.getElementById("tareaInput");
    const listaTareas = document.getElementById("listaTareas");
    if (btnAgregar && tareaInput && listaTareas) {
        btnAgregar.addEventListener("click", () => {
            const texto = tareaInput.value.trim();
            if (!texto) return;
            const li = document.createElement("li");
            li.textContent = texto;
            listaTareas.appendChild(li);
            tareaInput.value = "";
        });

        tareaInput.addEventListener("keypress", e => {
            if (e.key === "Enter") btnAgregar.click();
        });
    }

    // 6. Formulario validación
    const form = document.getElementById("formulario");
    const mensajeForm = document.getElementById("mensajeForm");
    if (form && mensajeForm) {
        form.addEventListener("submit", e => {
            e.preventDefault();
            const nombre  = document.getElementById("nombre")?.value.trim();
            const correo = document.getElementById("correo")?.value.trim();
            const edad   = document.getElementById("edad")?.value.trim();

            if (!nombre || !correo || !edad) {
                mensajeForm.style.color = "red";
                mensajeForm.textContent = "Debe completar todos los campos";
            } else {
                mensajeForm.style.color = "green";
                mensajeForm.textContent = "¡Formulario enviado correctamente!";
                form.reset();
            }
        });
    }

    // 7. Calculadora
    const btnCalcular = document.getElementById("btnCalcular");
    if (btnCalcular) {
        btnCalcular.addEventListener("click", () => {
            const a = Number(document.getElementById("calcA")?.value) || 0;
            const b = Number(document.getElementById("calcB")?.value) || 0;
            const op = document.getElementById("operacion")?.value;
            let res = 0;

            switch (op) {
                case "+": res = a + b; break;
                case "-": res = a - b; break;
                case "*": res = a * b; break;
                case "/": res = b !== 0 ? a / b : "Error: división por 0"; break;
                default:  res = "Operación inválida";
            }

            const resultadoCalc = document.getElementById("resultadoCalc");
            if (resultadoCalc) resultadoCalc.textContent = "Resultado: " + res;
        });
    }

    // 8. Lista con eliminar
    const btnAgregar2 = document.getElementById("btnAgregar2");
    const tareaInput2 = document.getElementById("tareaInput2");
    const listaTareas2 = document.getElementById("listaTareas2");
    if (btnAgregar2 && tareaInput2 && listaTareas2) {

        const agregar = () => {
            const texto = tareaInput2.value.trim();
            if (!texto) return;

            const li = document.createElement("li");
            li.textContent = texto + " ";

            const btnDel = document.createElement("button");
            btnDel.textContent = "Eliminar";
            btnDel.onclick = () => li.remove();

            li.appendChild(btnDel);
            listaTareas2.appendChild(li);
            tareaInput2.value = "";
        };

        btnAgregar2.addEventListener("click", agregar);

        tareaInput2.addEventListener("keypress", e => {
            if (e.key === "Enter") agregar();
        });
    }

    // 9. Temporizador
    let segundos = 0;
    let intervalo = null;
    const display = document.getElementById("resultado-temporizador");

    const actualizar = () => {
        if (!display) return;
        let min = Math.floor(segundos / 60).toString().padStart(2, '0');
        let seg = (segundos % 60).toString().padStart(2, '0');
        display.textContent = `${min}:${seg}`;
    };

    const btnIniciar = document.getElementById("btnIniciar");
    if (btnIniciar) {
        btnIniciar.addEventListener("click", () => {
            if (intervalo) return;
            intervalo = setInterval(() => {
                segundos++;
                actualizar();
            }, 1000);
        });
    }

    const btnDetener = document.getElementById("btnDetener");
    if (btnDetener) {
        btnDetener.addEventListener("click", () => {
            clearInterval(intervalo);
            intervalo = null;
        });
    }

    const btnReiniciar = document.getElementById("btnReiniciar");
    if (btnReiniciar) {
        btnReiniciar.addEventListener("click", () => {
            clearInterval(intervalo);
            intervalo = null;
            segundos = 0;
            actualizar();
        });
    }

});