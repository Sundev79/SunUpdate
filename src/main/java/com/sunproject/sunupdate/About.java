package com.sunproject.sunupdate;

public class About {

        private String version, name, repoUrl;

        public About(String ver, String depName, String repoUrl) {
            this.version = ver;
            this.name = depName;
            this.repoUrl = repoUrl;
        }

        public String getVersion() {
            return version;
        }

        public String getRepoUrl() {
            return repoUrl;
        }

        public String getName() {
            return name;
        }
}
