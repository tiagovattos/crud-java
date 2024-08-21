package br.edu.fema;

import br.edu.fema.dao.PetDao;
import br.edu.fema.entity.Pet;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        PetDao petDao = new PetDao(conn);

        Pet pet = new Pet(1L, "Dog", 13);
        Pet pet2 = new Pet(2L, "Cat", 15);

        petDao.insert(pet);
        petDao.insert(pet2);
        System.out.println(petDao.selectById(1L) + " (Printed from select by id)");

        pet.setName("Brown dog");
        petDao.update(pet);
        System.out.println(petDao.selectAll());

        petDao.delete(1L);
        petDao.delete(2L);
        System.out.println(petDao.selectAll());

        conn.close();
    }
}
