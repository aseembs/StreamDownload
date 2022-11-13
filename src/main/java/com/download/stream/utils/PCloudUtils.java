package com.download.stream.utils;

import com.pcloud.sdk.ApiClient;
import com.pcloud.sdk.ApiError;
import com.pcloud.sdk.Authenticators;
import com.pcloud.sdk.DataSource;
import com.pcloud.sdk.PCloudSdk;
import com.pcloud.sdk.ProgressListener;
import com.pcloud.sdk.RemoteFile;
import com.pcloud.sdk.RemoteFolder;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class PCloudUtils {
    private static final String O_AUTH_KEY = System.getenv("O_AUTH_KEY");
    private static final String API_HOST = System.getenv("API_HOST");

    private static final ApiClient API_CLIENT = PCloudSdk.newClientBuilder()
            .authenticator(Authenticators.newOAuthAuthenticator(O_AUTH_KEY))
            .apiHost(API_HOST)
            .create();

    public static void uploadFile(String filePath) throws IOException, ApiError {
        System.out.println("Uploading file: " + filePath);
        File localFile = new File(filePath);
        ProgressListener listener = (done, total) ->
                System.out.format("\rUploading... %.1f\n", ((double) done / (double) total) * 100d);
        RemoteFile uploadedFile = API_CLIENT.createFile(
                        RemoteFolder.ROOT_FOLDER_ID,
                        localFile.getName(),
                        DataSource.create(localFile),
                        new Date(localFile.lastModified()),
                        listener)
                .execute();
        System.out.println("File upload successful");
    }


}
