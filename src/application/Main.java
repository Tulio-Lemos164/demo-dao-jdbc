package application;

import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyy");

        Department dept = new Department(1, "Vendas");
        Seller seller = new Seller(1, "Jim", "jim@gmail.com", LocalDate.parse("13/04/2005", fmt), 3000.0, new Department(1, "Vendas"));
        System.out.println(seller);
        System.out.println(dept);
    }
}