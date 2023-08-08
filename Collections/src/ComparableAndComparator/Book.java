package ComparableAndComparator;

class Book implements Comparable<Book>{

	private String title;
	private int year;
	public Book(String title, int year) {
		this.title = title;
		this.year = year;
	}
	
	public int compareTo(Book other) {
		return this.title.compareTo(other.title);
	}
	
	public String toString() {
		return title + "(" + year +")";
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}
}
