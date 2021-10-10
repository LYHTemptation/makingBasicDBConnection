/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1.stock.vo;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class Member implements Serializable{
        private String id, name, addr;
        
        public Member(){
        
        }

    public Member(String id, String name, String addr) {
        setId(id);
        setName(name);
        setAddr(addr);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddr() {
        return addr;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

        
}
