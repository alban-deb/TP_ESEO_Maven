package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import com.config.ConnectionBdd;
import com.dto.Ville;








public class VilleFranceDAO {
	private String tableName = "ville_france";
	
public boolean creationVille(Ville ville) {
		
		
		boolean init = false;
		try {
			Connection conn = ConnectionBdd.getInstance();
			
			String query = "INSERT INTO " + tableName ;
			query += "(Code_commune_INSEE,Nom_commune,Code_postal,Libelle_acheminement,Ligne_5, Latitude, Longitude)";
			query += " VALUES(?,?,?,?,?,?,?)";
			
			PreparedStatement prepare = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			prepare.setString(1, ville.getCodeCommuneINSEE());
			prepare.setString(2, ville.getNomCommune());
			prepare.setString(3, ville.getCodePostal());
			prepare.setString(4, ville.getLibelleAcheminement());
			prepare.setString(5, ville.getLigne5());
			prepare.setString(6, ville.getLatitude());
			prepare.setString(7, ville.getLongitude());
			
			prepare.executeUpdate();
			init = true;
			
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return init;
	}

	public Ville [] listeVilles() {
	    Ville [] villes = null;
		
		try {
			Connection conn = ConnectionBdd.getInstance();
			String query = "SELECT * FROM "+tableName;
	
			
			PreparedStatement prepare = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet res = prepare.executeQuery();
			res.last();
			int size = res.getRow();
			res.beforeFirst();
			
			villes = new Ville [size];
			
			int i = 0;
			while (res.next()) {
				Ville v = new Ville();
				
				v.setCodeCommuneINSEE(res.getString("Code_commune_INSEE"));
				v.setNomCommune(res.getString("Nom_commune"));
				v.setCodePostal(res.getString("Code_postal"));
				v.setLibelleAcheminement(res.getString("Libelle_acheminement"));
				v.setLigne5(res.getString("Ligne_5"));
				v.setLatitude(res.getString("Latitude"));
				v.setLongitude(res.getString("Longitude"));
				
				
				villes[i] = v;
				i++;
			}
			
			res.close();
			prepare.close();
			
			
			
		}catch (Exception e) {
		      e.printStackTrace();
	
	
	  }
		return villes;
	}

	public void supprimerVille(String codeCommune) {
		try {
			Connection conn = ConnectionBdd.getInstance();
			String query = "DELETE FROM "+ tableName +" WHERE Code_commune_INSEE = '" + codeCommune + "'";
	
			PreparedStatement prepare = conn.prepareCall(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			prepare.executeUpdate();

			prepare.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public boolean update(Ville ville, String codeCommune) {
		// TODO Auto-generated method stub
		boolean init = false;
		try {
			Connection connect = ConnectionBdd.getInstance();

			String query = "UPDATE " + tableName;
			query += " SET Code_commune_INSEE = ?, Nom_commune = ?, Code_postal = ?, Libelle_acheminement = ?, Ligne_5 = ?, Latitude = ?, Longitude = ? ";
			query +=  "WHERE Code_commune_INSEE = '" + codeCommune + "'";
			
			
			PreparedStatement prepare = connect.prepareCall(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			prepare.setString(1, ville.getCodeCommuneINSEE());
			prepare.setString(2, ville.getNomCommune());
			prepare.setString(3, ville.getCodePostal());
			prepare.setString(4, ville.getLibelleAcheminement());
			prepare.setString(5, ville.getLigne5());
			prepare.setString(6, ville.getLatitude());
			prepare.setString(7, ville.getLongitude());
			System.out.println("QUERY : "+query);
			prepare.executeUpdate();

			prepare.close();
			init = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return init;
	}


	public Ville[] gestionFiltre(String filtre, String value) {
		Ville[] villes = null;
		try {
			Connection conn = ConnectionBdd.getInstance();
			String query = "SELECT * FROM "+tableName;
			query += " WHERE "+ filtre +" = '"+value+"'";
			
			PreparedStatement prepare = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	
			
			
			ResultSet res = prepare.executeQuery();
			res.last();
			int size = res.getRow();
			res.beforeFirst();
			
			villes = new Ville [size];
			
			int i = 0;
			while (res.next()) {
				Ville v = new Ville();
				
				v.setCodeCommuneINSEE(res.getString("Code_commune_INSEE"));
				v.setNomCommune(res.getString("Nom_commune"));
				v.setCodePostal(res.getString("Code_postal"));
				v.setLibelleAcheminement(res.getString("Libelle_acheminement"));
				v.setLigne5(res.getString("Ligne_5"));
				v.setLatitude(res.getString("Latitude"));
				v.setLongitude(res.getString("Longitude"));
				
				
				villes[i] = v;
				i++;
			}
			
			res.close();
			prepare.close();
			
			
			
		}catch (Exception e) {
		      e.printStackTrace();
	
	
	  }
		return villes;
	
	}

}
