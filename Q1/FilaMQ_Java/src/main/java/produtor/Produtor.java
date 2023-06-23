package produtor;

import classveiculo.Veiculos;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


public class Produtor {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL; // "vm://localhost";
    private static String subject = "MSG"; //Nome da fila da mensagem
    
    private static void envia(String nome, String marca, int ano, double valor, Date data) throws JMSException {
        
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(subject);
        MessageProducer producer = session.createProducer(destination);
        XStream xstream = new XStream(new StaxDriver());
        Veiculos mp = new Veiculos(nome, marca, ano, valor, data);
        TextMessage message = session.createTextMessage(xstream.toXML(mp));
        producer.send(message);
        System.out.println("Mensagem enviada!");
        connection.close();
    }
    public static void main(String[] args) throws JMSException {
    	
            Scanner scan = new Scanner(System.in);
            System.out.println("Cliente: ");
            String nome = scan.nextLine().toUpperCase();
            System.out.println("Veiculo: ");
            String marca = scan.nextLine().toUpperCase();
            System.out.println("Ano de fabricação: ");
            int ano = scan.nextInt();
            System.out.println("Valor do Veiculo: ");
            double valor = scan.nextDouble();
            scan.nextLine();
            Calendar calendar = Calendar.getInstance();
            Date data = calendar.getTime();
            
            envia(nome, marca, ano, valor,data);
            
                              
    } 
    
}
