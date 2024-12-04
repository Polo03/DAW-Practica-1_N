package org.example.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOEmpleado {
    Connection conexion;
    Empleado empleado;
    ArrayList<Empleado> listaEmpleados;
    //Constructor
    public DAOEmpleado() {
        conexion = DB.getConexion();

    }

    public ArrayList<Empleado> getAllEmpleados() throws SQLException {
        listaEmpleados=new ArrayList<>();
        PreparedStatement sentencia;
        sentencia = conexion.prepareStatement("SELECT * FROM empleados ORDER BY id ASC");


        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {

            int idEmp=resultado.getInt(1);
            String nombreEmp=resultado.getString(2);
            int edadEmp=resultado.getInt(3);
            int idDpto=resultado.getInt(4);
            empleado = new Empleado(idEmp,nombreEmp,edadEmp,idDpto);
            listaEmpleados.add(empleado);
        }
        return listaEmpleados;
    }

    public Empleado getEmpleadoById(int id) throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM empleados where id = ?");
        sentencia.setInt(1, id);
        ResultSet resultado = sentencia.executeQuery();
        if (resultado.next()) {
            int idEmp=resultado.getInt(1);
            String nombreEmp=resultado.getString(2);
            int edadEmp=resultado.getInt(3);
            int idDpto=resultado.getInt(4);
            empleado = new Empleado(idEmp,nombreEmp,edadEmp,idDpto);
        }
        return empleado;

    }

    public Empleado getEmpleadoByNombre(String nombre) throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM empleados where nombre = ?");
        sentencia.setString(1, nombre);
        ResultSet resultado = sentencia.executeQuery();
        if (resultado.next()) {
            int idEmp=resultado.getInt(1);
            String nombreEmp=resultado.getString(2);
            int edadEmp=resultado.getInt(3);
            int idDpto=resultado.getInt(4);
            empleado = new Empleado(idEmp,nombreEmp,edadEmp,idDpto);
        }
        return empleado;

    }

    public ArrayList<Empleado> getAllEmpleadosByIdDpto(int idDpto) throws SQLException {
        listaEmpleados=new ArrayList<>();
        PreparedStatement sentencia;
        sentencia = conexion.prepareStatement("SELECT * FROM empleados WHERE dpto_id = ? ORDER BY id ASC");
        sentencia.setInt(1, idDpto);
        ResultSet resultado = sentencia.executeQuery();

        while (resultado.next()) {

            int idEmp=resultado.getInt(1);
            String nombreEmp=resultado.getString(2);
            int edadEmp=resultado.getInt(3);
            int id_dpto=resultado.getInt(4);
            empleado = new Empleado(idEmp,nombreEmp,edadEmp,id_dpto);
            listaEmpleados.add(empleado);
        }
        return listaEmpleados;

    }

    public int addEmpleado(Empleado empleado) throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO empleados (id, nombre, edad, dpto_id) VALUES (?, ?, ?, ?)");
        sentencia.setInt(1, empleado.getId());
        sentencia.setString(2, empleado.getNombre());
        sentencia.setInt(3, empleado.getEdad());
        sentencia.setInt(4, empleado.getIdDpto());
        return sentencia.executeUpdate();


    }

    public int updateEmpleado(Empleado empleado) throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("UPDATE empleados SET nombre = ?, edad = ?, idDpto = ? WHERE id = ?");
        sentencia.setInt(2, empleado.getId());
        sentencia.setInt(3, empleado.getEdad());
        sentencia.setInt(4, empleado.getIdDpto());
        sentencia.setString(1, empleado.getNombre());
        return sentencia.executeUpdate();

    }

    public int deleteEmpleadoById(int id) throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("DELETE FROM empleados WHERE id = ?");
        sentencia.setInt(1, id);
        return sentencia.executeUpdate();

    }

    public int deleteAllEmpleados() throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("DELETE FROM empleados");
        return sentencia.executeUpdate();

    }

    public int getLastId() throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("SELECT MAX(id) FROM empleados;");
        ResultSet resultado = sentencia.executeQuery();
        if (resultado.next()) {
            return resultado.getInt(1);
        }
        return 0;
    }

}