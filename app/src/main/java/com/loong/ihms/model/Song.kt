package com.loong.ihms.model

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Song(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("name") val name: String,
    @SerializedName("artist") val artist: Artist,
    @SerializedName("album") val album: Album,
    @SerializedName("tag") val tag: List<Any>,
    @SerializedName("albumartist") val albumArtist: AlbumArtist,
    @SerializedName("filename") val filename: String,
    @SerializedName("track") val track: Int,
    @SerializedName("playlisttrack") val playListTrack: Int,
    @SerializedName("time") val time: Int,
    @SerializedName("year") val year: Int,
    @SerializedName("bitrate") val bitrate: Int,
    @SerializedName("rate") val rate: Int,
    @SerializedName("mode") val mode: String,
    @SerializedName("mime") val mime: String,
    @SerializedName("url") val url: String,
    @SerializedName("size") val size: Int,
    @SerializedName("mbid") val mbid: Any,
    @SerializedName("album_mbid") val albumMbid: Any,
    @SerializedName("artist_mbid") val artistMbid: String,
    @SerializedName("albumartist_mbid") val albumArtistMbid: String,
    @SerializedName("art") val art: String,
    @SerializedName("flag") val flag: Int,
    @SerializedName("preciserating") val preciseRating: Any,
    @SerializedName("rating") val rating: Any,
    @SerializedName("averagerating") val averageRating: Any,
    @SerializedName("playcount") val playCount: Int,
    @SerializedName("catalog") val catalog: Int,
    @SerializedName("composer") val composer: String,
    @SerializedName("channels") val channels: Any,
    @SerializedName("comment") val comment: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("language") val language: String,
    @SerializedName("replaygain_album_gain") val replayGainAlbumGain: String,
    @SerializedName("replaygain_album_peak") val replayGainAlbumPeak: String,
    @SerializedName("replaygain_track_gain") val replayGainTrackGain: String,
    @SerializedName("replaygain_track_peak") val replayGainTrackPeak: String,
    @SerializedName("genre") val genre: List<Any>
)