package model.dao.impl;

import db.DB;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller seller) {

    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT seller.*,department.Name as DeptName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()){
                Department dept = instantiateDepartment(rs);

                Seller seller = instantiateSeller(rs, dept);

                return seller;
            }
            return null;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dept = new Department();
        dept.setId(rs.getInt("DepartmentId"));
        dept.setName(rs.getString("DeptName"));

        return dept;
    }

    private Seller instantiateSeller(ResultSet rs, Department dept) throws SQLException {
        Seller seller = new Seller();
        seller.setId(rs.getInt("Id"));
        seller.setName(rs.getString("Name"));
        seller.setEmail(rs.getString("Email"));
        seller.setBaseSalary(rs.getDouble("BaseSalary"));
        seller.setBirthDate(rs.getDate("BirthDate").toLocalDate());
        seller.setDepartment(dept);

        return seller;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

    @Override
    public List<Seller> findByDepartment(Department dept) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
                    +"FROM seller INNER JOIN department "
                    +"ON seller.DepartmentId = department.Id "
                    +"WHERE DepartmentId = ? "
                    +"ORDER BY Name");

            st.setInt(1, dept.getId());
            rs = st.executeQuery();

            List<Seller> myList = new ArrayList<>();

            while (rs.next()){

                Seller seller = instantiateSeller(rs, dept);
                myList.add(seller);
            }
            return myList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}
