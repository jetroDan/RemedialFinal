
package Modelo;

public class Empleado {
    int id;
    String nom;
    String correo;
    int telefono;
    String rfc;
    String turno;

    public Empleado() {
    }

    public Empleado(int id, String nom, String correo, int telefono, String rfc, String turno) {
        this.id = id;
        this.nom = nom;
        this.correo = correo;
        this.telefono = telefono;
        this.rfc = rfc;
        this.turno = turno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
    
    

    
    
}
