# -_calculadora_en_java_consola_con_oracle_19C_- :.
🧮 Calculadora en Java (Consola) con Oracle 19c:

<img width="1536" height="1024" alt="image" src="https://github.com/user-attachments/assets/79d06f78-bdaa-4ec8-8bd7-d33ad1c37209" />    

<img width="2541" height="1074" alt="image" src="https://github.com/user-attachments/assets/8436404d-467a-40a9-baae-9b8a6cc4e689" />    

A continuación tienes un ejemplo académico completo de una Calculadora en Java (consola) desarrollada en IntelliJ IDEA con persistencia en Oracle 19c .

La arquitectura es sencilla pero correcta para proyectos universitarios.

## 🏗 Arquitectura utilizada
Modelo
DAO
Servicio
UI (Consola)
Base de Datos Oracle

La aplicación permite:

- ✔ Realizar operaciones matemáticas
- ✔ Guardar resultados en base de datos
- ✔ Registrar historial de cálculos

## Operaciones disponibles:

- ➕ Suma

- ➖ Resta

- ✖ Multiplicación

- ➗ División

Cada operación registra en la base de datos:

Números usados
Tipo de operación
Resultado
Fecha de ejecución

### 1️⃣ Base de Datos Oracle 19c:
Crear tabla

```
CREATE TABLE CALCULADORA_OPERACIONES (
    ID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    NUMERO1 NUMBER,
    NUMERO2 NUMBER,
    OPERACION VARCHAR2(20),
    RESULTADO NUMBER,
    FECHA TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 2️⃣ Estructura del Proyecto (IntelliJ):
```
CALCULADORA_ORACLE
│
├── src
│   ├── config
│   │      ConexionBD.java
│   │
│   ├── modelo
│   │      Operacion.java
│   │
│   ├── dao
│   │      OperacionDAO.java
│   │
│   ├── servicio
│   │      CalculadoraServicio.java
│   │
│   └── ui
│          CalculadoraApp.java
```
### 3️⃣ Clase de Conexión a Oracle:
📁 config/ConexionBD.java
```
package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {

    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521:XE";

    private static final String USER = "SYSTEM";
    private static final String PASSWORD = "oracle";

    public static Connection conectar() {

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}
```
### 4️⃣ Modelo:
📁 modelo/Operacion.java
```
package modelo;

public class Operacion {

    private double numero1;
    private double numero2;
    private String operacion;
    private double resultado;

    public Operacion(double numero1, double numero2, String operacion, double resultado) {
        this.numero1 = numero1;
        this.numero2 = numero2;
        this.operacion = operacion;
        this.resultado = resultado;
    }

    public double getNumero1() {
        return numero1;
    }

    public double getNumero2() {
        return numero2;
    }

    public String getOperacion() {
        return operacion;
    }

    public double getResultado() {
        return resultado;
    }
}
```
### 5️⃣ DAO (Guardar en Oracle):
📁 dao/OperacionDAO.java
```
package dao;

import config.ConexionBD;
import modelo.Operacion;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OperacionDAO {

    public void guardarOperacion(Operacion op) {

        String sql = """
                INSERT INTO CALCULADORA_OPERACIONES
                (NUMERO1, NUMERO2, OPERACION, RESULTADO)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, op.getNumero1());
            ps.setDouble(2, op.getNumero2());
            ps.setString(3, op.getOperacion());
            ps.setDouble(4, op.getResultado());

            ps.executeUpdate();

            System.out.println("✔ Operación guardada en Oracle");

        } catch (Exception e) {
            System.out.println("Error guardando operación: " + e.getMessage());
        }
    }
}
```
### 6️⃣ Servicio de Calculadora:
📁 servicio/CalculadoraServicio.java
```
package servicio;

import modelo.Operacion;
import dao.OperacionDAO;

public class CalculadoraServicio {

    private OperacionDAO dao = new OperacionDAO();

    public double sumar(double a, double b) {

        double resultado = a + b;
        dao.guardarOperacion(new Operacion(a, b, "SUMA", resultado));
        return resultado;
    }

    public double restar(double a, double b) {

        double resultado = a - b;
        dao.guardarOperacion(new Operacion(a, b, "RESTA", resultado));
        return resultado;
    }

    public double multiplicar(double a, double b) {

        double resultado = a * b;
        dao.guardarOperacion(new Operacion(a, b, "MULTIPLICACION", resultado));
        return resultado;
    }

    public double dividir(double a, double b) {

        double resultado = a / b;
        dao.guardarOperacion(new Operacion(a, b, "DIVISION", resultado));
        return resultado;
    }
}
```
### 7️⃣ Interfaz de Consola:
📁 ui/CalculadoraApp.java
```
package ui;

import servicio.CalculadoraServicio;

import java.util.Scanner;

public class CalculadoraApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CalculadoraServicio calculadora = new CalculadoraServicio();

        int opcion;

        do {

            System.out.println("\n===== CALCULADORA JAVA + ORACLE =====");
            System.out.println("1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("0. Salir");

            System.out.print("Seleccione opción: ");
            opcion = sc.nextInt();

            if (opcion >= 1 && opcion <= 4) {

                System.out.print("Ingrese número 1: ");
                double n1 = sc.nextDouble();

                System.out.print("Ingrese número 2: ");
                double n2 = sc.nextDouble();

                double resultado = 0;

                switch (opcion) {

                    case 1 -> resultado = calculadora.sumar(n1, n2);
                    case 2 -> resultado = calculadora.restar(n1, n2);
                    case 3 -> resultado = calculadora.multiplicar(n1, n2);
                    case 4 -> resultado = calculadora.dividir(n1, n2);
                }

                System.out.println("Resultado: " + resultado);
            }

        } while (opcion != 0);

        System.out.println("Programa finalizado");
    }
}
```
### 8️⃣ Dependencia JDBC para Oracle:
Descargar el driver JDBC:
ojdbc11.jar
Agregarlo en IntelliJ IDEA:

File
Project Structure
Libraries
+ Add JAR
+         

### 9️⃣ Ejemplo de registro en la Base de Datos:

Si ejecutas la operación:

8 + 4

Se registrará en la tabla:
```
ID | NUMERO1 | NUMERO2 | OPERACION | RESULTADO | FECHA |
1	8	4	SUMA	12	2026
```
### ✅ Resultado del Proyecto:

Con este proyecto obtienes:

- ✔ Aplicación Java en consola
- ✔ Arquitectura limpia (Modelo – DAO – Servicio – UI)
- ✔ Persistencia en Oracle 19c
- ✔ Uso de JDBC profesional
- ✔ Proyecto listo para IntelliJ IDEA / .
