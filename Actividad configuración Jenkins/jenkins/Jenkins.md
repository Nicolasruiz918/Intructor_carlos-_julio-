# EVIDENCIA DE APRENDIZAJE

# Simulación de Flujo CI/CD con Jenkins, Git y Automatización de Despliegues

---

# Aprendiz

Nicolas Estid Ruiz Sastoque

# Formación

Integración Continua y Automatización con Jenkins

---

# 1. Introducción

En esta actividad se realizó la simulación de un flujo CI/CD utilizando Jenkins, GitHub y Maven.

Durante el desarrollo de la práctica se configuró un entorno de integración continua mediante Docker, automatizando compilaciones, pruebas y notificaciones.

Además, el pipeline fue evolucionando progresivamente agregando nuevas funcionalidades como:

- Notificaciones Gmail
- Discord
- Microsoft Teams
- Telegram
- Webhooks GitHub

También se realizaron simulaciones de errores y correcciones para validar el comportamiento del pipeline.

---

# 2. Objetivos

## Objetivo General

Implementar un flujo CI/CD funcional utilizando Jenkins y GitHub.

## Objetivos Específicos

- Configurar Jenkins usando Docker.
- Integrar Jenkins con GitHub.
- Automatizar compilaciones Maven.
- Ejecutar pruebas automáticas.
- Configurar notificaciones.
- Simular errores y correcciones.
- Validar el pipeline con distintos escenarios.

---

# 3. Herramientas Utilizadas

| Herramienta     | Función              |
| --------------- | --------------------- |
| Docker          | Contenedor Jenkins    |
| Jenkins         | Integración continua |
| GitHub          | Control de versiones  |
| Git             | Gestión de ramas     |
| Maven           | Compilación          |
| Gmail SMTP      | Correos automáticos  |
| Discord         | Alertas               |
| Microsoft Teams | Notificaciones        |
| Telegram Bot    | Mensajería           |

---

# 4. Instalación Docker

## Paso 1 — Descargar Docker Desktop

Descargar:

```text
https://www.docker.com/products/docker-desktop/
```

Instalar normalmente.

---

## Paso 2 — Verificar Docker

```bash
docker --version
```

Resultado esperado:

```text
Docker version
```

![Captura 1](./imagenes/Captura%20de%20pantalla%202026-05-21%20071238.png)

---

# 5. Instalación Jenkins

## Paso 3 — Crear volumen Jenkins

```bash
docker volume create jenkins_home
```

![Captura 2](./imagenes/Captura%20de%20pantalla%202026-05-21%20071717.png)

---

## Paso 4 — Crear contenedor Jenkins

```bash
docker run -d --name jenkins ^
-p 8080:8080 ^
-p 50000:50000 ^
-v jenkins_home:/var/jenkins_home ^
-v //var/run/docker.sock:/var/run/docker.sock ^
jenkins/jenkins:lts
```

---

## Paso 5 — Verificar contenedor

```bash
docker ps
```

![Captura 3](./imagenes/Captura%20de%20pantalla%202026-05-21%20071821.png)

---

## Paso 6 — Abrir Jenkins

```text
http://localhost:8080
```

![Captura 4](./imagenes/Captura%20de%20pantalla%202026-05-21%20071926.png)

---

## Paso 7 — Obtener contraseña inicial

```bash
docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
```

![Captura 5](./imagenes/Captura%20de%20pantalla%202026-05-21%20072013.png)

---

## Paso 8 — Instalar plugins sugeridos

Seleccionar:

```text
Install suggested plugins
```

![Captura 6](./imagenes/Captura%20de%20pantalla%202026-05-21%20072045.png)

---

## Paso 9 — Crear usuario administrador

Configurar:

- Usuario
- Contraseña
- Correo

![Captura 7](./imagenes/Captura%20de%20pantalla%202026-05-21%20074220.png)

---

# 6. Instalación Plugins

## Paso 10 — Instalar plugins necesarios

Ir:

```text
Manage Jenkins
→ Plugins
```

Instalar:

- Git
- GitHub Integration
- Pipeline
- Maven Integration
- Mailer
- Discord Notifier
- Office 365 Connector
- Telegram Bot

![Captura 8](./imagenes/Captura%20de%20pantalla%202026-05-21%20075449.png)

---

# 7. Instalación Maven

## Paso 11 — Entrar contenedor Jenkins

