package com.ornellaia.stargazers;

import com.ornellaia.stargazers.util.HandleJSON;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ornella on 05/03/17.
 */


public class HandleJSONTest {

    private HandleJSON handleJSON;
    private String url;

    @Before
    public void before() throws IOException, JSONException {
        url = "https://api.github.com/repos/vmg/redcarpet/stargazers";
        handleJSON = new HandleJSON(url);
    }

    @Test
    public void HandleJSON_GetConnectionResponseCodeShouldBe200() throws IOException {
        URL url_connection = new URL(url);
        HttpURLConnection httpURLConnection = handleJSON.getConnection(url_connection);
        assertEquals(httpURLConnection.getResponseCode(), 200);
    }

    @Test
    public void HandleJSON_ConvertStreamToStringShouldReturnAStringThanContainsTheSubstringLogin() throws IOException, JSONException {
        URL url_connection = new URL(url);
        HttpURLConnection httpURLConnection = handleJSON.getConnection(url_connection);
        InputStream stream = httpURLConnection.getInputStream();
        String data = handleJSON.convertStreamToString(stream);
        assertTrue(data.contains("login"));
    }


}
