package model;

public class Empleado {
private int id;
private String nombre;
private String apellido;
private String dni;
private String puesto;
private double salario;
public Empleado() {
	
}

    public Empleado(String nombre,String apellido, String dni, String puesto, double salario) {
        this.nombre = nombre;
		this.apellido = apellido;
        this.dni = dni;
        this.puesto = puesto;
        this.salario = salario;
    }

   
    public Empleado(int id,String nombre,String apellido, String dni, String puesto, double salario) {
		this.id=id;
        this.nombre = nombre;
		this.apellido = apellido;
        this.dni = dni;
        this.puesto = puesto;
        this.salario = salario;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }


}
