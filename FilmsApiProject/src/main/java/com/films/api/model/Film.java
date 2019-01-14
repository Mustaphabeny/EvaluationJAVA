package com.films.api.model;

import java.util.Date;

public class Film {

		private Long id;
		private String title;
		private String director;
		private Date releaseDate;
		private String type;

		
		@Override
		public String toString() {
			return "Film [id=" + id + ", title=" + title + ", director=" + director + ", releaseDate  = "+ releaseDate+", type=" + type + "]";
		}



		public Long getId() {
			return id;
		}



		public void setId(Long id) {
			this.id = id;
		}



		public String getTitle() {
			return title;
		}



		public void setTitle(String title) {
			this.title = title;
		}



		public String getDirector() {
			return director;
		}



		public void setDirector(String director) {
			this.director = director;
		}



		public Date getReleaseDate() {
			return releaseDate;
		}



		public void setReleaseDate(Date releaseDate) {
			this.releaseDate = releaseDate;
		}



		public String getType() {
			return type;
		}



		public void setType(String type) {
			this.type = type;
		}



		public Film(Long id, String title, String director, Date releaseDate, String type) {
			super();
			this.id = id;
			this.title = title;
			this.director = director;
			this.releaseDate = releaseDate;
			this.type = type;
		}
		
		
		
	
		
		
		
}
