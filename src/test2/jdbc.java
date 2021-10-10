/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import java.sql.*;
/**
 *
 * @author admin
 */
public class jdbc {
    public static void main(String[] args) throws Exception{
    //1. 드라이버 등록
    Class.forName("org.mariadb.jdbc.Driver");

    //2. 연결 
    Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/testDB?user=root&password=1234");

    //3. Statement 생성
    Statement stmt=connection.createStatement();

    //4. SQL 전송
    ResultSet rs=stmt.executeQuery("select * from member");

    //5. 결과 받기
    while(rs.next()){
        String id=rs.getString("id");
        String name=rs.getString("name");
        String addr=rs.getString("addr");
        System.out.println(id+":"+name+":"+addr);
       }

    //6. 자원 종료
    rs.close();
    stmt.close();
    connection.close();

    }
}
