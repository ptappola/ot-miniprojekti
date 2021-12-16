package ot.miniprojekti.ui;

import java.util.Scanner;
import ot.miniprojekti.domain.Blog;
import ot.miniprojekti.domain.Book;
import ot.miniprojekti.domain.Podcast;
import ot.miniprojekti.domain.Video;
import ot.miniprojekti.logic.BookmarkManager;

public class TextUserInterface {

    private BookmarkManager bookmarkManager;
    private Scanner reader;

    public TextUserInterface(BookmarkManager bookmarkManager, Scanner reader) {
        this.bookmarkManager = bookmarkManager;
        this.reader = reader;
    }

    public void start() {
        while (true) {
            printCommands();

            System.out.print("> ");
            String answer = reader.nextLine();

            if (answer.equals("1")) {
                addBookmark();
            } else if (answer.equals("2")) {
                printAll();
            } else if (answer.equals("3")) {
                System.out.print("Tagi: ");
                String tag = reader.nextLine();
                searchBooksByTagName(tag);
                searchVideosByTagName(tag);
                searchBlogsByTagName(tag);
                searchPodcastsByTagName(tag);
            } else if (answer.equals("4")) {
                printAll();
                markAsRead();
            } else if (answer.equals("5")) {
                printAll();
                System.out.println("Kirjoita poistettavan vinkin id");
                System.out.print("> ");
                String id = reader.nextLine();
                System.out.println(this.bookmarkManager.deleteBookmarkById(id));
            } else if (answer.equals("x") || answer.equals("")) {
                break;
            }
        }
    }

    private void printCommands() {
        System.out.println("Valitse toiminto:");
        System.out.println("[1] Lisää vinkki");
        System.out.println("[2] Tulosta vinkit");
        System.out.println("[3] Hae tagin perusteella");
        System.out.println("[4] Merkitse vinkki luetuksi");
        System.out.println("[5] Poista vinkki");
        System.out.println("[x] Sulje sovellus");
    }

    private void printAll() {
        System.out.println("Lukemattomat vinkit:");
        System.out.println("");
        printUnreadBooks();
        System.out.println("");
        printUnreadVideos();
        System.out.println("");
        printUnreadBlogs();
        System.out.println("");
        printUnreadPodcasts();
        System.out.println("");
        System.out.println("Luetut vinkit:");
        System.out.println("");
        printReadBooks();
        System.out.println("");
        printReadVideos();
        System.out.println("");
        printReadBlogs();
        System.out.println("");
        printReadPodcasts();
        System.out.println("");
    }

    private void addBookmark() {
        System.out.println("Valitse tyyppi:");
        System.out.println("[1] Lisää kirja");
        System.out.println("[2] Lisää video");
        System.out.println("[3] Lisää blogi");
        System.out.println("[4] Lisää podcast");

        System.out.print("> ");
        String answer = reader.nextLine();

        if (answer.equals("1")) {
            addBook();
        } else if (answer.equals("2")) {
            addVideo();
        } else if (answer.equals("3")) {
            addBlog();
        } else if (answer.equals("4")) {
            addPodcast();
        }

        addTag();
    }

    private void addBook() {
        System.out.print("Kirjoittaja: ");
        String author = reader.nextLine();
        System.out.print("Otsikko: ");
        String title = reader.nextLine();
        System.out.print("ISBN: ");
        String isbn = reader.nextLine();
        this.bookmarkManager.addBook(author, title, isbn);
    }

    private void addVideo() {
        System.out.print("Otsikko: ");
        String title = reader.nextLine();
        System.out.print("Url: ");
        String url = reader.nextLine();
        System.out.print("Kommentti: ");
        String comment = reader.nextLine();
        this.bookmarkManager.addVideo(title, url, comment);
    }

    private void addBlog() {
        System.out.print("Otsikko: ");
        String title = reader.nextLine();
        System.out.print("Kirjoittaja: ");
        String author = reader.nextLine();
        System.out.print("Url: ");
        String url = reader.nextLine();
        this.bookmarkManager.addBlog(title, author, url);
    }

    private void addPodcast() {
        System.out.print("Tekijän nimi: ");
        String name = reader.nextLine();
        System.out.print("Otsikko: ");
        String title = reader.nextLine();
        System.out.print("Kuvaus: ");
        String description = reader.nextLine();
        this.bookmarkManager.addPodcast(name, title, description);
    }

    private void addTag() {
        System.out.print("Tagit: ");
        String tag = reader.nextLine();
        this.bookmarkManager.addTag(tag);
    }

    private void printUnreadBooks() {
        System.out.println("Kirjat:");
        for (Book b : bookmarkManager.getUnreadBooks()) {
            System.out.println(b.toString());
        }
    }

    private void printUnreadVideos() {
        System.out.println("Videot:");
        for (Video v : bookmarkManager.getUnreadVideos()) {
            System.out.println(v.toString());
        }
    }

    private void printUnreadBlogs() {
        System.out.println("Blogit:");
        for (Blog b : bookmarkManager.getUnreadBlogs()) {
            System.out.println(b.toString());
        }
    }

    private void printUnreadPodcasts() {
        System.out.println("Podcastit:");
        for (Podcast p : bookmarkManager.getUnreadPodcasts()) {
            System.out.println(p.toString());
        }
    }

    private void printReadBooks() {
        System.out.println("Kirjat:");
        for (Book b : bookmarkManager.getReadBooks()) {
            System.out.println(b.toString());
        }
    }

    private void printReadVideos() {
        System.out.println("Videot:");
        for (Video v : bookmarkManager.getReadVideos()) {
            System.out.println(v.toString());
        }
    }

    private void printReadBlogs() {
        System.out.println("Blogit:");
        for (Blog b : bookmarkManager.getReadBlogs()) {
            System.out.println(b.toString());
        }
    }

    private void printReadPodcasts() {
        System.out.println("Podcastit:");
        for (Podcast p : bookmarkManager.getReadPodcasts()) {
            System.out.println(p.toString());
        }
    }

    private void searchBooksByTagName(String tag) {
        for (Book b : bookmarkManager.getBooksByTagName(tag)) {
            System.out.println(b.toString());
        }
    }

    private void searchVideosByTagName(String tag) {
        for (Video v : bookmarkManager.getVideosByTagName(tag)) {
            System.out.println(v.toString());
        }
    }

    private void searchBlogsByTagName(String tag) {
        for (Blog b : bookmarkManager.getBlogsByTagName(tag)) {
            System.out.println(b.toString());
        }
    }

    private void searchPodcastsByTagName(String tag) {
        for (Podcast p : bookmarkManager.getPodcastsByTagName(tag)) {
            System.out.println(p.toString());
        }
    }

    private void markAsRead() {
        System.out.println("Vinkin uudelleen merkitseminen korvaa entisen muistiinpanon");
        System.out.println("Kirjoita luetun vinkin id");
        System.out.print("> ");
        String id = reader.nextLine();
        System.out.println("Kirjoita muistiinpano tai jatka painamalla enter");
        System.out.print("> ");
        String comment = reader.nextLine();
        System.out.println(this.bookmarkManager.markBookmarkAsRead(id, comment));
    }
}
