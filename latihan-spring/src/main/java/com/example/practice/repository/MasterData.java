package com.example.practice.repository;


import com.example.practice.model.Product;
import com.example.practice.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class MasterData {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Sql2o sql2o;

    public List<Product> fetchProductAll(Integer cari) {
        try (Connection con = sql2o.open()) {
            if (ObjectUtils.isEmpty(cari)) {
//                cari = null;
                final String query =
                        "SELECT * FROM product";

                return con.createQuery(query)
                        .executeAndFetch(Product.class);
            } else {
                final String query =
                        "SELECT * FROM product WHERE productId LIKE concat('%' , :ID, '%')";

                return con.createQuery(query)
                        .addParameter("ID", cari)
                        .executeAndFetch(Product.class);
            }

        }
    }

    public Product fetchProductById(Integer cari) {
        try (Connection con = sql2o.open()) {
            if (ObjectUtils.isEmpty(cari)) {
                cari = null;
            }
            final String query =
                    "SELECT * FROM product WHERE productId = :ID";

            return con.createQuery(query)
                    .addParameter("ID", cari)
                    .executeAndFetchFirst(Product.class);
        }
    }

    public void insertproduct(Product product) {
        final String query = "insert into product values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query,
                product.getProductId(),
                product.getProductName(),
                product.getSupplierId(),
                product.getCategoryId(),
                product.getQuantityPerUnit(),
                product.getUnitPrice(),
                product.getUnitInStock(),
                product.getUnitsOnOrder(),
                product.getReOrderLevel(),
                product.getDiscontinued());
    }

    public void updateProduct(Product product) {
        final String query = "update product set productName = ? where productId = ?";
        jdbcTemplate.update(query, product.getProductName(), product.getProductId());
    }

    public void deleteProduct(Integer id) {
        final String query = "DELETE FROM product WHERE productId = ?";
        jdbcTemplate.update(query, id);
    }

    // public Product deleteProduct(Integer cari) {
    //     try (Connection con = sql2o.open()) {
    //         final String query =
    //                 "DELETE FROM product WHERE productId = :ID";

    //         return con.createQuery(query)
    //                 .addParameter("ID", cari)
    //                 .executeAndFetchFirst(Product.class);
    //     }
    // }

    

    // public Login getUser(String username, String password){
    //     Login userSatu = new Login();
    //     userSatu.setUsername(username);
    //     userSatu.setPassword(password);
    //     return userSatu;
    // }

    public Login getUser(String username) {
         try (Connection con = sql2o.open()) {
            if (ObjectUtils.isEmpty(username)) {
                username = null;
            }
            final String query =
                    "SELECT * FROM user WHERE username = :name";

            return con.createQuery(query)
                    .addParameter("name", username)
                    .executeAndFetchFirst(Login.class);
        }
    }

    public void updateJam(Login login) {
        final String query = "update user set jam = now() where username = ?";
        jdbcTemplate.update(query, login.getUsername());
    }

    public void updateIsLogin(Login login) {
        final String query = "update user set isLogin = ? where username = ?";
        jdbcTemplate.update(query, login.getIsLogin() ,login.getUsername());
    }

}