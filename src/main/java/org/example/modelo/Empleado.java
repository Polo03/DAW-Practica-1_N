package org.example.modelo;

public class Empleado {
    int id;
    String nombre;
    int edad;
    int idDpto;

    public Empleado() {
        super();
    }

    public Empleado(int id, String nombre, int edad, int idDpto) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.idDpto = idDpto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getIdDpto() {
        return idDpto;
    }

    public void setIdDpto(int idDpto) {
        this.idDpto = idDpto;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", idDpto=" + idDpto +
                '}';
    }
}
