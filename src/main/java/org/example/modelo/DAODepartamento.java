package org.example.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAODepartamento {
    Connection conexion;
    Departamento departamento;
    ArrayList<Departamento> listaDepartamentos;
    //Constructor
    public DAODepartamento() {
        conexion = DB.getConexion();

    }

    public ArrayList<Departamento> getAllDepartamentos() throws SQLException {
        listaDepartamentos=new ArrayList<>();
        PreparedStatement sentencia;
        sentencia = conexion.prepareStatement("SELECT * FROM departamentos ORDER BY id ASC");


        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {

            int idDpto=resultado.getInt(1);
            String nombreDpto=resultado.getString(2);
            departamento = new Departamento(idDpto,nombreDpto);
            listaDepartamentos.add(departamento);
        }
        return listaDepartamentos;
    }

    public Departamento getDepartamentoById(int id) throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM departamento where id = ?");
        sentencia.setInt(1, id);
        ResultSet resultado = sentencia.executeQuery();
        if (resultado.next()) {
            int idDpto=resultado.getInt(1);
            String nombreDpto=resultado.getString(2);
            departamento = new Departamento(idDpto,nombreDpto);
        }
        return departamento;

    }

    public Departamento getDepartamentoByNombre(String nombre) throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM departamento where id = ?");
        sentencia.setString(1, nombre);
        ResultSet resultado = sentencia.executeQuery();
        if (resultado.next()) {
            int idDpto=resultado.getInt(1);
            String nombreDpto=resultado.getString(2);
            departamento = new Departamento(idDpto,nombreDpto);
        }
        return departamento;

    }

    public int addDepartamento(Departamento departamento) throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO departamentos (id, nombre) VALUES (?, ?)");
        sentencia.setInt(1, departamento.getId());
        sentencia.setString(2, departamento.getNombre());
        return sentencia.executeUpdate();
    }

    public int updateDepartamento(Departamento departamento) throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("UPDATE departamentos SET nombre = ? WHERE id = ?");
        sentencia.setInt(2, departamento.getId());
        sentencia.setString(1, departamento.getNombre());
        return sentencia.executeUpdate();

    }

    public int deleteDepartamentoById(int id) throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("DELETE FROM departamentos WHERE id = ?");
        sentencia.setInt(1, id);
        return sentencia.executeUpdate();

    }

    public int deleteAllDepartamentos() throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("DELETE FROM departamentos");
        return sentencia.executeUpdate();

    }

    public int getLastId() throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("SELECT MAX(id) FROM departamentos;");
        ResultSet resultado = sentencia.executeQuery();
        if (resultado.next()) {
            return resultado.getInt(1);
        }
        return 0;
    }

}