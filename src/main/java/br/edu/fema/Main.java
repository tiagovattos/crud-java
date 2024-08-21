package br.edu.fema;

import br.edu.fema.dao.PetDao;
import br.edu.fema.exception.ObjectNotFoundException;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        PetDao petDao = new PetDao(conn);

//        Pet pet = new Pet(2L, "Esquilo", 13);

//        petDao.insert(pet);

        try {
            System.out.println(petDao.selectById(3L));
        } catch (ObjectNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(petDao.selectAll());

        conn.close();
    }
}