```bash
docker exec -u 0 -it jenkins bash
```

![Captura 9](./imagenes/Captura%20de%20pantalla%202026-05-21%20084325.png)

---

## Paso 12 — Actualizar paquetes

```bash
apt update
```

![Captura 10](./imagenes/Captura%20de%20pantalla%202026-05-21%20084448.png)

---

## Paso 13 — Instalar Maven

```bash
apt install maven -y
```

![Captura 11](./imagenes/Captura%20de%20pantalla%202026-05-21%20084602.png)

---

## Paso 14 — Verificar Maven

```bash
mvn -version
```

![Captura 12](./imagenes/Captura%20de%20pantalla%202026-05-21%20084649.png)

---

## Paso 15 — Configurar Maven Jenkins

Ir:

```text
Manage Jenkins
→ Tools
```

Buscar:

```text
Maven installations
```

Configurar:

```text
Name: Maven
```

Desactivar:

```text
Install automatically
```

Ruta:

```text
/usr/share/maven
```

Guardar.

![Captura 13](./imagenes/Captura%20de%20pantalla%202026-05-21%20084940.png)

---

# 8. Configuración GitHub

## Paso 16 — Crear repositorio

Crear:

```text
ci-cd-simulacion
```

![Captura 14](./imagenes/Captura%20de%20pantalla%202026-05-21%20092609.png)

---

## Paso 17 — Clonar repositorio

```bash
git clone https://github.com/Nicolasruiz918/ci-cd-simulacion.git
```

![Captura 15](./imagenes/Captura%20de%20pantalla%202026-05-21%20092625.png)

---

## Paso 18 — Entrar proyecto

```bash
cd ci-cd-simulacion
```

![Captura 16](./imagenes/Captura%20de%20pantalla%202026-05-21%20092705.png)

---

## Paso 19 — Crear rama dev

```bash
git switch -c dev
```

Subir rama:

```bash
git push origin dev
```

![Captura 17](./imagenes/Captura%20de%20pantalla%202026-05-21%20092737.png)

---

## Paso 20 — Crear rama HU-01-structure

```bash
git switch -c HU-01-structure
```

Subir:

```bash
git push origin HU-01-structure
```

![Captura 18](./imagenes/Captura%20de%20pantalla%202026-05-21%20093947.png)

---

# 9. Crear Proyecto Maven

## Paso 21 — Crear proyecto Spring Boot

Entrar:

```text
https://start.spring.io/
```

Configurar:

```text
Project: Maven
Language: Java
Spring Boot: 3.5.0
Java: 17
```

Dependencias:

```text
Spring Web
Spring Boot DevTools
```

Descargar proyecto.

---

## Paso 22 — Copiar proyecto

Extraer archivos dentro del repositorio Git.

---

## Paso 23 — Commit inicial

```bash
git add .
```

```bash
git commit -m "Proyecto inicial Maven"
```

```bash
git push origin HU-01-structure
```

![Captura 19](./imagenes/Captura%20de%20pantalla%202026-05-21%20094010.png)

---

# 10. Crear Pipeline Inicial

# Primera versión — Solo Gmail

## Paso 24 — Crear Job Jenkins

Seleccionar:

```text
New Item
```

Nombre:

```text
verificacion-git
```

Seleccionar:

```text
Pipeline
```

![Captura 20](./imagenes/Captura%20de%20pantalla%202026-05-21%20094115.png)

---

## Paso 25 — Configurar Pipeline

Ir:

```text
Pipeline
```

Seleccionar:

```text
Pipeline script
```

---

## Paso 26 — Crear Jenkinsfile Inicial

Crear:

```text
Jenkinsfile
```

Pegar:

```groovy
pipeline {

    agent any

    stages {

        stage('Clonar repositorio') {
            steps {
                git branch: 'dev',
                url: 'https://github.com/Nicolasruiz918/ci-cd-simulacion.git'
            }
        }

        stage('Compilar') {
            steps {
                sh 'cd practica-jenkins && mvn clean compile'
            }
        }

        stage('Pruebas') {
            steps {
                sh 'cd practica-jenkins && mvn test'
            }
        }
    }

    post {

        success {

            mail to: 'nruizsastoque@gmail.com',
            subject: 'Pipeline EXITOSO',
            body: 'La compilación terminó correctamente.'
        }

        failure {

            mail to: 'nruizsastoque@gmail.com',
            subject: 'Pipeline FALLÓ',
            body: 'La compilación presentó errores.'
        }

        unstable {

            mail to: 'nruizsastoque@gmail.com',
            subject: 'Pipeline INESTABLE',
            body: 'Se detectaron advertencias.'
        }
    } 
}
```

