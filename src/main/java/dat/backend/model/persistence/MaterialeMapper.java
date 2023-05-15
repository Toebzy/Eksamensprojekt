package dat.backend.model.persistence;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class MaterialeMapper
{
    public static Double getPrice(int mvariant, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "SELECT mvariant.idmvariant, materials.unit, materials.priceprunit, materials.description  FROM carport.mvariant INNER JOIN materials ON mvariant.idmaterial =materials.idmaterials WHERE idmvariant = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, mvariant);
                ResultSet rs = ps.executeQuery();
                System.out.println(ps);
                if (rs.next())
                {
                    System.out.println("true");
                    String price = rs.getString("materials.priceprunit");
                    double intprice = Double.parseDouble(price);
                    return intprice;
                } else
                {
                    System.out.println("false");
                    return null;
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
    }

    public static Integer getLength(int mvariant, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "SELECT mvariant.idmvariant, materials.unit, mvariant.length, materials.priceprunit, materials.description  FROM mvariant INNER JOIN materials ON mvariant.idmaterial =materials.idmaterials WHERE idmvariant = ?;";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, mvariant);
                ResultSet rs = ps.executeQuery();
                System.out.println(ps);
                if (rs.next())
                {
                    System.out.println("true");
                    String length = rs.getString("mvariant.length");
                    int intlength = Integer.parseInt(length);
                    return intlength;
                } else
                {
                    System.out.println("false");
                    return null;
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
    }

    public static String getUnit(int mvariant, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "SELECT mvariant.idmvariant, materials.unit, mvariant.length, materials.priceprunit, materials.description  FROM mvariant INNER JOIN materials ON mvariant.idmaterial =materials.idmaterials WHERE idmvariant = ?;";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, mvariant);
                ResultSet rs = ps.executeQuery();
                System.out.println(ps);
                if (rs.next())
                {
                    System.out.println("true");
                    String unit = rs.getString("materials.unit");
                    return unit;
                } else
                {
                    System.out.println("false");
                    return null;
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
    }

    public static String getDescription(int mvariant, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "SELECT mvariant.idmvariant, materials.unit, mvariant.length, materials.priceprunit, materials.description  FROM mvariant INNER JOIN materials ON mvariant.idmaterial =materials.idmaterials WHERE idmvariant = ?;";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, mvariant);
                ResultSet rs = ps.executeQuery();
                System.out.println(ps);
                if (rs.next())
                {
                    System.out.println("true");
                    String description = rs.getString("materials.description");
                    return description;
                } else
                {
                    System.out.println("false");
                    return null;
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
    }
}
