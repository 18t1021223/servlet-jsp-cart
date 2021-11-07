package com.vn.repository;

import com.vn.Utils;
import com.vn.model.Category;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CategoryRepository {

    public List<Category> findAll() {
        String sql = "select * from loai";
        try {
            Statement statement = ConnectSQL.getInstance().createStatement();
            return Utils.categoryMappers(statement.executeQuery(sql));
        } catch (SQLException exception) {
            return null;
        }
    }
}
