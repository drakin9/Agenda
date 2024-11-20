package Controller;

import View.Inicio_GUI;
import static View.Inicio_GUI.email_txt;
import static View.Inicio_GUI.nome_txt;
import static View.Inicio_GUI.tel_txt;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Funcoes_DAO {

    public static int cod;
    public static String nom;
    public static String em;
    public static long tel;

    static String url = "jdbc:mysql://localhost:3307/APRENDER"; // enderço do BD
    static String username = "root"; //nome de um usuário de seu BD
    static String password = "";  // senha do BD

    public static void cadastrar() {
        //-->inicio
        nom = nome_txt.getText(); // recebendo o nome
        em = email_txt.getText(); // recebendo o email
        tel = Long.valueOf(tel_txt.getText());// recebendo o telefone

        Model.Conecta_DB.carregaDriver();

        try {

            Connection con = null;

            try {
                con = (Connection) DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {

                Logger.getLogger(Inicio_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Recebendo os dados a serem inseridos na tabela
            String sql = "INSERT INTO cliente(cli_nome,cli_email,cli_tel) values('" + nom + "','" + em + "','" + tel + "')";

            try { // Tratamento de Erros para inserção

                // Criando varialvel que executara a inserção
                PreparedStatement inserir = (PreparedStatement) con.prepareStatement(sql);
                inserir.execute(); // Executando a inserção

                JOptionPane.showMessageDialog(null, "\nInserção realizada com sucesso!!!\n", "", -1);
                nome_txt.setText("");
                email_txt.setText("");
                tel_txt.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "\nErro na inserção!", "ERRO!", 0);
            }

        } catch (NumberFormatException erro) {
            // Tratamento de erro caso o usuario não digite o telefone corretamente
            JOptionPane.showMessageDialog(null, "Digite os dados corretamente", "ERRO", 0);
            tel_txt.setText("");
        }

    }

    //-->fim
}