## Paso 27 — Ejecutar Build

Seleccionar:

```text
Build Now
```

Resultado esperado:

```text
BUILD SUCCESS
```

---

# 11. Configuración Gmail SMTP

## Paso 28 — Activar verificación en dos pasos

Entrar:

```text
https://myaccount.google.com/security
```

Activar:

```text
Verificación en 2 pasos
```

![Captura 21](./imagenes/Captura%20de%20pantalla%202026-05-21%20094736.png)

---

## Paso 29 — Crear contraseña aplicación

Entrar:

```text
https://myaccount.google.com/apppasswords
```

Crear:

```text
Jenkins
```

Copiar contraseña generada.

---

## Paso 30 — Configurar correo Jenkins

Ir:

```text
Manage Jenkins
→ Configure System
```

Buscar:

```text
Notificación por correo electrónico
```

Configurar:

SMTP:

```text
smtp.gmail.com
```

Usuario:

```text
nruizsastoque@gmail.com
```

Contraseña:

```text
Contraseña aplicación Google
```

Puerto:

```text
465
```

Activar:

```text
Use SSL
```

Guardar.

---

## Paso 31 — Probar correo

Usar:

```text
Test configuration by sending test e-mail
```

Resultado esperado:

```text
Email was successfully sent
```

![Captura 23](./imagenes/Captura%20de%20pantalla%202026-05-21%20105202.png)

---

# 12. Mejorar Pipeline — Discord

## Paso 32 — Crear Webhook Discord

Ir:

```text
Canal
→ Edit Channel
→ Integrations
→ Webhooks
```

Copiar URL.

![Captura 24](./imagenes/Captura%20de%20pantalla%202026-05-21%20105412.png)

---

## Paso 33 — Configurar Discord Jenkins

Ir:

```text
Manage Jenkins
→ Configure System
```

Buscar:

```text
Discord Notifier
```

Pegar webhook.

Guardar.

---

## Paso 34 — Agregar Discord Jenkinsfile

Agregar:

```groovy
discordSend description: 'Build SUCCESS'
```

y:

```groovy
discordSend description: 'Build FAILED'
```

El Jenkinsfile queda:

```groovy
post {

    success {

        mail to: 'nruizsastoque@gmail.com',
        subject: 'Pipeline EXITOSO',
        body: 'La compilación terminó correctamente.'

        discordSend description: 'Build SUCCESS'
    }

    failure {

        mail to: 'nruizsastoque@gmail.com',
        subject: 'Pipeline FALLÓ',
        body: 'La compilación presentó errores.'

        discordSend description: 'Build FAILED'
    }
}
```

---

## Paso 35 — Probar Discord

Ejecutar:

```text
Build Now
```

Verificar mensaje Discord.

![Captura 25](./imagenes/Captura%20de%20pantalla%202026-05-21%20110649.png)

---

# 13. Mejorar Pipeline — Microsoft Teams

## Paso 36 — Crear Incoming Webhook Teams

Ir:

```text
Canal
→ Connectors
→ Incoming Webhook
```

Copiar URL.

---

## Paso 37 — Configurar Office 365 Connector

Ir:

```text
Manage Jenkins
→ Configure System
```

Buscar:

```text
Office 365 Connector
```

Pegar webhook.

Guardar.

---

## Paso 38 — Agregar Teams Jenkinsfile

Agregar:

```groovy
office365ConnectorSend webhookUrl: 'WEBHOOK',
message: 'Pipeline SUCCESS'
```

y:

```groovy
office365ConnectorSend webhookUrl: 'WEBHOOK',
message: 'Pipeline FAILED'
```

---

## Paso 39 — Probar Teams

Ejecutar:

```text
Build Now
```

---

# 14. Mejorar Pipeline — Telegram

## Paso 40 — Crear Bot Telegram

Buscar:

```text
BotFather
```

Enviar:

```text
/newbot
```

Copiar TOKEN.

![Captura 35](./imagenes/WhatsApp%20Image%202026-05-21%20at%202.55.01%20PM.jpeg)

---

## Paso 41 — Obtener Chat ID

Buscar:

```text
userinfobot
```

