import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;

//********************************************************************************//

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

public class YoutubeVideos {

    //YouTube youtubeService = YoutubeManager.getService();

    public YoutubeVideos() throws GeneralSecurityException, IOException {
    }

    String[] ytPlaylistVideoTitle;
    int noVideos;

    public String[] getYtPlaylistVideoTitle(){
        return ytPlaylistVideoTitle;
    }

    public int getNoVideos(){
        return noVideos;
    }

    public void accessYtPlaylistVideos(String ytPlaylistId, YouTube youtubeService) throws GeneralSecurityException, IOException{

        //get video titles
        YouTube.PlaylistItems.List itemsRequest = youtubeService.playlistItems()
                .list(Collections.singletonList("snippet,contentDetails"));
        PlaylistItemListResponse itemsResponse = itemsRequest.setMaxResults(50L)
                .setPlaylistId(/*"PLTc36CiFknvx0a1-hRR1xPyB3RDiVkJ9v"*/ ytPlaylistId)
                .setFields("items(snippet/title)")
                .execute();

        List<PlaylistItem> playlistVideoTitles = new ArrayList<PlaylistItem>(itemsResponse.getItems());

        /*
        System.out.println("Items response: " + itemsResponse);
        System.out.println("Items request video list " + playlistVideoTitles);
        System.out.println("Items request video list first item " + playlistVideoTitles.get(0).getSnippet().getTitle());
         */

        //the no of vids in a yt playlist is the length of the playlist
        noVideos = playlistVideoTitles.size();
        ytPlaylistVideoTitle = new String[noVideos];

        for( int i=0; i<noVideos; i++ ){

            ytPlaylistVideoTitle[i] = playlistVideoTitles.get(i).getSnippet().getTitle();

        }

        for( int i=0; i<noVideos; i++ ){

            System.out.println("Items request video list " + ytPlaylistVideoTitle[i]);

        }

    }

}
