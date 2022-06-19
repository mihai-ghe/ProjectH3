import java.sql.*;


public class DB_connection {

    public static Connection connect() {

        Connection connection = null;

        String jdbcUrl = "jdbc:sqlite:/E:\\WORK\\Spring Projects\\H3Hackathon\\database.db";

        try {

            connection = DriverManager.getConnection(jdbcUrl);

        }
        catch (SQLException e) {

            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }

        return connection;
    }

    protected static void insertDoc(Connection con, Doctor doc) {

        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO Doctor VALUES ( ?, ? ) ";
            ps = con.prepareStatement(sql);
            ps.setString(1, doc.medicode);
            ps.setString(2, doc.specialty);
            ps.execute();
            System.out.println("Data inserted!");
        }
        catch(SQLException e) {
            System.out.println(e.toString());
        }
    }

    protected static void insertPat(Connection con, Patient pat){

        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO  Patient VALUES (NULL, ?, ?, ?, ?" +
                    ", NULL, NULL, NULL) ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, pat.age);
            ps.setDouble(2, pat.weight);
            ps.setDouble(3, pat.height);
            ps.setInt(4, pat.gen);

            ps.execute();
            System.out.println("Data inserted!");
        }
        catch(SQLException e) {
            System.out.println(e.toString());
        }

    }

    protected static void insertMed(Connection con, Meds med) {

        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO  Meds VALUES ( ?, ?, ?, ?, ?, ?) ";
            ps = con.prepareStatement(sql);
            ps.setString(1, med.atc) ;
            ps.setString(2, med.g0);
            ps.setString(3, med.g1);
            ps.setString(4, med.g2);
            ps.setString(5, med.g3);
            ps.setString(5, med.g4);
            ps.execute();
            System.out.println("Data inserted!");
        }
        catch(SQLException e) {
            System.out.println(e.toString());
        }
    }

    protected static void insertDiagnostic(Connection con, Diagnostic dians) {

        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO Diagnostics VALUES ( ?, ? ) ";
            ps = con.prepareStatement(sql);
            ps.setString(1, dians.name);
            ps.setString(2, dians.pt);
            ps.execute();
            System.out.println("Data inserted!");
        }
        catch(SQLException e) {
            System.out.println(e.toString());
        }
    }

    protected static void fetchAll(Connection con, String tn) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String sql = "SELECT * FROM " + tn;

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            switch (tn) {
                case "Doctor" :
                    System.out.println("All Doctors: ");
                    while (rs.next()) {

                        String medicode = rs.getString("medicode");
                        String specialty = rs.getString("specialty");

                        System.out.println(medicode + '|' + specialty);
                    }

                    break;

                case "Patient" :
                    System.out.println("All Patients: ");
                    while(rs.next()) {

                        int id = rs.getInt("id");
                        int age = rs.getInt("age");
                        double weight = rs.getDouble("weight");
                        double height = rs.getDouble("height");
                        int gen = rs.getInt("gen");
                        String meds = rs.getString("meds");
                        String diagnostics = rs.getString("diagnostics");
                        String supplements = rs.getString("supplements");

                        System.out.printf("ID: %d\nAge: %d\nWeight: %.2f\nHeight: %.2f\nMedication: %s\nSupplements: %s\nDiagnostics: %s\n",
                                            id, age, weight, height, meds, supplements, diagnostics);
                    }

                    break;

                case "Meds" :
                    System.out.println("All Registered Medication: ");
                    while(rs.next()) {

                        String atc = rs.getString("atc");
                        String g0 = rs.getString("g0");
                        String g1 = rs.getString("g1");
                        String g2 = rs.getString("g2");
                        String g3 = rs.getString("g3");
                        String g4 = rs.getString("g4");

                        System.out.println("ATC: " + atc);
                        System.out.println("Interactiuni: ");
                        System.out.printf("Grad 0: %s\nGrad 1: %s\nGrad 2: %s\nGrad 3: %s\nGrad 4: %s", g0, g1, g2, g3, g4);
                    }

                    break;

                case "Diagnostics" :
                    System.out.println("All Registered Diagnostics: ");
                    while(rs.next()) {

                        String name = rs.getString("name");
                        String pt = rs.getString("pt");

                        System.out.printf("%s - Tratamente Posibile: %s", name, pt);
                    }

                    break;
            }
        } catch(SQLException e) {
            System.out.println(e.toString());
        } finally {

            try {

                rs.close();
                ps.close();

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

    }

    protected static void fetchSpP(Connection con, Patient pat) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String sql = "SELECT * FROM Patient WHERE ID = ? ";
            ps = con.prepareStatement(sql);

            ps.setInt(1, pat.id);

            rs = ps.executeQuery();

            if(rs != null) {

               pat.age = rs.getInt("age");
               pat.weight = rs.getDouble("weight");
               pat.height = rs.getDouble("height");
               pat.gen = rs.getInt("gen");
               pat.meds = rs.getString("meds");
               pat.supp = rs.getString("supplements");
               pat.dians = rs.getString("diagnostics");
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {

                rs.close();
                ps.close();

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }


    }

    protected static void fetchMbyG(Connection con, String subs, Meds med) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String sql = "Select * FROM Meds ( WHERE GRADE0 LIKE ? )" +
                         "OR ( WHERE GRADE1 LIKE ? ) OR ( WHERE GRADE2 LIKE ? )" +
                         "OR ( WHERE GRADE3 LIKE ? ) OR ( WHERE GRADE4 LIKE ? )";
            ps = con.prepareStatement(sql);
            ps.setString(1, subs);
            ps.setString(2, subs);
            ps.setString(3, subs);
            ps.setString(4, subs);
            ps.setString(5, subs);

            rs = ps.executeQuery();

            if(rs != null) {

                med.atc = rs.getString("atc");
                med.g0 = rs.getString("grade0");
                med.g1 = rs.getString("grade1");
                med.g2 = rs.getString("grade2");
                med.g3 = rs.getString("grade3");
                med.g4 = rs.getString("grade4");
            }


        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {

                rs.close();
                ps.close();

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

    }

    protected static void fetchMbyATC(Connection con, String subs, Meds med) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String sql = "Select * FROM Meds WHERE ATC LIKE ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, subs);

            rs = ps.executeQuery();

            if(rs != null) {

                med.atc = rs.getString("atc");
                med.g0 = rs.getString("grade0");
                med.g1 = rs.getString("grade1");
                med.g2 = rs.getString("grade2");
                med.g3 = rs.getString("grade3");
                med.g4 = rs.getString("grade4");
            }


        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {

                rs.close();
                ps.close();

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

    }

    protected static void fetchSpD(Connection con, Diagnostic dians) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String sql = "SELECT * FROM Diagnostics WHERE NAME like ? ";
            ps = con.prepareStatement(sql);

            ps.setString(1, dians.name);

            rs = ps.executeQuery();

            if(rs != null) {

                dians.pt = rs.getString("pt");
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {

                rs.close();
                ps.close();

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }


    }

    protected static void updatePat(Connection con, int id, String meds, String dians) {

        PreparedStatement ps = null;
        try {

            String sql = "UPDATE Patient SET MEDS = ? WHERE ID = ?";
            ps = con.prepareStatement(sql);

            ps.setString(1, meds);
            ps.setInt(2, id);

            ps.execute();

            System.out.println("Update successfull!");

            sql = "UPDATE Patient SET Diagnostics = ? WHERE ID = ?";

            ps = con.prepareStatement(sql);

            ps.setString(1, dians);
            ps.setInt(2, id);

            ps.execute();

            System.out.println("Update successfull!");

        } catch (SQLException e) {
            System.out.println(e.toString());
        }


    }

}
