package ProyectoBD;
import java.sql.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class MySql {

    String BasedeDatos = "bdprogramacion";
    String username = "root";
    String password = "";

    public boolean versiesta(String XX) {
        boolean v = false;
        try {

            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());

            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + BasedeDatos, username, password);

            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);

            while (rs.next()) {
                v = true;
            }

            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

    public void RegistrosTabla(String sql, JTable jtQuery) {
        try {

            DefaultTableModel modelo = new DefaultTableModel();
            jtQuery.setModel(modelo);

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + BasedeDatos, username, password);

            Statement s = conexion.createStatement();

            ResultSet rs = s.executeQuery(sql);
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            for (int i = 1; i <= cantidadColumnas; i++) {
                modelo.addColumn(rsMd.getColumnLabel(i));

            }
            for (int i = 1; i < cantidadColumnas; i++) {
                TableColumnModel mo = jtQuery.getColumnModel();
                mo.getColumn(i).setPreferredWidth(250);

            }
            jtQuery.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            jtQuery.setColumnSelectionAllowed(true);
            while (rs.next()) {
                Object[] fila = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            rs.close();
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void LlenarTabla(String sql, JTable tabla) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            tabla.setModel(modelo);

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + BasedeDatos, username, password);
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(sql);
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            for (int i = 1; i <= cantidadColumnas; i++) {
                modelo.addColumn(rsMd.getColumnLabel(i));
            }
            while (rs.next()) {
                Object[] fila = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            rs.close();
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void EjecutarConsultas(String XX) {
        try {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + BasedeDatos, username, password);

            Statement s = conexion.createStatement();
            s.execute(XX);
            conexion.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            JOptionPane.showMessageDialog(null, "Verificar la consulta \n" + XX, "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

    

    public int CantidadRegistros(String XX) {
        int numero = 0;
        try {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + BasedeDatos, username, password);
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            while (rs.next()) {
                numero++;

            }
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numero;
    }

    public String valor(String XX) {
        String numero = "";
        try {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + BasedeDatos, username, password);
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            while (rs.next()) {
                numero = rs.getString(1);

            }
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numero;
    }

    public String[][] LlenarCampos(String XX) {
        String A[][] = new String[1][1];
        int C = 0;
        int i = 0;
        int j = 0;
        try {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + BasedeDatos, username, password);
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            C = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                i++;
            }
            A = new String[i][C];
            i = 0;
            rs = s.executeQuery(XX);
            while (rs.next()) {
                for (int x = 1; x <= C; x++) {
                    A[i][j] = rs.getString(x);
                    j++;
                }
                j = 0;
                i++;

            }
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return A;
    }

    public String[] ColumLlenar(String XX) {
        String A[] = new String[1];
        int i = 0;
        try {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + BasedeDatos, username, password);
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            A = new String[rs.getMetaData().getColumnCount()];
            i = 1;
            for (int x = 0; x < A.length; x++) {
                A[x] = rs.getMetaData().getColumnName(i);
                i++;
            }

            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return A;
    }

    public void CamposCombo(String XX, JComboBox cmb) {
        String A[] = new String[1];
        cmb.removeAllItems();
        int i = 0;
        try {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + BasedeDatos, username, password);
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            A = new String[rs.getMetaData().getColumnCount()];
            i = 1;
            for (int x = 0; x < A.length; x++) {
                cmb.addItem(rs.getMetaData().getColumnName(i));
                i++;
            }

            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    int contar = 0;
    int contar1 = 0;
    int T = 4;

    public void LlenarLista(String sqlx, JTable tablaAfiliados, DefaultTableModel afiliados) {
        JTableHeader header = tablaAfiliados.getTableHeader();
        header.setBackground(Color.yellow);

        String Noc[] = ColumLlenar(sqlx);

        System.out.println("tablaAfiliados.getColumnCount() " + tablaAfiliados.getColumnCount());

        for (int i = 0; i < tablaAfiliados.getColumnCount() - 1; i++) {
            TableColumn tcol = tablaAfiliados.getColumnModel().getColumn(i);
            tablaAfiliados.removeColumn(tcol);
        }

        for (int i = 0; i < Noc.length; i++) {
            afiliados.addColumn(Noc[i]);

        }

        for (int i = 0; i < Noc.length; i++) {
            TableColumn columnId = null;

            columnId = tablaAfiliados.getColumnModel().getColumn(i);

            columnId.setPreferredWidth(150);
        }
        tablaAfiliados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaAfiliados.setColumnSelectionAllowed(true);
        tablaAfiliados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaAfiliados.setColumnSelectionAllowed(true);

        try {
            contar = 0;
            contar1 = 0;

            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());

            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + BasedeDatos, username, password);

            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(sqlx);

            T = rs.getMetaData().getColumnCount();
            String a[] = new String[T];

            int cc = 0;

            for (int i = tablaAfiliados.getRowCount() - 1; i >= 0; i--) {
                afiliados.removeRow(i);
            }

            rs = s.executeQuery(sqlx);
            while (rs.next()) {

                for (int j = 1; j <= T; j++) {
                    a[contar] = rs.getString(j);
                    contar++;
                }

                afiliados.addRow(a);
                contar = 0;

                contar1++;

            }

            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void LlenarCombo(String XX, JComboBox cmb) {
        String A[] = new String[1];
        int i = 0;
        cmb.removeAllItems();
        try {

            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());

            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + BasedeDatos, username, password);

            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);

            while (rs.next()) {

                cmb.addItem("" + rs.getObject(1));
                i++;

            }

            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String[] Llenar(String XX, String Campo) {
        String A[] = new String[1];
        int i = 0;
        try {

            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());

            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + BasedeDatos, username, password);

            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            while (rs.next()) {
                i++;
            }
            A = new String[i];
            i = 0;
            rs = s.executeQuery(XX);

            while (rs.next()) {
                A[i] = rs.getString(Campo);
                i++;

            }

            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return A;
    }

    public void ingresoDeImagen(String x, String ruta, int nCampo) {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/" + BasedeDatos, username, password);
            PreparedStatement ps = conn.prepareStatement(x);
            conn.setAutoCommit(false);

            File file = new File(ruta);

            FileInputStream fis = new FileInputStream(file);
            ps.setBinaryStream(nCampo, fis, (int) file.length());

            ps.executeUpdate();
            conn.commit();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ImageIcon[] ArregloImagen(String x, int NumeroCampo) {
        ImageIcon m[] = new ImageIcon[CantidadRegistros(x)];

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/" + BasedeDatos, username, password);
            PreparedStatement ps = conn.prepareStatement(x);
            conn.setAutoCommit(false);
            ResultSet r = ps.executeQuery();
            int n = 0;
            while (r.next()) {
                byte[] i = null;
                i = r.getBytes(NumeroCampo);
                m[n] = new ImageIcon(i);
                n++;
            }
            r.close();
            conn.commit();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return m;

    }

    public ImageIcon sacarImagen(String x, int x1, int y1, int nc) {
        ImageIcon m = new ImageIcon();

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/" + BasedeDatos, username, password);
            PreparedStatement ps = conn.prepareStatement(x);
            conn.setAutoCommit(false);
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                byte[] i = null;
                i = r.getBytes(nc);
                m = new ImageIcon(i);
            }
            r.close();
            conn.commit();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageIcon icon = m;
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(x1, y1, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);

        return m;

    }

    public JLabel ImagenEtiqueta(String x, int x1, int y1) {
        ImageIcon m = new ImageIcon();
        JLabel yo = new JLabel();
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/" + BasedeDatos, username, password);
            PreparedStatement ps = conn.prepareStatement(x);
            conn.setAutoCommit(false);
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                byte[] i = null;
                System.out.println(r.getString(1));
                i = r.getBytes("imagen");
                m = new ImageIcon(i);
            }
            r.close();
            conn.commit();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageIcon icon = m;
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(x1, y1, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        yo.setIcon(newIcon);
        yo.setSize(150, 150);

        return yo;

    }

    public double Total(String XX) {
        double numero = 0;
        try {

            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());

            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + BasedeDatos, username, password);

            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);

            while (rs.next()) {
                numero = rs.getInt(1);

            }

            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numero;
    }

    public void copia(String ficheroOriginal, String ficheroCopia) {
        try {

            FileInputStream fileInput = new FileInputStream(ficheroOriginal);
            BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);

            FileOutputStream fileOutput = new FileOutputStream(ficheroCopia);
            BufferedOutputStream bufferedOutput = new BufferedOutputStream(fileOutput);

            byte[] array = new byte[1000];
            int leidos = bufferedInput.read(array);
            while (leidos > 0) {
                bufferedOutput.write(array, 0, leidos);
                leidos = bufferedInput.read(array);
            }

            bufferedInput.close();
            bufferedOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
