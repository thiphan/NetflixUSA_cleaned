
public abstract class Media {
	protected String title;
	protected String year;
	protected String rating;
	protected String length;
	
	public Media(String title2, String year2, String rating2, String length2) {
		this.title = title2;
		this.year = year2;
		this.rating = rating2;
		this.length = length2;// TODO Auto-generated constructor stub
	}
	public Media() {
		this.title = "";
		this.year = "";
		this.rating = "";
		this.length = "";
	}

	public String getTitle() {
		return this.title;
	}
	public String getYear() {
		return this.year;
	}
	public String getRating() {
		return this.rating;
	}
	public String getLength() {
		return this.length;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String toString() {
		return title + " (" + year + ")" + " | " + rating + " , " + length;
	}

}
