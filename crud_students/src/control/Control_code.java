package control;


import model.Student;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.Scanner;

public class Control_code {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        /* manualy student image input area start */

        String data_in = "C:/Users/Lenovo/Desktop/crud_java/crud_students/src/upload_images/person_3.png";
        //String data_in = "C:/Users/Lenovo/Desktop/crud_java/crud_students/src/upload_images/person_4.png";
        //String data_in = "C:/Users/Lenovo/Desktop/crud_java/crud_students/src/upload_images/person_1.png";

        /* manualy student image input area end */

        /* manualy student image output path area start */

        String file_path = "C:/Users/Lenovo/Desktop/crud_java/crud_students/src/downloads/";


        /* manualy user image output path area start */


        String url = "jdbc:mysql://localhost:3306/students";
        String name = "root";
        String password = "";

       Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection(url,name,password);



        System.out.println(" Please choose your important option [ registration or update or delete or retrieve or  retrieveall ]");

        Scanner user_input = new Scanner(System.in);
        System.out.print("Please type your important option : ");
        String option = user_input.nextLine();


        String sql;
        PreparedStatement pps;
        ResultSet resultSet;


        try {

            switch (option) {
                case "retrieve" -> {

                    Scanner user_input_id = new Scanner(System.in);
                    System.out.print("Please type your id : ");
                    int input_id = user_input_id.nextInt();

                    //sql = "select * from registration";
                    sql = "select *from registration where id = '" + input_id + "' ";
                    pps = con.prepareStatement(sql);
                    resultSet = pps.executeQuery();

                    System.out.println("Successfully retrieve your All data! ");

                    while (resultSet.next()) {

                        String studentid = resultSet.getString("id");
                        String studentname = resultSet.getString("name");
                        String studentemail = resultSet.getString("email");
                        String studentnid = resultSet.getString("nid");
                        String studentphone = resultSet.getString("phone");
                        String studentyear_of_passing = resultSet.getString("year_of_passing");
                        String studentcgpa = resultSet.getString("cgpa");


                        byte[] image_bytes = resultSet.getBytes("image");

                        FileOutputStream fos = new FileOutputStream(file_path + studentname + ".jpg");
                        fos.write(image_bytes);


                        System.out.println("Your id : " + studentid + "\t Name : " + studentname + "\t Email : " + studentemail + "\t NID : " + studentnid + "\t Phone : +880" + studentphone + "\t Year Of Passing : SSC " + studentyear_of_passing + "\t SSC CGPA : " + studentcgpa);

                    }

                }
                case "insert" -> {
                    Scanner input = new Scanner(System.in);
                    System.out.print("Please type your id : ");
                    int student_id = input.nextInt();

                    Scanner inputname = new Scanner(System.in);
                    System.out.print("Please type your name : ");
                    String student_name = inputname.nextLine();

                    Scanner inputemail = new Scanner(System.in);
                    System.out.print("Please type your email : ");
                    String student_email = inputemail.nextLine();

                    Scanner inputnid = new Scanner(System.in);
                    System.out.print("Please type your nid : ");
                    int student_nid = inputnid.nextInt();

                    Scanner inputphone = new Scanner(System.in);
                    System.out.print("Please type your phone : ");
                    int student_phone = inputphone.nextInt();


                    Scanner inputyear_of_passing = new Scanner(System.in);
                    System.out.print("Please type your SSC year_of_passing : ");
                    int student_year_of_passing = inputyear_of_passing.nextInt();

                    Scanner inputcgpa = new Scanner(System.in);
                    System.out.print("Please type your SSC cgpa : ");
                    double student_cgpa = inputcgpa.nextDouble();


                    Student student = new Student();
                    student.setId(student_id);
                    student.setName(student_name);
                    student.setEmail(student_email);
                    student.setNid(student_nid);
                    student.setPhone(student_phone);
                    student.setYear_of_passing(student_year_of_passing);
                    student.setCgpa(student_cgpa);
                    student.setImage(data_in);


                    sql = "insert into registration (id,name,email,nid,phone,year_of_passing,cgpa,image) values(?,?,?,?,?,?,?,?)";
                    File myfile = new File(student.getImage());
                    FileInputStream fis = new FileInputStream(myfile);
                    pps = con.prepareStatement(sql);
                    pps.setInt(1, student.getId());
                    pps.setString(2, student.getName());
                    pps.setString(3, student.getEmail());
                    pps.setInt(4, student.getNid());
                    pps.setInt(5, student.getPhone());
                    pps.setInt(6, student.getYear_of_passing());
                   pps.setDouble(7,student.getCgpa());
                    pps.setBinaryStream(8, fis, fis.available());
                    pps.executeUpdate();
                    System.out.println("Ok: " + option);
                }
                case "update" -> {


                    Scanner update_id = new Scanner(System.in);
                    System.out.print("Please type your id : ");
                    int update_student_id = update_id.nextInt();

                    Scanner updatename = new Scanner(System.in);
                    System.out.print("Please type your name : ");
                    String update_student_name = updatename.nextLine();

                    Scanner updateemail = new Scanner(System.in);
                    System.out.print("Please type your email : ");
                    String update_student_email = updateemail.nextLine();

                    Scanner updatenid = new Scanner(System.in);
                    System.out.print("Please type your nid : ");
                    int update_student_nid = updatenid.nextInt();

                    Scanner updatephone = new Scanner(System.in);
                    System.out.print("Please type your phone : ");
                    int update_student_phone = updatephone.nextInt();


                    Scanner updateyear_of_passing = new Scanner(System.in);
                    System.out.print("Please type your SSC year_of_passing : ");
                    int update_student_year_of_passing = updateyear_of_passing.nextInt();

                    Scanner updatecgpa = new Scanner(System.in);
                    System.out.print("Please type your SSC cgpa : ");
                    float update_student_cgpa = updatecgpa.nextFloat();


                    Student student_update = new Student();
                    student_update.setId(update_student_id);
                    student_update.setName(update_student_name);
                    student_update.setEmail(update_student_email);
                    student_update.setNid(update_student_nid);
                    student_update.setPhone(update_student_phone);
                    student_update.setYear_of_passing(update_student_year_of_passing);
                    student_update.setCgpa(update_student_cgpa);
                    student_update.setImage(data_in);


                    sql = "update registration set id=?, name=?, email=?, nid=?, phone=?, year_of_passing=?, cgpa=?, image=? where id= '"+update_student_id+"'";

                    File myfile = new File(data_in);
                    FileInputStream fis = new FileInputStream(myfile);

                    pps = con.prepareStatement(sql);
                    pps.setInt(1, update_student_id);
                    pps.setString(2, student_update.getName());
                    pps.setString(3, student_update.getEmail());
                    pps.setInt(4, student_update.getNid());
                    pps.setInt(5, student_update.getPhone());
                    pps.setInt(6, student_update.getYear_of_passing());
                    pps.setDouble(7, student_update.getCgpa());
                    pps.setBinaryStream(8, fis, fis.available());
                    pps.executeUpdate();

                    System.out.println("Ok : " + option);
                }

                case "delete" -> {

                    Scanner delete_id = new Scanner(System.in);
                    System.out.print("Please type your id : ");
                    int delete_student_id = delete_id.nextInt();

                    sql = "delete from registration where id = ? ";

                    pps = con.prepareStatement(sql);

                    pps.setInt(1, delete_student_id);

                    pps.executeUpdate();

                    System.out.println("ok: " + option);
                }

                case "retrieveall" -> {

                    sql = "select * from registration";

                    pps = con.prepareStatement(sql);
                    resultSet = pps.executeQuery();


                    System.out.println("Successfully retrieve your All data! ");

                    while (resultSet.next()) {


                        String studentid = resultSet.getString("id");
                        String studentname = resultSet.getString("name");
                        String studentemail = resultSet.getString("email");
                        String studentnid = resultSet.getString("nid");
                        String studentphone = resultSet.getString("phone");
                        String studentyear_of_passing = resultSet.getString("year_of_passing");
                        String studentcgpa = resultSet.getString("cgpa");

                      /*  byte[] image_bytes = resultSet.getBytes("image");

                        FileOutputStream fos = new FileOutputStream(file_path + studentname + ".jpg");
                        fos.write(image_bytes);*/


                        System.out.println("\t Your id : " + studentid + "\n Name : " + studentname + "\n Email : " + studentemail + "\n NID : " + studentnid + "\n Phone : +880" + studentphone + "\n Year Of Passing : SSC " + studentyear_of_passing + "\n SSC CGPA : " + studentcgpa);


                    }

                }
                default -> System.out.println("Value Not Match, please contact your system administrator...");
            }


        } catch (Throwable e) {
            System.out.println("Problem found exception: " + e);
        }


    }


}
