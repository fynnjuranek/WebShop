package de.leuphana.webshop.connector.dbconnector;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import de.leuphana.shop.structure.Article;
import de.leuphana.shop.structure.Book;
import de.leuphana.shop.structure.BookCategory;
import de.leuphana.shop.structure.CD;

public class ArticleDbConnector {
	
	Connection dbConnection;
	
	public ArticleDbConnector() {
		dbConnection = getConnection();
	}
	
	private Connection getConnection() {
		if (dbConnection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException exception) {
				System.out.println("Driver failed " + exception.getMessage());
			}
			try {
				dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webmo", "webmo", "webmo");
			} catch (SQLException e) {
				System.out.println("Connection failed " + e.getMessage());
			}
		}
		return dbConnection;
	}
	
	public Set<Article> fetchArticles() throws SQLException {
		// TODO
		Connection c = dbConnection;
		Statement stmnt =  c.createStatement();
		String query = "SELECT id, name, price, category, artist, manufactor FROM article";
		ResultSet rs = stmnt.executeQuery(query);
		Set<Article> articles = new HashSet<>();
		try {
			rs = stmnt.executeQuery(query);
			while (rs.next()) {
				Integer articleId = rs.getInt("id");
				String articleName = rs.getString("name");
				float articlePrice = rs.getFloat("price");
				if (rs.getObject("category") != null) {
					BookCategory articleCategory = BookCategory.valueOf(rs.getString("category"));
					Book book = new Book();
					book.setName(articleName);
					book.setPrice(articlePrice);
					book.setBookCategory(articleCategory);
					articles.add(book);
				} else if (rs.getObject("artist") != null){
					CD cd = new CD();
					cd.setName(articleName);
					cd.setArtist(rs.getString("artist"));
					cd.setManufactor(rs.getString("manufactor"));
					cd.setPrice(articlePrice);
					articles.add(cd);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException e) { e.printStackTrace();}
		    }
		    if (stmnt != null) {
		        try {
		            stmnt.close();
		        } catch (SQLException e) { e.printStackTrace();}
		    }
		    if (c != null) {
		        try {
		            c.close();
		        } catch (SQLException e) { e.printStackTrace();}
		    }
		}
		
		return articles;
	}
}
