package com.chainspower.mytab;



import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Description:
 * Date：2019/2/14-18:07
 * Author: cwh
 */
public class MusicWords {
    public static void main(String[] args) {
//        readLrc("D:\\AndroidProject\\UW\\app\\3814673.lrc");
//        readLrc("D:\\AndroidProject\\UW\\app\\3443588.lrc");

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入歌曲名(输入ss//退出)：");

        while (scanner.hasNextLine()) {
            String song = scanner.nextLine();
            if (song.equals("ss//")) {
                break;
            } else{
                startGETLrcForNet(song);
            }
        }
        scanner.close();
    }

    private static void readLrc(File musicFile) {
        if (!musicFile.exists()) {
            System.out.print("Music Lrc Not Exist");
        } else {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(musicFile));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    //[02:15.30]I'm a new soul〖带着一颗纯洁的心灵〗
                    String lrcStr;
                    int fontIndex = line.lastIndexOf("]");
                    int behindIndex = line.indexOf("〖");
                    //曲名
                    if (line.startsWith("[ti:")) {
                        int index = line.indexOf("[ti:");
                        lrcStr = line.substring(index + 4, fontIndex) + "\n";
                    } else if (line.startsWith("[ar:")) {
                        int index = line.indexOf("[ar:");
                        lrcStr = line.substring(index + 4, fontIndex) + "\n";
                    } else if (line.startsWith("[al:")) {
                        int index = line.indexOf("[al:");
                        lrcStr = line.substring(index + 4, fontIndex) + "\n";
                    } else if (behindIndex == -1) {
                        lrcStr = line.substring(fontIndex + 1) + "\n";
                    } else {
                        lrcStr = line.substring(fontIndex + 1, behindIndex) + "\n"
                                + line.substring(behindIndex + 1, line.length() - 1) + "\n";
                    }
                    System.out.println(lrcStr);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void readLrc(String path) {
        if (path == null || path.length() == 0) {
            System.out.print("Path is Null");
            return;
        }
        File musicFile = new File(path);
        readLrc(musicFile);
    }

    private static void startGETLrcForNet(final String song) {
        System.out.println("开始搜索...");
        new Thread() {
            @Override
            public void run() {
                String[] lrcUrls = readMusicId(song);
                if (lrcUrls == null || lrcUrls.length == 0) {
                    System.out.println("未搜索到歌曲");

                    System.out.println("请输入歌曲名(输入ss//退出)：");
                } else {
                    int i = 0;
                    while (i < lrcUrls.length) {
                        try {
                            readLrcForNet(lrcUrls[i]);
                            System.out.println("请输入歌曲名(输入ss//退出)：");
                            break;
                        }catch (IOException e) {
                            //e.printStackTrace();
                            i++;
                        }
                    }

                }

            }
        }.start();
    }

    //从网络根据歌名获取Music Info
    //https://api.bzqll.com/music/tencent/search?key=579621905&s=Five%20Hundred%20Miles&limit=100&offset=0&type=song
    private static String[] readMusicId(String song) {
        try {
            URL url = new URL("https://api.bzqll.com/music/tencent/search?key=579621905&s=" + URLEncoder.encode(song, "UTF-8") +
                    "&limit=10&offset=0&type=song");
            HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.connect();

            if (200 == httpURLConnection.getResponseCode()) {
                InputStream inputStream = httpURLConnection.getInputStream();
                byte[] bytes = new byte[1024];
                int len;
                StringBuffer stringBuffer = new StringBuffer();
                while ((len = inputStream.read(bytes)) != -1) {
                    String jsonStr = new String(bytes, 0, len, "UTF-8");
                    stringBuffer.append(jsonStr);
                }
                inputStream.close();
                Gson gson = new Gson();
                SongInfo songInfo = gson.fromJson(stringBuffer.toString(), SongInfo.class);
                List<SongInfo.Song> songs = songInfo.getSongs();
                String[] lrcs = new String[songs.size()];
                for (int i = 0; i < songs.size(); i++) {
                    lrcs[i] = songs.get(i).lrc;
                }
                return lrcs;
            } else {
                System.out.println("搜索歌曲错误\n" + httpURLConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("搜索歌曲错误");
        }
        return null;
    }

    private static void readLrcForNet(String lrcUrl) throws IOException {
        if (lrcUrl == null || lrcUrl.length() == 0) {
            System.out.println("歌词对应的Url不能为空");
            throw new IOException("获取歌词错误");
        }
        URL url = null;
        HttpsURLConnection httpURLConnection = null;
        url = new URL(lrcUrl.trim());
        httpURLConnection = (HttpsURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();
        if (200 == httpURLConnection.getResponseCode()) {
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            StringBuffer stringBuffer = new StringBuffer();
            while ((len = inputStream.read(bytes)) != -1) {
                String jsonStr = new String(bytes, 0, len, "UTF-8");
                stringBuffer.append(jsonStr);
            }
            inputStream.close();

            System.out.println("歌词为：\n" + StringEscapeUtils.unescapeXml(stringBuffer.toString()));
        } else {
            System.out.println("获取歌词错误");
            throw new IOException("获取歌词错误");
        }

    }

    private class SongInfo {


        /**
         * result : SUCCESS
         * code : 200
         * data : [{"id":"00262GDA2fW2H3",
         * "name":"Five Hundred Miles",
         * "time":205,
         * "singer":"Justin Timberlake/Carey Mulligan/Stark Sands",
         * "url":"https://api.bzqll.com/music/tencent/url?id=00262GDA2fW2H3&key=579621905",
         * "pic":"https://api.bzqll.com/music/tencent/pic?id=00262GDA2fW2H3&key=579621905",
         * "lrc":"https://api.bzqll.com/music/tencent/lrc?id=00262GDA2fW2H3&key=579621905"}]
         */

        private String result;
        private int code;
        @SerializedName("data")
        private List<Song> songs;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public List<Song> getSongs() {
            return songs;
        }

        public void setSongs(List<Song> songs) {
            this.songs = songs;
        }

        public class Song {
            /**
             * id : 00262GDA2fW2H3
             * name : Five Hundred Miles
             * time : 205
             * singer : Justin Timberlake/Carey Mulligan/Stark Sands
             * url : https://api.bzqll.com/music/tencent/url?id=00262GDA2fW2H3&key=579621905
             * pic : https://api.bzqll.com/music/tencent/pic?id=00262GDA2fW2H3&key=579621905
             * lrc : https://api.bzqll.com/music/tencent/lrc?id=00262GDA2fW2H3&key=579621905
             */

            private String id;
            private String name;
            private int time;
            private String singer;
            private String url;
            private String pic;
            private String lrc;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public String getSinger() {
                return singer;
            }

            public void setSinger(String singer) {
                this.singer = singer;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getLrc() {
                return lrc;
            }

            public void setLrc(String lrc) {
                this.lrc = lrc;
            }

            @Override
            public String toString() {
                return "Song{" +
                        "id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        ", time=" + time +
                        ", singer='" + singer + '\'' +
                        ", url='" + url + '\'' +
                        ", pic='" + pic + '\'' +
                        ", lrc='" + lrc + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "SongInfo{" +
                    "result='" + result + '\'' +
                    ", code=" + code +
                    ", songs=" + songs +
                    '}';
        }
    }


}
