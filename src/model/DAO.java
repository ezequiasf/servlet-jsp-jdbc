package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * The Class DAO.
 */
public class DAO {
	
	/** The driver. */
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	/** The url. */
	private String url = "jdbc:sqlserver://localhost:1434;databaseName=agenda;user=sa;password=think;";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	public Connection conectar() {
		Connection con = null;	
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url);
		}catch(Exception ex) {
			System.err.println("Erro:"+ex.getMessage());
		}
		return con;
	}
	
	/**
	 * Salvar contato.
	 *
	 * @param nome the nome
	 * @param telefone the telefone
	 * @param email the email
	 */
	public void salvarContato (String nome, String telefone,String email) {
		String inserir = "insert into contato (nome,telefone,email) values(?,?,?)";
		
		try {
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement(inserir);
			ps.setString(1,nome);
			ps.setString(2, telefone);
			ps.setString(3, email);
			ps.executeUpdate();
			con.close();
		}catch(Exception ex) {
			System.err.println("Erro:"+ex.getMessage());
		}
	}
	
	/**
	 * Procura contato.
	 *
	 * @param id the id
	 * @return the string[]
	 */
	public String[] procuraContato(String id) {
		String query = "select * from contato where id=?";
		String[] contato = new String[4];
		
		try {
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				contato[0] = rs.getString(1);
				contato[1] = rs.getString(2);
				contato[2] = rs.getString(3);
				contato[3] = rs.getString(4);
			}
			con.close();
		}catch(Exception e) {
			System.err.println("Erro"+e.getMessage());
		}
		return contato;
	}
	
	/**
	 * Altera contato.
	 *
	 * @param id the id
	 * @param nome the nome
	 * @param tel the tel
	 * @param email the email
	 */
	public void alteraContato (String id,String nome,String tel, String email) {
		String update = "update contato set nome=?,telefone=?,email=? where id=?";
		
		try {
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement(update);
			ps.setString(1, nome);
			ps.setString(2, tel);
			ps.setString(3, email);
			ps.setString(4, id);
			ps.executeUpdate();
			con.close();
		}catch(Exception e) {
			System.err.println("Erro:"+e.getMessage());
		}
	}
	
	/**
	 * Listar contatos.
	 *
	 * @return the array list
	 */
	public ArrayList<String[]> listarContatos(){
		String query = "select * from contato order by nome";
		ArrayList<String[]> contatos = new ArrayList<>();
		try {
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String tel = rs.getString(3);
				String email = rs.getString(4);
				contatos.add(new String[] {id, nome, tel, email});
			}
			con.close();
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
		return contatos;
	}
	
	/**
	 * Deletar contato.
	 *
	 * @param id the id
	 */
	public void deletarContato(String id) {
		String deletar = "delete from contato where id=?";
		
		try {
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement(deletar);
			ps.setString(1, id);
			ps.executeUpdate();
			con.close();
		}catch(Exception ex) {
			System.err.println("Erro:"+ex.getMessage());
		}
	}

}