Enviar:

```text
/start
```

Copiar CHAT_ID.

![Captura 36](./imagenes/WhatsApp%20Image%202026-05-21%20at%202.55.02%20PM.jpeg)

---

## Paso 42 — Configurar Telegram Jenkins

Ir:

```text
Manage Jenkins
→ Configure System
```

Buscar:

```text
Telegram Bot
```

Agregar:

- TOKEN
- CHAT_ID

Guardar.

![Captura 26](./imagenes/Captura%20de%20pantalla%202026-05-21%20152626.png)

---

## Paso 43 — Agregar Telegram Jenkinsfile

Agregar:

```groovy
telegramSend message: 'Build SUCCESS'
```

y:

```groovy
telegramSend message: 'Build FAILED'
```

---

## Paso 44 — Probar Telegram

Ejecutar:

```text
Build Now
```

---

## Paso 44.5 — Pipeline completo con detección de conflictos (UNSTABLE)

Actualizar el pipeline en Jenkins con el siguiente código:

```groovy
pipeline {

    agent any

    stages {
        stage('Clonar repositorio') {
            steps {
                git branch: 'dev',
                    url: 'https://github.com/Nicolasruiz918/ci-cd-simulacion.git'
            }
        }

        stage('Compilar') {
            steps {
                sh 'cd practica-jenkins && mvn clean compile'
            }
        }

        stage('Pruebas') {
            steps {
                sh 'cd practica-jenkins && mvn test'
            }
        }

        stage('Verificar Conflictos') {
            steps {
                script {
                    def commitMsg = sh(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
  
                    if (commitMsg.toLowerCase().contains('conflict')) {
                        currentBuild.result = 'UNSTABLE'
                        echo "Build UNSTABLE - Conflicto de merge detectado"
                    } else {
                        echo "Build SUCCESS - No se detectaron conflictos"
                    }
                }
            }
        }
    }

    post {
        success {
            mail to: 'nruizsastoque@gmail.com',
                subject: 'Pipeline EXITOSO',
                body: 'La compilacion termino correctamente.'

            discordSend description: 'Build SUCCESS',
                webhookURL: 'https://discord.com/api/webhooks/1507048823883698259/0bvCp3y0pRXuL7BwRBYAubKtb1_UGD71mMQ46ToAmq1vRk95C-5wzkagUZbiqwGXTwj5'

            telegramSend(
                chatId: 7802989294,
                message: 'Build SUCCESS'
            )
        }

        failure {
            mail to: 'nruizsastoque@gmail.com',
                subject: 'Pipeline FALLO',
                body: 'La compilacion presento errores.'

            discordSend description: 'Build FAILED',
                webhookURL: 'https://discord.com/api/webhooks/1507048823883698259/0bvCp3y0pRXuL7BwRBYAubKtb1_UGD71mMQ46ToAmq1vRk95C-5wzkagUZbiqwGXTwj5'

            telegramSend(
                chatId: 7802989294,
                message: 'Build FAILED'
            )
        }

        unstable {
            mail to: 'nruizsastoque@gmail.com',
                subject: 'Pipeline INESTABLE',
                body: 'Se detectaron conflictos de merge o advertencias.'

            discordSend description: 'Build UNSTABLE',
                webhookURL: 'https://discord.com/api/webhooks/1507048823883698259/0bvCp3y0pRXuL7BwRBYAubKtb1_UGD71mMQ46ToAmq1vRk95C-5wzkagUZbiqwGXTwj5'

            telegramSend(
                chatId: 7802989294,
                message: 'Build UNSTABLE'
            )
        }
    }
}
```

Este pipeline incluye el stage `Verificar Conflictos` que detecta la palabra "conflict" en el mensaje del commit y marca el build como UNSTABLE.

---

# 15. Configuración Webhooks GitHub

## Paso 45 — Crear túnel público

```bash
ssh -R 80:localhost:8080 nokey@localhost.run
```

Copiar URL.

![Captura 27](./imagenes/Captura%20de%20pantalla%202026-05-21%20162504.png)

---

## Paso 46 — Activar Trigger Jenkins

Ir al Job.

Buscar:

```text
Build Triggers
```

Activar:

```text
GitHub hook trigger for GITScm polling
```

Guardar.

![Captura 28](./imagenes/Captura%20de%20pantalla%202026-05-21%20162727.png)

---

## Paso 47 — Configurar Webhook GitHub

