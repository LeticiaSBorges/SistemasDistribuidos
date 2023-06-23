/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consumidor;

/**
 *
 * @author israe
 */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;
//import javax.jms.JMSException;
import classveiculo.Veiculos;

public class BancoH2 {
    String jdbcUrl = "jdbc:h2:tcp://localhost/~/test"; 
    String username = "sa"; 
    String password = "";
    
    public void start() {
        
        try {
            Connection connection = (Connection) DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "veiculo", null);

        if (!tables.next()) {
            
            String createTableQuery = "CREATE TABLE veiculo (id INT PRIMARY KEY AUTO_INCREMENT, nome_cliente VARCHAR(50), marca_modelo_veiculo VARCHAR(50), ano_modelo INT, valor_venda DOUBLE,cadastro VARCHAR(50))";
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableQuery);
            statement.close();
            System.out.println("Tabela veiculos criada com sucesso.");
        }
          
        System.out.println("Conexão com o banco de dados fechada.");
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao estabelecer a conexão com o banco de dados: " + e.getMessage());
        }
   }
    
    public void insert(Veiculos par){
        String nome = par.getCliente();
        String marca = par.getMarca();
        int ano = par.getAno();
        double valor = par.getValor();
        Date data = par.getData();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String dataString = formato.format(data);
               
        try {
            Connection connection = (Connection) DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            String insertQuery = "INSERT INTO veiculo (nome_cliente, marca_modelo_veiculo, ano_modelo, valor_venda,cadastro) VALUES (?,?, ?, ?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, marca);
            preparedStatement.setInt(3, ano);
            preparedStatement.setDouble(4, valor);
            preparedStatement.setString(5, dataString);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dados do veículo foram inseridos com sucesso no banco de dados.");
            }
            preparedStatement.close();
            connection.close();
            System.out.println("Conexão com o banco de dados fechada.");
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao estabelecer a conexão com o banco de dados: " + e.getMessage());
        }

    }
}


