package com.example.demo.repository;

import com.example.demo.model.OrderRq;
import com.example.demo.model.PurchaseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class PurchaseRepository {
    @Autowired
    protected NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    protected JdbcTemplate jdbcTemplate1;

    public void createTransaction(PurchaseModel purchaseModel){
        try {
            String sql = "INSERT INTO PUBLIC.TB_PEMBELIAN (transactionid,id_product,product_name,amount,quantity,purchase_date,status_payment,status_order,payment_type,id_user)" +
                    " VALUES (:transactionid,:id_product,:product_name,:amount,:quantity,:purchase_date,:status_payment,:status_order,:payment_type,:id_user)";
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("transactionid", purchaseModel.getTransactionid());
            parameterSource.addValue("id_product", purchaseModel.getProductId());
            parameterSource.addValue("product_name", purchaseModel.getProductName());
            parameterSource.addValue("amount", purchaseModel.getAmount());
            parameterSource.addValue("quantity", purchaseModel.getQuantity());
            parameterSource.addValue("purchase_date", purchaseModel.getPurchaseDate());
            parameterSource.addValue("status_payment", purchaseModel.getStatusPayment());
            parameterSource.addValue("status_order", purchaseModel.getStatusOrder());
            parameterSource.addValue("payment_type", purchaseModel.getPaymentType());
            parameterSource.addValue("id_user", purchaseModel.getIdUser());

            jdbcTemplate.update(sql, parameterSource);
        }catch (Exception e){
            e.getMessage();
            log.error("[ACTOR={}][ACTION={}][STATUS=NOT SUCCESS][RESPONSE={}]", "INSERT DATA PURCHASE", "INSERT", e.getMessage());
        }
    }
    public Long count (){
        String sql = "SELECT COUNT(transactionid)FROM PUBLIC.TB_PEMBELIAN";
        return jdbcTemplate1.queryForObject(sql,Long.class);
    }

    public List<PurchaseModel> getListTrxByTrxId(String transactionid){
        String sql = " SELECT * FROM PUBLIC.TB_PEMBELIAN WHERE transactionid  = :transactionid";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("transactionid", transactionid);
        return jdbcTemplate.query(sql, parameterSource, new BeanPropertyRowMapper<>(PurchaseModel.class));
    }
//    public List<OrderRq> getListTrxByTrxId(String transactionid){
//        String sql = " SELECT * FROM PUBLIC.TB_PEMBELIAN WHERE transactionid  = :transactionid";
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("transactionid", transactionid);
//        return jdbcTemplate.query(sql, parameterSource, new BeanPropertyRowMapper<>(OrderRq.class));
//    }

    public void updateStatus(String transactionId, String orderStatus,String paymentStatus){
        try {
            String sql = "UPDATE PUBLIC.TB_PEMBELIAN SET STATUS_ORDER = :orderStatus AND STATUS_PAYMENT= :paymentStatus WHERE transactionid = :transactionId";
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("orderStatus", orderStatus);
            parameterSource.addValue("paymentStatus", paymentStatus);
            parameterSource.addValue("transactionId", transactionId);

            jdbcTemplate.update(sql,parameterSource);

        }catch (Exception e){
            e.getMessage();
            log.error("[ACTOR={}][ACTION={}][STATUS=NOT SUCCESS][RESPONSE={}]", "UPDATE DATA PURCHASE", "UPDATE", e.getMessage());
        }

    }
    public void updateStatusOrder(String transactionId, String orderStatus){
        try {
            String sql = "UPDATE PUBLIC.TB_PEMBELIAN SET STATUS_ORDER = :orderStatus WHERE transactionid = :transactionId";
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("orderStatus", orderStatus);
            parameterSource.addValue("transactionId", transactionId);

            jdbcTemplate.update(sql,parameterSource);

        }catch (Exception e){
            e.getMessage();
            log.error("[ACTOR={}][ACTION={}][STATUS=NOT SUCCESS][RESPONSE={}]", "UPDATE DATA PURCHASE", "UPDATE", e.getMessage());
        }

    }
}
