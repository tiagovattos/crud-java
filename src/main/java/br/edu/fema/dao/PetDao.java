package br.edu.fema.dao;

import br.edu.fema.entity.Pet;
import br.edu.fema.exception.ObjectNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetDao {
    private final Connection connection;

    public PetDao(Connection connection) {
        this.connection = connection;
    }

    public List<Pet> selectAll() throws SQLException {
        String sql = "SELECT * FROM PET";
        List<Pet> pets = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            Pet pet = new Pet();
            pet.setId(rs.getLong("id"));
            pet.setName(rs.getString("name"));
            pet.setAge(rs.getInt("age"));
            pets.add(pet);
        }
        rs.close();
        ps.close();
        return pets;
    }

    public Pet selectById(Long id) throws SQLException, ObjectNotFoundException {
        String sql = "SELECT * FROM PET WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Pet pet = new Pet();
                    pet.setId(rs.getLong("id"));
                    pet.setName(rs.getString("name"));
                    pet.setAge(rs.getInt("age"));
                    return pet;
                } else {
                    throw new ObjectNotFoundException("Pet not found");
                }
            }
        } // ps e rs sao fechados automaticamente com o try catch
    }

    public void insert(Pet pet) throws SQLException {
        String sql = "INSERT INTO PET VALUES (?, ?, ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, pet.getId());
        ps.setString(2, pet.getName());
        ps.setInt(3, pet.getAge());
        ps.execute();
        ps.close();
    }

}
