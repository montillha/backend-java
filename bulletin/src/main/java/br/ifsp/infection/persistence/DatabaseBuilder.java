package br.ifsp.infection.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class DatabaseBuilder {
    public static void main(String[] args) throws SQLException, IOException {
        dropDatabaseIfExists();
        createTable();
        populate();
        System.out.println("\nAll set =)");
    }

    private static void dropDatabaseIfExists() throws IOException {
        final Path path = Paths.get("database.db");
        if(Files.exists(path)) {
            Files.delete(path);
            System.out.println("Deleting existing database...");
        }
    }

    private static void createTable() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        Statement stmt = connection.createStatement();
        String sql = """
                CREATE TABLE bulletins(
                    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                    city TEXT NOT NULL,
                    state TEXT NOT NULL,
                    infected INTEGER NOT NULL,
                    deaths INTEGER NOT NULL,
                    icu_ratio REAL NOT NULL,
                    date TEXT NOT NULL
                )
                """;
        System.out.println("Creating table: \n \n" + sql);
        stmt.executeUpdate(sql);
        stmt.close();
        connection.close();
    }

    private static void populate() throws SQLException {
        final var connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        final var stmt = connection.createStatement();

        final String sql = """
                INSERT INTO bulletins (city, state, infected, deaths, icu_ratio, date) 
                VALUES ('%s', '%s', %d, '%d', %.2f, '%s')
                """;

        System.out.println("Populating table << bulletin >> ...");
        stmt.addBatch(String.format(Locale.US, sql, "Araraquara", "São Paulo", 104, 2, 10.2, "2022-01-03"));
        stmt.addBatch(String.format(Locale.US, sql, "São Carlos", "São Paulo", 230, 3, 40.9, "2022-01-03"));
        stmt.addBatch(String.format(Locale.US, sql, "Ribeirão Preto", "São Paulo", 450, 4, 55.3, "2022-01-05"));
        stmt.addBatch(String.format(Locale.US, sql, "Américo Brasiliense", "São Paulo", 58, 0, 5.7, "2022-02-17"));
        stmt.addBatch(String.format(Locale.US, sql, "Araraquara", "São Paulo", 196, 1, 20.8, "2022-04-29"));
        stmt.addBatch(String.format(Locale.US, sql, "São Carlos", "São Paulo", 110, 2, 25.1, "2022-04-29"));
        stmt.addBatch(String.format(Locale.US, sql, "São Carlos", "São Paulo", 85, 0, 10.0, "2022-06-02"));
        stmt.addBatch(String.format(Locale.US, sql, "Itajubá", "Minas Gerais", 40, 4, 67.9, "2022-02-02"));
        stmt.executeBatch();

        stmt.close();
        connection.close();
    }
}
