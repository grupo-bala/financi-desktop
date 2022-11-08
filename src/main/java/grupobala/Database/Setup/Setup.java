package grupobala.Database.Setup;

import java.sql.*;

public class Setup {

    public static Connection setup() {
        try {
            return Setup.createDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Connection createDB() throws SQLException {
        String query = "CREATE DATABASE financi;";

        Connection rootConection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/?user=postgres&password=postgres"
        );

        rootConection.createStatement().executeUpdate(query);

        rootConection.close();

        Connection connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/financi?user=postgres&password=postgres"
        );

        Statement statement = connection.createStatement();

        Setup.createTables(statement);

        statement.close();

        return connection;
    }

    private static void createTables(Statement statement) throws SQLException {
        String[] queries = {
            "CREATE TABLE usuario (id SERIAL PRIMARY KEY, nome TEXT NOT NULL, nomeusuario TEXT NOT NULL UNIQUE, senha TEXT NOT NULL, saldo FLOAT NOT NULL)",
            "CREATE TABLE categoria (id SERIAL PRIMARY KEY, nome TEXT NOT NULL UNIQUE)",
            "CREATE TABLE meta (id SERIAL PRIMARY KEY, titulo TEXT NOT NULL, idusuario INTEGER NOT NULL, valormeta FLOAT NOT NULL, valoratual FLOAT NOT NULL, datalimite DATE, valoridealpormes FLOAT NOT NULL, FOREIGN KEY (idusuario) REFERENCES usuario(id), UNIQUE(titulo, idusuario))",
            "CREATE TABLE aula (id SERIAL PRIMARY KEY , titulo TEXT NOT NULL UNIQUE, descricao TEXT NOT NULL, videourl TEXT NOT NULL, duracaosegundos INTEGER NOT NULL, thumbnailurl TEXT NOT NULL)",
            "CREATE TABLE aulaassistida (id SERIAL PRIMARY KEY, idusuario INTEGER NOT NULL, idaula INTEGER NOT NULL, UNIQUE(idusuario, idaula), FOREIGN KEY (idusuario) REFERENCES usuario(id), FOREIGN KEY (idaula) REFERENCES aula(id))",
            "CREATE TABLE movimentacao (id SERIAL PRIMARY KEY, idusuario INTEGER NOT NULL, valor FLOAT NOT NULL, data DATE NOT NULL, idcategoria INTEGER NOT NULL, titulo TEXT NOT NULL, entrada BOOLEAN NOT NULL, FOREIGN KEY (idusuario) REFERENCES usuario(id), FOREIGN KEY (idcategoria) REFERENCES categoria(id))",
            "INSERT INTO categoria(nome) VALUES ('comida'), ('roupa'), ('saúde'), ('entretenimento'), ('pagamentos'), ('outros')",
        };

        for (String query : queries) {
            statement.executeUpdate(query);
        }
    }
}
