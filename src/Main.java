import java.sql.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
         Connection con = null;
         try{
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/menu","root","Anu123#");
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
         try{
             if(con.isClosed())
                 System.out.println("close");
             else
                 System.out.println("open");

         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
         Scanner sc = new Scanner(System.in);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~  Welcome to our Recipe List ~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        while(true){
            System.out.println("Enter 1 to add a new recipe :");
            System.out.println("Enter 2 to remove a recipe :");
            System.out.println("Enter 3 to make changes in a recipe :");
            System.out.println("Enter 4 to display all the recipes:");
            System.out.println("Enter 5 to exit the recipe list :");
            int number = sc.nextInt();
            if(number == 1)
            {
                int id = sc.nextInt();
                String recipe_name = sc.next();
                sc.nextLine();
                String r_code = sc.next();
                PreparedStatement stmt = null;
                try{
                    stmt = con.prepareStatement("insert into menu.recipe Value(?,?,?)");

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try{
                   stmt.setInt(1,id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
               try{
                   stmt.setString(2,recipe_name);
               } catch (SQLException e) {
                   throw new RuntimeException(e);
               }
               try{
                   stmt.setString(3,r_code);
               } catch (SQLException e) {
                   throw new RuntimeException(e);
               }
               try{
                   stmt.execute();
               } catch (SQLException e) {
                   throw new RuntimeException(e);
               }
                System.out.println("Recipes entered successfully");


            } else if (number == 2) {
                System.out.println("Enter the recipe to be deleted:");
                int rno = sc.nextInt();
                PreparedStatement stmt = null;
                try{
                    stmt = con.prepareStatement("delete from recipe where id =?");

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try{
                    stmt.setInt(1,rno);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try{
                    stmt.execute();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Recipe Deleted Successfully");


            }else if(number == 3){
                System.out.println("Enter the recipe to be updated:");
                String str = sc.next();
                int r = sc.nextInt();
                PreparedStatement stmt = null;
                try{
                    stmt = con.prepareStatement("update recipe set name =? where id=?");

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    stmt.setString(1, str);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try{
                    stmt.setInt(2,r);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try{
                    stmt.execute();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(" Recipe updated successfully");

            } else if (number == 4) {
                try{
                    PreparedStatement stmt = con.prepareStatement("select * from menu.recipe");
                    ResultSet set = stmt.executeQuery();
                    while(set.next()){
                        int id = set.getInt(1);
                        String recipe_name = set.getString(2);
                        String re_code = set.getString(3);
                        System.out.println("id ->"+ id+ "and recipe name ="+recipe_name+"and recipe code -> "+re_code);


                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("List shown");


            } else if (number==5){
                break;
            }


        }

    }
}
