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
            System.out.println("error guardando operación: " + e.getMessage());
        }
    }
}
