import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;

import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.requests.data.playlists.CreatePlaylistRequest;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.requests.data.playlists.AddItemsToPlaylistRequest;

import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

//********************************************************************************//

public class SpotifyPlaylists {

    SpotifyManager spotifyManager = new SpotifyManager();
    SpotifyApi spotifyApi = spotifyManager.getSpotifyApi();

    //String tempName;

    private final String userId = spotifyManager.getUserId();
    //private final String name = spotifyManager.getName();
    //private final String name = tempName;

    String playlistId;
    String urlString;

    public String getPlaylistId(){
        return playlistId;
    }


    //create a new spotify playlist
    public void createNewPlaylist( String name ){

        //Create a request to make empty playlist for a Spotify user
        final CreatePlaylistRequest createPlaylistRequest = spotifyApi.createPlaylist(userId, name)
                .build();

        //try lets u write code that's tested for errors while being executed
        try{

            //Playlist lets you get info about playlist objects
            //by building instances from this class
            //.execute creates a new playlist
            final Playlist playlist = createPlaylistRequest.execute();

            playlistId = playlist.getId();

            System.out.println( "Name: " + playlist.getName() );
            System.out.println( "ID createNewPlaylist playlistID: " + playlistId);

        }
        //if an error in try block occurs, execute code in catch statement
        catch(IOException | SpotifyWebApiException | ParseException e){

            e.printStackTrace();

        }

    }

    //add the track to the spotify playlist
    public void addToPlaylist(String playlistId, String urlString){

        final String[] uris = new String[]{urlString};

        final AddItemsToPlaylistRequest addItemsToPlaylistRequest = spotifyApi
                .addItemsToPlaylist(playlistId, uris)
                .build();

        System.out.println( "ID addToPlaylist: " + playlistId );

        try {

            final SnapshotResult snapshotResult = addItemsToPlaylistRequest.execute();

        }
        catch ( IOException | SpotifyWebApiException | ParseException e ) {

            System.out.println("Error: " + e.getMessage());

        }

    }


}
