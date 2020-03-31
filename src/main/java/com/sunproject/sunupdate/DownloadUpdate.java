package com.sunproject.sunupdate;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 * <h3>Class for download a file.</h3>
 *
 * @author sundev79
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class DownloadUpdate {
    static Request req;
    static OkHttpClient client;
    static Call call;
    static Response res;
    static double percent;

    /**
     * method for download a file from the Internet.
     *
     * @param url  online path of the file source.
     * @param path path of the file destination.
     * @throws IOException return a IOException while trying to download file.
     */
    public static void download(String url, File path) throws IOException {
        req = new Request.Builder().url(url).build();
        client = new OkHttpClient();
        call = client.newCall(req);

        res = call.execute();
        if (res.isSuccessful()) {

            double FileSize = Double.parseDouble(res.headers().get("Content-Length"));
            double total = 0;
            int count;
            byte[] dataBytes = new byte[1024];

            File updateFile = new File(path + ".tmp");
            if (!updateFile.exists()) {
                updateFile.getParentFile().mkdirs();
                try {
                    updateFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try (OutputStream output = new FileOutputStream(updateFile);
                 InputStream dataStream = res.body().byteStream();
                 BufferedInputStream bfStream = new BufferedInputStream(dataStream)) {

                while ((count = bfStream.read(dataBytes)) != -1) {
                    total += count;
                    percent = (total / FileSize) * 100.0;
                    output.write(dataBytes, 0, count);
                    // System.out.println(Math.round(percent) + " %");
                }
            }

            FileUtils.moveFile(updateFile, new File(path.getParent() + "/" + updateFile.getName().replaceAll(".tmp", "")));
        }
    }

    public static double getPercent() {
        return percent;
    }
}
