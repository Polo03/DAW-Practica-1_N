package org.example;

import org.example.modelo.DAODepartamento;
import org.example.modelo.DAOEmpleado;
import org.example.modelo.Departamento;
import org.example.modelo.Empleado;
import java.util.Scanner;
import java.sql.SQLException;

public class index {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int indice;

        do {
            System.out.println("¿Que accion quiere realizar?");
            System.out.println("1. Crear empleado ");
            System.out.println("2. Listar todos los empleados");
            System.out.println("3. Listar todos los empleados por departamento");
            System.out.println("4. Buscar empleado");
            System.out.println("5. Modificar empleado");
            System.out.println("6. Eliminar empleado");
            System.out.println("7. Eliminar todos los empleados");
            System.out.println("8. Crear departamento ");
            System.out.println("9. Listar todos los departamentos");
            System.out.println("10. Buscar departamento");
            System.out.println("11. Modificar departamento");
            System.out.println("12. Eliminar departamento");
            System.out.println("13. Eliminar todos los departamentos");
            System.out.println("14. Salir");
            System.out.print("Ingrese una opcion: ");
            if (scanner.hasNextInt()) {
                indice = scanner.nextInt();
            } else {
                indice=0;
            }
            scanner.nextLine();
            realizarAccion(indice);
        }while(indice!=8);
    }
    public static void realizarAccion(int indice) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        DAOEmpleado daoEmp = new DAOEmpleado();
        DAODepartamento daoDpt = new DAODepartamento();
        Empleado empleado;
        Departamento departamento;

        int idEmp, idDpt, edadEmp, idDptoEmp;
        String nombreEmp, nombreDpto;


        System.out.println("------------------------------------------------");
        switch (indice){
            case 1:
                System.out.print("Introduzca el nombre del empleado a introducir: ");
                nombreEmp=scanner.nextLine();
                System.out.print("Introduzca la edad del empleado a introducir: ");
                edadEmp=scanner.nextInt();
                System.out.print("Introduzca el id del departamento del empleado a introducir: ");
                idDptoEmp=scanner.nextInt();
                daoEmp.addEmpleado(new Empleado(daoEmp.getLastId()+1,nombreEmp,edadEmp,idDptoEmp));
                System.out.println("Empleado creado.\n");
                break;
            case 2:
                for(Empleado emp: daoEmp.getAllEmpleados()){
                    System.out.println(emp);
                }
                break;
            case 3:
                System.out.print("Introduzca el id del departamento del empleado a buscar: ");
                idDptoEmp=scanner.nextInt();
                for(Empleado emp: daoEmp.getAllEmpleadosByIdDpto(idDptoEmp)){
                    System.out.println(emp.toString());
                }
                break;
            case 4:
                System.out.print("¿Por cual de los identificadores quiere buscar el empleado, por id(1) o nombre(2)? ");
                int filtrado=scanner.nextInt();
                switch (filtrado){
                    case 1:
                        System.out.print("Introduzca el id del empleado a buscar: ");
                        idEmp=scanner.nextInt();
                        empleado= daoEmp.getEmpleadoById(idEmp);
                        if(empleado!=null)
                            System.out.println(empleado);
                        else
                            System.out.println("El empleado buscado no existe");
                        break;
                    case 2:
                        System.out.print("Introduzca el nombre del empleado a buscar: ");
                        nombreEmp=scanner.nextLine();
                        empleado= daoEmp.getEmpleadoByNombre(nombreEmp);
                        if(empleado!=null)
                            System.out.println(empleado);
                        else
                            System.out.println("El empleado buscado no existe");
                        break;
                    default:
                        System.out.println("No ha introducido una opcion valida");
                }
                break;
            case 5:
                System.out.print("Introduzca el id del empleado a modificar: ");
                idEmp=scanner.nextInt();
                System.out.print("Introduzca el nuevo nombre del empleado: ");
                nombreEmp=scanner.nextLine();
                System.out.print("Introduzca la nueva edad del empleado: ");
                edadEmp=scanner.nextInt();
                System.out.print("Introduzca el nuevo id del departamento del empleado: ");
                idDptoEmp=scanner.nextInt();
                daoEmp.updateEmpleado(new Empleado(idEmp,nombreEmp,edadEmp,idDptoEmp));
                break;
            case 6:
                System.out.print("Introduzca el id del empleado a buscar: ");
                idEmp=scanner.nextInt();
                daoEmp.deleteEmpleadoById(idEmp);
                System.out.println("Empleado eliminado");
                break;
            case 7:
                daoEmp.deleteAllEmpleados();
                System.out.println("Empleados eliminados");
                break;
            case 8:
                System.out.print("Introduzca el nombre del departamento a introducir: ");
                nombreDpto=scanner.nextLine();
                daoDpt.addDepartamento(new Departamento(daoEmp.getLastId()+1,nombreDpto));
                System.out.println("Departamento creado.\n");
                break;
            case 9:
                for(Departamento dpt: daoDpt.getAllDepartamentos()){
                    System.out.println(dpt.toString());
                }
                break;
            case 10:
                System.out.print("Introduzca el id del departamento a buscar: ");
                idDpt=scanner.nextInt();
                departamento= daoDpt.getDepartamentoById(idDpt);
                if(departamento!=null)
                    System.out.println(departamento);
                else
                    System.out.println("El departamento buscado no existe");
                break;
            case 11:
                System.out.print("Introduzca el id del departamento a modificar: ");
                idDpt=scanner.nextInt();
                System.out.print("Introduzca el nuevo nombre del empleado: ");
                nombreDpto=scanner.nextLine();
                daoDpt.updateDepartamento(new Departamento(idDpt,nombreDpto));
                break;
            case 12:
                System.out.print("Introduzca el id del departamento a eliminar: ");
                idDpt=scanner.nextInt();
                daoDpt.deleteDepartamentoById(idDpt);
                System.out.println("Departamento eliminado");
                break;
            case 13:
                daoDpt.deleteAllDepartamentos();
                System.out.println("Departamentos eliminados");
                break;
            case 14:
                System.out.println("Ha salido de la consola");
                break;

            default:
                System.out.println("Ha ocurrido un error a la hora de introducir la opción, introduzcala otra vez porfavor.");
                break;
        }
        System.out.println("------------------------------------------------");
    }
}
