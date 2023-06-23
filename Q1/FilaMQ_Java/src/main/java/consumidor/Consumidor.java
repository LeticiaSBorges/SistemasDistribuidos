package consumidor;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import classveiculo.Veiculos;



public class Consumidor {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL; 
    private static String subject = "MSG"; 
    public static Veiculos veiculo;
    
    private static void consumir() throws JMSException {
        
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection1 = (Connection) connectionFactory.createConnection();
        connection1.start();
        Session session = connection1.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(subject);
        MessageConsumer consumer = session.createConsumer(destination);
        Message message = consumer.receive(15000);

        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            
            System.out.println("\n\nRecebi da Fila a msg = '" + textMessage.getText() + "'");
            if (textMessage.getText().toLowerCase().contains("xml")) {
            	recuperaMensagemObjeto(textMessage);
                
            }
        } else {
            System.out.println("** SEM MENSAGENS **  ");
        }
        
        consumer.close();
        session.close();
        connection1.close();
    }
    
    private static void recuperaMensagemObjeto(TextMessage msg) throws JMSException {
    
    	TextMessage tmsg = (TextMessage)msg;
    	XStream xstream = new XStream(new StaxDriver());
    	xstream.addPermission(AnyTypePermission.ANY);
        
        veiculo = (Veiculos) xstream.fromXML(tmsg.getText());
        
        
        System.out.println("Cliente: " + veiculo.getCliente());
        System.out.println("Marca e Modelo: " + veiculo.getMarca());
        System.out.println("Ano Fabricação: " + veiculo.getAno());
        System.out.println("Valor: " + veiculo.getValor());
        System.out.println("Data: " + veiculo.getData());
    	 
    }
    
    
    
    public static void main(String[] args) throws JMSException {     
        BancoH2 banco = new BancoH2();
        banco.start(); // ROdar 1 vez quando a tabela Veiculos ainda não existe
        while (true) {
            consumir();
          if (veiculo != null) {
            banco.insert(veiculo);
            veiculo = null;
          }
 
        }       
    }
   
}




