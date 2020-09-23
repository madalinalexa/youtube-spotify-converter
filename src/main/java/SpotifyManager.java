import com.wrapper.spotify.SpotifyApi;
import java.util.*;

//********************************************************************************//

public class SpotifyManager {

    private static final String accessToken = "";
    private static final String userId = "";

    public SpotifyApi getSpotifyApi(){
        //SpotifyApi class gives access to spotify web api
        //.builder is a class that builds spotifyApi instances
        final SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(accessToken)
                .build();

        return spotifyApi;
    }

    public String getAccessToken(){
        return accessToken;
    }

    public String getUserId(){
        return userId;
    }


}
