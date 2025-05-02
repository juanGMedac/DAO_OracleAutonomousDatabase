# DAO\_OracleAutonomousDatabase

Este proyecto es una guía práctica para aprender a conectar una aplicación Java con una base de datos Oracle Autonomous Database utilizando el patrón DAO (Data Access Object). Está diseñado para estudiantes de DAM que deseen adquirir experiencia en el desarrollo de aplicaciones que interactúan con bases de datos en la nube.

## 🧠 Objetivos del proyecto

* Comprender el patrón de diseño DAO y su aplicación en Java.
* Configurar y conectar una aplicación Java a Oracle Autonomous Database.
* Realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) utilizando JDBC.
* Familiarizarse con el uso de Oracle Cloud Infrastructure (OCI) y las herramientas asociadas.

## 🛠️ Requisitos previos

* **Java Development Kit (JDK)** 17 o superior.
* **IntelliJ IDEA** o cualquier otro IDE compatible con Java.
* **Cuenta en Oracle Cloud** con acceso a Oracle Autonomous Database.
* **Wallet de conexión** descargado desde Oracle Cloud para la base de datos.
* **Dependencias JDBC** para Oracle (incluidas en la carpeta `lib` del proyecto).

## 📁 Estructura del proyecto

```
DAO_OracleAutonomousDatabase/
├── lib/                 # Librerías JDBC necesarias para la conexión
├── src/
│   ├── dao/             # Clases DAO que manejan la lógica de acceso a datos
│   ├── model/           # Clases que representan las entidades de la base de datos
│   └── Main.java        # Clase principal para ejecutar la aplicación
├── .gitignore           # Archivos y carpetas ignorados por Git
├── LICENSE              # Licencia del proyecto
└── README.md            # Este archivo
```

## 🚀 Pasos para ejecutar el proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/juanGMedac/DAO_OracleAutonomousDatabase.git
cd DAO_OracleAutonomousDatabase
```

### 2. Importar el proyecto en IntelliJ IDEA

* Abre IntelliJ IDEA.
* Selecciona **File > Open** y elige la carpeta del proyecto clonado.
* Asegúrate de que el JDK esté configurado correctamente.

### 3. Configurar la conexión a la base de datos

* Descarga el **wallet** desde Oracle Cloud para tu instancia de Autonomous Database.
* Descomprime el archivo y coloca su contenido en una carpeta accesible, por ejemplo, `wallet/`.
* Configura las propiedades de conexión en tu aplicación, asegurándote de que apunten al directorio del wallet.

### 4. Ejecutar la aplicación

* Navega a la clase `Main.java`.
* Ejecuta la clase principal.
* Observa en la consola las operaciones realizadas y los resultados obtenidos.

## 📝 Notas adicionales

* Asegúrate de que la base de datos esté en estado **"Available"** antes de intentar conectarte.
* Verifica que las credenciales y la configuración del wallet sean correctas.
* Si encuentras problemas de conexión, revisa los archivos `tnsnames.ora` y `sqlnet.ora` en el wallet para confirmar los detalles de la conexión.

## 📚 Recursos recomendados

* [Documentación oficial de Oracle Autonomous Database](https://docs.oracle.com/en/cloud/paas/autonomous-database/index.html)
* [Guía para conectar Java con Oracle Autonomous Database](https://guides.micronaut.io/latest/micronaut-oracle-autonomous-db-gradle-java.html)
* [Ejemplos de Oracle Autonomous Database en GitHub](https://github.com/oracle-devrel/oracle-autonomous-database-samples)

---

