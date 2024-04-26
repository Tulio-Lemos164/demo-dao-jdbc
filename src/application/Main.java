package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== Test 1: Find by Id ===");
        Seller seller = sellerDao.findById(7);
        System.out.println(seller);

        System.out.println();
        System.out.println("=== Test 2: Find by Department ===");
        List<Seller> sellers = sellerDao.findByDepartment(new Department(4, "Books"));
        for (int i = 0; i<sellers.size(); i++){
            System.out.println(sellers.get(i));
        }

        System.out.println();
        System.out.println("=== Test 3: Find All ===");
        List<Seller> allSellers = sellerDao.findAll();
        for (int i = 0; i<allSellers.size(); i++){
            System.out.println(allSellers.get(i));
        }

        //System.out.println();
        //System.out.println("=== Test 4: Seller Insert ===");
        //Seller newSeller = new Seller(null, "Vou ser apagado", "vouserapagado@gmail.com", LocalDate.parse("13/04/1969", fmt), 8500.00, new Department(3, "Fashion"));
        //sellerDao.insert(newSeller);
        //System.out.println("New Seller inserted! Id: " + newSeller.getId());

        //System.out.println();
        //System.out.println("=== Test 5: Seller Update ===");
        //seller.setEmail("dwight@gmail.com");
        //sellerDao.update(seller);
        //System.out.println("Update Completed!");

        System.out.println();
        System.out.println("=== Test 6: Seller Delete ===");
        sellerDao.deleteById(11);
        System.out.println("Deleted!");
    }
}