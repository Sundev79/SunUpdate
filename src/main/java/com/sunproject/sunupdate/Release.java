package com.sunproject.sunupdate;

import org.json.simple.JSONObject;

/**
 * <h3>Class represent a {@link GithubAPI} release.</h3>
 * @author sundev79
 * @since 1.0.0
 * @version 1.0.0
 */

public class Release {

	private final String latestBinName, latestBinUrl;
	private final long contentLength;

	/**
	 * Release constructor.
	 * @param assetArray a json array of assets.
	 */
	public Release(JSONObject assetArray) {
		this.latestBinName = (String) assetArray.get("name");
		this.latestBinUrl = (String) assetArray.get("browser_download_url");
		this.contentLength =  (Long) assetArray.get("size");
	}

	public final String getBinName() {
		return latestBinName;
	}

	public final String getBinUrl() {
		return latestBinUrl;
	}
	
	public final long getFileSize() {
		return this.contentLength;
	}
}
