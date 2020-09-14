import java.util.Scanner;

public class Input {


    String spotifyPlaylistName;

    String ytPlaylistName;

    Scanner scanner = new Scanner(System.in);

    public void inputSpotifyPlaylistName(){

        spotifyPlaylistName = scanner.nextLine();

    }

    public void inputYtPlaylistName(){

        ytPlaylistName = scanner.nextLine();

    }

    public String getSpotifyPlaylistName() {
        return spotifyPlaylistName;
    }

    public String getYtPlaylistName() {
        return ytPlaylistName;
    }

}
