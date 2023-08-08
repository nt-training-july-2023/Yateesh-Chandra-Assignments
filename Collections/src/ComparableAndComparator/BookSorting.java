package ComparableAndComparator;

import java.util.*;
public class BookSorting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Book> books = new ArrayList<>(); 
		books.add(new Book("Java Programming",2020));
		books.add(new Book("Python CookBook",2018));
		books.add(new Book("C++ primer",2019));
		books.add(new Book("Javascript Basics",2021));
		
		Collections.sort(books);
		
		System.out.println("Sorted by natural ordering (book title) :");
		for(Book book : books) {
			System.out.println(book);
		}
		
		TitleComparator titleComparator = new TitleComparator();
		Collections.sort(books, titleComparator);
		
		System.out.println("\nSorted using custom comparator (book title):");
		
		for(Book book : books) {
			System.out.println(book);
		}
	}

}
