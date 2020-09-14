import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;

import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;

import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

//********************************************************************************//

public class SpotifyTracks {

    SpotifyManager spotifyManager = new SpotifyManager();

    String urlString;
    Track trackPagingItem;

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    public String getUrlString(){
        return urlString;
    }

    SpotifyApi spotifyApi = spotifyManager.getSpotifyApi();

    public void searchSong(String trackSearchName) {

        //Create a request to search for tracks in spotify
        final SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(trackSearchName)
                .build();

        try{
            //Create an instance of a Track of the paging class,
            //to retrieve info about paging objects (ie track name, artist id, name)
            //and execute req to search tracks

            //paging is a container for a set of objects, paging<t> gets info
            //abt paging objects

            //searchtracksrequest gets info about tracks
            final Paging<Track> trackPaging = searchTracksRequest.execute();

            //search for all items of the title you searched
            final Track[] trackPagingItems = trackPaging.getItems();

            //store first search result
            if( trackPagingItems.length != 0 ) {
                trackPagingItem = trackPagingItems[0];
            }
            else{
                return;
            }

            //get the uri of first search result
            String itemUri = trackPagingItem.getUri();
            urlString = itemUri;

            System.out.println("Item 0 uri: " + itemUri + " UrlString: " + itemUri);

        }
        catch( IOException | SpotifyWebApiException | ParseException e ){
            System.out.println( "Error: " + e.getMessage() );
        }

    }

}
