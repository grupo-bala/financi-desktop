package grupobala.Database.Setup;

import java.sql.*;

public class Setup {

    public static Statement setup() {
        try {
            Statement statement = Setup.createDB();

            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Statement createDB() throws SQLException {
        String query = "CREATE DATABASE financi;";

        DriverManager
            .getConnection(
                "jdbc:postgresql://localhost:5432/?user=postgres&password=postgres"
            )
            .createStatement()
            .executeUpdate(query);

        Statement statement = DriverManager
            .getConnection(
                "jdbc:postgresql://localhost:5432/financi?user=postgres&password=postgres"
            )
            .createStatement();

        Setup.createTables(statement);

        return statement;
    }

    private static void createTables(Statement statement) throws SQLException {
        String[] queries = {
            "CREATE TABLE usuario (id SERIAL PRIMARY KEY, nome TEXT NOT NULL, nomeUsuario TEXT UNIQUE NOT NULL, senha TEXT NOT NULL, rendaFixa FLOAT NOT NULL)",
            "CREATE TABLE categoria (id SERIAL PRIMARY KEY, nome TEXT NOT NULL)",
            "CREATE TABLE meta (id SERIAL PRIMARY KEY, titulo TEXT NOT NULL, idUsuario INTEGER NOT NULL, valorMeta FLOAT NOT NULL, valorAtual FLOAT NOT NULL, dataLimite DATE, FOREIGN KEY (idUsuario) REFERENCES usuario(id))",
            "CREATE TABLE aula (id SERIAL PRIMARY KEY , titulo TEXT NOT NULL, descricao TEXT NOT NULL, videoURL TEXT NOT NULL, duracaoSegundos INTEGER NOT NULL, thumbnailURL TEXT NOT NULL)",
            "CREATE TABLE aulaAssistida (id SERIAL PRIMARY KEY, idUsuario INTEGER NOT NULL, idAula INTEGER NOT NULL, UNIQUE(idUsuario, idAula), FOREIGN KEY (idUsuario) REFERENCES usuario(id), FOREIGN KEY (idAula) REFERENCES aula(id))",
            "CREATE TABLE movimentacao (id SERIAL PRIMARY KEY, idUsuario INTEGER NOT NULL, valor FLOAT NOT NULL, data DATE NOT NULL, idCategoria INTEGER NOT NULL, titulo TEXT NOT NULL, descricao TEXT NOT NULL, entrada BOOLEAN NOT NULL, FOREIGN KEY (idUsuario) REFERENCES usuario(id), FOREIGN KEY (idCategoria) REFERENCES categoria(id))",
        };

        for (String query : queries) {
            statement.executeUpdate(query);
        }
    }
}
