
package test1.stock.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import test1.stock.vo.Member;
import test1.stock.vo.MyProtocol;

public class StockServer {
    
    public void ready() throws Exception{
        ServerSocket ss = new ServerSocket(9999);
        while(true){
            Socket s = ss.accept();
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ServerThread t = new ServerThread(s,in,out);
            t.start();
        }
    }
    class ServerThread extends Thread{
        Socket s;
        ObjectInputStream in;
        ObjectOutputStream out;
        private ServerThread(Socket s, ObjectInputStream in, ObjectOutputStream out) {
            this.s = s;
            this.in = in;
            this.out = out;
        }
        @Override
        public void run(){
            try {
                while(true){
                    MyProtocol p  =(MyProtocol)in.readObject();
                    if(p.getSign().equals("INSERT_MEMBER")){
                        Member m =(Member)p.getParameterObj();
                        System.out.println(m.getId());
                        System.out.println(m.getName());
                        System.out.println(m.getAddr());
                        //DB 처리 
                        MemberDAO memberDAO = new MemberDAO();
                        Member fm = memberDAO.selectMember(m.getId()); //중복체크
                        if(fm!=null){
                            out.writeObject("id가 중복되었습니다.");
                        }else{
                            memberDAO.insertMember(m);
                            out.writeObject("등록되었습니다");
                        }                        
                    }else if(p.getSign().equals("SELECT_MEMBER")){
                        String id = (String)p.getParameterObj();
                        MemberDAO memberDAO = new MemberDAO();
                        Member fm = memberDAO.selectMember(id); //중복체크                        
                        if(fm==null){
                            out.writeObject("해당 고객이 없습니다");
                        }else{
                            out.writeObject(fm);
                        }
                    }else if(p.getSign().equals("UPDATE_MEMBER")){
                     Member m=(Member)p.getParameterObj();
                     MemberDAO memberDAO=new MemberDAO();
                     memberDAO.updateMember(m);
                 }else if(p.getSign().equals("DELETE_MEMBER")){
                     String id=(String) p.getParameterObj();
                    MemberDAO memberDAO=new MemberDAO();
                    Member findMember=memberDAO.selectMember(id);
                  if(findMember==null){
                      out.writeObject("해당 고객 정보가 없습니다");
                  }else{
                      memberDAO.deleteMember(id);
                      out.writeObject("정상적으로 삭제되었습니다");
                  }
                 }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("client 퇴장");
            }
        }
    }
    public static void main(String[] args) throws Exception {
        new StockServer().ready();
    }
}
