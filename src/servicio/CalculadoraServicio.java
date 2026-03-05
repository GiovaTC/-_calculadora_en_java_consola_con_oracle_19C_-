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
