package br.edu.fema;

import br.edu.fema.dao.PetDao;
import br.edu.fema.entity.Pet;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        PetDao petDao = new PetDao(conn);

        Pet pet = new Pet(1L, "Esquilo", 13);

        petDao.insert(pet);

        System.out.println(petDao.selectById(2L));

        System.out.println(petDao.selectAll());

        petDao.delete(2L);

        conn.close();
    }
}
