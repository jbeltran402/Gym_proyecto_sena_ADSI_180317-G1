package ModeloDAO;

import Config.Conexion;
import Config.Encriptado;
import Interfaces.Interfaz;
import Modelo.Constructor_Sedes;
import Modelo.Constructor_Usuarios;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Operaciones implements Interfaz {

    Encriptado md5 = new Encriptado();
    String contrasena = "";
    Conexion cn = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    Constructor_Usuarios p = new Constructor_Usuarios();

    public void operaciones() {

    }

//_____________________Operaciones de Super Administrador________________________________//    
    
    @Override
    public List buscar(int doc) {
        
        ArrayList<Constructor_Usuarios> list = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE estado = '1' and Roles_id_roles != 3 and doc_usuario = " + doc +" ORDER BY Roles_id_roles";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Constructor_Usuarios per = new Constructor_Usuarios();
                per.setDoc(rs.getInt("doc_usuario"));
                per.setRol(rs.getInt("Roles_id_roles"));
                per.setTipo_doc(rs.getString("tipo_documeto"));
                per.setNomb_1(rs.getString("nombre_1"));
                per.setNomb_2(rs.getString("nombre_2"));
                per.setApel_1(rs.getString("apellido_1"));
                per.setApel_2(rs.getString("apellido_2"));
                per.setTel(rs.getInt("tel_cliente"));
                per.setCorreo(rs.getString("correo"));
                per.setEstado(rs.getInt("estado"));
                list.add(per);
            }
        } catch (Exception e) {
        }
        return list;
    }    
    
    @Override
    public List listar() {

        ArrayList<Constructor_Usuarios> list = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE estado = '1' and Roles_id_roles = 1 or Roles_id_roles = 2 ORDER BY Roles_id_roles";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Constructor_Usuarios per = new Constructor_Usuarios();
                per.setDoc(rs.getInt("doc_usuario"));
                per.setRol(rs.getInt("Roles_id_roles"));
                per.setTipo_doc(rs.getString("tipo_documeto"));
                per.setNomb_1(rs.getString("nombre_1"));
                per.setNomb_2(rs.getString("nombre_2"));
                per.setApel_1(rs.getString("apellido_1"));
                per.setApel_2(rs.getString("apellido_2"));
                per.setTel(rs.getInt("tel_cliente"));
                per.setCorreo(rs.getString("correo"));
                per.setEstado(rs.getInt("estado"));
                list.add(per);
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public Constructor_Usuarios list(int doc) {

        String sql = "SELECT * FROM usuario WHERE doc_usuario =" + doc;
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                p.setDoc(rs.getInt("doc_usuario"));
                p.setRol(rs.getInt("Roles_id_roles"));
                p.setTipo_doc(rs.getString("tipo_documeto"));
                p.setNomb_1(rs.getString("nombre_1"));
                p.setNomb_2(rs.getString("nombre_2"));
                p.setApel_1(rs.getString("apellido_1"));
                p.setApel_2(rs.getString("apellido_2"));
                p.setTel(rs.getInt("tel_cliente"));
                p.setCorreo(rs.getString("correo"));
                p.setEstado(rs.getInt("estado"));

            }
        } catch (Exception e) {
        }
        return p;
    }

    @Override
    public boolean add(Constructor_Usuarios per) {

        contrasena = md5.getEncriptado(String.valueOf(per.getDoc()));

        String sql = "INSERT INTO usuario VALUES ( " + per.getDoc() + ","
                + per.getRol() + ",'" + per.getTipo_doc() + "','" + per.getNomb_1()
                + "','" + per.getNomb_2() + "','" + per.getApel_1() + "','"
                + per.getApel_2() + "'," + per.getTel() + ",'" + per.getCorreo()
                + "','" + contrasena + "','1')";

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();

        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public boolean edit(Constructor_Usuarios per) {

        String sql = "";
        
        if (per.getCon().equals("si")) {

            contrasena = md5.getEncriptado(String.valueOf(per.getCambio_doc()));

            sql = "UPDATE usuario SET doc_usuario = " + per.getCambio_doc()
                    + ", Roles_id_roles = " + per.getRol() + ", tipo_documeto = '"
                    + per.getTipo_doc() + "', nombre_1 = '" + per.getNomb_1()
                    + "', nombre_2 = '" + per.getNomb_2() + "', apellido_1 = '"
                    + per.getApel_1() + "', apellido_2 = '" + per.getApel_2()
                    + "', tel_cliente = " + per.getTel() + ", correo = '" + per.getCorreo()
                    + "', contrasena = '" + contrasena + "', estado ='" + 1
                    + "' WHERE doc_usuario=" + per.getDoc();

        } else if (per.getCon().equals("no")) {

            sql = "UPDATE usuario SET doc_usuario = " + per.getCambio_doc()
                    + " , Roles_id_roles = " + per.getRol() + " , tipo_documeto = '"
                    + per.getTipo_doc() + "', nombre_1 = '" + per.getNomb_1()
                    + "' , nombre_2 = '" + per.getNomb_2() + "', apellido_1 = '"
                    + per.getApel_1() + "', apellido_2 = '" + per.getApel_2()
                    + "' , tel_cliente = " + per.getTel() + ", correo = '" + per.getCorreo()
                    + "' , estado = '1' WHERE doc_usuario=" + per.getDoc();

        }

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();

            contrasena = "";
            sql = "";
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean eliminar(int doc) {

        String sql = "UPDATE usuario SET estado = '0' WHERE doc_usuario = " + doc;
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();

        } catch (Exception e) {
        }
        return false;
    }

//__________________________Operaciones Administrador___________________________________//
    @Override
    public List listar_Admin() {

        ArrayList<Constructor_Usuarios> list = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE estado = '1' AND Roles_id_roles = 1 ORDER BY Roles_id_roles";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Constructor_Usuarios per = new Constructor_Usuarios();
                per.setDoc(rs.getInt("doc_usuario"));
                per.setTipo_doc(rs.getString("tipo_documeto"));
                per.setNomb_1(rs.getString("nombre_1"));
                per.setNomb_2(rs.getString("nombre_2"));
                per.setApel_1(rs.getString("apellido_1"));
                per.setApel_2(rs.getString("apellido_2"));
                per.setTel(rs.getInt("tel_cliente"));
                per.setCorreo(rs.getString("correo"));
                per.setEstado(rs.getInt("estado"));
                list.add(per);
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public boolean add_admin(Constructor_Usuarios per) {

        contrasena = md5.getEncriptado(String.valueOf(per.getDoc()));

        String sql = "INSERT INTO usuario VALUES ( " + per.getDoc() + ",1,'"
                + per.getTipo_doc() + "','" + per.getNomb_1()
                + "','" + per.getNomb_2() + "','" + per.getApel_1() + "','"
                + per.getApel_2() + "'," + per.getTel() + ",'" + per.getCorreo()
                + "','" + contrasena + "','1')";

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();

        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public boolean edit_admin(Constructor_Usuarios per) {

        String sql = "";
        
        if (per.getCon().equals("si")) {

            contrasena = md5.getEncriptado(String.valueOf(per.getCambio_doc()));

            sql = "UPDATE usuario SET doc_usuario = " + per.getCambio_doc() + ", tipo_documeto = '"
                    + per.getTipo_doc() + "', nombre_1 = '" + per.getNomb_1()
                    + "', nombre_2 = '" + per.getNomb_2() + "', apellido_1 = '"
                    + per.getApel_1() + "', apellido_2 = '" + per.getApel_2()
                    + "', tel_cliente = " + per.getTel() + ", correo = '" + per.getCorreo()
                    + "', contrasena = '" + contrasena + "', estado ='" + 1
                    + "' WHERE doc_usuario=" + per.getDoc();

        } else if (per.getCon().equals("no")) {

            sql = "UPDATE usuario SET doc_usuario = " + per.getCambio_doc() + per.getRol() + " , tipo_documeto = '"
                    + per.getTipo_doc() + "', nombre_1 = '" + per.getNomb_1()
                    + "' , nombre_2 = '" + per.getNomb_2() + "', apellido_1 = '"
                    + per.getApel_1() + "', apellido_2 = '" + per.getApel_2()
                    + "' , tel_cliente = " + per.getTel() + ", correo = '" + per.getCorreo()
                    + "' , estado = '1' WHERE doc_usuario=" + per.getDoc();

        }
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();

            contrasena = "";

        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean eliminar_admin(int doc) {

        String sql = "UPDATE usuario SET estado = '0' WHERE doc_usuario = " + doc;

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();

        } catch (Exception e) {
        }
        return false;
    }

//__________________________Operaciones Administrador___________________________________//
    @Override
    public boolean edit_usu(Constructor_Usuarios per) {
        
        String sql = "";
        
        int Documento = per.getDoc();
        
        if (per.getCon().equals("si")) {
        
            contrasena = md5.getEncriptado(per.getContra());
            
            sql= "UPDATE usuario SET tipo_documeto = '"
                + per.getTipo_doc() + "', nombre_1 = '" + per.getNomb_1()
                + "', nombre_2 = '" + per.getNomb_2() + "', apellido_1 = '"
                + per.getApel_1() + "', apellido_2 = '" + per.getApel_2()
                + "', tel_cliente = " + per.getTel() + ", correo = '" + per.getCorreo()
                + "', contrasena = '" + contrasena + "', estado ='" + 1
                + "' WHERE doc_usuario=" + Documento;
            
        }else if(per.getCon().equals("no")){
        
            sql= "UPDATE usuario SET tipo_documeto = '" + per.getTipo_doc() + "' , nombre_1 = '" 
                + per.getNomb_1() + "', nombre_2 = '" + per.getNomb_2() 
                + "', apellido_1 = '"+ per.getApel_1() + "', apellido_2 = '" 
                + per.getApel_2()+ "', tel_cliente = " + per.getTel() 
                + ", correo = '" + per.getCorreo() + "', estado ='" + 1
                + "' WHERE doc_usuario=" + Documento;           
        }

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();

            contrasena = "";

        } catch (SQLException e) {
        }
        return false;
    }
//__________________________Operaciones Sedes___________________________________//
    @Override
    public List sedes() {
        
        ArrayList<Constructor_Sedes> list = new ArrayList<>();
        String sql = "select nombre , direccion , nom_barrio , nom_localidad  from sedes , barrio , localidad  where barrio_id_barrio = id_barrio and localidad_id_localidad = id_localidad;";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                Constructor_Sedes sedes = new Constructor_Sedes();
                
                sedes.setNombre(rs.getString("nombre"));
                sedes.setDireccion(rs.getString("direccion"));
                sedes.setBarrio(rs.getString("nom_barrio"));
                sedes.setLocalidad(rs.getString("nom_localidad"));
                list.add(sedes);
                
            }
        } catch (Exception e) {
        }
        return list;
    }
}