import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;

//********************************************************************************//

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

public class YoutubePlaylists {

    public YoutubePlaylists() throws GeneralSecurityException, IOException {
    }

    String ytPlaylistId;
    String ytPlaylistTitle;

    public String getYtPlaylistId(){
        return ytPlaylistId;
    }

    public String getYtPlaylistTitle(){
        return ytPlaylistTitle;
    }

    public void accessYtPlaylist(YouTube youtubeService, String ytPlaylistTitle) throws GeneralSecurityException, IOException {

        // Define and execute the API request, get list of all my playlists
        YouTube.Playlists.List playlistRequest = youtubeService.playlists()
                .list(Collections.singletonList("snippet,contentDetails"));

        PlaylistListResponse playlistResponse = playlistRequest.setMaxResults(50L)
                .setMine(true)
                //.setPageToken("CAoQAA")
                .execute();

        List<Playlist> playlistList = new ArrayList<Playlist>(playlistResponse.getItems());

        //Search through list of playlists for a matching name to the one searched,
        //if found a match then get its id
        for( int i=0; i<playlistList.size(); i++ ){

            if(ytPlaylistTitle.equals( playlistList.get(i).getSnippet().getTitle() ) ){
                ytPlaylistId = playlistList.get(i).getId();
            }
            /*else{
                System.out.println("Type a valid playlist name");
                System.exit(0);
            }*/

        }

        System.out.println("YT Playlist ID: " + ytPlaylistId);
        System.out.println("YT Playlist Title: " + ytPlaylistTitle);

    }

}
