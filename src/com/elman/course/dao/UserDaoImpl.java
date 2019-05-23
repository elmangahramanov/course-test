package com.elman.course.dao;

import com.elman.course.exceptions.DuplicateEmailException;
import com.elman.course.model.Role;
import com.elman.course.model.User;
import com.elman.course.util.DbUtil;
import com.elman.course.util.MessageConstants;

import java.awt.image.DataBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final String GET_ALL_ROLE_TYPE= "select * from role";
    private static final String ADD_NEW_USER = "insert into user (email,password,token,status,id_role) values (?,?,?,?,?)";
    private static final String CHECK_EMAIL_VALID = "select count(*) as count from user where email=?";

    @Override
    public List<Role> getAllRoleType() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Role> list = new ArrayList<>();
        Role role = null;
        try {
            con = DbUtil.connect();
            ps = con.prepareStatement(GET_ALL_ROLE_TYPE);
            rs = ps.executeQuery();
            while (rs.next()){
                role = new Role();
                role.setId(rs.getInt("id_role"));
                role.setRoleType(rs.getString("role_type"));
                list.add(role);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean registerUser(User user) throws DuplicateEmailException{
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            if (!isEmailValid(user.getEmail())){
                throw new DuplicateEmailException();
            }
            con = DbUtil.connect();
            ps  = con.prepareStatement(ADD_NEW_USER);
            ps.setString(1,user.getEmail());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getToken());
            ps.setInt(4,user.getStatus());
            ps.setInt(5,user.getRole().getId());
            ps.execute();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbUtil.closeAll(con,ps);
        }
        return result;
    }
    private static boolean isEmailValid(String email){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = DbUtil.connect();
            ps = con.prepareStatement(CHECK_EMAIL_VALID);
            ps.setString(1,email);
            rs = ps.executeQuery();
            if (rs.next()){
                if (rs.getInt("count")==0){
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbUtil.closeAll(con,ps,rs);
        }
        return result;
    }
}
