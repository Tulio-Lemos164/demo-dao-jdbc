package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== Test 1: Find by Id ===");
        Seller seller = sellerDao.findById(7);
        System.out.println(seller);

        System.out.println();
        System.out.println("=== Test 2: Find by Id ===");
        List<Seller> sellers = sellerDao.findByDepartment(new Department(4, "Books"));

        for (int i = 0; i<sellers.size(); i++){
            System.out.println(sellers.get(i));
        }
    }
}