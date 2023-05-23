package dat.backend.model.persistence;

import dat.backend.model.entities.Material;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

class MaterialMapper
{


static List<Material> getMaterialById(int id, ConnectionPool connectionPool) throws DatabaseException {
        List<Material> materialList = new ArrayList<>();
        String sql = "SELECT mvariant.idmvariant,  mvariant.length, materials.unit, materials.priceprunit, materials.description FROM carport.mvariant INNER JOIN materials ON mvariant.idmaterial =materials.idmaterials WHERE idmaterial = ?";
    try(Connection connection = connectionPool.getConnection()) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int mvariant = resultSet.getInt("idmvariant");
                int length = resultSet.getInt("length");
                double priceprunit = resultSet.getDouble("priceprunit");
                String unit = resultSet.getString("unit");
                String description = resultSet.getString("description");

                materialList.add(new Material(mvariant, length, priceprunit, unit, description));
            }
        }
    }catch (SQLException e) {
            throw new DatabaseException(e, "Could not get material by id");
        }

        return materialList;
    }

    public static Double getPrice(int mvariant, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "SELECT mvariant.idmvariant, materials.unit, materials.priceprunit, materials.description  FROM carport.mvariant INNER JOIN materials ON mvariant.idmaterial =materials.idmaterials WHERE idmvariant = ?";
        try(Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, mvariant);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String price = rs.getString("materials.priceprunit");
                    double doubleprice = Double.parseDouble(price);
                    return doubleprice;
                } else {
                    return null;
                }
            }
        }catch (SQLException ex) {
          throw new DatabaseException(ex, "Error getting material price. Something went wrong with the database");
      }
    }

    public static Integer getLength(int mvariant, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "SELECT mvariant.idmvariant, materials.unit, mvariant.length, materials.priceprunit, materials.description  FROM mvariant INNER JOIN materials ON mvariant.idmaterial =materials.idmaterials WHERE idmvariant = ?;";
        try(Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, mvariant);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String length = rs.getString("mvariant.length");
                    int intlength = Integer.parseInt(length);
                    return intlength;
                } else {
                    return null;
                }
            }
        }
         catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error getting material length. Something went wrong with the database");
        }
    }

    public static String getUnit(int mvariant, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "SELECT mvariant.idmvariant, materials.unit, mvariant.length, materials.priceprunit, materials.description  FROM mvariant INNER JOIN materials ON mvariant.idmaterial =materials.idmaterials WHERE idmvariant = ?;";
        try(Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, mvariant);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String unit = rs.getString("materials.unit");
                    return unit;
                } else {
                    return null;
                }
            }
        }
            catch (SQLException ex) {
                throw new DatabaseException(ex, "Error getting material units. Something went wrong with the database");
            }
    }

    public static String getDescription(int mvariant, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "SELECT mvariant.idmvariant, materials.unit, mvariant.length, materials.priceprunit, materials.description  FROM mvariant INNER JOIN materials ON mvariant.idmaterial =materials.idmaterials WHERE idmvariant = ?;";
        try(Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, mvariant);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String description = rs.getString("materials.description");
                    return description;
                } else {
                    return null;
                }
            }
        }
         catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error getting material description. Something went wrong with the database");
        }
    }
}