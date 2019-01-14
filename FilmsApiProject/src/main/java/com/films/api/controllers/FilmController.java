package com.films.api.controllers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.films.api.model.Film;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController

public class FilmController {

	private ArrayList<Film> films=new ArrayList<Film>();

    @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/films/add",method=RequestMethod.POST)
	public Object postFilm(HttpSession session,@RequestParam("title") String title,@RequestParam("type") String type,@RequestParam("releaseDate") String releaseDate,@RequestParam("director") String director) {

		try {

				ArrayList<Film> listF=fillFilmsInArray();
			Long newId = (Long) listF.get(listF.size()-1).getId();
			newId++;	
			SimpleDateFormat format = new SimpleDateFormat("dd/mm/YYYY");
			listF.add(new Film(newId, title,director,new Date(releaseDate),type));		
			fillTheFile(listF);

		}
		catch(Exception ex)
		{
			System.out.println("Exception :"+ex.getMessage());
		}

		return null;
	}
    @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/films/edit/{id}",method=RequestMethod.POST)
	public Object editFilm(HttpSession session,@PathVariable("id")  long id,@RequestParam("title") String title,@RequestParam("type") String type,@RequestParam("releaseDate") String releaseDate,@RequestParam("director") String director) {

		try {
			ArrayList<Film> myFilms=fillFilmsInArray();
		displayListFilm(myFilms);
		boolean founded=false;
		int i=0;
		for(Film x : myFilms)
		{
			if(x.getId()==(Long)id)
			{
				System.out.println("founded");
				founded=true;
				break;
			}
			else
			{
				i++;
			}
		}
		
		System.out.println("Film to edit : ");
		System.out.println(myFilms.get(i).toString());
		
		if(founded)
		{
			
			myFilms.get(i).setDirector(director);
			
			myFilms.get(i).setTitle(title);
			myFilms.get(i).setReleaseDate(new Date(releaseDate));
			myFilms.get(i).setType(type);
			fillTheFile(myFilms);
		}
		else
		{
			return "Not found";
		}

		}
		catch(Exception ex)
		{
			System.out.println("Exception :"+ex.getMessage());
		}

		return null;
	}

	//file to list
	public ArrayList<Film> fillFilmsInArray()
	{

		JSONParser parser = new JSONParser();
		
		ArrayList<Film> newFilmList=new ArrayList<Film>();
		
		try
		{
			Object obj = parser.parse(new FileReader(
				"/home/mustapha/Entretien/EvaluationJAVA/FilmsApiProject/src/main/resources/data/movies.json"));

			JSONArray jsonArray = (JSONArray) obj;
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
			Object list[]=  jsonArray.toArray();
			
			for(Object x : list)
			{ 
				JSONObject y = (JSONObject) x;
				Film f=new Film((Long)y.get("id"),(String)y.get("title"),(String)y.get("director"),new Date((String) y.get("releaseDate")),(String)y.get("type"));
				System.out.println("Filme : "+f.toString());
				newFilmList.add(f);
			}
			return newFilmList;
			
		}catch(Exception ex)
		{
			System.out.println("Exception : "+ex.getMessage());
			return null;
		}

	}


	public boolean fillTheFile(ArrayList<Film> films)
	{
		try (Writer writer = new FileWriter(
		"/home/mustapha/Entretien/EvaluationJAVA/FilmsApiProject/src/main/resources/data/movies.json")) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(films, writer);
			
			return true;
		}
		catch(Exception ex)
		{
			System.out.println("Exception : "+ex.getMessage());
		}
		return false;
	}

	
	public void displayListFilm(ArrayList<Film> filmsL)
	{
		System.out.println("Liste filmes : ");
		for(Film f : filmsL)
		{
			System.out.println("Filme : "+f.toString());
		}
	}
	
	
    @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/films/delete/{id}",method=RequestMethod.GET)
	public Object deleteFilm(HttpSession session,@PathVariable("id")  long id) {

		ArrayList<Film> myFilms=fillFilmsInArray();
		displayListFilm(myFilms);
		
		for(Film x : myFilms)
		{
			if(x.getId()==(Long)id)
			{
				System.out.println("founded");
				myFilms.remove(x);
				break;
			}
		}
		
		displayListFilm(myFilms);
		
		JSONObject response = new JSONObject();
		
		if(fillTheFile(myFilms))
		{
			return response.put("result", "success");
		}


		return response.put("result", "failed");



	}
    @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/films/getAll")
	public JSONArray testReadFile()
	{
		JSONParser parser = new JSONParser();
		try {

			Object obj = parser.parse(new FileReader(
		"/home/mustapha/Entretien/EvaluationJAVA/FilmsApiProject/src/main/resources/data/movies.json"));

			JSONArray jsonArray = (JSONArray) obj;

			return jsonArray;
		}
		catch(Exception ex)
		{
			System.out.println("Exception :"+ex.getMessage());
		}
		return null;
	}

}
