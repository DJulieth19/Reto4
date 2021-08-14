// Importaciones necesarias en iMaster
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// Util (No modificar)
class JDBCUtilities {
    private static final String DATABASE_LOCATION = "ProyectosConstruccion.db";

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:"+DATABASE_LOCATION;

        return DriverManager.getConnection(url);
    }
}

// Remplace en adelante por las clases de sus archivos .java
// Vista
class VistaRequerimientos {

    public static final ControladorRequerimientos controlador = new ControladorRequerimientos();

    public static void requerimiento1(){
        try {
            // Su código
            ArrayList<Requerimiento_1> req1 = controlador.consultarRequerimiento1();
            for (Requerimiento_1 req_1 : req1){
                System.out.printf("%s %s %d\n",req_1.getLider(),req_1.getCargo(),req_1.getProyectos());       
            } 
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void requerimiento2(){
        try {
            // Su código
            ArrayList<Requerimiento_2> req2 = controlador.consultarRequerimiento2();
            for (Requerimiento_2 req_2 : req2){
                System.out.printf("%s %s %d %d\n",req_2.getMaterialConstruccion(),req_2.getNombre_Material(),req_2.getCantidad(),req_2.getPrecio_Unidad(),req_2.getPrecio_Total());       
            } 
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void requerimiento3(){
        try {
            // Su código
            ArrayList<Requerimiento_2> req2 = controlador.consultarRequerimiento2();
            for (Requerimiento_2 req_2 : req2){
                System.out.printf("%s %s %d %d\n",req_2.getMaterialConstruccion(),req_2.getNombre_Material(),req_2.getCantidad(),req_2.getPrecio_Unidad(),req_2.getPrecio_Total());       
            } 
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
}

// Controlador
class ControladorRequerimientos {
    // Su código
    private Requerimiento_1Dao Req1;
    private Requerimiento_2Dao Req2;
    private Requerimiento_3Dao Req3;

    public ControladorRequerimientos(){
        this.Req1 = new Requerimiento_1Dao();
        this.Req2 = new Requerimiento_2Dao();
        this.Req3 = new Requerimiento_3Dao();
    }
    public ArrayList<Requerimiento_1> consultarRequerimiento1() throws SQLException {
        // Su código
        return Req1.requerimiento1();
        
    }
    
    public ArrayList<Requerimiento_2> consultarRequerimiento2() throws SQLException {
        // Su código
        return Req2.requerimiento2();
    }

    public ArrayList<Requerimiento_3> consultarRequerimiento3() throws SQLException {
        // Su código
        return Req3.requerimiento3();
    }
}

// Modelo
// VO
class Requerimiento_1 {
    // Su código
    private String lider;
    private String cargo;
    private Integer proyectos;

    public Requerimiento_1(){}

    public String getLider(){
        return lider;
    }

    public void setLider(String dato){
        lider = dato;
    }

    public String getCargo(){
        return cargo;
    }

    public void setCargo(String dato){
        cargo = dato;
    }

    public Integer getProyectos(){
        return proyectos;
    }
    public void setProyectos(Integer dato){
        proyectos = dato;
    }
}

class Requerimiento_2 {
    // Su código
    private String materialConstruccion;
    private String Nombre_Material;
    private Integer cantidad;
    private Integer precio_Unidad;
    private Integer precio_Total;

    public Requerimiento_2(){}

    public String getMaterialConstruccion(){
        return materialConstruccion;
    }

    public void setMaterialConstruccion(String dato){
        materialConstruccion = dato;
    }

    public String getNombre_Material(){
        return Nombre_Material;
    }
    public void setNombreMaterial(String dato){
        Nombre_Material = dato;
    }

    public Integer getCantidad(){
        return cantidad;
    }
    public void setCantidad(Integer dato){
        cantidad = dato;
    }
    public Integer getPrecio_Unidad(){
        return precio_Unidad;
    }
    public void setPrecio_Unidad (Integer dato){
        precio_Unidad = dato;
    }
    public Integer getPrecio_Total(){
        return precio_Total;
    }
    public void setPrecio_Total(Integer dato){
        precio_Total = dato;
    }

    
}

class Requerimiento_3 {
    // Su código
    private String idProyecto;
    private String ciudad;
    private String clasificacion;
    private Integer costo_Proyecto;

    public Requerimiento_3(){}

    public String getIdProyecto(){
        return idProyecto;
    }

    public void setIdProyecto(String dato){
        idProyecto = dato;
    }
    public String getCiudad(){
        return ciudad;
    }
    public void setCiudad(String dato){
        ciudad = dato;
    }
    public String getClasisificacion(){
        return clasificacion;
    }
    public void setClasificacion(String dato){
        clasificacion = dato;
    }
    public Integer getCosto_Proyecto(){
        return costo_Proyecto;
    }
    public void setCosto_Proyecto (Integer dato){
        costo_Proyecto =dato;
    }

}

// DAO
class Requerimiento_1Dao {
    public ArrayList<Requerimiento_1> requerimiento1() throws SQLException {
        // Su código
        ArrayList<Requerimiento_1> req1= new ArrayList<>();
        Requerimiento_1 registro = null;
        String sql = "SELECT Nombre||' '||Primer_Apellido Lider,Cargo,COUNT(ID_Lider) '# Proyectos' "+ 
        "FROM Lider l join Proyecto p "+
        "USING (ID_Lider) "+
        "where p.constructora = 'Arquitectura S.A.' "
        +"group by p.ID_Lider " 
        +"ORDER BY Cargo , Lider;";

                    try (
                        
                        Connection conn = JDBCUtilities.getConnection();
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                    ) {
                        while(rs.next()){
                            registro= new Requerimiento_1();
                            registro.setLider(rs.getString("lider"));
                            registro.setCargo(rs.getString("Cargo"));
                            registro.setProyectos(rs.getInt("Proyectos"));
                            req1.add(registro);
                        }
                    }
                    return req1;
    }
}

class Requerimiento_2Dao {
    public ArrayList<Requerimiento_2> requerimiento2() throws SQLException {
        // Su código
        ArrayList<Requerimiento_2> req2= new ArrayList<>();
        Requerimiento_2 registro = null;
        String sql = "SELECT ID_MaterialConstruccion,Nombre_Material ,Cantidad,Precio_Unidad , Cantidad * Precio_Unidad 'Precio_Total' "+ 
        "FROM MaterialConstruccion mc JOIN Compra c "+
        "USING (ID_MaterialConstruccion) "+
        "where p.constructora = 'Arquitectura S.A.' "
        +"WHERE ID_Proyecto IN (10,14,23,24,38,50,29) " 
        +"ORDER BY ID_Proyecto, Precio_Unidad DESC;";

                    try (
                        
                        Connection conn = JDBCUtilities.getConnection();
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                    ) {
                        while(rs.next()){
                            registro= new Requerimiento_2();
                            registro.setMaterialConstruccion(rs.getString("ID_MaterialConstruccion"));
                            registro.setNombreMaterial(rs.getString("Nombre_Material"));
                            registro.setCantidad(rs.getInt("Cantidad"));
                            registro.setPrecio_Unidad(rs.getInt("Precio_Unidad"));
                            registro.setPrecio_Total(rs.getInt("Precio_Total"));
               
                            req2.add(registro);
                        }
                    }
                    return req2;
    }

}

class Requerimiento_3Dao {
    public ArrayList<Requerimiento_3> requerimiento3() throws SQLException {
        // Su código
        ArrayList<Requerimiento_3> req3= new ArrayList<>();
        Requerimiento_3 registro = null;
        String sql = "SELECT sc.ID_Proyecto, Ciudad, Clasificacion,SUM(Precio_Total) Costo_Proyecto "+ 
        "FROM (SELECT ID_Proyecto,ID_MaterialConstruccion,Cantidad * Precio_Unidad Precio_Total "+
        "FROM MaterialConstruccion mc "+
        "JOIN Compra c "+
        "USING (ID_MaterialConstruccion))sc, Proyecto p "+
        "WHERE sc.ID_Proyecto = p.ID_Proyecto AND Ciudad IN ('Monteria','Santa Marta') "
        +"GROUP BY Ciudad, Clasificacion, sc.ID_Proyecto " 
        +"HAVING SUM(Precio_Total) > 70000;";

                    try (
                        Connection conn = JDBCUtilities.getConnection();
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                    ) {
                        while(rs.next()){
                            registro= new Requerimiento_3();
                            registro.setIdProyecto(rs.getString("ID_Proyecto"));
                            registro.setCiudad(rs.getString("Ciudad"));
                            registro.setClasificacion(rs.getString("Clasificacion"));
                            registro.setCosto_Proyecto(rs.getInt("Costo_Proyecto"));
               
                            req3.add(registro);
                        }
                    }
                    return req3;
    }
}
