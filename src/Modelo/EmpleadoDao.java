package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDao {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Empleado p = new Empleado();

    public List listar() {
        List<Empleado> datos = new ArrayList<>();
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement("select * from persona");
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleado p = new Empleado();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setCorreo(rs.getString(3));
                p.setTelefono(rs.getInt(4));
                 p.setRfc(rs.getString(5));
                  p.setTurno(rs.getString(6));
                datos.add(p);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    public int agregar(Empleado per) {  
        int r=0;
        String sql="insert into persona(Nombres,Correo,Telefono,rfc,turno)values(?,?,?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);            
            ps.setString(1,per.getNom());
            ps.setString(2,per.getCorreo());
            ps.setInt(3,per.getTelefono());
            ps.setString(4,per.getRfc());
            ps.setString(5,per.getTurno());
            
            r=ps.executeUpdate();    
            if(r==1){
                return 1;
            }
            else{
                return 0;
            }
        } catch (Exception e) {
        }  
        return r;
    }
    public int Actualizar(Empleado per) {  
        int r=0;
        String sql="update persona set Nombres=?,Correo=?,Telefono=?, rfc=?,turno=? where Id=?";        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);            
            ps.setString(1,per.getNom());
            ps.setString(2,per.getCorreo());
            ps.setInt(3,per.getTelefono());
            ps.setString(4,per.getRfc());
            ps.setString(5,per.getTurno());
            ps.setInt(6,per.getId());
            r=ps.executeUpdate();    
            if(r==1){
                return 1;
            }
            else{
                return 0;
            }
        } catch (Exception e) {
        }  
        return r;
    }
    public int Delete(int id){
        int r=0;
        String sql="delete from persona where Id="+id;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            r= ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
}
