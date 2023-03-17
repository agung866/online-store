package com.example.demo.repository;

import com.example.demo.model.DataProductRq;
import com.example.demo.model.DataProductRs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Slf4j
public class ProductRepository {
    @Autowired
    protected NamedParameterJdbcTemplate jdbcTemplate;

    public List<DataProductRs> getAllDataProduct(int id) throws DataAccessException {

        String sql = " SELECT * FROM PUBLIC.TB_PRODUCT WHERE ID = :id ";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);

    return jdbcTemplate.query(sql, parameterSource, new BeanPropertyRowMapper<>(DataProductRs.class));



    }
    public void insertProduct(DataProductRq dataProductRq) {

        try {
            Date date =new Date();
            String sql = "INSERT INTO PUBLIC.TB_PRODUCT (nama_product,amount,stok,description,created_date,last_updated,supplier)" +
                    " VALUES (:nama_product,:amount,:stok,:description,:created_date,:last_updated,:supplier)";
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("nama_product", dataProductRq.getNama_product());
            parameterSource.addValue("amount", dataProductRq.getAmount());
            parameterSource.addValue("stok", dataProductRq.getStok());
            parameterSource.addValue("description",dataProductRq.getDescription() );
            parameterSource.addValue("created_date", date);
            parameterSource.addValue("last_updated",date );
            parameterSource.addValue("supplier", dataProductRq.getSupplier());

            jdbcTemplate.update(sql, parameterSource);
        }catch (Exception e){
            e.getMessage();
            log.error("[ACTOR={}][ACTION={}][STATUS=NOT SUCCESS][RESPONSE={}]", "INSERT DATA PRODUCT", "INSERT", e.getMessage());
        }
    }
    public void updateStok(int id,int stok){
        try {Date date =new Date();
        String sql= "UPDATE PUBLIC.TB_PRODUCT SET STOK = :stok, LAST_UPDATED=:lastupdate WHERE ID = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        parameterSource.addValue("stok", stok);
        parameterSource.addValue("lastupdate", date);

         jdbcTemplate.update(sql,parameterSource);
        }catch (Exception e){
            e.getMessage();
            log.error("[ACTOR={}][ACTION={}][STATUS=NOT SUCCESS][RESPONSE={}]", "Update DATA PRODUCT", "UPDATE", e.getMessage());
        }
    }
    public void updateHarga(int id,int amount){
        try {
        Date date =new Date();
        String sql= "UPDATE PUBLIC.TB_PRODUCT SET AMOUNT = :amount, LAST_UPDATED=:lastupdate WHERE ID = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        parameterSource.addValue("lastupdate", date);
        parameterSource.addValue("amount", amount);

         jdbcTemplate.update(sql,parameterSource);
    }catch (Exception e){
        e.getMessage();
        log.error("[ACTOR={}][ACTION={}][STATUS=NOT SUCCESS][RESPONSE={}]", "UPDATE DATA PRODUCT", "UPDATE", e.getMessage());
    }
    }

    public void delete(int id){
        String sql= "DELETE FROM PUBLIC.TB_PRODUCT WHERE ID = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);

         jdbcTemplate.update(sql,parameterSource);
    }

}
