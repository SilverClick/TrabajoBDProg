package trabajobd1;
import java.sql.*;
import javax.swing.*;
public class Conexion {
    public Connection conexion;
    public Statement sentencia;
  

    public void conectarBasedeDatos(){
        try {
            final String Controlador = "org.postgresql.Driver";
            Class.forName( Controlador );
            final String url_bd = "jdbc:postgresql://localhost:5432/prueba";
                conexion = DriverManager.getConnection(url_bd,"postgres","postgres");
            sentencia = conexion.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(), "Excepcion", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void desconectarBasedeDatos() {
        try {
            if (conexion != null ) {
                if(sentencia != null) {
                    sentencia.close();
                }
                conexion.close();
            }
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(), "Excepcion", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    public Connection getConnection(){
        return conexion;
    }
    public void insertar(String idnuevo){
        try{
       conectarBasedeDatos();
       String sql="insert into ejemplo values ("+idnuevo+");";
       sentencia.execute(sql);
       JOptionPane.showMessageDialog(null, "Insertado con éxito");
       desconectarBasedeDatos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(), "Excepcion", JOptionPane.ERROR_MESSAGE);
        }     
    }
public void consultar(String campo){
        try{
       conectarBasedeDatos();
       String sql="select "+campo+" from ejemplo;";
       ResultSet resultados =sentencia.executeQuery(sql);
           while (resultados.next()) {
            Object valor = resultados.getObject(1);
           JOptionPane.showMessageDialog(null,valor);
        }
        resultados.close();
       desconectarBasedeDatos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(), "Excepcion", JOptionPane.ERROR_MESSAGE);
        }   
    }
 public void borrar(String campo,String idABorrar){
        try{
       conectarBasedeDatos();
       String sql="delete from ejemplo where "+campo+" = '"+idABorrar+"';";
       sentencia.execute(sql);
       JOptionPane.showMessageDialog(null, "Borrado con éxito");
       desconectarBasedeDatos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(), "Excepcion", JOptionPane.ERROR_MESSAGE);
        }  
 }
        public void actualizar(String idActual,String idNuevo){
        try{
       conectarBasedeDatos();
       String sql="UPDATE ejemplo SET id =  '"+idNuevo+"' WHERE id = '"+idActual+"';";
       sentencia.execute(sql);
       JOptionPane.showMessageDialog(null, "Actualizado con éxito");
       desconectarBasedeDatos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(), "Excepcion", JOptionPane.ERROR_MESSAGE);
        } 
}
}

