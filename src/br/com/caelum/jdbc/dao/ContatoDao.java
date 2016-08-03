package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sun.util.resources.CalendarData;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Contato;

public class ContatoDao {
	 
	private Connection connection;
	 
	public ContatoDao() throws ClassNotFoundException {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Contato contato) {
	     String sql = "insert into contatos " +
	             "(nome,email,endereco,dataNascimento)" +
	             " values (?,?,?,?)";
	 
	     try {
	         // prepared statement para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         stmt.setString(1,contato.getNome());
	         stmt.setString(2,contato.getEmail());
	         stmt.setString(3,contato.getEndereco());
	         stmt.setDate(4, new Date(
	                 contato.getDataNascimento().getTimeInMillis()));
	 
	         // executa
	         stmt.execute();
	         stmt.close();
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	 }
	
	public List<Contato> getLista(){
		
		String sql = "select * from contatos";
		
		try{
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			List<Contato> contatos = new ArrayList<Contato>();
			
			while(rs.next()){
				Contato contato = new Contato();
				
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				
				Calendar date = Calendar.getInstance();
				date.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(date);
				
				contatos.add(contato);
			}
			
			rs.close();
			stmt.close();
			
			return contatos;
			
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
