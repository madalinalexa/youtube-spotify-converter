import com.google.api.services.youtube.YouTube;

import java.io.IOException;

import java.security.GeneralSecurityException;

public class SpotifyApplication {

    public static void main(String[] args) throws GeneralSecurityException, IOException {

        YoutubeManager youtubeManager = new YoutubeManager();
        YoutubePlaylists youtubePlaylists = new YoutubePlaylists();
        YoutubeVideos youtubeVideos = new YoutubeVideos();
        Instructions instructions = new Instructions();
        Input input = new Input();
        YouTube youtubeService = YoutubeManager.getService();
        SpotifyManager spotifyManager = new SpotifyManager();
        SpotifyPlaylists spotifyPlaylists = new SpotifyPlaylists();
        SpotifyTracks spotifyTracks = new SpotifyTracks();

        instructions.inputSpotifyPlaylistName();
        input.inputSpotifyPlaylistName();
        String spotifyPlaylistName = input.getSpotifyPlaylistName();

        spotifyPlaylists.createNewPlaylist(spotifyPlaylistName);
        String playlistId = spotifyPlaylists.getPlaylistId();

        instructions.inputYtPlaylistName();
        input.inputYtPlaylistName();

        String ytPlaylistTitle = input.getYtPlaylistName();
        youtubePlaylists.accessYtPlaylist(youtubeService, ytPlaylistTitle);

        String ytPlaylistId = youtubePlaylists.getYtPlaylistId();

        youtubeVideos.accessYtPlaylistVideos(ytPlaylistId, youtubeService);
        String[] ytPlaylistVideoTitle = youtubeVideos.getYtPlaylistVideoTitle();
        int noVideos = youtubeVideos.getNoVideos();

        for( int i=0; i<noVideos; i++ ){

            spotifyTracks.setUrlString("");

            String trackSearchName = ytPlaylistVideoTitle[i];

            spotifyTracks.searchSong(trackSearchName);

            String urlString = spotifyTracks.getUrlString();

            spotifyPlaylists.addToPlaylist(playlistId, urlString);

        }

    }

}
