package com.example.demo.repository;

import com.example.demo.model.DataProductRs;
import com.example.demo.model.PurchaseModel;
import com.example.demo.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.List;

@Repository
@Slf4j
public class UserRepository {

    @Autowired
    protected NamedParameterJdbcTemplate jdbcTemplate;
//
//    public List<DataProduct> doLogin(String username) throws DataAccessException {
//
//
//        String sql = " SELECT * FROM PUBLIC.TB_PRODUCT WHERE USERNAME = :username ";
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("username", username);
////            if (list.isEmpty()) {
////                log.warn("[ACTOR={}][ACTION={}][ERROR={}]", "GET DATA PRODUCT ", "GET", String.format("DATA PRODUCT NOTFOUND"));
////                throw new Exception("DATA PRODUCT NOT FOUND");
////
////            }
////        } catch (Exception e) {
////            log.error("[ACTOR={}][ACTION={}][STATUS=NOT SUCCESS][RESPONSE={}]", "GET DATA PRODUCT", "GET", e.getMessage());
////        }
//        return jdbcTemplate.query(sql, new MapSqlParameterSource[]{parameterSource}, new BeanPropertyRowMapper<>(DataProduct.class));
//
//    }
    public List<UserModel> getAllDataUser(int idUser) throws DataAccessException {

        String sql = " SELECT * FROM PUBLIC.TB_USER WHERE ID_USER = :id_user";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_user", idUser);

        return jdbcTemplate.query(sql, parameterSource,new BeanPropertyRowMapper<>(UserModel.class));
    }

    public void insertUser(UserModel userModel) throws DataAccessException {
        try{


            String sql = "INSERT INTO PUBLIC.TB_USER (username,password,email,full_name,status,role,last_login,alamat)" +
                    " VALUES (:username,:password,:email,:full_name,:status,:role,:last_login,:alamat)";
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("username", userModel.getUsername());
            parameterSource.addValue("password", userModel.getPassword());
            parameterSource.addValue("email", userModel.getEmail());
            parameterSource.addValue("full_name", userModel.getFullname());
            parameterSource.addValue("role", userModel.getRole());
            parameterSource.addValue("last_login", userModel.getLastLogin());
            parameterSource.addValue("alamat", userModel.getAlamat());

            jdbcTemplate.query(sql, parameterSource,new BeanPropertyRowMapper<>(UserModel.class));
        }catch (Exception e){
            e.getMessage();
            log.error("[ACTOR={}][ACTION={}][STATUS=NOT SUCCESS][RESPONSE={}]", "INSERT DATA USER", "INSERT", e.getMessage());
        }
    }

    public List<UserModel> getAllDataUser(String username){
        String sql = " SELECT * FROM PUBLIC.TB_USER WHERE id_user  = :username";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("username", username);
        return jdbcTemplate.query(sql, parameterSource, new BeanPropertyRowMapper<>(UserModel.class));
    }

    public void updateLastLogin(int iduser){
        try {
            Date date =new Date();
            String sql = "UPDATE PUBLIC.TB_USER SET last_login = :lastLogin WHERE id_user = :id_user";
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("id_user", iduser);
            parameterSource.addValue("lastLogin", date);

            jdbcTemplate.update(sql,parameterSource);

        }catch (Exception e){
            e.getMessage();
            log.error("[ACTOR={}][ACTION={}][STATUS=NOT SUCCESS][RESPONSE={}]", "UPDATE DATA PURCHASE", "UPDATE", e.getMessage());
        }
    }

    public void updatepassword(int iduser,String password){
        try {
            Date date =new Date();
            String sql = "UPDATE PUBLIC.TB_USER SET password = :lastLogin WHERE id_user = :id_user";
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("id_user", iduser);
            parameterSource.addValue("password", password);

            jdbcTemplate.update(sql,parameterSource);
        }catch (Exception e){
            e.getMessage();
            log.error("[ACTOR={}][ACTION={}][STATUS=NOT SUCCESS][RESPONSE={}]", "UPDATE DATA PURCHASE", "UPDATE", e.getMessage());
        }
    }

}
