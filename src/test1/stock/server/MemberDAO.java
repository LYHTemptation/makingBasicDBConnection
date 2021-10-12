/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1.stock.server;

import java.sql.*;
import test1.stock.vo.Member;

public class MemberDAO {
    public void insertMember(Member vo) throws Exception{
        // 1.드라이버 등록
        Class.forName("org.mariadb.jdbc.Driver");
        // 2.연결
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/testDB", "root", "1234");
        /*Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/testDB?user=root&password=1234");*/
        
        // 3.Statement 생성
        PreparedStatement stmt = con.prepareStatement("insert into member(id,name,addr) values(?,?,?)");
        stmt.setString(1, vo.getId());
        stmt.setString(2, vo.getName());
        stmt.setString(3, vo.getAddr());
        
        // 4.sql전송
        int i = stmt.executeUpdate();
        System.out.println(i+"행이 insert 되었습니다");
        
        // 5.
        // 6.자원 종료
        stmt.close();
        con.close();
        
    }
    public Member selectMember(String id) throws Exception{
        // 1.드라이버 등록
        Class.forName("org.mariadb.jdbc.Driver");
        // 2.연결
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/testDB", "root", "1234");
        /*Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/testDB?user=root&password=1234");*/
        
        // 3.Statement 생성
        PreparedStatement stmt = con.prepareStatement("select * from member where id = ?");
        stmt.setString(1, id);
        
        // 4.SQL 전송
        ResultSet rs = stmt.executeQuery();
        
        // 5.결과 받기
        if(rs.next()){
            String name = rs.getString("name");
            String addr = rs.getString("addr");
            Member m = new Member(id,name,addr);
            return m;
        }
        return null;
    }
    public void updateMember(Member vo) throws Exception{
         //1.드라이버 등록
        Class.forName("org.mariadb.jdbc.Driver");
       
        //2.연결
        Connection con=DriverManager.getConnection("jdbc:mariadb://localhost:3306/testDB", "root","1234");
        
        //3.Statement 생성
        PreparedStatement stmt=con.prepareStatement("update member set name=?, addr=? where id=?");
        stmt.setString(3,vo.getId());
        stmt.setString(1,vo.getName());
        stmt.setString(2,vo.getAddr());
        
        //4.sql전송
       int i=stmt.executeUpdate();
        System.out.println(i+"행이 update 되었습니다");
       
        //6.자원 종료
        stmt.close();
        con.close();
    }
    public void deleteMember(String id) throws Exception{
         //1.드라이버 등록
        Class.forName("org.mariadb.jdbc.Driver");
       
                //2.연결
        Connection con=DriverManager.getConnection("jdbc:mariadb://localhost:3306/testDB", "root", "1234");
        //3. Statement 생성
        PreparedStatement stmt=con.prepareStatement("delete from member where id=? ");
        stmt.setString(1, id);
           
        //4. SQL 전송
        int i=stmt.executeUpdate();
        System.out.println(i+"행이 delete 되었습니다");
        
        //6. 자원 종료
        stmt.close();
        con.close();

    }
}