En GitHub:

```text
Settings
→ Webhooks
→ Add webhook
```

Payload URL:

```text
https://URL_GENERADA/github-webhook/
```

IMPORTANTE:

Debe terminar en:

```text
/github-webhook/
```

---

# 16. Simulación CI/CD

# Iteración 1 — SUCCESS

## Paso 48 — Crear rama login

```bash
git checkout -b feature/login-ui
```

![Captura 29](./imagenes/Captura%20de%20pantalla%202026-05-21%20163755.png)

---

## Paso 49 — Modificar proyecto

Ejemplo:

```html
<h1>Nueva interfaz login</h1>
```

---

## Paso 50 — Commit y push

```bash
git add .
```

```bash
git commit -m "Nueva interfaz login"
```

```bash
git push origin feature/login-ui
```

Resultado esperado:

```text
SUCCESS
```

![Captura 37](./imagenes/WhatsApp%20Image%202026-05-21%20at%206.01.30%20PM.jpeg)

![Captura 38](./imagenes/WhatsApp%20Image%202026-05-21%20at%206.01.40%20PM.jpeg)

---

# Iteración 2 — FAILURE

## Paso 51 — Crear rama error

```bash
git checkout -b feature/auth-error
```

---

## Paso 52 — Introducir error

```java
package com.sena.practica_jenkins.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/hello/")
public class HelloWorldController {

    @GetMapping("")
    public String getMethodName() {
        System.out.println( // ← Error: falta cerrar paréntesis y punto y coma
        return "Hola mundo como estan, esta es una prueba de Jenkins y parece ser estar bien ";
    }
}

```

---

## Paso 53 — Commit y push

```bash
git add .
```

```bash
git commit -m "Error autenticacion"
```

```bash
git push origin feature/auth-error
```

Resultado esperado:

```text
FAILURE
```

![Captura 39](./imagenes/WhatsApp%20Image%202026-05-22%20at%201.05.24%20PM.jpeg)

![Captura 40](./imagenes/WhatsApp%20Image%202026-05-22%20at%201.05.24%20PM%20(1).jpeg)

![Captura 41](./imagenes/WhatsApp%20Image%202026-05-22%20at%201.09.36%20PM.jpeg)

---

# Iteración 3 — Corrección

## Paso 54 — Corregir error

```java
package com.sena.practica_jenkins.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/hello/")
public class HelloWorldController {

    @GetMapping("")
    public String getMethodName() {
        return "Hola mundo como estan, esta es una prueba de Jenkins y parece ser estar bien ";
    }
}
```

---

## Paso 55 — Commit y push

```bash
git add .
```

```bash
git commit -m "Correccion bug"
```

```bash
git push origin feature/auth-error
```

Resultado esperado:

```text
SUCCESS
```

![Captura 42](./imagenes/WhatsApp%20Image%202026-05-22%20at%201.21.29%20PM.jpeg)

![Captura 43](./imagenes/WhatsApp%20Image%202026-05-22%20at%201.21.30%20PM.jpeg)

![Captura 44](./imagenes/WhatsApp%20Image%202026-05-22%20at%201.21.30%20PM%20(1).jpeg)

---

# Iteración 4 – Conflicto de merge simulado

### Paso 1: Asegúrate de estar en dev y tener todo actualizado

```bash
git checkout dev
git pull origin dev
```

### Paso 2: Modifica feature/login-ui para que sea DIFERENTE a dev

```bash
git checkout feature/login-ui
```

Edita el archivo `HelloWorldController.java`:

```java
package com.sena.practica_jenkins.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/hello/")
public class HelloWorldController {

    @GetMapping("")
    public String getMethodName() {
        return "MENSAJE LOGIN-UI: Sistema de login version 2.0";
    }
}
```

```bash
git add .
git commit -m "login-ui: cambiar mensaje para generar conflicto"
git push origin feature/login-ui
```

### Paso 3: Modifica feature/auth-error para que sea DIFERENTE a dev y a login-ui

```bash
git checkout feature/auth-error
```

Edita el MISMO archivo `HelloWorldController.java`:

```java
package com.sena.practica_jenkins.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/hello/")
public class HelloWorldController {

    @GetMapping("")
    public String getMethodName() {
        return "MENSAJE AUTH-ERROR: Sistema de autenticacion version 2.0";
    }
}
```

