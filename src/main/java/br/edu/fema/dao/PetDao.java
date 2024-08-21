package br.edu.fema.dao;

import br.edu.fema.entity.Pet;

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
        while (rs.next()) {
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

    public Pet selectById(Long id) throws SQLException {
        String sql = "SELECT * FROM PET WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, id);
        Pet pet = null;
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            pet = new Pet();
            pet.setId(rs.getLong("id"));
            pet.setName(rs.getString("name"));
            pet.setAge(rs.getInt("age"));
        }
        return pet;
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

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM PET WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

}