```bash
git add .
git commit -m "auth-error: cambiar mensaje para generar conflicto"
git push origin feature/auth-error
```

### Paso 4: Merge de feature/login-ui a dev

```bash
git checkout dev
git merge feature/login-ui
```

Si hay conflicto, resuélvelo. Si no, haz push:

```bash
git push origin dev
```

### Paso 5: Merge de feature/auth-error a dev (AQUÍ HABRÁ CONFLICTO)

```bash
git merge feature/auth-error
```

**Verás:**

```
CONFLICT (content): Merge conflict in HelloWorldController.java
Automatic merge failed; fix conflicts and then commit the result.
```

### Paso 6: Resolver conflicto manualmente

Abre `HelloWorldController.java`. Verás:

```java
<<<<<<< HEAD
return "MENSAJE LOGIN-UI: Sistema de login version 2.0";
=======
return "MENSAJE AUTH-ERROR: Sistema de autenticacion version 2.0";
>>>>>>> feature/auth-error
```

**Resuelve creando un mensaje de integración:**

```java
return "INTEGRACION COMPLETA: Login UI + Auth Error version 2.0 funcionando correctamente";
```

### Paso 7: Commit y push

```bash
git add .
git commit -m "Resolve merge conflict: integrar login-ui y auth-error"
git push origin dev
```

### Paso 8: Jenkins se ejecutará automáticamente

**Resultado esperado:**

```text
UNSTABLE
```

![Captura 46](./imagenes/WhatsApp%20Image%202026-05-22%20at%201.57.53%20PM.jpeg)

![Captura 45](./imagenes/WhatsApp%20Image%202026-05-22%20at%201.57.36%20PM.jpeg)

![Captura 47](./imagenes/WhatsApp%20Image%202026-05-22%20at%201.58.00%20PM.jpeg)

---

# Iteración 5 – Integración final controlada

### Paso 1: Asegúrate de estar en dev y tener todo actualizado

```bash
git checkout dev
git pull origin dev
```

### Paso 2: Crea una rama de integración final

```bash
git switch -c feature/integracion-final
```

### Paso 3: Corrige el archivo para que tenga un mensaje limpio y correcto

Edita `HelloWorldController.java`:

```java
package com.sena.practica_jenkins.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/hello/")
public class HelloWorldController {

    @GetMapping("")
    public String getMethodName() {
        return "Hola mundo, integracion final completa - Pipeline CI/CD funcionando correctamente";
    }
}
```

### Paso 4: Commit y push

```bash
git add .
git commit -m "integracion-final: unificar mensajes correctamente sin conflictos"
git push origin -u feature/integracion-final
```

### Paso 5: Crear Pull Request en GitHub

1. Ve a: `https://github.com/Nicolasruiz918/ci-cd-simulacion`
2. Crea Pull Request de `feature/integracion-final` → `dev`
3. Haz clic en **Merge pull request**

### Paso 6: Jenkins se ejecutará automáticamente

**Resultado esperado:**

```text
SUCCESS
```

![Captura 48](./imagenes/WhatsApp%20Image%202026-05-22%20at%202.36.36%20PM.jpeg)

### Captura 49

![Captura 49](./imagenes/WhatsApp%20Image%202026-05-22%20at%202.36.42%20PM.jpeg)

![Captura 50](./imagenes/WhatsApp%20Image%202026-05-22%20at%202.31.28%20PM.jpeg)

---

# 17. Evidencias

Capturas de:

- Jenkins SUCCESS
- Jenkins FAILURE
- Consola Jenkins
- Gmail
- Discord
- Teams
- Telegram
- GitHub Webhook
- Branches GitHub

## Evidencias insertadas

### Captura 33

![Captura 33](./imagenes/Captura%20de%20pantalla%202026-05-22%20143434.png)

### Captura 50

Link de repositorio: https://github.com/Nicolasruiz918/ci-cd-simulacion.git

---

# 18. Conclusiones

La práctica permitió comprender el funcionamiento de un pipeline CI/CD utilizando Jenkins y GitHub.

Se automatizaron compilaciones, pruebas y notificaciones utilizando distintas plataformas.

Además, se validó el comportamiento del pipeline ante errores y correcciones mediante integración continua.

---

# 19. Recomendaciones

- Mantener ramas organizadas.
- Automatizar pruebas.
- Revisar conflictos antes de merge.
- Mantener plugins actualizados.
- Monitorear el pipeline constantemente.
